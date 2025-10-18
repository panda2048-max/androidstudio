package com.example.promedio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import com.example.promedio.model.LoginDatabase
import com.example.promedio.repository.LoginRepository
import com.example.promedio.ui.theme.Logins
import com.example.promedio.viewmodel.LoginViewModel
import com.example.promedio.viewmodel.LoginViewModelFactory
import com.example.promedio.ui.theme.PromedioTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val dao = LoginDatabase.getDatabase(application).loginDao()
        val repository = LoginRepository(dao)
        val factory = LoginViewModelFactory(repository)
        val viewModel = ViewModelProvider(this, factory)[LoginViewModel::class.java]
        setContent {
            PromedioTheme {
                Logins(viewModel)
            }
        }

    }
}



