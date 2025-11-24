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
import com.example.promedio.data.api.repository.LoginRepositoryApi;
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Habilita diseño full screen moderno

        // ===================================
        //         BASE DE DATOS (ROOM)
        // ===================================

        // Obtiene instancia de la base de datos Room
        val db = AppDatabase.getDatabase(application)

        // Repositorios para cada tabla de la base de datos
        val ingenieriaRepository = IngenieriaRepository(db.ingenieriaDao())
        val odontologiaRepository = OdontologiaRepository(db.odontologiaDao())

        // Repositorio local (Room) para Login
        val loginRepository = LoginRepository(db.loginDao())

        // ===================================
        //         API (RETROFIT)
        // ===================================

        // Repositorio remoto que usa Retrofit
        val loginRepositoryApi = LoginRepositoryApi()


        // ===================================
        //         VIEWMODELS (MVVM)
        // ===================================

        // ViewModel de Ingeniería con su Factory y su repositorio Room
        val ingenieriaViewModel = ViewModelProvider(
            this,
            IngenieriaViewModelFactory(ingenieriaRepository)
        )[IngenieriaViewModel::class.java]

        // ViewModel de Odontología con su Factory
        val odontologiaViewModel = ViewModelProvider(
            this,
            OdontologiaViewModelFactory(odontologiaRepository)
        )[OdontologiaViewModel::class.java]

        // ViewModel del Login con repositorio local (Room) + API (Retrofit)
        val loginViewModel = ViewModelProvider(
            this,
            LoginViewModelFactory(loginRepository, loginRepositoryApi)
        )[LoginViewModel::class.java]


        // ===================================
        //             UI + NAVIGATION
        // ===================================

        setContent {
            val navController = rememberNavController()

            // Define las pantallas de la app
            NavHost(navController = navController, startDestination = "login") {

                // Pantalla principal de login
                composable("login") {
                    LoginScreen(
                        onNavigateIngenieria = { navController.navigate("ingenieria") },
                        onNavigateOdontologia = { navController.navigate("odontologia") },
                        viewModel = loginViewModel
                    )
                }

                // Pantalla de Ingeniería
                composable("ingenieria") {
                    IngenieriaTheme(
                        viewModel = ingenieriaViewModel,
                        onBackToLogin = { navController.navigate("login") }
                    )
                }

                // Pantalla de Odontología
                composable("odontologia") {
                    OdontologiaTheme(
                        viewModel = odontologiaViewModel,
                        onBackToLogin = { navController.navigate("login") }
                    )
                }
            }
        }
    }
}



