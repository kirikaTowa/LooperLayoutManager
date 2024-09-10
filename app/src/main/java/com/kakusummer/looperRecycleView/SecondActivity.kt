package com.kakusummer.looperRecycleView

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import com.kakusummer.LooperRecycleView.R
import com.kakusummer.looperRecycleView.autoPollRecyclerView.AutoPollAdapter
import com.kakusummer.looperRecycleView.autoPollRecyclerView.AutoPollRecyclerView


class SecondActivity : AppCompatActivity() {

    private var mRecyclerView: AutoPollRecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        mRecyclerView = findViewById<View>(R.id.recycleView) as AutoPollRecyclerView
        val list: ArrayList<String> = ArrayList<String>()

        var i = 0
        while (i < 5) {
            list.add(" Item: " + ++i)
        }

        val adapter = AutoPollAdapter(list)
        mRecyclerView?.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
        mRecyclerView?.setAdapter(adapter)
        mRecyclerView?.start()

    }

    override fun onDestroy() {
        super.onDestroy()

        if (null != mRecyclerView) {
            mRecyclerView?.stop()
        }
    }
}