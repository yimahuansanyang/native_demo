package com.example.observerdemo.mode.proxy_mode;

public class ProxyObject extends AbstractObject {
    private RealObject realObject = new RealObject();

    @Override
    public void opreation() {
        System.out.println("before");
        realObject.opreation();
        //调用目标对象之后可以做相关操作
        System.out.println("after");

    }
}
