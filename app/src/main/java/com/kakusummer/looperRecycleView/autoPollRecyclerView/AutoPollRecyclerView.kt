package com.kakusummer.looperRecycleView.autoPollRecyclerView

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.annotation.Nullable
import androidx.recyclerview.widget.RecyclerView
import java.lang.ref.WeakReference


class AutoPollRecyclerView(context: Context?, @Nullable attrs: AttributeSet?) :
    RecyclerView(context!!, attrs) {
    var autoPollTask: AutoPollTask = AutoPollTask(this)
    private var running = false //表示是否正在自动轮询
    private var canRun = false //表示是否可以自动轮询

    class AutoPollTask(reference: AutoPollRecyclerView) : Runnable {
        private val mReference = WeakReference(reference)

        override fun run() {
            val recyclerView = mReference.get()
            if (recyclerView != null && recyclerView.running && recyclerView.canRun) {
                recyclerView.scrollBy(2, 2)
                recyclerView.postDelayed(recyclerView.autoPollTask, TIME_AUTO_POLL)
            }
        }
    }

    //开启:如果正在运行,先停止->再开启
    fun start() {
        if (running) stop()
        canRun = true
        running = true
        postDelayed(autoPollTask, TIME_AUTO_POLL)
    }

    fun stop() {
        running = false
        removeCallbacks(autoPollTask)
    }

    override fun onTouchEvent(e: MotionEvent): Boolean {
        when (e.action) {
            MotionEvent.ACTION_DOWN -> if (running) stop()
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL, MotionEvent.ACTION_OUTSIDE -> if (canRun) start()
        }
        return super.onTouchEvent(e)
    }

    companion object {
        private const val TIME_AUTO_POLL: Long = 16
    }
}