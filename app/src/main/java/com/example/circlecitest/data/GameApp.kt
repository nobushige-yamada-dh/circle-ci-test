package com.example.circlecitest.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
        tableName = "game_apps",
        indices = arrayOf(Index(value = ["application_id"], unique = true))
)
data class GameApp(
        @PrimaryKey(autoGenerate = true) val id: Long = 0,
        @ColumnInfo(name = "application_id") var applicationId: String,
        var name: String
)
