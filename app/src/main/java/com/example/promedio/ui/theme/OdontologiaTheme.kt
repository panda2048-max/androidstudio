package com.example.promedio.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.promedio.viewmodel.OdontologiaViewModel
import com.example.promedio.model.modelOdontologia

// creacion de la parte visula de Odontologia
@Composable
fun OdontologiaTheme(viewModel: OdontologiaViewModel, onBackToLogin: () -> Unit) {

    val nota_1 by viewModel.nota_1.collectAsState()
    val nota_2 by viewModel.nota_2.collectAsState()
    val nota_3 by viewModel.nota_3.collectAsState()

    var notaFinal by remember { mutableStateOf<Double?>(null) }

    // Porcentajes iniciales
    var porc1 by remember { mutableStateOf(35) }
    var porc2 by remember { mutableStateOf(25) }
    var porc3 by remember { mutableStateOf(40) }

    val sumaPorcentajes = porc1 + porc2 + porc3

    // ============================================
    // VALIDACIONES
    // ============================================

    // Permite escribir mientras no haya más de 1 decimal
    val escribirRegex = Regex("^\\d{0,3}(\\.\\d{0,1})?$")

    fun validarEntrada(input: String): Boolean {
        return escribirRegex.matches(input)
    }

    // Validación final estricta (no pasar de 100)
    fun validarNotaFinal(input: String): Boolean {
        if (!input.matches(Regex("^\\d{1,3}(\\.\\d)?$"))) return false

        val valor = input.toDoubleOrNull() ?: return false

        return valor in 0.0..100.0
    }

    val notasValidas =
        validarNotaFinal(nota_1) &&
                validarNotaFinal(nota_2) &&
                validarNotaFinal(nota_3)

    // ============================================
    // UI
    // ============================================

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Tecnico en odontologia",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // ============================================
        // NOTA 1
        // ============================================
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {

            OutlinedTextField(
                value = nota_1,
                onValueChange = { newValue ->
                    if (validarEntrada(newValue)) {

                        // RESTRINGIR QUE NO SUPERE 100
                        if (newValue.toDoubleOrNull() ?: 0.0 <= 100.0) {
                            viewModel.nota_1.value = newValue
                        }
                    }
                },
                label = { Text("Nota 1") },
                singleLine = true,
                modifier = Modifier.weight(1f),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            Spacer(Modifier.width(8.dp))

            AjustarPorcentajeOdontologia(porc1) { porc1 = it }
        }

        // ============================================
        // NOTA 2
        // ============================================
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {

            OutlinedTextField(
                value = nota_2,
                onValueChange = { newValue ->
                    if (validarEntrada(newValue)) {
                        if (newValue.toDoubleOrNull() ?: 0.0 <= 100.0) {
                            viewModel.nota_2.value = newValue
                        }
                    }
                },
                label = { Text("Nota 2") },
                singleLine = true,
                modifier = Modifier.weight(1f),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            Spacer(Modifier.width(8.dp))

            AjustarPorcentajeOdontologia(porc2) { porc2 = it }
        }

        // ============================================
        // NOTA 3
        // ============================================
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {

            OutlinedTextField(
                value = nota_3,
                onValueChange = { newValue ->
                    if (validarEntrada(newValue)) {
                        if (newValue.toDoubleOrNull() ?: 0.0 <= 100.0) {
                            viewModel.nota_3.value = newValue
                        }
                    }
                },
                label = { Text("Nota 3") },
                singleLine = true,
                modifier = Modifier.weight(1f),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            Spacer(Modifier.width(8.dp))

            AjustarPorcentajeOdontologia(porc3) { porc3 = it }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // ADVERTENCIA SUMA PORCENTAJES
        if (sumaPorcentajes != 100) {
            Text(
                text = "Los porcentajes deben sumar 100% (actual: $sumaPorcentajes%)",
                color = Color.Red,
                modifier = Modifier.padding(8.dp)
            )
        }

        // ============================================
        // BOTÓN CALCULAR
        // ============================================
        Button(
            onClick = {
                val n1 = nota_1.toDouble()
                val n2 = nota_2.toDouble()
                val n3 = nota_3.toDouble()

                notaFinal =
                            n1 * (porc1 / 100.0) +
                            n2 * (porc2 / 100.0) +
                            n3 * (porc3 / 100.0)

                viewModel.agregarNotas(
                    modelOdontologia(
                        nota_1 = n1,
                        nota_2 = n2,
                        nota_3 = n3
                    )
                )

                viewModel.nota_1.value = ""
                viewModel.nota_2.value = ""
                viewModel.nota_3.value = ""
            },
            enabled = notasValidas && sumaPorcentajes == 100
        ) {
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

        Button(onClick = { onBackToLogin() }) {
            Text("Volver al Login")
        }
    }
}


// ============================================
// COMPONENTE PORCENTAJES (+ / -)
// ============================================
@Composable
fun AjustarPorcentajeOdontologia(value: Int, onValueChange: (Int) -> Unit) {

    Row(verticalAlignment = Alignment.CenterVertically) {

        Button(
            onClick = { if (value > 0) onValueChange(value - 1) },
            modifier = Modifier.size(32.dp),
            contentPadding = PaddingValues(0.dp)
        ) {
            Text("-")
        }

        Text(
            "$value%",
            modifier = Modifier.padding(horizontal = 8.dp)
        )

        Button(
            onClick = { if (value < 100) onValueChange(value + 1) },
            modifier = Modifier.size(32.dp),
            contentPadding = PaddingValues(0.dp)
        ) {
            Text("+")
        }
    }
}

