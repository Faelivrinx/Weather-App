package com.mypieceofcode.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mypieceofcode.data.db.entity.CURRENT_WHEATHER_ID
import com.mypieceofcode.data.db.entity.CurrentWeatherEntry
import com.mypieceofcode.data.db.unlocalized.current.ImperialCurrentWeatherEntry
import com.mypieceofcode.data.db.unlocalized.current.MetricCurrentWeatherEntry

@Dao
interface CurrentWeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(weatherEntry: CurrentWeatherEntry)

    @Query("select * from current_weather where id =$CURRENT_WHEATHER_ID")
    fun getWeatherMetric() : LiveData<MetricCurrentWeatherEntry>

    @Query("select * from current_weather where id =$CURRENT_WHEATHER_ID")
    fun getWeatherImperial() : LiveData<ImperialCurrentWeatherEntry>
}