package com.example.circlecitest.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game_apps")
data class GameApp constructor(
        @PrimaryKey(autoGenerate = true) val id: Long = 0,
        var name: String
)
