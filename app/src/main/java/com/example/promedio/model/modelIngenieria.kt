package com.example.promedio.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "ingenierias")
data class modelIngenieria(
    @PrimaryKey(autoGenerate = true) val id : Int = 0,
    var nota_1 : Double,
    var nota_2: Double,
    var nota_3: Double
)
