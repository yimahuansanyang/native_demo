package com.example.observerdemo.mode.flyweight_mode;

import java.util.HashMap;

public class ConcreteCompositeFlyweight implements Flyweight {
    private HashMap<Character, Flyweight> map = new HashMap<>();

    public void add(Character character, Flyweight fly) {
        map.put(character, fly);
    }

    @Override
    public void opreation(String statue) {
        Flyweight flyweight = null;
        for (Object o : map.keySet()) {
             flyweight = map.get(o);
            flyweight.opreation(statue);
        }

    }
}
