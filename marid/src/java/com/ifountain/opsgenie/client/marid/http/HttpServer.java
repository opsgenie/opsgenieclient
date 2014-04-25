package com.ifountain.opsgenie.client.marid.http;

import com.ifountain.client.util.JsonUtils;
import org.apache.log4j.Logger;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.group.ChannelGroup;
import org.jboss.netty.channel.group.DefaultChannelGroup;
import org.jboss.netty.channel.socket.oio.OioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.http.*;
import org.jboss.netty.handler.codec.http.HttpHeaders;
import org.jboss.netty.handler.codec.http.HttpRequest;
import org.jboss.netty.handler.codec.http.HttpResponse;
import org.jboss.netty.handler.codec.http.HttpVersion;
import org.jboss.netty.handler.ssl.SslHandler;
import org.jboss.netty.handler.timeout.*;
import org.jboss.netty.util.CharsetUtil;
import org.jboss.netty.util.HashedWheelTimer;
import org.jboss.netty.util.Timer;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.security.KeyStore;
import java.security.Security;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Sezgin Kucukkaraaslan
 * Date: 1/16/12
 * Time: 9:43 AM
 */
public class HttpServer {
    private String host;
    private int port;
    private Channel channel;
    private ChannelFactory factory;
    private ChannelGroup allChannels;
    private int maxContentLength = 100 * 1024 * 1024;
    private int threadPoolSize = 100;
    private Logger logger = Logger.getLogger(HttpServer.class);
    private Logger requestLogger = Logger.getLogger(HttpServer.class.getName() + ".request");
    private Logger requestWithExceptionLogger = Logger.getLogger(HttpServer.class.getName() + ".request.exception");
    private long timeout;
    private boolean sslEnabled = false;
    private String keystorePath;
    private String keyPassword;
    private SimpleDateFormat dateFormatter;
    private int idleConnectionTimeout = 60;

    public HttpServer(String host, int port) {
        this.host = host;
        this.port = port;
        this.sslEnabled = false;
        createDateFormatter();
    }

    public HttpServer(String host, int port, String keystorePath, String keyPassword) {
        this.host = host;
        this.port = port;
        this.keyPassword = keyPassword;
        this.keystorePath = keystorePath;
        this.sslEnabled = true;
        createDateFormatter();
    }

    public void open() {
        logger.debug(getLogPrefix() + "Starting server at host [" + host + "] on port [" + port + "] with pool size " + threadPoolSize);
        allChannels = new DefaultChannelGroup("HttpServerAdapter");
        factory = new OioServerSocketChannelFactory(new ThreadPoolExecutor(0, threadPoolSize,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>()), new ThreadPoolExecutor(0, threadPoolSize,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>()));
        ServerBootstrap bootstrap = new ServerBootstrap(factory);
        bootstrap.setOption("child.tcpNoDelay", true);
        bootstrap.setOption("child.keepAlive", true);
        bootstrap.setPipelineFactory(new HttpListenerPipelineFactory(new HashedWheelTimer()));
        channel = bootstrap.bind(new InetSocketAddress(host, port));
        allChannels.add(channel);
        logger.info(getLogPrefix() + "Successfully started server.");
    }

    public int getMaxContentLength() {
        return maxContentLength;
    }

    public void setMaxContentLength(int maxContentLength) {
        this.maxContentLength = maxContentLength;
    }

    public void close() {
        if (channel != null) {
            try {
                allChannels.close().awaitUninterruptibly(timeout);
            } catch (Exception e) {
                logger.warn(getLogPrefix() + "Could not close channel.", e);
            }
        }
        if (factory != null) {
            factory.releaseExternalResources();
        }
        logger.info(getLogPrefix() + "Closed.");
    }

