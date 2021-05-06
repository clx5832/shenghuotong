package com.example.shenghuotong.kudichaxun;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shenghuotong.R;

import java.util.List;

public class KuaidiAdapter extends RecyclerView.Adapter<KuaidiAdapter.MyHolder> {
    private Context context;
    private List<ResultBody.ResultBean.ListBean> list;

    public KuaidiAdapter(Context context, List<ResultBody.ResultBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        //创建ViewHolder
        View inflater = LayoutInflater.from(context).inflate(R.layout.item_activity, parent, false);
        MyHolder myHolder = new MyHolder(inflater);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull KuaidiAdapter.MyHolder holder, int position) {
        System.out.println(holder);
        System.out.println(list.get(position).getTime());
        System.out.println(list.get(position).getStatus());
        holder.item_left.setText(list.get(position).getTime());
        //设置状态
        holder.item_right.setText(list.get(position).getStatus());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {

        private TextView item_left;
        private TextView item_right;

        public MyHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            item_left = itemView.findViewById(R.id.item_left);
            item_right = itemView.findViewById(R.id.item_right);
        }
    }
}
