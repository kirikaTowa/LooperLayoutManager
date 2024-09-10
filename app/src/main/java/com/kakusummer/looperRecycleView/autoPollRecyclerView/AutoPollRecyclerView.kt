package com.kakusummer.looperRecycleView.autoPollRecyclerView

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.annotation.Nullable
import androidx.recyclerview.widget.RecyclerView
import com.kakusummer.LooperRecycleView.R
import java.lang.ref.WeakReference

//https://www.jianshu.com/p/16fe31f7058e
class AutoPollRecyclerView(context: Context?, @Nullable attrs: AttributeSet?) :
    RecyclerView(context!!, attrs) {
    var autoPollTask: AutoPollTask = AutoPollTask(this)
    private var running = false //表示是否正在自动轮询
    private var canRun = false //表示是否可以自动轮询

    init {
        if (context != null) {
            if (attrs != null) {
                init(context, attrs)
            }
        }
    }

    private fun init(context: Context, attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.AutoPollRecyclerView)

        scrollX =
            typedArray.getInt(R.styleable.AutoPollRecyclerView_scrollX, 2)
        scrollY =
            typedArray.getInt(R.styleable.AutoPollRecyclerView_scrollY, 2)


        typedArray.recycle()
    }


    class AutoPollTask(reference: AutoPollRecyclerView) : Runnable {
        private val mReference = WeakReference(reference)

        override fun run() {
            val recyclerView = mReference.get()
            if (recyclerView != null && recyclerView.running && recyclerView.canRun) {
                recyclerView.scrollBy(scrollX, scrollY)
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
        private var scrollX = 2
        private var scrollY = 2
    }

}