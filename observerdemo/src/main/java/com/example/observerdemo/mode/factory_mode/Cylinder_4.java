package com.example.observerdemo.mode.factory_mode;

public class Cylinder_4 implements Cylinder {
    private final int count;

    public Cylinder_4(int count) {
        this.count = count;
    }

    @Override
    public void calculate() {
        System.out.println("Cylinder_4 汽缸的数量：" + count);
    }
}
