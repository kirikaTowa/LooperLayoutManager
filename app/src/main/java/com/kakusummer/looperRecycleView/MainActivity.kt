package com.kakusummer.looperRecycleView


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.kakusummer.LooperRecycleView.R
import com.kakusummer.looperRecycleView.looppagerecyclerview.LooperLayoutManager
import com.kakusummer.looperRecycleView.looppagerecyclerview.MyAdapter
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    private val mList = ArrayList<Int>()
    private var recyclerView: RecyclerView? = null
    private val handler = Handler()
    private var autoScrollRunnable: Runnable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerview)
        recyclerView?.setAdapter(MyAdapter())
        val layoutManager = LooperLayoutManager()
        layoutManager.setLooperEnable(true)
        recyclerView?.setLayoutManager(layoutManager)

        // 设置自动滚动的逻辑
        autoScrollRunnable = object : Runnable {
            override fun run() {
                // 每次向左滚动一定的距离
                recyclerView?.smoothScrollBy(10, 0)
                // 继续执行下一个滚动
                handler.postDelayed(this, 50) // 每 50ms 执行一次
            }
        }
        handler.post(autoScrollRunnable as Runnable) // 启动滚动

        initListener()
    }

    private fun initListener(){
        findViewById<TextView>(R.id.tv_two).setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        autoScrollRunnable?.let { handler.removeCallbacks(it) } // 停止滚动
    }
}