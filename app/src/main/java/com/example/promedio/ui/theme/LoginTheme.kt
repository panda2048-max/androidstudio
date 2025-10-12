package com.example.promedio.ui.theme


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.promedio.viewmodel.LoginViewModel

@Composable
fun LoginTheme(viewModel: LoginViewModel){

    var abrirModal by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        OutlinedTextField(
            value = viewModel.login.nombre,
            onValueChange = {viewModel.login.nombre = it},
            label = { Text("ingrese nombre") },
            isError = !viewModel.verificarNombre(),
            supportingText = {Text(viewModel.mensajeError.nombre, color = androidx.compose.ui.graphics.Color.Red)}
        )
        OutlinedTextField(
            value = viewModel.login.correo,
            onValueChange = {viewModel.login.correo = it},
            label = { Text("ingrese correo") },
            isError = !viewModel.verificarCorreo(),
            supportingText = {Text(viewModel.mensajeError.correo, color = androidx.compose.ui.graphics.Color.Red)}
        )
        Checkbox(
            checked = viewModel.login.terminos,
            onCheckedChange = {viewModel.login.terminos = it},
        )
        Text("acepta los terminos")

        Button(
            enabled = viewModel.verificarLogin(),
            onClick = {
                if (viewModel.verificarLogin()) {
                    abrirModal = true
                }
            }
        ){
            Text("enviar")
        }

        if (abrirModal){
            AlertDialog(
                onDismissRequest = { },
                title = { Text("confirmacion")},
                text = {Text(("login iniciado con exito"))},
                confirmButton = {
                    Button(onClick = {abrirModal = false}){ Text("ok")}
                }
            )
        }
    }
}