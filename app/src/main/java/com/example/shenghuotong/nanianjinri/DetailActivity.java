package com.example.shenghuotong.nanianjinri;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.shenghuotong.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailActivity extends AppCompatActivity {

    String e_id=null;
    @BindView(R.id.detail_images)
    LinearLayout detailImages;
    @BindView(R.id.detail_content)
    TextView detailContent;
    @BindView(R.id.detail_title)
    TextView detailTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        Intent intent=getIntent();
        e_id=intent.getStringExtra("e_id");//获取上个页面跳转过来的事件id
        Toast.makeText(DetailActivity.this,"事件id"+e_id,Toast.LENGTH_SHORT).show();
        initDetail(e_id);
    }

    private void initDetail(String e_id) {
        //创建retrofit实例
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://v.juhe.cn/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        LaoHuangInterface api = retrofit.create(LaoHuangInterface.class);

        String key="587b9808612c2bfe938aa24c6a3602db";//请求的key
        Call<LishiJinDetail>res=api.getDetail(e_id,key);//通过事件id获取详情
        res.enqueue(new Callback<LishiJinDetail>() {
            @Override
            public void onResponse(Call<LishiJinDetail> call, Response<LishiJinDetail> response) {
                //成功调用方法 设置数据
                LishiJinDetail body=response.body();//获取响应对象的结果
                LishiJinDetail.ResultBean result=body.getResult().get(0);//获取具体结果对象
                String title=result.getTitle();//获取标题
                detailTitle.setText(title);//设置标题

                List<LishiJinDetail.ResultBean.PicUrlBean>picUrl=result.getPicUrl();//获取图片数组
                for (int i=0;i<picUrl.size();i++){
                    LishiJinDetail.ResultBean.PicUrlBean picUrlBean=picUrl.get(i);
                    String pic_title=picUrlBean.getPic_title();//获取图片的标题
                    String url=picUrlBean.getUrl();//获取图片的地址
                    ImageView imageView=new ImageView(DetailActivity.this);//创建图片的控件
                    Glide.with(DetailActivity.this).load(url).into(imageView);//加载图片
                    detailImages.addView(imageView);//将图片控件加载到detailImages布局中
                    //在图片下方加上图片说明（标题）
                    TextView textView=new TextView(DetailActivity.this);
                    textView.setGravity(Gravity.CENTER);
                    textView.setText(pic_title);
                    textView.setPadding(0,0,0,22);
                    detailImages.addView(textView);
                }
                String content=result.getContent();//获取内容
                detailContent.setText(content);//设置内容

            }

            @Override
            public void onFailure(Call<LishiJinDetail> call, Throwable t) {
                Toast.makeText(DetailActivity.this, "日期查询出错，请检查代码是否有误！！！", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
