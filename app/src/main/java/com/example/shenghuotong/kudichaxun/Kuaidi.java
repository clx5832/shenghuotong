package com.example.shenghuotong.kudichaxun;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shenghuotong.R;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTouch;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Kuaidi extends AppCompatActivity {

    @BindView(R.id.ed_kuaidi)
    EditText edKuaidi;
    @BindView(R.id.chaxun)
    Button chaxun;
    @BindView(R.id.ll_kuai)
    LinearLayout llKuai;
    //    @BindView(R.id.tv_kuaidi)
//    TextView tvKuaidi;
    @BindView(R.id.image_kuaidi)
    ImageView imageKuaidi;
    @BindView(R.id.kuai_message)
    TextView kuaiMessage;
    @BindView(R.id.kuai_phone)
    TextView kuaiPhone;
    @BindView(R.id.kuai_status)
    TextView kuaiStatus;
    //    @BindView(R.id.image_kuaidi)
//    ImageView imageKuaidi;
    @BindView(R.id.items)
    RecyclerView items;
    @BindView(R.id.ed_erweima)
    EditText edErweima;
//    @BindView(R.id.items)
//    RecyclerView items;


    //private RecyclerView items;
    private Context context;
    private String callPhone;
    private Camera mCamera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kuaidi);
        ButterKnife.bind(this);
        context = this;
    }

    @OnClick(R.id.chaxun)
    public void ChaXun(View v) {
        String no = edKuaidi.getText().toString();//获取快递单号
        if (no == null || no.length() == 0) {
//判断是否输入快递单号
            Toast.makeText(this, "请输入快递单号", Toast.LENGTH_LONG).show();

        } else {
            //创建retrofit
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://kdwlcxf.market.alicloudapi.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            Login login = retrofit.create(Login.class);
            final Call<ResultBody> res = login.getCall(no);//播快递单号
            //发送异步请求
            res.enqueue(new Callback<ResultBody>() {
                @Override
                public void onResponse(Call<ResultBody> call, Response<ResultBody> response) {
                    try {
                        ResultBody resultBody = response.body();
                        List<ResultBody.ResultBean.ListBean> list = resultBody.getResult().getList();//最终数据存放在这个list中
//                        String resultStr = "";//定义一个字符串，用for将想要的信息存放在这个字符串中
//                        for (ResultBody.ResultBean.ListBean bean : list) {
//                            String time = bean.getTime();
//                            String status = bean.getStatus();
//                            resultStr = resultStr + time + ":" + status + "\n";
//                        }
//                        tvKuaidi.setText(resultStr);//将快递查询的结果展示在界面上
                        String logoUrl = resultBody.getResult().getLogo();//快递公司地址
                        String expName = resultBody.getResult().getExpName();//快递公司名称
                        String exPhone = resultBody.getResult().getExpPhone();//快递公司的电话
                        callPhone = exPhone;
                        //当前状态，0快件揽收，1在途中，2正在派件，3已签收，4派送失败，5疑难件 6退件签收
                        String deliverystatus = resultBody.getResult().getDeliverystatus();
                        String[] aa = {"已揽件", "在途中", "正在派件", "已签收", "派送失败", "疑难件", "推荐签收"};
                        Glide.with(context).load(logoUrl)
                                .into(imageKuaidi);//设置快递的Logo界面
                        kuaiMessage.setText(expName);//设置快递公司的名称展现在界面
                        kuaiPhone.setText(exPhone);//快递公司的电话号码展现在界面上
                        kuaiStatus.setText(aa[Integer.parseInt(deliverystatus)]);
//                KuaiDiAdapter adapter=new KuaiDiAdapter(Kuaidi.this,list);
//                        LinearLayoutManager manager=new LinearLayoutManager(Kuaidi.this);
//                        manager.setOrientation(LinearLayout.VERTICAL);
//                        items.setLayoutManager(manager);
//                        items.setAdapter(adapter);
                        KuaidiAdapter kuaidiAdapter = new KuaidiAdapter(Kuaidi.this, list);
                        LinearLayoutManager manager = new LinearLayoutManager(Kuaidi.this);
                        manager.setOrientation(RecyclerView.VERTICAL);
                        items.setLayoutManager(manager);
                        items.setAdapter(kuaidiAdapter);
                    } catch (Exception e) {
                        //出错了
                        System.out.println("捕获异常");
                        Toast.makeText(Kuaidi.this, "查询出错了，请联系管理员", Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }

                }

                @Override
                public void onFailure(Call<ResultBody> call, Throwable t) {
                    //失败调用的方法
                    System.out.println("onFailure()");
                    Toast.makeText(Kuaidi.this, "查询出错了，请联系管理员", Toast.LENGTH_LONG).show();
                }
            });
        }

    }

    @OnClick(R.id.kuai_phone)
    public void callPhone(View v) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + callPhone));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }


    public void surfaceDestroyed(SurfaceHolder holder) {
        // TODO Auto-generated method stub
        mCamera.stopPreview();

        //手动释放 一定得加！
        mCamera.release();

        mCamera=null;

    }



    @OnTouch(R.id.ed_erweima)
    public boolean MyClick(View v, MotionEvent event) {
        //edKuaidi为快递输入框 edKuaidi.getCompoundDrawables()的得到一个长度为4的数组，分别表示左，上，右，下
        Drawable drawable = edErweima.getCompoundDrawables()[2];//2表示右边的图片
        //event.getAction()获取触摸状态
        //MotionEvent.ACTION_DOWN手指按下
        //MotionEvent.ACTION_MOVE手指移动（从手指抬起时,手指move被多次执行
        //MotionEvent.ACTION_MOVE_UP手指抬起
        if (drawable != null && event.getAction() == MotionEvent.ACTION_DOWN) {
            if (event.getX() < edErweima.getWidth()) {//点击区域为扫一扫的区域
                Toast.makeText(Kuaidi.this, "扫一扫", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Kuaidi.this, CaptureActivity.class);
                startActivityForResult(intent, 1);
            }
        } else {
            return false;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //处理二维码扫描结果
        if (requestCode == 1) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);//获得扫描的结果
                    getSystemService(VIBRATOR_SERVICE);//扫描成功手机震动一下
                    edKuaidi.setText(result);//将扫描的结果设置进快递单号的输入框
                    this.ChaXun(null);//调用查询的方法
//                    this.surfaceDestroyed(null);
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(Kuaidi.this, "扫描失败！！", Toast.LENGTH_LONG).show();
                }

                }
            }
        }
    private boolean safeCameraOpen(int id) {

        try {

            ReleaseCamera();

            mCamera = Camera.open(id);

        } catch (Exception e) {

e.printStackTrace();
        }
return true;
    }

    private void ReleaseCamera()
    {
        if(mCamera != null)
        {
            mCamera.release();
            mCamera = null;
        }
    }
    }

