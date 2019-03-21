package com.example.observerdemo.mode.factory_mode;

public class CarEngineer {
    private Brand_Car brand;
    private Cylinder cylinder;

    public void makeCar(AbstractFactory abstractFactory) {
        this.brand = abstractFactory.creat_brand_car();
        this.cylinder = abstractFactory.creat_cylinder();
        this.brand.spray_paint();
        this.cylinder.calculate();
    }
}
