package com.example.circlecitest.ui

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor

class MainThreadExecutor : Executor {

    private val mainThreadHandler = Handler(Looper.getMainLooper())

    override fun execute(proc: Runnable) {
        mainThreadHandler.post(proc)
    }
}