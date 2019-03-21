package com.example.observerdemo.observer;

import java.util.HashSet;
import java.util.Set;

public class EventType {

    private static volatile EventType mEventType;
    private final static Set<String> eventsTypes = new HashSet<String>();
    public final static String UPDATE_MAIN = "com.updateMain";
    public final static String UPDATE_Text = "com.updateText";
    public final static String UPDATE_DATA = "com.updateData";
    private static EventType eventType;

    private EventType() {
        eventsTypes.add(UPDATE_MAIN);
        eventsTypes.add(UPDATE_Text);
        eventsTypes.add(UPDATE_DATA);
    }

    public static EventType getInstance() {
        if (eventType == null) {
            eventType = new EventType();
        }
        return eventType;
    }

    public boolean contain(String eventType) {
        return eventsTypes.contains(eventType);
    }
}
