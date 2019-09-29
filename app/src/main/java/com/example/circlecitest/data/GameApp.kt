package com.example.circlecitest.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
        tableName = "game_apps",
        indices = arrayOf(
                Index(value = ["application_id"]),
                Index(value = ["application_id", "class_name"], unique = true)
        )
)
data class GameApp(
        @PrimaryKey(autoGenerate = true) var id: Long = 0,
        @ColumnInfo(name = "application_id") var applicationId: String,
        @ColumnInfo(name = "class_name") var className: String
)
