package com.example.zxdemo;

public class People {
    private String name;
    private int id;
    private String phoneNumber;
    private int age;
    private String introduce;

    public People(String name, int id, String phoneNumber, int age,
                  String introduce) {
        super();
        this.name = name;
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.introduce = introduce;
    }
    // ....... 这里省略getter和setter方法

    @Override
    public boolean equals(Object arg0) {
        // TODO Auto-generated method stub
        People p = (People) arg0;
        return name.equals(p.name) && phoneNumber.equals(p.phoneNumber);
    }

    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
        String str = name + phoneNumber;
        return str.hashCode();
    }

}
