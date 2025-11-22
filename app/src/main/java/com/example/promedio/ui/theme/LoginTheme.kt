package com.example.promedio.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.promedio.model.Login

import com.example.promedio.viewmodel.LoginViewModel

// creacion de la parte visual
@Composable
fun LoginScreen(
    onNavigateIngenieria: () -> Unit,
    onNavigateOdontologia: () -> Unit,
    viewModel: LoginViewModel
) {
    val logins by viewModel.logins.collectAsState()
    val students by viewModel.usuariosRemotos.collectAsState() // API

    var mostrarDatos by remember { mutableStateOf(false) }

    var name by remember { mutableStateOf(TextFieldValue("")) }
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var selectedCareer by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Inicio de Sesión",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nombre") },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                emailError = !email.text.endsWith("@gmail.com")
            },
            label = { Text("Correo electrónico") },
            singleLine = true,
            isError = emailError,
            supportingText = {
                if (emailError) Text("El correo debe terminar en @gmail.com")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp)
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = selectedCareer == "ingenieria",
                onCheckedChange = { selectedCareer = "ingenieria" }
            )
            Text("Ingeniería en Informática")
        }

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

        Button(
            onClick = {
                val nuevoLogin = Login(
                    nombre = name.text,
                    correo = email.text
                )
                viewModel.agregarUsuario(nuevoLogin)

                when (selectedCareer) {
                    "ingenieria" -> onNavigateIngenieria()
                    "odontologia" -> onNavigateOdontologia()
                }
            },
            enabled = name.text.isNotBlank() &&
                    email.text.isNotBlank() &&
                    !emailError &&
                    selectedCareer.isNotEmpty(),
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .padding(bottom = 16.dp)
        ) {
            Text("Continuar")
        }

        // BOTÓN PARA VER REGISTROS + API
        Button(
            onClick = {
                mostrarDatos = !mostrarDatos
                if (mostrarDatos) {
                    viewModel.cargarUsuariosRemotos() // ← Cargar datos API
                }
            },
            modifier = Modifier.fillMaxWidth(0.7f)
        ) {
            Text(if (mostrarDatos) "Ocultar datos" else "Mostrar datos guardados")
        }

        if (mostrarDatos) {
            Spacer(modifier = Modifier.height(16.dp))

            // --- DATOS ROOM ---
            Text("Datos guardados localmente (Room):", style = MaterialTheme.typography.titleMedium)

            if (logins.isEmpty()) {
                Text("No hay registros guardados")
            } else {
                Column(modifier = Modifier.fillMaxWidth()) {
                    logins.forEach { login ->
                        Text("Nombre: ${login.nombre}")
                        Text("Correo: ${login.correo}")
                        Divider()
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // --- DATOS API ---
            Text("Datos desde la API:", style = MaterialTheme.typography.titleMedium)

            if (students.isEmpty()) {
                Text("Cargando datos de la API...")
            } else {
                Column(modifier = Modifier.fillMaxWidth()) {
                    students.forEach { estudiante ->
                        Text("ID: ${estudiante.id}")
                        Text("Nombre: ${estudiante.name}")
                        Text("Email: ${estudiante.email}")
                        Divider()
                    }
                }
            }
        }
    }
}


