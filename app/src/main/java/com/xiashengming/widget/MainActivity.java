package com.xiashengming.widget;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.xiashengming.widget.looppagerecyclerview.LooperLayoutManager;
import com.xiashengming.widget.looppagerecyclerview.MyAdapter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Integer> mList = new ArrayList<>();
    private RecyclerView recyclerView;
    private Handler handler = new Handler();
    private Runnable autoScrollRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setAdapter(new MyAdapter());
        LooperLayoutManager layoutManager = new LooperLayoutManager();
        layoutManager.setLooperEnable(true);
        recyclerView.setLayoutManager(layoutManager);


        // 设置自动滚动的逻辑
        autoScrollRunnable = new Runnable() {
            @Override
            public void run() {
                // 每次向左滚动一定的距离
                recyclerView.smoothScrollBy(10, 0);
                // 继续执行下一个滚动
                handler.postDelayed(this, 50); // 每 50ms 执行一次
            }
        };
        handler.post(autoScrollRunnable); // 启动滚动
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(autoScrollRunnable); // 停止滚动
    }
}
