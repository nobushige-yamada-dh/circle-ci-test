package com.example.circlecitest.data.source

import java.util.concurrent.Executor
import java.util.concurrent.Executors

class AppExecutors(
        val uiExecutor: Executor
) {

    val diskIoExecutor = Executors.newSingleThreadExecutor()
}
