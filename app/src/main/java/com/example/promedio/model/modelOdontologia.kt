package com.example.promedio.model

import androidx.room.Entity
import androidx.room.PrimaryKey


// creacion de la clase y variables a ocupar
// creacion de la tabla de la BD que ocuparemos de manera local con room
@Entity(tableName = "odontologias")
data class modelOdontologia(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var nota_1: Double,
    var nota_2: Double,
    var nota_3: Double
)
