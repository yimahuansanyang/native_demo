package com.example.databinddemo;

import android.databinding.BaseObservable;
import android.util.Log;
import android.view.View;

public class PersonInfo extends BaseObservable {
    private String name;
    private String sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    private String age;

    public void click(View view) {
        Log.d("TAG",sex+"_"+name);
    }
}
