package com.example.shenghuotong.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shenghuotong.R;
import com.example.shenghuotong.WeatherData.WeatherData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.MyViewHolder> {

    private Context context;
    private List<WeatherData.ResultBean.FutureBean> list;//这个需要展示在列表中的数据

    public WeatherAdapter(Context context, List<WeatherData.ResultBean.FutureBean> list) {
        this.context = context;
        this.list = list;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.future_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(inflate);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.date2.setText(list.get(position).getDate());
        holder.temperature.setText(list.get(position).getTemperature());
        holder.weather.setText(list.get(position).getWeather());
        holder.direct.setText(list.get(position).getDirect());
        holder.day.setText(list.get(position).getWid().getDay());
        holder.night.setText(list.get(position).getWid().getNight());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {


//        @BindView(R.id.date2)
//        TextView date2;
//        @BindView(R.id.temperature)
//        TextView temperature;
//        @BindView(R.id.weather)
//        TextView weather;
//        @BindView(R.id.direct)
//        TextView direct;
//        @BindView(R.id.day)
//        TextView day;
//        @BindView(R.id.night)
//        TextView night;

        private TextView date2;
        private TextView temperature;
        private TextView weather;
        private TextView direct;
        private TextView night;
        private TextView day;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
//            ButterKnife.bind(this, itemView);
            date2=itemView.findViewById(R.id.date2);
            temperature=itemView.findViewById(R.id.temperature);
            weather=itemView.findViewById(R.id.weather);
            direct=itemView.findViewById(R.id.direct);
            day=itemView.findViewById(R.id.day);
            night=itemView.findViewById(R.id.night);
        }
    }
}
