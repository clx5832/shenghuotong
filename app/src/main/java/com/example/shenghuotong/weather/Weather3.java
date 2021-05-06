package com.example.shenghuotong.weather;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shenghuotong.Adapter.WeatherAdapter;
import com.example.shenghuotong.R;
import com.example.shenghuotong.WeatherData.WeatherData;
import com.example.shenghuotong.api.Weather;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Weather3 extends AppCompatActivity {


    //    @BindView(R.id.date2)
//    TextView date2;
//    @BindView(R.id.temperature)
//    TextView temperature;
//    @BindView(R.id.weather)
//    TextView weather;
//    @BindView(R.id.direct)
//    TextView direct;
//    @BindView(R.id.day)
//    TextView day;
//    @BindView(R.id.night)
//    TextView night;
    @BindView(R.id.items)
    RecyclerView items;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather3);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String city = intent.getStringExtra("city");


        initWeather(city);
    }

    private void initWeather(String city) {
        //初始化retrofit实例
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://apis.juhe.cn/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Weather api = retrofit.create(Weather.class);
        String key = "d3482553d402a7ec9241372e09ac3a9c";//请求的key
        final Call<WeatherData> res = api.getCall(city, key);

        res.enqueue(new Callback<WeatherData>() {
            @Override
            public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {

                //成功调用方法
                WeatherData body = response.body();//获取相应的结果

                int error_code = body.getError_code();
                switch (error_code) {
                    case 0:
                        List<WeatherData.ResultBean.FutureBean> future = body.getResult().getFuture();
                        WeatherAdapter weatherAdapter = new WeatherAdapter(Weather3.this, future);
                        LinearLayoutManager manager = new LinearLayoutManager(Weather3.this);
                        manager.setOrientation(LinearLayoutManager.VERTICAL);//垂直
                        items.setLayoutManager(manager);
                        items.setAdapter(weatherAdapter);
    //                        for (WeatherData.ResultBean.FutureBean futureBean : future) {


//                            WeatherAdapter weatherAdapter = new WeatherAdapter(Weather3.this, future);
//                            LinearLayoutManager manager = new LinearLayoutManager(Weather3.this);
//                            manager.setOrientation(LinearLayoutManager.VERTICAL);//垂直
//                            items.setLayoutManager(manager);
//                            items.setAdapter(weatherAdapter);
//                            String date1 = futureBean.getDate();
//                            String temperature1 = futureBean.getTemperature();
//                            String direct1 = futureBean.getDirect();
//                            String weather1 = futureBean.getWeather();
//
//                            weather.setText(weather1 + "\n");
//                            date2.setText(date1 + "\n");
//                            temperature.setText(temperature1 + "\n");
//                            direct.setText(direct1 + "\n");
//
//                            WeatherData.ResultBean.FutureBean.WidBean wid = futureBean.getWid();
//                            day.setText(wid.getDay() + "\n");
//                            night.setText(wid.getNight() + "\n");
//                        }


//                        List<WeatherData.ResultBean.FutureBean.WidBean> future1=body.getResult().getFuture().
//                        String day1 = widBean.getDay();
//                        String night1 = widBean.getNight();


//                        day.setText(day1);
//                        night.setText(night1);
                        break;

                    case 10001:
                        Toast.makeText(Weather3.this, "\t错误的请求KEY", Toast.LENGTH_SHORT).show();
                        break;
                    case 10002:
                        Toast.makeText(Weather3.this, "\t\t该KEY无请求权限", Toast.LENGTH_SHORT).show();
                        break;
                    case 10003:
                        Toast.makeText(Weather3.this, "\t\tKEY过期", Toast.LENGTH_SHORT).show();
                        break;
                    case 10004:
                        Toast.makeText(Weather3.this, "\t\t\t错误的OPENID", Toast.LENGTH_SHORT).show();
                        break;
                    case 10005:
                        Toast.makeText(Weather3.this, "\t\t\t应用未审核超时，请提交认证", Toast.LENGTH_SHORT).show();
                        break;
                    case 10012:
                        Toast.makeText(Weather3.this, "\t\t\t请求超过次数限制", Toast.LENGTH_SHORT).show();
                        break;
                    case 10007:
                        Toast.makeText(Weather3.this, "\t\t\t未知的请求源", Toast.LENGTH_SHORT).show();
                        break;
                    case 10008:
                        Toast.makeText(Weather3.this, "\t\t\t被禁止的IP", Toast.LENGTH_SHORT).show();
                        break;
                    case 10009:
                        Toast.makeText(Weather3.this, "\t\t\t被禁止的KEY", Toast.LENGTH_SHORT).show();
                        break;
                    case 10011:
                        Toast.makeText(Weather3.this, "\t\t当前IP请求超过限制", Toast.LENGTH_SHORT).show();
                        break;

                    case 10013:
                        Toast.makeText(Weather3.this, "\t\t测试KEY超过请求限制", Toast.LENGTH_SHORT).show();
                        break;

                    case 10014:
                        Toast.makeText(Weather3.this, "\t\t系统内部异常(调用充值类业务时，请务必联系客服或通过订单查询接口检测订单，避免造成损失)", Toast.LENGTH_SHORT).show();
                        break;

                    case 10020:
                        Toast.makeText(Weather3.this, "\t\t接口维护", Toast.LENGTH_SHORT).show();
                        break;

                    case 10021:
                        Toast.makeText(Weather3.this, "\t\t接口停用", Toast.LENGTH_SHORT).show();
                        break;

                }
            }

            @Override
            public void onFailure(Call<WeatherData> call, Throwable t) {
                Toast.makeText(Weather3.this, "查询出错", Toast.LENGTH_LONG).show();
            }
        });
    }
}
