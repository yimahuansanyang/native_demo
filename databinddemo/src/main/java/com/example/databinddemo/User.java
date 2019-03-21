package com.example.databinddemo;

import android.databinding.BaseObservable;

public class User extends BaseObservable {
    private String id;
    private String name;
    private String blog;


    public void setId(String id) {
        this.id = id;
        //notifyPropertyChanged(BR.id);
    }

    //@Bindable
    public String getId() {
        return this.id;
    }


    public void setName(String name) {
        this.name = name;
        //notifyPropertyChanged(BR.name);
    }

    //@Bindable
    public String getName() {
        return this.name;
    }

    public void setBlog(String blog) {
        this.blog = blog;
        //notifyPropertyChanged(BR.blog);
    }

    //@Bindable
    public String getBlog() {
        return this.blog;
    }
}
