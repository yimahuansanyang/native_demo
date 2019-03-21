package com.example.observerdemo.observer;

public interface EventObserverInterface {
    /**
     * 根据事件进行数据或者UI的更新
     *
     * @param eventType
     */
    public void dispatchChange(String eventType);
}
