package com.example.shenghuotong.weather;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.shenghuotong.MyUtils.MyUtils;
import com.example.shenghuotong.R;
import com.example.shenghuotong.WeatherData.WeatherData;
import com.example.shenghuotong.api.Weather;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Weather2 extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.week)
    TextView week1;
    @BindView(R.id.info)
    TextView info;
    @BindView(R.id.wid)
    TextView wid;
    @BindView(R.id.temperature)
    TextView temperature;
    @BindView(R.id.humidity)
    TextView humidity;
    @BindView(R.id.direct)
    TextView direct;
    @BindView(R.id.power)
    TextView power;
    @BindView(R.id.aqi)
    TextView aqi;
    @BindView(R.id.future)
    TextView future;
    @BindView(R.id.genggai)
    Button genggai;
    @BindView(R.id.qingchu)
    Button qingchu;
    @BindView(R.id.city1)
    EditText city1;
    @BindView(R.id.date1)
    TextView date1;
    @BindView(R.id.ll_weather)
    LinearLayout llWeather;
    private String city2 = "贺州";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather);
        ButterKnife.bind(this);
        city1 = (EditText) findViewById(R.id.city1);
        week1 = findViewById(R.id.week);
        genggai.setOnClickListener(this);
        qingchu.setOnClickListener(this);
        String city1 = "贺州";
        String city = city2;
        initWeather(city);
        initWeather(city1);

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

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
//获取当前时间戳
                        Date date2 = new Date(System.currentTimeMillis());
                        date1.setText(simpleDateFormat.format(date2));

                        WeatherData.ResultBean.RealtimeBean result = body.getResult().getRealtime();//获取具体的结果对象
                        String info1 = result.getInfo();
                        String wid1 = result.getWid();//00,01,02

//                       int aa [0][4]={R.drawable.qing,R.drawable.duoyun,R.drawable.yin,R.drawable.zhenyu,R.drawable.leizhenyu};
//                       int[] aa ={R.drawable.qing,R.drawable.duoyun,R.drawable.yin,R.drawable.zhenyu,R.drawable.leizhenyu};
                        switch (wid1) {
                            case "00":

                                llWeather.setBackgroundResource(R.drawable.qing);
                                break;
                            case "01":
                                llWeather.setBackgroundResource(R.drawable.duoyun);
                                break;

                            case "02":
                                llWeather.setBackgroundResource(R.drawable.yin);
                                break;
                            case "03":
                                llWeather.setBackgroundResource(R.drawable.zhenyu);
                                break;
                            case "04":
                                llWeather.setBackgroundResource(R.drawable.leizhenyu);
                                break;
                            case "05":
                                llWeather.setBackgroundResource(R.drawable.leizhenyubanbingbao);
                                break;
                            case "06":
                                llWeather.setBackgroundResource(R.drawable.yujiaxue);
                                break;
                            case "07":
                                llWeather.setBackgroundResource(R.drawable.xiaoyu);
                                break;
                            case "08":
                                llWeather.setBackgroundResource(R.drawable.zhongyu);
                                break;
                            case "09":
                                llWeather.setBackgroundResource(R.drawable.dayu);
                                break;
                            case "10":
                                llWeather.setBackgroundResource(R.drawable.baoyu);
                                break;
                            case "11":
                                llWeather.setBackgroundResource(R.drawable.dabaoyu);
                                break;
                            case "12":
                                llWeather.setBackgroundResource(R.drawable.tedabaoyu);
                                break;

                            case "13":
                                llWeather.setBackgroundResource(R.drawable.zhenxue);
                                break;
                            case "14":
                                llWeather.setBackgroundResource(R.drawable.xiaoxue);
                                break;
                            case "15":
                                llWeather.setBackgroundResource(R.drawable.zhongxue);
                                break;
                            case "16":
                                llWeather.setBackgroundResource(R.drawable.daxue);
                                break;
                            case "17":
                                llWeather.setBackgroundResource(R.drawable.baoxue);
                                break;

                            case "18":
                                llWeather.setBackgroundResource(R.drawable.wu);
                                break;
                            case "21":
                                llWeather.setBackgroundResource(R.drawable.xiaodaozhongyu);
                                break;

                            case "22":
                                llWeather.setBackgroundResource(R.drawable.zhongdaodayu);
                                break;

                            case "23":
                                llWeather.setBackgroundResource(R.drawable.dadapbaoyu);
                                break;
                            case "24":
                                llWeather.setBackgroundResource(R.drawable.baoyudaodabaoyu);
                                break;
                            case "25":
                                llWeather.setBackgroundResource(R.drawable.dabaoyudaotedaobaoyu);
                                break;



                        }


