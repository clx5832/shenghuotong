package com.example.shenghuotong;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.graphics.drawable.AnimationDrawable;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.shenghuotong.Fragment.Four;
import com.example.shenghuotong.Fragment.Guzhen3;
import com.example.shenghuotong.Fragment.One;
import com.example.shenghuotong.Fragment.Three;
import com.example.shenghuotong.Fragment.Two;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SuccessDenglu extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.tv_success)
    TextView tvSuccess;
    @BindView(R.id.imageview)
    ImageView imageview;
    @BindView(R.id.one)
    Button one;
    @BindView(R.id.two)
    Button two;
    @BindView(R.id.three)
    Button three;
    @BindView(R.id.ll_fragment)
    LinearLayout llFragment;
    @BindView(R.id.four)
    Button four;
    @BindView(R.id.guzhen)
    Button guzhen;


    private SoundPool soundPool;
    private int soundId3;
    private int soundId4;
    private int soundId5;
    private static final int MENU1 = 1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.successdenglu);
        ButterKnife.bind(this);
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        guzhen.setOnClickListener(this);

        //获取背景资源，从anim_nv xml资源中获取1
        imageview.setBackgroundResource(R.drawable.anim_nv);
        //通过动画的AnimationDrawable动画背景得到背景,并通过imageview得到背景并显示出来
        AnimationDrawable animationDrawable = (AnimationDrawable) imageview.getBackground();
        //开始执行
        animationDrawable.start();


        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 1);
        soundId3 = soundPool.load(this, R.raw.shinian, 1);
        soundId4 = soundPool.load(this, R.raw.tianqiyubao, 1);
    }

    @Override
    public void onClick(View v) {

        FragmentManager fragmentManager = getFragmentManager();//Fragment管理者
        FragmentTransaction transaction = fragmentManager.beginTransaction();//事务提交
        switch (v.getId()) {
            case R.id.one:
                transaction.replace(R.id.ll_fragment, new One());
                soundPool.play(soundId4, 1, 1, 1, 0, 1);
                break;
            case R.id.two:
                transaction.replace(R.id.ll_fragment, new Two());
                break;

            case R.id.three:
                transaction.replace(R.id.ll_fragment, new Three());
                soundPool.play(soundId3, 1, 1, 1, 0, 1);
                break;

            case R.id.four:
                transaction.replace(R.id.ll_fragment, new Four());
                break;

            case R.id.guzhen:
                transaction.replace(R.id.ll_fragment, new Guzhen3());
                break;

        }
        transaction.commit();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuItem about=menu.add(0,MENU1,0,"关于");


        MenuItem.OnMenuItemClickListener onMenuItemClickListener = new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case MENU1:
                     final  AlertDialog.Builder builder = new AlertDialog.Builder(SuccessDenglu.this);
                        builder.setIcon(R.drawable.clx);
                        builder.setMessage("作者：陈良项\n制作时间是2021.1.8 \n路漫漫其修远兮，吾将上下而求索！\n软件制作不易，有不足请帮忙提出！");
                        builder.setNegativeButton("退出", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(SuccessDenglu.this,"退出成功",Toast.LENGTH_LONG).show();
                            }
                        });
                        builder.show();
                        break;
                }
                return true;
            }
        };
        about.setOnMenuItemClickListener(onMenuItemClickListener);
        return true;
    }
}
