package com.example.observerdemo.mode.builder_mode;

public class Dirctor {
    private final Builder builder;

    public Dirctor(Builder builder) {
        this.builder = builder;
    }
    public void construt(){
        builder.builder1();
        builder.builder2();
    }
}
