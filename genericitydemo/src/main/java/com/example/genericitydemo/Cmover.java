package com.example.genericitydemo;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Cmover {
    public class Dog {
    }

    public class Cat {
    }

    public static void main(String[] args) {
        Cmover cmover = new Cmover();
        Map<String, Cat> map = new HashMap<>();
        try {
            Method method = map.getClass().getDeclaredMethod("put", Object.class, Object.class);
            method.invoke(map,"dog",cmover.new Dog());
            System.out.println(map);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
