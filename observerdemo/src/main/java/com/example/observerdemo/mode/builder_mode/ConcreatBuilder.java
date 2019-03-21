package com.example.observerdemo.mode.builder_mode;

public class ConcreatBuilder implements Builder {
    private final Product product;

    public ConcreatBuilder() {
        this.product = new Product();
    }

    @Override
    public void builder1() {
        product.setPart1("编号：9527");

    }

    @Override
    public void builder2() {
        product.setPart2("唐伯虎啊");
    }

    @Override
    public Product retriviceResult() {
        return product;
    }
}
