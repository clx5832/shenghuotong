package com.example.shenghuotong.kudichaxun;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.shenghuotong.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class KuaiActivitydd extends AppCompatActivity {

    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.denglu)
    TextView denglu;
    @BindView(R.id.ll_name)
    LinearLayout llName;
    @BindView(R.id.ll_psd)
    LinearLayout llPsd;
    @BindView(R.id.login)
    Button login;
    @BindView(R.id.ed_name)
    EditText edName;
    @BindView(R.id.ed_psd)
    EditText edPsd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_kuaidi);
        ButterKnife.bind(this);
        initImage();

    }

    private void initImage() {
        String url = "https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1606576796&di=87fb8a3ebd6190449cbadb079875b38b&src=http://d.hiphotos.baidu.com/zhidao/pic/item/8435e5dde71190ef5529c1e8cf1b9d16fdfa6032.jpg";
        Glide.with(this).load(url).into(image);
    }

    @OnClick(R.id.login)
    public void onViewClicked(View v) {
        String username =edName.getText().toString();
        String password=edPsd.getText().toString();
        if (username==null||password==null){
            Toast.makeText(KuaiActivitydd.this,"用户名和密码不能为空",Toast.LENGTH_LONG).show();
            System.exit(0);
        }else if (username.equals("clx")&&password.equals("123456")){
            Intent intent=new Intent(KuaiActivitydd.this, Kuaidi.class);
            startActivity(intent);
            Toast.makeText(KuaiActivitydd.this,"登录成功！！",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(KuaiActivitydd.this,"用户名或者密码输入错误！！",Toast.LENGTH_LONG).show();
        }


    }
}
