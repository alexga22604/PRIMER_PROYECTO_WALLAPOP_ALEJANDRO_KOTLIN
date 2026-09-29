package com.example.wallapopandroidalejandro.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.wallapopandroidalejandro.ui.theme.grid.CartViewModel

@Composable
fun AddProductScreen(viewModel: CartViewModel, onBack: () -> Unit) {
    var name by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(24.dp)) {
        Text("Vender Producto", style = MaterialTheme.typography.headlineMedium)

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nombre del artículo") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = price,
            onValueChange = { price = it },
            label = { Text("Precio (€)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(32.dp))

        Button(
            modifier = Modifier.fillMaxWidth().height(56.dp),
            onClick = {
                val p = price.toDoubleOrNull() ?: 0.0
                if (name.isNotEmpty()) {
                    viewModel.addNewProductToStore(name, p)
                    onBack() // Vuelve a la cuadrícula
                }
            }
        ) {
            Text("Publicar ahora")
        }
    }
}