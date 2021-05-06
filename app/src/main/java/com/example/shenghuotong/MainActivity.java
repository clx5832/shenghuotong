package com.example.shenghuotong;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.bumptech.glide.Glide;
import com.example.shenghuotong.data.data;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.ed_name)
    EditText edName;
    @BindView(R.id.ll_name)
    LinearLayout llName;
    @BindView(R.id.tv_passd)
    TextView tvPassd;
    @BindView(R.id.ed_passd)
    EditText edPassd;
    @BindView(R.id.ll_passd)
    LinearLayout llPassd;
    @BindView(R.id.denglu)
    Button denglu;
    @BindView(R.id.zhuce)
    Button zhuce;
    @BindView(R.id.ll_denglu)
    LinearLayout llDenglu;
    @BindView(R.id.cb_save)
    CheckBox cbSave;

    private MyHelper helper;
    private SharedPreferences sp;
    private MyHelper helper1;
    private SQLiteDatabase db;
    private SoundPool dp2;
    private int soundId;
    private int soundId2;
    private NotificationManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper = new MyHelper(this);
        ButterKnife.bind(this);
        initImage();
        sendNotification();
        sp = getSharedPreferences("save", MODE_PRIVATE);
        Boolean ok = sp.getBoolean("is save", false);
        cbSave.setChecked(ok);
        dp2=new SoundPool(1, AudioManager.STREAM_MUSIC,0);
        soundId=dp2.load(this,R.raw.click,1);
        soundId2=dp2.load(this,R.raw.beep,1);
        if (ok) {
            edName.setText(sp.getString("name", ""));
            edPassd.setText(sp.getString("password", ""));
        }
        helper1 = new MyHelper(this);
    }

    private void sendNotification() {
        Context context;
        manager  = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplication());
        builder.setSmallIcon(R.drawable.clx);//设置小图标
        builder.setTicker("Hello guzhen!!");//设置手机状态的提示
        builder.setWhen(System.currentTimeMillis());//设置系统时间
        builder.setContentTitle("通知栏通知");//设置标题
        builder.setContentText("-------------生活通正在运行-------------");
//        builder.setContentIntent(PendingIntent.readPendingIntentOrNullFromParcel(null));
        builder.setDefaults(Notification.DEFAULT_SOUND);//设置提示音
        builder.setDefaults(Notification.DEFAULT_LIGHTS);//设置闪关灯
        builder.setDefaults(Notification.DEFAULT_VIBRATE);//设置震动
        builder.setDefaults(Notification.DEFAULT_ALL);
        builder.setAutoCancel(true);
        manager.notify(1,builder.build());
    }

    private void initImage() {
        String url = "https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1606576796&di=87fb8a3ebd6190449cbadb079875b38b&src=http://d.hiphotos.baidu.com/zhidao/pic/item/8435e5dde71190ef5529c1e8cf1b9d16fdfa6032.jpg";
        Glide.with(MainActivity.this).load(url).into(image);
    }

    @OnClick(R.id.denglu)
    public void denglu(View view) {
//        System.out.println("44444444444444444444444444444444444444444444444444444444444444444444");
//        if (edName.getText().toString().isEmpty() || edPassd.getText().toString().isEmpty()) {
//            Toast.makeText(KuaiActivitydd.this, "账号密码不能为空！！！！", Toast.LENGTH_SHORT).show();
//        } else if ((edName.getText().toString().equals("c")) && (edPassd.getText().toString().equals("1"))) {
//                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
//                Intent intent = new Intent(KuaiActivitydd.this, SuccessDenglu.class);
//                Toast.makeText(KuaiActivitydd.this, "登录成功！！！！", Toast.LENGTH_SHORT).show();
//                startActivity(intent);
//            }
//        Boolean cb = cbSave.isChecked();
//        SharedPreferences.Editor editor = sp.edit();
//        if (cb) {
//            editor.putBoolean("is save", cb);
//            editor.putString("name", edName.getText().toString().trim());
//            editor.putString("password", edPassd.getText().toString().trim());
//        } else {
//            clear();
//        }
        dp2.play(soundId,1,1,1,1,1);

        String name = edName.getText().toString().trim();
        String password = edPassd.getText().toString().trim();
        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(password)) {
            ArrayList<data> data = helper1.getAllData();
            boolean match = false;
            for (int i = 0; i < data.size(); i++) {
                data user = data.get(i);
                if (name.equals(user.getName()) && password.equals(user.getPossword())) {
                    match = true;
                    break;
                } else {
                    match = false;
                }
            }
            if (match) {
                Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, SuccessDenglu.class);
                startActivity(intent);
                finish();//销毁此Activity
            } else {
                Toast.makeText(this, "用户名或密码不正确，请重新输入", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "请输入你的用户名或密码", Toast.LENGTH_SHORT).show();
        }
        }

    @OnClick(R.id.zhuce)
    public void zhuce(View view) {
        dp2.play(soundId2,1,1,1,1,1);
        String name;
        String pssdword;
        SQLiteDatabase db;
        ContentValues values;
        switch (view.getId()) {
            case R.id.zhuce:
                name = edName.getText().toString().trim();
                pssdword = edPassd.getText().toString().trim();
                db = helper.getWritableDatabase();
                values = new ContentValues();
                values.put("name", name);
                values.put("password", pssdword);
                db.insert("info", null, values);
                Toast.makeText(MainActivity.this, "恭喜你注册成功！", Toast.LENGTH_SHORT).show();
                db.close();
                clear();
                Intent intent = new Intent(MainActivity.this, ZhuCeAcitivity.class);
                startActivity(intent);
                break;


        }
    }

    public void clear() {
        edName.setText("");
        edPassd.setText("");
    }
}


