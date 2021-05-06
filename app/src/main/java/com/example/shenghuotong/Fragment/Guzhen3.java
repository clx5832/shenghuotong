package com.example.shenghuotong.Fragment;


import android.app.Fragment;


import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.example.shenghuotong.R;
import com.example.shenghuotong.guzhen.GuZhen;
public class Guzhen3 extends Fragment{

  private NotificationManager manager;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.guzhen2,null);
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ImageView guzhen5=getActivity().findViewById(R.id.guzhen5);
        guzhen5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                manager= (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
                Notification.Builder builder = new Notification.Builder(getActivity());
                builder.setSmallIcon(R.drawable.clx);//设置小图标
                builder.setTicker("Hello guzhen!!");//设置手机状态的提示
                builder.setWhen(System.currentTimeMillis());//设置系统时间
                builder.setContentTitle("通知栏通知");//设置标题
                builder.setContentText("-------------古镇下雨正在运行-------------");
//        builder.setContentIntent(PendingIntent.readPendingIntentOrNullFromParcel(null));
                builder.setDefaults(Notification.DEFAULT_SOUND);//设置提示音
                builder.setDefaults(Notification.DEFAULT_LIGHTS);//设置闪关灯
                builder.setDefaults(Notification.DEFAULT_VIBRATE);//设置震动
                builder.setDefaults(Notification.DEFAULT_ALL);
                builder.setAutoCancel(true);
                manager.notify(1,builder.build());
                Intent intent=new Intent(getActivity(), GuZhen.class);
                startActivity(intent);

            }
        });
    }

    private void sendNotification() {
//        manager= (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
//        Notification.Builder builder = new Notification.Builder(getActivity());
//        builder.setSmallIcon(R.drawable.clx);//设置小图标
//        builder.setTicker("Hello guzhen!!");//设置手机状态的提示
//        builder.setWhen(System.currentTimeMillis());//设置系统时间
//        builder.setContentTitle("通知栏通知");//设置标题
//        builder.setContentText("-------------古镇下雨正在运行-------------");
////        builder.setContentIntent(PendingIntent.readPendingIntentOrNullFromParcel(null));
//        builder.setDefaults(Notification.DEFAULT_SOUND);//设置提示音
//        builder.setDefaults(Notification.DEFAULT_LIGHTS);//设置闪关灯
//        builder.setDefaults(Notification.DEFAULT_VIBRATE);//设置震动
//        builder.setDefaults(Notification.DEFAULT_ALL);
//        builder.setAutoCancel(true);
//        manager.notify(1,builder.build());
//        Notification notification = builder.build();//4.1以上
//        manager.notify(notification_ID,notification);
    }
}
