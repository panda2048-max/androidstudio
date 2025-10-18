package com.example.promedio.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.promedio.model.Login
import com.example.promedio.viewmodel.LoginViewModel

@Composable
fun Logins(viewModel: LoginViewModel) {
    val logins by viewModel.logins.collectAsState()
    val nombre by viewModel.nombre.collectAsState()
    val correo by viewModel.correo.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Inicio de Sesión",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = nombre,
            onValueChange = { viewModel.nombre.value = it },
            label = { Text("Nombre") }
        )

        OutlinedTextField(
            value = correo,
            onValueChange = { viewModel.correo.value = it },
            label = { Text("Correo") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if (nombre.isNotBlank() && correo.isNotBlank()) {
                viewModel.agregarUsuario(Login(nombre = nombre, correo = correo))
                viewModel.nombre.value = ""
                viewModel.correo.value = ""
            }
        }) {
            Text("Agregar Usuario")
        }

        Spacer(modifier = Modifier.height(24.dp))

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(logins) { usuario ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = usuario.nombre, modifier = Modifier.weight(1f))
                        Text(text = usuario.correo, modifier = Modifier.weight(1f))
                    }
                    Divider()
                }
            }
        }
    }
}
