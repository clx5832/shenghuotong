package com.example.shenghuotong.api;

import com.example.shenghuotong.WeatherData.WeatherData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Weather {

    @GET("simpleWeather/query")
    public Call<WeatherData> getCall(@Query("city")String city, @Query("key")String key);
}
