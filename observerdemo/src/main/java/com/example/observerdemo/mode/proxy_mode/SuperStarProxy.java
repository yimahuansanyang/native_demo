package com.example.observerdemo.mode.proxy_mode;

public class SuperStarProxy implements Star {
    private final Star star;

    public SuperStarProxy(Star star) {
        this.star = star;
    }

    @Override
    public void singsong() {
        System.out.println("唱歌前");

        star.singsong();
        System.out.println("唱歌后");

    }
}
