package com.example.shenghuotong.piaoxuexiaoguo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CustomView extends View {
//    private BobbleBean lBobbleBean;

    public CustomView(Context context) {
        this(context,null);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }
    //画笔
    Paint mPaint;
//保存点集合
    List<BobbleBean>mBobbleBeanList;


    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint=new Paint();
        mBobbleBeanList=new ArrayList<>();
    }

//    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
    /*
    * View绘制过程
    * View的测量---onMeasure()
    * View的位置确定----onLayout()
    *View的绘制-----onDraw()
    * */
    //第一步测量
    //默认View大小
    private int mDefaultWidth=dp2px(100);
    private int mDefaultHeight=dp2px(100);


    private int mMeasureWidth=dp2px(100);
    private int mMeasureHeight=dp2px(100);
//一个dp转像素计算
    private int dp2px(int dp) {
        float density=getContext().getResources().getDisplayMetrics().density;
        return (int)(dp*density+0.5f);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //获取测量计算相关内容
        int widthSpecMode=MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize=MeasureSpec.getSize(widthMeasureSpec);

        if (widthSpecMode==MeasureSpec.EXACTLY){
            //specMode=EXACTLY时，精确值模式，即当我们在布局文件中为View指定了具体的大小
            mMeasureWidth=widthSpecSize;
        }else {
            //指定默认大小
            mMeasureWidth=mDefaultWidth;
            if (widthSpecMode==MeasureSpec.AT_MOST){
                mMeasureWidth=Math.min(mMeasureWidth,widthSpecSize);
            }
        }
        //测量计算的View的高
        int heightSpecMode=MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize=MeasureSpec.getSize(heightMeasureSpec);

        if (heightSpecMode==MeasureSpec.EXACTLY){
            //specMode=EXACTLY时，精确值模式，即当我们在布局文件中为View指定了具体的大小
            mMeasureHeight=heightSpecSize;
        }else {
            //指定默认大小
            mMeasureHeight=mDefaultHeight;
            if (widthSpecMode==MeasureSpec.AT_MOST){
                mMeasureHeight=Math.min(mMeasureHeight,widthSpecSize);
            }
        }
        mMeasureHeight=mMeasureHeight-getPaddingBottom()-getPaddingTop();
        mMeasureWidth=mMeasureWidth-getPaddingLeft()-getPaddingBottom();

        //重新测量
        setMeasuredDimension(mMeasureWidth,mMeasureHeight);
    }

    //这里创建点
    Random mrandom=new Random();
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        for (int i=0;i<mMeasureWidth/3;i++){
            BobbleBean lbobbleBean = new BobbleBean();

            //生成位置信息,随机
            //取值范围时0-mMeasureWidth
            int x=mrandom.nextInt(mMeasureWidth);
            int y=mrandom.nextInt(mMeasureHeight);
//            lBobbleBean=new BobbleBean();

            //绘制使用的位置
            lbobbleBean.postion=new Point(x,y);
            //重置的位置
            lbobbleBean.origin=new Point(x,0);
            //随机的半径 1-4
            lbobbleBean.radius=mrandom.nextFloat()*3+dp2px(1);

            //随机的速度 3-6
            lbobbleBean.speed=3+mrandom.nextInt(3);

            //随机透明的白色
            lbobbleBean.color=ColorUtil.randomWhiteColor();
            mBobbleBeanList.add(lbobbleBean);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//绘制时重新计算位置
        for (BobbleBean lbobbleBean : mBobbleBeanList) {
            Point postion = lbobbleBean.postion;
            //在竖直方向增加偏移
            postion.y+= lbobbleBean.speed;
//在X轴方向上再微微的偏移一点
            float randValue=mrandom.nextFloat()*2-0.5f;
            postion.x+=randValue;


            //边界控制
            if (postion.y>mMeasureHeight){
                postion.y=0;
            }
        }


        //先将这些点全部绘制出来
        for (BobbleBean lbobbleBean : mBobbleBeanList) {
            //修改画笔的颜色
            mPaint.setColor(lbobbleBean.color);
            //绘制
            // 参数 一 二 圆点位置
            // 参数三 半径
            // 参数四 画笔
            canvas.drawCircle(lbobbleBean.postion.x,lbobbleBean.postion.y,lbobbleBean.radius,mPaint);
        }
//循环刷新 10毫秒刷新一次
        postInvalidateDelayed(1L);

    }
}

