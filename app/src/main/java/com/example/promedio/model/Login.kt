package com.example.promedio.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "logins")
data class Login(
    @PrimaryKey(autoGenerate = true) val id : Int = 0,
    var nombre : String,
    var correo : String
)