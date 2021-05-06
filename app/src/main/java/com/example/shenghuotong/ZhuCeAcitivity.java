package com.example.shenghuotong;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ZhuCeAcitivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.ed_zhuce_name)
    EditText edZhuceName;
    @BindView(R.id.ed_zhuce_passd)
    EditText edZhucePassd;
    @BindView(R.id.ll_zhuce)
    LinearLayout llZhuce;
    @BindView(R.id.chaxun)
    Button chaxun;
    @BindView(R.id.gengxin)
    Button gengxin;
    @BindView(R.id.shanchu)
    Button shanchu;
    @BindView(R.id.ll_zhuce_ll)
    LinearLayout llZhuceLl;
    @BindView(R.id.tv_zhuce_name)
    TextView tvZhuceName;
    @BindView(R.id.tv_zhuce_passd)
    TextView tvZhucePassd;

    private MyHelper helper;
    private   SQLiteDatabase db;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhuceactivity);
        ButterKnife.bind(this);
        chaxun.setOnClickListener(this);
        shanchu.setOnClickListener(this);
        gengxin.setOnClickListener(this);
        helper = new MyHelper(this);
    }

    @Override
    public void onClick(View v) {
        ContentValues values;
        switch (v.getId()) {
            case R.id.chaxun:
                db = helper.getReadableDatabase();
                Cursor cursor = db.query("info", null, null, null, null, null, null);
                if (cursor.getCount() == 0) {
                    clear1();
                    Toast.makeText(ZhuCeAcitivity.this, "没有注册任何的用户，请注册", Toast.LENGTH_SHORT).show();
                } else {
                    cursor.moveToFirst();
                    tvZhuceName.setText( "name:" + cursor.getString(cursor.getColumnIndex("name")) +"\n");
                    tvZhucePassd.setText("password:" + cursor.getString(cursor.getColumnIndex("password"))+"\n");
                }
                while (cursor.moveToNext()) {
                    tvZhuceName.append(  "name:" + cursor.getString(cursor.getColumnIndex("name")) + "\n");
                    tvZhucePassd.append( "password:" + cursor.getString(cursor.getColumnIndex("password"))+"\n");

                }
                cursor.close();
                db.close();
                Toast.makeText(ZhuCeAcitivity.this, "查询用户名成功！！！", Toast.LENGTH_SHORT).show();
                clear1();
                break;

            case R.id.gengxin:
                db = helper.getWritableDatabase();
                values = new ContentValues();
                values.put("name", edZhuceName.getText().toString().trim());
                values.put("password", edZhucePassd.getText().toString().trim());
                db.update("info", values, "name=?", new String[]{edZhuceName.getText().toString().trim()});
                db.update("info", values, "password=?", new String[]{edZhucePassd.getText().toString().trim()});
                db.close();
                Toast.makeText(ZhuCeAcitivity.this, "用户或密码更新成功！！！", Toast.LENGTH_SHORT).show();
                clear();
                break;

            case R.id.shanchu:
                db = helper.getWritableDatabase();
                db.delete("info", "name=?", new String[]{edZhuceName.getText().toString().trim()});
//                db.delete("info", "password=?", new String[]{edZhucePassd.getText().toString().trim()});
                db.close();
                Toast.makeText(ZhuCeAcitivity.this, "用户或密码删除成功！！！", Toast.LENGTH_SHORT).show();
                clear();
                break;
        }
    }

    public void clear() {
        edZhuceName.setText("");
        edZhucePassd.setText("");
        tvZhuceName.setText("");
        tvZhucePassd.setText("");
    }
    public void clear1(){
        edZhuceName.setText("");
        edZhucePassd.setText("");
    }

}
