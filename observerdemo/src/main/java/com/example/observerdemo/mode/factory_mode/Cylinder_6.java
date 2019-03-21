package com.example.observerdemo.mode.factory_mode;

public class Cylinder_6 implements Cylinder {
    private final int count;

    public Cylinder_6(int count) {
        this.count = count;
    }

    @Override
    public void calculate() {
        System.out.println("Cylinder_6 汽缸的数量：" + count);
    }
}