//                     int aa [0][4]={R.drawable.qing,R.drawable.duoyun,R.drawable.yin,R.drawable.zhenyu,R.drawable.leizhenyu};
                        String temperature1 = result.getTemperature();
                        String humidity1 = result.getHumidity();
                        String direct1 = result.getDirect();
                        String power1 = result.getPower();
                        String aqi1 = result.getAqi();


                        info.setText(info1);
                        wid.setText(wid1);
                        temperature.setText(temperature1);
                        humidity.setText(humidity1);
                        direct.setText(direct1);
                        power.setText(power1);
                        aqi.setText(aqi1);
                        System.out.println(wid1 + "++++++++++++");

                        String dateToWeek = MyUtils.dateToWeek(simpleDateFormat.format(new Date()));
                        week1.setText(dateToWeek);

                        break;

                    case 10001:
                        Toast.makeText(Weather2.this, "\t错误的请求KEY", Toast.LENGTH_SHORT).show();
                        break;
                    case 10002:
                        Toast.makeText(Weather2.this, "\t\t该KEY无请求权限", Toast.LENGTH_SHORT).show();
                        break;
                    case 10003:
                        Toast.makeText(Weather2.this, "\t\tKEY过期", Toast.LENGTH_SHORT).show();
                        break;
                    case 10004:
                        Toast.makeText(Weather2.this, "\t\t\t错误的OPENID", Toast.LENGTH_SHORT).show();
                        break;
                    case 10005:
                        Toast.makeText(Weather2.this, "\t\t\t应用未审核超时，请提交认证", Toast.LENGTH_SHORT).show();
                        break;
                    case 10012:
                        Toast.makeText(Weather2.this, "\t\t\t请求超过次数限制", Toast.LENGTH_SHORT).show();
                        break;
                    case 10007:
                        Toast.makeText(Weather2.this, "\t\t\t未知的请求源", Toast.LENGTH_SHORT).show();
                        break;
                    case 10008:
                        Toast.makeText(Weather2.this, "\t\t\t被禁止的IP", Toast.LENGTH_SHORT).show();
                        break;
                    case 10009:
                        Toast.makeText(Weather2.this, "\t\t\t被禁止的KEY", Toast.LENGTH_SHORT).show();
                        break;
                    case 10011:
                        Toast.makeText(Weather2.this, "\t\t当前IP请求超过限制", Toast.LENGTH_SHORT).show();
                        break;

                    case 10013:
                        Toast.makeText(Weather2.this, "\t\t测试KEY超过请求限制", Toast.LENGTH_SHORT).show();
                        break;

                    case 10014:
                        Toast.makeText(Weather2.this, "\t\t系统内部异常(调用充值类业务时，请务必联系客服或通过订单查询接口检测订单，避免造成损失)", Toast.LENGTH_SHORT).show();
                        break;

                    case 10020:
                        Toast.makeText(Weather2.this, "\t\t接口维护", Toast.LENGTH_SHORT).show();
                        break;

                    case 10021:
                        Toast.makeText(Weather2.this, "\t\t接口停用", Toast.LENGTH_SHORT).show();
                        break;

                }


            }


            @Override
            public void onFailure(Call<WeatherData> call, Throwable t) {
                Toast.makeText(Weather2.this, "查询出错", Toast.LENGTH_LONG).show();
            }
        });

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.genggai:
                city2 = city1.getText().toString();
                Toast.makeText(Weather2.this, "更改城市成功", Toast.LENGTH_LONG).show();
                initWeather(city2);
                break;
            case R.id.qingchu:
                clear();
                break;

            case R.id.future:
//                Intent intent=new Intent(Weather2.this,Weather3.class);
//                startActivity(intent);
                tianji(v);
                break;
        }
    }

    public void clear() {
        city1.setText("");
        info.setText("");
        temperature.setText("");
        humidity.setText("");
        aqi.setText("");
        power.setText("");
        direct.setText("");
        wid.setText("");


    }

    public void tianji(View view) {
        Intent intent = new Intent(Weather2.this, Weather3.class);
        intent.putExtra("city", city2);
        startActivity(intent);
    }

    public String getCity2() {
        return city2;
    }

    public void setCity2(String city2) {
        this.city2 = city2;
    }

}


