package com.example.promedio.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SeleccionCarrera(onSeleccion: (String) -> Unit) {
    var carreraSeleccionada by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Seleccione su carrera",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = carreraSeleccionada == "Ingenieria",
                onClick = { carreraSeleccionada = "Ingenieria" }
            )
            Text("Ingeniería en Informática", modifier = Modifier.padding(start = 8.dp))
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = carreraSeleccionada == "Odontologia",
                onClick = { carreraSeleccionada = "Odontologia" }
            )
            Text("Técnico en Odontología", modifier = Modifier.padding(start = 8.dp))
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            enabled = carreraSeleccionada.isNotBlank(),
            onClick = { onSeleccion(carreraSeleccionada) }
        ) {
            Text("Continuar")
        }
    }
}
