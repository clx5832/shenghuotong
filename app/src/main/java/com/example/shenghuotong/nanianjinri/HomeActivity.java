package com.example.shenghuotong.nanianjinri;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shenghuotong.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeActivity extends AppCompatActivity {
    @BindView(R.id.home_header_yangli)
    TextView homeHeaderYangli;
    @BindView(R.id.home_header_day)
    TextView homeHeaderDay;
    @BindView(R.id.home_header_week)
    TextView homeHeaderWeek;
    @BindView(R.id.home_header_nongli)
    TextView homeHeaderNongli;
    @BindView(R.id.home_header_baiji)
    TextView homeHeaderBaiji;
    @BindView(R.id.home_header_wuxing)
    TextView homeHeaderWuxing;
    @BindView(R.id.home_header_chongsha)
    TextView homeHeaderChongsha;
    @BindView(R.id.home_header_jishen)
    TextView homeHeaderJishen;
    @BindView(R.id.home_header_xiongshen)
    TextView homeHeaderXiongshen;
    @BindView(R.id.home_header_yi)
    TextView homeHeaderYi;
    @BindView(R.id.home_header_ji)
    TextView homeHeaderJi;
    @BindView(R.id.home_toh_items)
    RecyclerView homeTohItems;
    @BindView(R.id.scroll_view)
    ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");//这个为日期格式
        String date = sf.format(new Date());
        initHeader(date);//初始化日历


        SimpleDateFormat sf2=new SimpleDateFormat("M/d");//日期的格式
        String date2=sf2.format(new Date());//日期格式
        initToh(date2);
    }

    private void initToh(String date) {
        //创建retrofit实例
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://v.juhe.cn/")
                .addConverterFactory(GsonConverterFactory.create())//采用gson解析
                .build();

        LaoHuangInterface api = retrofit.create(LaoHuangInterface.class);

        String key="587b9808612c2bfe938aa24c6a3602db";//请求的key
        final Call<LiShiJinTian>res=api.getTOH(date,key);//参数，日期,key

        res.enqueue(new Callback<LiShiJinTian>() {
            @Override
            public void onResponse(Call<LiShiJinTian> call, Response<LiShiJinTian> response) {
                //成功调用方法,设置数据
                LiShiJinTian body=response.body();//获取响应的结果

                int error_code = body.getError_code();
                switch (error_code) {
                    case 0:
                        //请求成功
                        List<LiShiJinTian.ResultBean>list=body.getResult();//获取具体结果数据

                        //设配器实例化
                        //参数context，当前activity，list，列表数据
                        LiShiJinTianAdapter kdItemAdapter=new LiShiJinTianAdapter(HomeActivity.this,list);

                        //设置laoutManager
                        LinearLayoutManager manager =new LinearLayoutManager(HomeActivity.this);
                        manager.setOrientation(LinearLayoutManager.VERTICAL);//垂直
                        homeTohItems.setLayoutManager(manager);
                        //设置Aapter
                        homeTohItems.setAdapter(kdItemAdapter);
                        break;
                    case 10001:
                        Toast.makeText(HomeActivity.this, "\t错误的请求KEY", Toast.LENGTH_SHORT).show();
                        break;
                    case 10002:
                        Toast.makeText(HomeActivity.this, "\t\t该KEY无请求权限", Toast.LENGTH_SHORT).show();
                        break;
                    case 10003:
                        Toast.makeText(HomeActivity.this, "\t\tKEY过期", Toast.LENGTH_SHORT).show();
                        break;
                    case 10004:
                        Toast.makeText(HomeActivity.this, "\t\t\t错误的OPENID", Toast.LENGTH_SHORT).show();
                        break;
                    case 10005:
                        Toast.makeText(HomeActivity.this, "\t\t\t应用未审核超时，请提交认证", Toast.LENGTH_SHORT).show();
                        break;
                    case 10012:
                        Toast.makeText(HomeActivity.this, "\t\t\t请求超过次数限制", Toast.LENGTH_SHORT).show();
                        break;
                    case 10007:
                        Toast.makeText(HomeActivity.this, "\t\t\t未知的请求源", Toast.LENGTH_SHORT).show();
                        break;
                    case 10008:
                        Toast.makeText(HomeActivity.this, "\t\t\t被禁止的IP", Toast.LENGTH_SHORT).show();
                        break;
                    case 10009:
                        Toast.makeText(HomeActivity.this, "\t\t\t被禁止的KEY", Toast.LENGTH_SHORT).show();
                        break;
                    case 10011:
                        Toast.makeText(HomeActivity.this, "\t\t当前IP请求超过限制", Toast.LENGTH_SHORT).show();
                        break;

                    case 10013:
                        Toast.makeText(HomeActivity.this, "\t\t测试KEY超过请求限制", Toast.LENGTH_SHORT).show();
                        break;

                    case 10014:
                        Toast.makeText(HomeActivity.this, "\t\t系统内部异常(调用充值类业务时，请务必联系客服或通过订单查询接口检测订单，避免造成损失)", Toast.LENGTH_SHORT).show();
                        break;

                    case 10020:
                        Toast.makeText(HomeActivity.this, "\t\t接口维护", Toast.LENGTH_SHORT).show();
                        break;

                    case 10021:
                        Toast.makeText(HomeActivity.this, "\t\t接口停用", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
            @Override
            public void onFailure(Call<LiShiJinTian> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "日期查询出错，请检查代码是否有误！！！", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initHeader(String date) {
        //创建retrofit实例
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://v.juhe.cn/")  //使用聚合数据接口
                .addConverterFactory(GsonConverterFactory.create())//采用Gson解析
                .build();
        LaoHuangInterface api = retrofit.create(LaoHuangInterface.class);
        String key = "bf805b0d455c8983ad7c785195d18182";//这个为请求key
        final Call<LaohuangliShuju> res = api.getCall(date, key);//参数，日期，key
        res.enqueue(new Callback<LaohuangliShuju>() {
            @Override
            public void onResponse(Call<LaohuangliShuju> call, Response<LaohuangliShuju> response) {
                //成功调用方法
                LaohuangliShuju body = response.body();//获取响应结果的对象

                int error_code = body.getError_code();
                switch (error_code) {
                    case 0:
                        LaohuangliShuju.ResultBean result = body.getResult();//获取具体结果对象
                        String yangli = result.getYangli();//阳历
                        String[] split = yangli.split("-");//分割为数组{2020/10/10}
                        String week = MyUtils.dateToWeek(yangli);//获取星期几

                        String yinli = result.getYinli();//阴历
                        String baiji = result.getBaiji();//彭祖百济
                        String wuxing = result.getWuxing();//五行
                        String chongsha = result.getChongsha();//冲煞
                        String jishen = result.getJishen();//吉神凶煞
                        String xiongshen = result.getXiongshen();//凶神趋势
                        String yi = result.getYi();//宜
                        String ji = result.getJi();//忌
                        //为各个控件设置值
                        homeHeaderYangli.setText("公历" + split[0] + "年" + split[1] + "月" + split[2] + "日" + week + "(阳历)");
                        homeHeaderDay.setText(split[2]);//设置星期日
                        homeHeaderWeek.setText(week);//星期几
                        homeHeaderNongli.setText(yinli + "(农历)");
                        homeHeaderBaiji.setText("彭祖百忌：" + baiji);
                        homeHeaderWuxing.setText("五行：" + wuxing);
                        homeHeaderChongsha.setText("冲煞:" + chongsha);
                        homeHeaderJishen.setText("吉神凶煞：" + jishen);
                        homeHeaderXiongshen.setText("凶神趋势；" + xiongshen);//设置凶神趋势
                        homeHeaderYi.setText("宜:" + yi);
                        homeHeaderJi.setText("忌:" + ji);
                        break;

                    case 10001:
                        Toast.makeText(HomeActivity.this, "\t错误的请求KEY", Toast.LENGTH_SHORT).show();
                        break;
                    case 10002:
                        Toast.makeText(HomeActivity.this, "\t\t该KEY无请求权限", Toast.LENGTH_SHORT).show();
                        break;
                    case 10003:
                        Toast.makeText(HomeActivity.this, "\t\tKEY过期", Toast.LENGTH_SHORT).show();
                        break;
                    case 10004:
                        Toast.makeText(HomeActivity.this, "\t\t\t错误的OPENID", Toast.LENGTH_SHORT).show();
                        break;
                    case 10005:
                        Toast.makeText(HomeActivity.this, "\t\t\t应用未审核超时，请提交认证", Toast.LENGTH_SHORT).show();
                        break;
                    case 10012:
                        Toast.makeText(HomeActivity.this, "\t\t\t请求超过次数限制", Toast.LENGTH_SHORT).show();
                        break;
                    case 10007:
                        Toast.makeText(HomeActivity.this, "\t\t\t未知的请求源", Toast.LENGTH_SHORT).show();
                        break;
                    case 10008:
                        Toast.makeText(HomeActivity.this, "\t\t\t被禁止的IP", Toast.LENGTH_SHORT).show();
                        break;
                    case 10009:
                        Toast.makeText(HomeActivity.this, "\t\t\t被禁止的KEY", Toast.LENGTH_SHORT).show();
                        break;
                    case 10011:
                        Toast.makeText(HomeActivity.this, "\t\t当前IP请求超过限制", Toast.LENGTH_SHORT).show();
                        break;

                    case 10013:
                        Toast.makeText(HomeActivity.this, "\t\t测试KEY超过请求限制", Toast.LENGTH_SHORT).show();
                        break;

                    case 10014:
                        Toast.makeText(HomeActivity.this, "\t\t系统内部异常(调用充值类业务时，请务必联系客服或通过订单查询接口检测订单，避免造成损失)", Toast.LENGTH_SHORT).show();
                        break;

                    case 10020:
                        Toast.makeText(HomeActivity.this, "\t\t接口维护", Toast.LENGTH_SHORT).show();
                        break;

                    case 10021:
                        Toast.makeText(HomeActivity.this, "\t\t接口停用", Toast.LENGTH_SHORT).show();
                        break;

                }


            }

            @Override
            public void onFailure(Call<LaohuangliShuju> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "日期查询出错，请检查代码是否有误！！！", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @OnClick(R.id.imageid)
    public void CalendarDialogClick(View view){
        Calendar calendar=Calendar.getInstance();
        DatePickerDialog dialog=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                initHeader(year+"/"+(month+1)+"/"+dayOfMonth);
                initToh((month+1)+"/"+dayOfMonth);
                Toast.makeText(HomeActivity.this,"您选择的时间是："+year+"-"+month+"-"+dayOfMonth,Toast.LENGTH_LONG).show();
            }
        },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();
//        homeHeaderDay.getText();
    }


}
