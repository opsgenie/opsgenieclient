package com.ifountain.opsgenie.client.test.util.logging;

import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.LogEvent;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Sezgin Kucukkaraaslan
 * @version 8/7/12 2:39 PM
 */
public class MockAppender extends AbstractAppender {
    private Map<String, List<String>> messages = new HashMap<String, List<String>>();
    private List<String> allMessages = new LinkedList<String>();

    @Override
    public void append(LogEvent logEvent) {
        String level = logEvent.getLevel().toString();
        List<String> levelMessages = messages.get(level);
        if (levelMessages == null) {
            levelMessages = new LinkedList<String>();
            messages.put(level, levelMessages);
        }
        levelMessages.add(logEvent.getRenderedMessage());
        allMessages.add(logEvent.getRenderedMessage());
    }

    @Override
    public boolean requiresLayout() {
        return false;
    }

    @Override
    public void close() {
        messages.clear();
        allMessages.clear();
    }

    public void clear() {
        messages.clear();
        allMessages.clear();
    }

    public List<String> getMessages(String level) {
        return messages.get(level);
    }

    public List<String> getAllMessages() {
        return allMessages;
    }
}
