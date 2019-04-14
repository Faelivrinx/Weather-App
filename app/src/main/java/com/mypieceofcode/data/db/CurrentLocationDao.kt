package com.mypieceofcode.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mypieceofcode.data.db.entity.WEATHER_LOCATION_ID
import com.mypieceofcode.data.db.entity.WeatherLocation

@Dao
interface CurrentLocationDao {

    @Query("select * from weather_location where id=$WEATHER_LOCATION_ID")
    fun getCurrentLocation() : LiveData<WeatherLocation>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(location: WeatherLocation)
}