package com.example.observerdemo.observer;

import java.util.ArrayList;
import java.util.List;
/*具体化的被观察者*/

public class EventSubject implements EventSubjectInterface {
    private List<EventObserver> list = new ArrayList<>();
    private static volatile EventSubject eventSubject;

    public static EventSubject getInstance() {
        if (eventSubject == null) {
            eventSubject = new EventSubject();
        }
        return eventSubject;
    }

    private EventSubject() {
    }

    /*注冊觀察者*/
    @Override
    public void registerObserver(EventObserver observer) {
        synchronized (list) {

            if (observer != null) {
                if (list.contains(observer)) {
                    return;
                }
                list.add(observer);
            }
        }

    }

    /*移除观察者*/
    @Override
    public void removeObserver(EventObserver observer) {
        synchronized (list) {
            int i = list.indexOf(observer);
            if (i >= 0) {
                list.remove(observer);
            }
        }

    }

    @Override
    public void notifyObserver(String eventType) {
        if (list != null && list.size() > 0 && eventType != null) {
            for (EventObserver observer : list) {
                observer.dispatchChange(eventType);

            }
        }

    }
}
