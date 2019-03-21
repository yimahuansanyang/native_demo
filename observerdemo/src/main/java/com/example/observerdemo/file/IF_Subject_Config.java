package com.example.observerdemo.file;

public interface IF_Subject_Config {
    public void register_observer(IF_Observer_Config observer);
    public void remove_observer(IF_Observer_Config observer);
    public void notify_observer();
}
