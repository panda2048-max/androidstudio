package com.example.promedio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.promedio.model.Login
import com.example.promedio.ui.theme.LoginTheme
import com.example.promedio.ui.theme.PromedioTheme
import com.example.promedio.viewmodel.LoginViewModel


class MainActivity : ComponentActivity() {

    private val viewModel = LoginViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PromedioTheme{
                LoginTheme(viewModel)
            }

        }
    }
}

