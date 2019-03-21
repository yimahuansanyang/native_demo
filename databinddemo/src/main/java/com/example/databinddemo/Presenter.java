package com.example.databinddemo;

import android.util.Log;

public class Presenter {
    public void onSaveClick(PersonInfo personInfo) {
        Log.d("TAG", personInfo.getName() + "_" + personInfo.getSex());
    }
}
