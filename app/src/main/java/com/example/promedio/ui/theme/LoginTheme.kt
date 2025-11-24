package com.example.promedio.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
    onNavigateIngenieria: () -> Unit,     // Navegación hacia ingeniería
    onNavigateOdontologia: () -> Unit,    // Navegación hacia odontología
    viewModel: LoginViewModel             // ViewModel que maneja Room + API
) {
    // Observa la lista de datos locales (Room)
    val logins by viewModel.logins.collectAsState()

    // Observa los usuarios remotos (API)
    val students by viewModel.usuariosRemotos.collectAsState()

    // Controla si se muestran los datos
    var mostrarDatos by remember { mutableStateOf(false) }

    // Inputs del usuario
    var name by remember { mutableStateOf(TextFieldValue("")) }
    var email by remember { mutableStateOf(TextFieldValue("")) }

    // Checkbox para elegir carrera
    var selectedCareer by remember { mutableStateOf("") }

    // Validación del correo
    var emailError by remember { mutableStateOf(false) }

    // CONTENEDOR PRINCIPAL DE LA UI
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .padding(24.dp)
            .verticalScroll(rememberScrollState()), // permite scroll en la pantalla
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // TÍTULO
        Text(
            text = "Inicio de Sesión",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // CAMPO DE NOMBRE
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nombre") },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        // CAMPO DE CORREO CON VALIDACIÓN
        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                emailError = !email.text.endsWith("@gmail.com") // Validación
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

        // CHECKBOX — Ingeniería
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = selectedCareer == "ingenieria",
                onCheckedChange = { selectedCareer = "ingenieria" }
            )
            Text("Ingeniería en Informática")
        }

        // CHECKBOX — Odontología
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

        // BOTÓN PARA CONTINUAR (GUARDA EN ROOM + NAVEGA)
        Button(
            onClick = {
                val nuevoLogin = Login(
                    nombre = name.text,
                    correo = email.text
                )

                // Guarda el usuario localmente en Room
                viewModel.agregarUsuario(nuevoLogin)

                // Navega según carrera
                when (selectedCareer) {
                    "ingenieria" -> onNavigateIngenieria()
                    "odontologia" -> onNavigateOdontologia()
                }
            },
            enabled =
                name.text.isNotBlank() &&
                        email.text.isNotBlank() &&
                        !emailError &&
                        selectedCareer.isNotEmpty(),
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .padding(bottom = 16.dp)
        ) {
            Text("Continuar")
        }

        // BOTÓN PARA MOSTRAR/OCULTAR DATOS GUARDADOS Y API
        Button(
            onClick = {
                mostrarDatos = !mostrarDatos
                if (mostrarDatos) {
                    // Cuando se muestra, carga los datos de la API
                    viewModel.cargarUsuariosRemotos()
                }
            },
            modifier = Modifier.fillMaxWidth(0.7f)
        ) {
            Text(if (mostrarDatos) "Ocultar datos" else "Mostrar datos guardados")
        }

        // SECCIÓN DE DATOS (SOLO SI mostrarDatos = true)
        if (mostrarDatos) {

            Spacer(modifier = Modifier.height(16.dp))

            // ------------------------------
            //      DATOS LOCALES (ROOM)
            // ------------------------------
            Text(
                "Datos guardados localmente (Room):",
                style = MaterialTheme.typography.titleMedium
            )

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

            // ------------------------------
            //         DATOS API
            // ------------------------------
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



