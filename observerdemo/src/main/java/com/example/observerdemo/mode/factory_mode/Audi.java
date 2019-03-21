package com.example.observerdemo.mode.factory_mode;

public class Audi implements Brand_Car {
    private final String c;

    public Audi(String color) {
        this.c = color;

    }

    @Override
    public void spray_paint() {
        System.out.print("Audi车的颜色是：" + c);

    }
}
