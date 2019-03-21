package com.example.observerdemo.mode.flyweight_mode;

public class ConcreatFlyweight implements Flyweight {
    private final Character c;

    public ConcreatFlyweight(Character character) {
        this.c = character;
    }

    @Override
    public void opreation(String statue) {
        System.out.println("内蕴状态：" + c);
        System.out.println("外蕴状态：" + statue);

    }
}
