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
        String no = edKuaidi.getText().toString();//??????????????????
        if (no == null || no.length() == 0) {
//??????????????????????????????
            Toast.makeText(this, "?????????????????????", Toast.LENGTH_LONG).show();

        } else {
            //??????retrofit
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://kdwlcxf.market.alicloudapi.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            Login login = retrofit.create(Login.class);
            final Call<ResultBody> res = login.getCall(no);//???????????????
            //??????????????????
            res.enqueue(new Callback<ResultBody>() {
                @Override
                public void onResponse(Call<ResultBody> call, Response<ResultBody> response) {
                    try {
                        ResultBody resultBody = response.body();
                        List<ResultBody.ResultBean.ListBean> list = resultBody.getResult().getList();//???????????????????????????list???
//                        String resultStr = "";//???????????????????????????for?????????????????????????????????????????????
//                        for (ResultBody.ResultBean.ListBean bean : list) {
//                            String time = bean.getTime();
//                            String status = bean.getStatus();
//                            resultStr = resultStr + time + ":" + status + "\n";
//                        }
//                        tvKuaidi.setText(resultStr);//??????????????????????????????????????????
                        String logoUrl = resultBody.getResult().getLogo();//??????????????????
                        String expName = resultBody.getResult().getExpName();//??????????????????
                        String exPhone = resultBody.getResult().getExpPhone();//?????????????????????
                        callPhone = exPhone;
                        //???????????????0???????????????1????????????2???????????????3????????????4???????????????5????????? 6????????????
                        String deliverystatus = resultBody.getResult().getDeliverystatus();
                        String[] aa = {"?????????", "?????????", "????????????", "?????????", "????????????", "?????????", "????????????"};
                        Glide.with(context).load(logoUrl)
                                .into(imageKuaidi);//???????????????Logo??????
                        kuaiMessage.setText(expName);//??????????????????????????????????????????
                        kuaiPhone.setText(exPhone);//?????????????????????????????????????????????
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
                        //?????????
                        System.out.println("????????????");
                        Toast.makeText(Kuaidi.this, "????????????????????????????????????", Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }

                }

                @Override
                public void onFailure(Call<ResultBody> call, Throwable t) {
                    //?????????????????????
                    System.out.println("onFailure()");
                    Toast.makeText(Kuaidi.this, "????????????????????????????????????", Toast.LENGTH_LONG).show();
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

        //???????????? ???????????????
        mCamera.release();

        mCamera=null;

    }



    @OnTouch(R.id.ed_erweima)
    public boolean MyClick(View v, MotionEvent event) {
        //edKuaidi?????????????????? edKuaidi.getCompoundDrawables()????????????????????????4?????????????????????????????????????????????
        Drawable drawable = edErweima.getCompoundDrawables()[2];//2?????????????????????
        //event.getAction()??????????????????
        //MotionEvent.ACTION_DOWN????????????
        //MotionEvent.ACTION_MOVE?????????????????????????????????,??????move???????????????
        //MotionEvent.ACTION_MOVE_UP????????????
        if (drawable != null && event.getAction() == MotionEvent.ACTION_DOWN) {
            if (event.getX() < edErweima.getWidth()) {//?????????????????????????????????
                Toast.makeText(Kuaidi.this, "?????????", Toast.LENGTH_LONG).show();
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
        //???????????????????????????
        if (requestCode == 1) {
            //??????????????????????????????????????????
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);//?????????????????????
                    getSystemService(VIBRATOR_SERVICE);//??????????????????????????????
                    edKuaidi.setText(result);//???????????????????????????????????????????????????
                    this.ChaXun(null);//?????????????????????
//                    this.surfaceDestroyed(null);
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(Kuaidi.this, "??????????????????", Toast.LENGTH_LONG).show();
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

