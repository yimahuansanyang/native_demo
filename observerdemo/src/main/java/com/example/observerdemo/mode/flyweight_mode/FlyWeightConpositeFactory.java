package com.example.observerdemo.mode.flyweight_mode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlyWeightConpositeFactory {
    private Map<Character, Flyweight> files = new HashMap<>();

    public Flyweight factory(List<Character> characterList) {
        ConcreteCompositeFlyweight concreteCompositeFlyweight = new ConcreteCompositeFlyweight();
        for (Character statue : characterList) {
            concreteCompositeFlyweight.add(statue, this.factory(statue));

        }
        return concreteCompositeFlyweight;
    }

    public Flyweight factory(Character character) {
        Flyweight flyweight = files.get(character);
        if (flyweight == null) {
            flyweight = new ConcreatFlyweight(character);
            files.put(character, flyweight);
        }
        return flyweight;
    }
}