    private String getLogPrefix() {
        return "[HttpServerAdapter]: ";
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getThreadPoolSize() {
        return threadPoolSize;
    }

    public void setThreadPoolSize(int threadPoolSize) {
        this.threadPoolSize = threadPoolSize;
    }

    public int getIdleConnectionTimeout() {
        return idleConnectionTimeout;
    }

    public void setIdleConnectionTimeout(int idleConnectionTimeout) {
        this.idleConnectionTimeout = idleConnectionTimeout;
    }

    protected Channel getChannel() {
        return channel;
    }

    private void createDateFormatter() {
        dateFormatter = new SimpleDateFormat("E, dd-MMM-y HH:mm:ss z", Locale.ENGLISH);
        dateFormatter.setTimeZone(TimeZone.getTimeZone("GMT"));
    }

    private void processMessage(ChannelHandlerContext ctx, MessageEvent e) {
        HTTPRequest req = createRequestObject(e);
        HTTPResponse httpResponse = HttpController.getInstance().dispatchRequest(req);
        logRequest(req);
        writeResponse(e, httpResponse);
    }


    private HTTPRequest createRequestObject(MessageEvent event) {
        HttpRequest request = (HttpRequest) event.getMessage();
        QueryStringDecoder decoder = new QueryStringDecoder(request.getUri());
        Map<String, String> parameters = createParametersFromDecoder(decoder);
        String url = decoder.getPath();
        String contentType = request.getHeader(HttpHeaders.Names.CONTENT_TYPE);
        ChannelBuffer contentBuffer = request.getContent();
        if (request.getMethod().equals(HttpMethod.POST) && (contentType != null && contentType.contains("x-www-form-urlencoded"))) {
            decoder = new QueryStringDecoder("?" + contentBuffer.toString(CharsetUtil.UTF_8));
            parameters.putAll(createParametersFromDecoder(decoder));

        }
        SocketAddress remoteAddress = event.getChannel().getRemoteAddress();
        HTTPRequest req = new HTTPRequest(remoteAddress, contentBuffer);
        req.setParameters(parameters);
        req.setUrl(url);

        Map<String, String> headers = new HashMap<String, String>();
        List<Map.Entry<String, String>> headerEntries = request.getHeaders();
        for (Map.Entry<String, String> entry : headerEntries) {
            headers.put(entry.getKey(), entry.getValue());
        }
        req.setHeaders(headers);
        req.setMethod(request.getMethod().getName());
        return req;
    }

    private void logRequestWithException(HTTPRequest request, HTTPResponse response) {
        if (requestWithExceptionLogger.isInfoEnabled()) {
            Map<String, Object> jsonParams = createRequestLogParams(request);
            jsonParams.put("response", new String(response.getContent()));
            jsonParams.put("status", response.getStatus().toString());
            try {
                requestWithExceptionLogger.info(JsonUtils.toJson(jsonParams));
            } catch (IOException e) {
                requestWithExceptionLogger.info("Could not logged request [" + request.getUrl() + "]. Reason:" + e.toString());
            }
        }
    }

    private Map<String, Object> createRequestLogParams(HTTPRequest request) {
        Map<String, Object> jsonParams = new HashMap<String, Object>();
        jsonParams.put("url", request.getUrl());
        jsonParams.put("method", request.getMethod());
        jsonParams.put("header", new HashMap<Object, Object>(request.getHeaders()));
        jsonParams.put("parameters", new HashMap<Object, Object>(request.getParameters()));
        jsonParams.put("logTime", System.currentTimeMillis());
        jsonParams.put("clientIp", request.getClientIpAddress());
        Map<String, Object> contentParameters = request.getContentParameters();
        if (contentParameters != null && contentParameters.size() > 0) {
            jsonParams.put("content", contentParameters);
        }
        return jsonParams;
    }

    private void logRequest(HTTPRequest request) {
        if (requestLogger.isDebugEnabled()) {
            Map<String, Object> jsonParams = createRequestLogParams(request);
            try {
                requestLogger.debug(JsonUtils.toJson(jsonParams));
            } catch (IOException e) {
                requestLogger.debug("Could not logged request [" + request.getUrl() + "]. Reason:" + e.toString());
            }
        }
    }

    private Map<String, String> createParametersFromDecoder(QueryStringDecoder decoder) {
        Map<String, List<String>> params = decoder.getParameters();
        Map<String, String> parameters = new HashMap<String, String>();
        for (Map.Entry<String, List<String>> entry : params.entrySet()) {
            String key = entry.getKey();
            List<String> valueList = entry.getValue();
            if (!valueList.isEmpty()) {
                parameters.put(key, valueList.get(0));
            }
        }
        return parameters;
    }

    protected void writeResponse(MessageEvent e, HTTPResponse HTTPResponse) {
        HttpRequest request = (HttpRequest) e.getMessage();
        boolean isKeepAlive = HttpHeaders.isKeepAlive(request);
        HttpResponse response = new DefaultHttpResponse(HttpVersion.HTTP_1_1, HTTPResponse.getStatus());
        response.setContent(ChannelBuffers.wrappedBuffer(HTTPResponse.getContent(), 0, HTTPResponse.getContentLength()));
        response.setHeader(HttpHeaders.Names.CONTENT_TYPE, HTTPResponse.getContentType());
        response.setHeader(HttpHeaders.Names.DATE, dateFormatter.format(new Date()));
        if (isKeepAlive) {
            // Add 'Content-Length' header only for a keep-alive connection.
            response.setHeader(HttpHeaders.Names.CONTENT_LENGTH, response.getContent().readableBytes());
        }
        // Encode the cookie.
        String cookieString = request.getHeader(HttpHeaders.Names.COOKIE);
        if (cookieString != null) {
            CookieDecoder cookieDecoder = new CookieDecoder();
            Set<Cookie> cookies = cookieDecoder.decode(cookieString);
            if (!cookies.isEmpty()) {
                // Reset the cookies if necessary.
                for (Cookie cookie : cookies) {
                    CookieEncoder cookieEncoder = new CookieEncoder(true);
                    cookieEncoder.addCookie(cookie);
                    response.addHeader(HttpHeaders.Names.SET_COOKIE, cookieEncoder.encode());
                }
            }
        }
        ChannelFuture channelFuture = e.getChannel().write(response);

        if (!isKeepAlive) {
            channelFuture.addListener(new ChannelFutureListener() {
                public void operationComplete(final ChannelFuture cf) throws Exception {
                    if (cf.getChannel().isConnected()) {
                        cf.getChannel().close();
                    }
                }
            });
        }
    }

    private class HttpListenerPipelineFactory implements ChannelPipelineFactory {
        private Timer timer;
        private IdleStateHandler idleStateHandler;

        private HttpListenerPipelineFactory(Timer timer) {
            this.timer = timer;
            this.idleStateHandler = new IdleStateHandler(timer, idleConnectionTimeout, 30, 0);
        }

        @Override
        public ChannelPipeline getPipeline() throws Exception {
            ChannelPipeline pipeline = Channels.pipeline();
            if (sslEnabled) {
                pipeline.addLast("ssl", new SslHandler(createSSLEngine()));
            }
            pipeline.addLast("idleStateHandler", idleStateHandler);
            pipeline.addLast("decoder", new HttpRequestDecoder());
            pipeline.addLast("aggregator", new HttpChunkAggregator(maxContentLength));
            pipeline.addLast("encoder", new HttpResponseEncoder());
            pipeline.addLast("deflater", new HttpContentCompressor());
            pipeline.addLast("handler", new HttpRequestHandler());
            return pipeline;
        }

        private SSLEngine createSSLEngine() throws Exception {
            FileInputStream inputStream = null;
            try {
                KeyStore ks = KeyStore.getInstance("JKS");
                inputStream = new FileInputStream(keystorePath);
                char[] password = keyPassword.toCharArray();
                ks.load(inputStream, password);
                String algorithm = Security.getProperty("ssl.KeyManagerFactory.algorithm");
                if (algorithm == null) {
                    algorithm = "SunX509";
                }
                KeyManagerFactory kmf = KeyManagerFactory.getInstance(algorithm);
                kmf.init(ks, password);

                SSLContext sslContext = SSLContext.getInstance("TLS");
                sslContext.init(kmf.getKeyManagers(), null, null);

                SSLEngine engine = sslContext.createSSLEngine();
                engine.setUseClientMode(false);
                return engine;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
            }
        }


    }

    private class HttpRequestHandler extends IdleStateAwareChannelHandler {
        @Override
        public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
            processMessage(ctx, e);
        }

        @Override
        public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
            if (!sslEnabled) {
                super.channelConnected(ctx, e);
            } else {
                final SslHandler sslHandler = ctx.getPipeline().get(SslHandler.class);
                // Get notified when SSL handshake is done.
                ChannelFuture handshakeFuture = sslHandler.handshake();
                handshakeFuture.addListener(new ChannelFutureListener() {
                    @Override
                    public void operationComplete(ChannelFuture channelFuture) throws Exception {
                        if (!channelFuture.isSuccess()) {
                            channelFuture.getChannel().close();
                        }
                    }
                });
            }
        }

        @Override
        public void channelOpen(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
            super.channelOpen(ctx, e);
            allChannels.add(e.getChannel());
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
            if (e.getCause() instanceof ReadTimeoutException) {
                logger.debug("Connection timed out from address " + ctx.getChannel().getRemoteAddress());
            } else {
                logger.warn("Http connection exception", e.getCause());
            }
            e.getChannel().close();
        }

        @Override
        public void channelIdle(ChannelHandlerContext ctx, IdleStateEvent e) throws Exception {
            if (e.getState() == IdleState.READER_IDLE) {
                e.getChannel().close();
            }
        }

    }

}
