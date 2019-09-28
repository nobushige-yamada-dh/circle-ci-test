package com.example.circlecitest.data.source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.circlecitest.data.GameApp

@Dao
interface GameAppsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertIfNotExists(gameApp: GameApp): Long

    @Query("SELECT * FROM game_apps")
    fun getAll(): List<GameApp>

    @Query("SELECT * FROM game_apps WHERE application_id = :applicationId")
    fun getByApplicationId(applicationId: String): List<GameApp>

    @Update
    fun update(gameApp: GameApp): Int

    @Delete
    fun delete(gameApp: GameApp): Int
}
