package com.ifountain.opsgenie.client.http;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.group.ChannelGroup;
import org.jboss.netty.channel.group.DefaultChannelGroup;
import org.jboss.netty.channel.socket.oio.OioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.http.*;
import org.jboss.netty.handler.timeout.IdleState;
import org.jboss.netty.handler.timeout.IdleStateAwareChannelHandler;
import org.jboss.netty.handler.timeout.IdleStateEvent;
import org.jboss.netty.handler.timeout.IdleStateHandler;
import org.jboss.netty.util.CharsetUtil;
import org.jboss.netty.util.HashedWheelTimer;
import org.jboss.netty.util.Timer

import java.util.concurrent.Executors;

/**
 * Created by Sezgin Kucukkaraaslan
 * Date: 5/30/12
 * Time: 10:32 AM
 */
public class HttpTestServer {
    private String host;
    private int port;
    private Channel channel;
    private ChannelFactory factory;
    private ChannelGroup allChannels;
    private int maxContentLength = 100 * 1024 * 1024;
    private HttpTestResponse responseToReturn = new HttpTestResponse(new byte[0]);
    private List<HttpTestRequestListener> requestListeners = new ArrayList<HttpTestRequestListener>();

    public HttpTestServer(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void open() {
        allChannels = new DefaultChannelGroup("HttpServerAdapter");
        factory = new OioServerSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool());
        ServerBootstrap bootstrap = new ServerBootstrap(factory);
        bootstrap.setOption("child.tcpNoDelay", true);
        bootstrap.setOption("child.keepAlive", true);
        bootstrap.setPipelineFactory(new HttpListenerPipelineFactory(new HashedWheelTimer()));
        channel = bootstrap.bind(new InetSocketAddress(host, port));
        allChannels.add(channel);
    }

    public void close() {
        if (channel != null) {
            try {
                allChannels.close().awaitUninterruptibly(60 * 1000L);
            } catch (Exception e) {
            }
        }
        if (factory != null) {
            factory.releaseExternalResources();
        }
    }

    private void processMessage(ChannelHandlerContext ctx, MessageEvent e) {
        HttpTestRequest req = createRequestObject(e);
        for (HttpTestRequestListener listener : requestListeners) {
            listener.requestRecieved(req);
        }
        writeResponse(e);
    }

    protected void writeResponse(MessageEvent e) {
        HttpRequest request = (HttpRequest) e.getMessage();
        boolean isKeepAlive = HttpHeaders.isKeepAlive(request);
        HttpResponse response = new DefaultHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.valueOf(responseToReturn.getStatus()));
        byte[] content = responseToReturn.getContent();
        response.setContent(ChannelBuffers.wrappedBuffer(content, 0, content.length));
        response.setHeader(HttpHeaders.Names.CONTENT_TYPE, responseToReturn.getContentType());
        if (isKeepAlive) {
            // Add 'Content-Length' header only for a keep-alive connection.
            response.setHeader(HttpHeaders.Names.CONTENT_LENGTH, response.getContent().readableBytes());
        }
        if(responseToReturn instanceof HttpTimeoutResponse){
            HttpTimeoutResponse timeoutResponse = (HttpTimeoutResponse) responseToReturn;
            try {
                Thread.sleep(timeoutResponse.getTimeout());
            } catch (InterruptedException ignored) {
            }
        }
        ChannelFuture channelFuture = e.getChannel().write(response);
        channelFuture.addListener(ChannelFutureListener.CLOSE);
    }


    private HttpTestRequest createRequestObject(MessageEvent event) {
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
        HttpTestRequest req = new HttpTestRequest(contentBuffer);
        req.setParameters(parameters);
        req.setUrl(url);
        req.setContentType(contentType);

        Map headers = new HashMap();
        List<Map.Entry<String, String>> headerEntries = request.getHeaders();
        for (Map.Entry<String, String> entry : headerEntries) {
            headers.put(entry.getKey(), entry.getValue());
        }
        req.setHeaders(headers);
        req.setMethod(request.getMethod().getName());
        return req;
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

    public void setResponseToReturn(HttpTestResponse responseToReturn) {
        this.responseToReturn = responseToReturn;
    }

    public void setResponseToReturn(String responseToReturn) {
        this.responseToReturn = new HttpTestResponse(responseToReturn);
    }

    public void addRequestListener(HttpTestRequestListener listener) {
        requestListeners.add(listener);
    }
    public void removeRequestListener(HttpTestRequestListener listener) {
        requestListeners.remove(listener);
    }

    private class HttpListenerPipelineFactory implements ChannelPipelineFactory {
        private Timer timer;
        private IdleStateHandler idleStateHandler;

        private HttpListenerPipelineFactory(Timer timer) {
            this.timer = timer;
            this.idleStateHandler = new IdleStateHandler(timer, 60, 30, 0);
        }

        @Override
        public ChannelPipeline getPipeline() throws Exception {
            ChannelPipeline pipeline = Channels.pipeline();
            pipeline.addLast("idleStateHandler", idleStateHandler);
            pipeline.addLast("decoder", new HttpRequestDecoder());
            pipeline.addLast("aggregator", new HttpChunkAggregator(maxContentLength));
            pipeline.addLast("encoder", new HttpResponseEncoder());
            pipeline.addLast("deflater", new HttpContentCompressor());
            pipeline.addLast("handler", new HttpRequestHandler());
            return pipeline;
        }
    }

    private class HttpRequestHandler extends IdleStateAwareChannelHandler {
        @Override
        public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
            processMessage(ctx, e);
        }

        @Override
        public void channelOpen(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
            super.channelOpen(ctx, e);
            allChannels.add(e.getChannel());
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
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
