package com.example.circlecitest.data.source.local

import com.example.circlecitest.data.GameApp

interface LocalDataSource {

    fun getAllGameApps(callback: (List<GameApp>) -> Unit)
}
