package com.example.promedio.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.promedio.viewmodel.OdontologiaViewModel
import com.example.promedio.model.modelOdontologia

@Composable
fun OdontologiaTheme(viewModel: OdontologiaViewModel, onBackToLogin: () -> Unit) {
    val nota_1 by viewModel.nota_1.collectAsState()
    val nota_2 by viewModel.nota_2.collectAsState()
    val nota_3 by viewModel.nota_3.collectAsState()
    var notaFinal by remember { mutableStateOf<Double?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Técnico en Odontología",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        fun validarDecimal(input: String) = input.matches(Regex("^\\d*\\.?\\d*\$"))

        OutlinedTextField(
            value = nota_1,
            onValueChange = { if (validarDecimal(it)) viewModel.nota_1.value = it },
            label = { Text("Ingrese su primera nota") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            )
        )

        OutlinedTextField(
            value = nota_2,
            onValueChange = { if (validarDecimal(it)) viewModel.nota_2.value = it },
            label = { Text("Ingrese su segunda nota") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            )
        )

        OutlinedTextField(
            value = nota_3,
            onValueChange = { if (validarDecimal(it)) viewModel.nota_3.value = it },
            label = { Text("Ingrese su tercera nota") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if (nota_1.isNotBlank() && nota_2.isNotBlank() && nota_3.isNotBlank()) {
                val n1 = nota_1.toDouble()
                val n2 = nota_2.toDouble()
                val n3 = nota_3.toDouble()
                notaFinal = n1 * 0.4 + n2 * 0.35 + n3 * 0.25
                viewModel.agregarNotas(modelOdontologia(nota_1 = n1, nota_2 = n2, nota_3 = n3))
                viewModel.nota_1.value = ""
                viewModel.nota_2.value = ""
                viewModel.nota_3.value = ""
            }
        }) {
            Text("Calcular nota final")
        }

        Spacer(modifier = Modifier.height(16.dp))

        notaFinal?.let {
            Text(
                "Nota final: %.2f".format(it),
                style = MaterialTheme.typography.headlineMedium
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Botón para volver al login
        Button(onClick = { onBackToLogin() }) {
            Text("Volver al Login")
        }
    }
}

