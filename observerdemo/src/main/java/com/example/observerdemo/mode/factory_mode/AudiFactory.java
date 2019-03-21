package com.example.observerdemo.mode.factory_mode;

public class AudiFactory extends AbstractFactory {
    @Override
    public Brand_Car creat_brand_car() {
        return new Audi("black");
    }

    @Override
    public Cylinder creat_cylinder() {
        return new Cylinder_6(6);
    }
}
