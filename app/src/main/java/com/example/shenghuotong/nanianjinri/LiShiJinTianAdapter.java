package com.example.shenghuotong.nanianjinri;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.shenghuotong.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LiShiJinTianAdapter extends RecyclerView.Adapter<LiShiJinTianAdapter.MyViewHolder> {

    private Context context;
    private List<LiShiJinTian.ResultBean>list;//这个需要展示在列表中的数据

    //设配器的构造方法,传入数据
    public LiShiJinTianAdapter(Context context,List<LiShiJinTian.ResultBean>list){
        this.context=context;
        this.list=list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //为子项目布局创建视图
        View inflater= LayoutInflater.from(context).inflate(R.layout.home_items,parent,false);
        MyViewHolder myViewHolder=new MyViewHolder(inflater);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        //绑定数据到子项布局视图
        holder.itemDate.setText(list.get(position).getDate());//设置日期
        holder.itemTitle.setText(list.get(position).getTitle());//设置标题
        holder.e_id=list.get(position).getE_id();//设置事件id
    }

    @Override
    public int getItemCount() {
        //获得子项的数量
        return list.size();
    }

    //这里为内部类,用于绑定控件用
    class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_date)
        TextView itemDate;
        @BindView(R.id.item_title)
        TextView itemTitle;

        String e_id;//事件id

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

            //设置列表点击事件，点击列表，跳转到列表详情界面，同时需要将id床底过去
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, DetailActivity.class);//跳转到详情界面
                    Bundle bundle=new Bundle();//捆绑
                    bundle.putString("e_id",e_id);
                    intent.putExtras(bundle);
                    context.startActivity(intent);//通过上下文跳转

                }
            });
        }
    }
}
