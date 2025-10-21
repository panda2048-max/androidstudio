package com.example.promedio.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.style.TextAlign


// creacion de la parte visual
@Composable
fun LoginScreen(
    onNavigateIngenieria: () -> Unit,
    onNavigateOdontologia: () -> Unit
) {
    var name by remember { mutableStateOf(TextFieldValue("")) }
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var selectedCareer by remember { mutableStateOf("") }

    // Column para que se cree esta apartado de manera de columnas hacia abajo
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // testo del inicio
        Text(
            text = "Inicio de Sesión",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 24.dp),
            textAlign = TextAlign.Center
        )

        // Campo de nombre
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nombre") },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        // Campo de correo
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Correo electrónico") },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp)
        )

        // Carrera: Ingeniería
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 8.dp)
        ) {
            Checkbox(
                checked = selectedCareer == "ingenieria",
                onCheckedChange = { selectedCareer = "ingenieria" }
            )
            Text("Ingeniería en Informática")
        }

        // Carrera: Odontología
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 24.dp)
        ) {
            Checkbox(
                checked = selectedCareer == "odontologia",
                onCheckedChange = { selectedCareer = "odontologia" }
            )
            Text("Técnico en Odontología")
        }

        // Botón Continuar
        Button(
            onClick = {
                when (selectedCareer) {
                    "ingenieria" -> onNavigateIngenieria()
                    "odontologia" -> onNavigateOdontologia()
                }
            },
            enabled = name.text.isNotBlank() &&
                    email.text.isNotBlank() &&
                    selectedCareer.isNotEmpty(),
            modifier = Modifier.fillMaxWidth(0.6f)
        ) {
            Text("Continuar")
        }
    }
}
