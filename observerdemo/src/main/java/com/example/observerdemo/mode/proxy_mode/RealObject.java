package com.example.observerdemo.mode.proxy_mode;

public class RealObject extends AbstractObject {
    @Override
    public void opreation() {
        System.out.println("一些很严肃的操作");
    }
}
