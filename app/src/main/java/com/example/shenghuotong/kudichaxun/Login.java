package com.example.shenghuotong.kudichaxun;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface Login {
    @Headers("Authorization: APPCODE 9dfff53b9a894208b275aaf7dbbf8b31")
    @GET("kdwlcx")
    public Call<ResultBody> getCall(@Query("no") String no);
}
