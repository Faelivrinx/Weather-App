package com.mypieceofcode.data.db.entity

import androidx.room.Embedded
import com.mypieceofcode.data.db.entity.Condition

data class Day(val avgvisKm: Int = 0,
               val uv: Double = 0.0,

               val avgtempF: Double = 0.0,

               val avgtempC: Double = 0.0,

               val maxtempC: Double = 0.0,

               val maxtempF: Double = 0.0,

               val mintempC: Double = 0.0,

               val avgvisMiles: Int = 0,

               val mintempF: Double = 0.0,

               val totalprecipIn: Double = 0.0,

               val avghumidity: Int = 0,

               @Embedded(prefix = "condition_")
               val condition: Condition,

               val maxwindKph: Double = 0.0,

               val maxwindMph: Double = 0.0,

               val totalprecipMm: Double = 0.0)