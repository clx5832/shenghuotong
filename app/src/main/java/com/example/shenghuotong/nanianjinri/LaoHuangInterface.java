package com.example.shenghuotong.nanianjinri;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LaoHuangInterface {

    //老黄历的接口，提供老黄历查询，黄历每日吉凶宜禁忌查询
    //请求参数：http://v.juhe.cn/laohuangli/d
    //需要传递date和key两个参数，返回结果用laohuangliShuju接收
    @GET("laohuangli/d")//接口地址
    public Call<LaohuangliShuju> getCall(@Query("date") String date, @Query("key") String key);


    //历史今天的接口，回顾历史的长河
    @GET("todayOnhistory/queryEvent.php")//接口地址
    public Call<LiShiJinTian>getTOH(@Query("date") String date, @Query("key") String key);


    @GET("todayOnhistory/queryDetail.php")
    public Call<LishiJinDetail> getDetail(@Query("e_id") String date, @Query("key") String key);
}
