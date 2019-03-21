package com.example.retrolfitdemo.face;

import com.example.retrolfitdemo.bean.DateBean;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MyInterface {
    @GET("ymdx.json")
    Call<DateBean> gerRequest();
}
