package com.example.shenghuotong.piaoxuexiaoguo;

import android.graphics.Color;

import java.util.Random;

public class ColorUtil {


    //生成随机颜色
    public static int   randomColor(){
        Random random=new Random();
        int red=random.nextInt(200);
        int green=random.nextInt(200);
        int blue=random.nextInt(200);
        return Color.rgb(red,green,blue);
    }

    //生成随机透明白色
    public static int randomWhiteColor(){
        Random random=new Random();
        int a=random.nextInt(200);
        return Color.argb(a,255,255,255);
    }
}
