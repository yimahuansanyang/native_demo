package com.example.observerdemo.mode.proxy_mode;

public class SuperStar implements Star {
    private String singer = "唱歌";

    @Override
    public void singsong() {
        System.out.println(singer);
    }
}
