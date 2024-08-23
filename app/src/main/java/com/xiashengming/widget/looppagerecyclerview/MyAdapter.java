package com.xiashengming.widget.looppagerecyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.xiashengming.widget.R;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter {
    ArrayList<String> data = new ArrayList<String>();

    public MyAdapter() {
        initData();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ((MyViewHolder) holder).btn.setText(data.get(position));

        Log.d("yeTest", "onBindViewHolder: "+position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        Button btn;
        public MyViewHolder(View itemView) {
            super(itemView);
            btn = itemView.findViewById(R.id.btn);
        }
    }

    private void initData(){
        String[] str = new String[]{"抽奖1","抽奖2","抽奖3","抽奖4","抽奖5","抽奖6"};
        for (int i = 0; i < 6; i++) {
            data.add(i, str[i % 6]);

        }
    }
}
