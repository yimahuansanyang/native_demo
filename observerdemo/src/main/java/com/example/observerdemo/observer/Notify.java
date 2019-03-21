package com.example.observerdemo.observer;

public class Notify {
    private static Notify notify;

    private Notify() {
    }

    public static Notify getInstance() {
        if (notify == null) {
            notify = new Notify();
        }
        return notify;
    }

    public void notifyInfo(String type) {
        EventSubject eventSubject = EventSubject.getInstance();
        EventType eventType = EventType.getInstance();
        if (eventType.contain(type)) {
            eventSubject.notifyObserver(type);
        }
    }
}
