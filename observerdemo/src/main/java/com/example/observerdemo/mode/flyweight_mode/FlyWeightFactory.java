package com.example.observerdemo.mode.flyweight_mode;

import java.util.HashMap;

public class FlyWeightFactory {
    private HashMap<Character, Flyweight> file = new HashMap<>();

    public Flyweight factory(Character character) {
        Flyweight flyweight = file.get(character);
        if (flyweight == null) {
            flyweight = new ConcreatFlyweight(character);
            file.put(character, flyweight);

        }
        return flyweight;
    }
}
