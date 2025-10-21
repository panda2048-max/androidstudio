package com.example.promedio.model

import androidx.room.Entity
import androidx.room.PrimaryKey

// creacion de la clase y variables a ocupar
// creacion de la tabla de la BD que ocuparemos de manera local con room
@Entity(tableName = "logins")
data class Login(
    @PrimaryKey(autoGenerate = true) val id : Int = 0,
    var nombre : String,
    var correo : String
)