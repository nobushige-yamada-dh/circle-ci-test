package com.example.circlecitest.ui

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer


fun <T : Any> AppCompatActivity.observe(
        liveData: MutableLiveData<T>,
        callback: (newValue: T) -> Unit
) {
    liveData.observe(this, Observer {
        it?.also {
            liveData.value = null
            callback(it)
        }
    })
}
