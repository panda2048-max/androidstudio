package com.example.promedio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.promedio.model.AppDatabase

import com.example.promedio.repository.*
import com.example.promedio.ui.theme.*
import com.example.promedio.viewmodel.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val db = AppDatabase.getDatabase(application)

        val ingenieriaRepository = IngenieriaRepository(db.ingenieriaDao())
        val odontologiaRepository = OdontologiaRepository(db.odontologiaDao())

        val ingenieriaViewModel = ViewModelProvider(this, IngenieriaViewModelFactory(ingenieriaRepository))[IngenieriaViewModel::class.java]
        val odontologiaViewModel = ViewModelProvider(this, OdontologiaViewModelFactory(odontologiaRepository))[OdontologiaViewModel::class.java]

        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "login") {
                composable("login") {
                    LoginScreen(
                        onNavigateIngenieria = { navController.navigate("ingenieria") },
                        onNavigateOdontologia = { navController.navigate("odontologia") }
                    )
                }
                composable("ingenieria") { IngenieriaTheme(viewModel = ingenieriaViewModel, onBackToLogin = { navController.navigate("login") }) }
                composable("odontologia") { OdontologiaTheme(viewModel = odontologiaViewModel, onBackToLogin = { navController.navigate("login") }) }
            }
        }
    }
}
