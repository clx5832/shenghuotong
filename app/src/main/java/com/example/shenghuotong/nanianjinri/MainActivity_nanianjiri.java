package com.example.shenghuotong.nanianjinri;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.shenghuotong.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity_nanianjiri extends AppCompatActivity {

    @BindView(R.id.lishi)
    ImageView lishi;
    private final int SPLASH_DISPLAY_LENGHT = 3000; // 延迟10秒
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_nanianjinri);
        ButterKnife.bind(this);

    }


// new Handler().postDelayed(new Runnable()
//    { @OnClick(R.id.lishi)
//        @Override
//        public void tijian()
//        {
//            /* Create an Intent that will start the Menu-Activity. */
//            Intent intent = new Intent(MainActivity_nanianjiri.this, MainActivity_nanianjiri.class);
//            MainActivity_nanianjiri.this.startActivity(intent);
//            MainActivity_nanianjiri.this.finish();
//        }
//    },2000);
    @OnClick(R.id.lishi)
    public void tianzhuan(View view) {
        AnimationSet animationSet = new AnimationSet(true);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
//			animationSet.setInterpolator(new AccelerateInterpolator());
        TranslateAnimation translateAnimation=new TranslateAnimation(0.5f,1.0f,0.5f,1.0f);
        RotateAnimation rotateAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF,
                0.45f, Animation.RELATIVE_TO_SELF, 0.45f);
        rotateAnimation.setInterpolator(new AccelerateInterpolator());
        animationSet.addAnimation(alphaAnimation);
        animationSet.addAnimation(rotateAnimation);
        animationSet.addAnimation(translateAnimation);
        animationSet.setDuration(3000);
        animationSet.setStartOffset(300);//启动的时间
        lishi.startAnimation(animationSet);
        Toast.makeText(MainActivity_nanianjiri.this,"恭喜您点击成功，现在进入历史的今天！！",Toast.LENGTH_LONG).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                AnimationSet animationSet = new AnimationSet(true);
//                AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
////			animationSet.setInterpolator(new AccelerateInterpolator());
//                RotateAnimation rotateAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF,
//                        0.45f, Animation.RELATIVE_TO_SELF, 0.45f);
//                rotateAnimation.setInterpolator(new AccelerateInterpolator());
//                animationSet.addAnimation(alphaAnimation);
//                animationSet.addAnimation(rotateAnimation);
//                animationSet.setDuration(3000);
//                animationSet.setStartOffset(300);//启动的时间
//                lishi.startAnimation(animationSet);

                Intent intent = new Intent(MainActivity_nanianjiri.this,
                        HomeActivity.class);

                startActivity(intent);
                MainActivity_nanianjiri.this.finish();
                finish();
            }
        }, SPLASH_DISPLAY_LENGHT);
//        AnimationSet animationSet = new AnimationSet(true);
//        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
////			animationSet.setInterpolator(new AccelerateInterpolator());
//        RotateAnimation rotateAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF,
//                0.45f, Animation.RELATIVE_TO_SELF, 0.45f);
//        rotateAnimation.setInterpolator(new AccelerateInterpolator());
//        animationSet.addAnimation(alphaAnimation);
//        animationSet.addAnimation(rotateAnimation);
//        animationSet.setDuration(3000);
//        animationSet.setStartOffset(300);//启动的时间
//        lishi.startAnimation(animationSet);

//        Intent intent = new Intent(MainActivity_nanianjiri.this, HomeActivity.class);
//        startActivity(intent);
    }
}
// new Handler().postDelayed(new Runnable()
//         {
//@Override
//public void run()
//        {
//        /* Create an Intent that will start the Menu-Activity. */
//        Intent intent = new Intent(SplashActivity.this, MainActivity_nanianjiri.class);
//        SplashActivity.this.startActivity(intent);
//        SplashActivity.this.finish();
//        }
//        },2000);
//private final int SPLASH_DISPLAY_LENGHT = 10000; // 延迟10秒
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.main);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent intent = new Intent(Test.this,
//                        Your.class);
//                startActivity(intent);
//                Test.this.finish();
//                finish();
//            }
//        }, SPLASH_DISPLAY_LENGHT);
//    }