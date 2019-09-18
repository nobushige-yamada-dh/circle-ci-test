package com.example.circlecitest.data.source.local

import androidx.room.*
import com.example.circlecitest.data.GameApp

@Dao
interface GameAppsDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(gameApp: GameApp): Long

    @Query("SELECT * FROM game_apps")
    fun getAll(): List<GameApp>

    @Update
    fun update(gameApp: GameApp): Int

    @Delete
    fun delete(gameApp: GameApp): Int
}
