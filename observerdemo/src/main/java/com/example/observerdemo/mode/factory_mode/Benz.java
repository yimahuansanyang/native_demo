package com.example.observerdemo.mode.factory_mode;

public class Benz implements Brand_Car {
    private final String c;

    public Benz(String color) {
        this.c = color;

    }

    @Override
    public void spray_paint() {
        System.out.print("Benz车的颜色是：" + c);

    }
}
