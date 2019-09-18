package com.example.circlecitest.data.source.local

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.circlecitest.MyApplication
import com.example.circlecitest.data.GameApp

@Database(entities = arrayOf(GameApp::class), version = 1, exportSchema = false)
abstract class MainDatabase: RoomDatabase() {

    abstract fun gameAppsDao(): GameAppsDao

    companion object {

        @Volatile private var INSTANCE: MainDatabase? = null

        fun getInstance(app: MyApplication) =
            INSTANCE ?: synchronized(MainDatabase::class.java) {
                INSTANCE ?:
                    Room.databaseBuilder(app, MainDatabase::class.java, "main.db")
                        .build().also {
                            INSTANCE = it
                        }
            }
    }
}
