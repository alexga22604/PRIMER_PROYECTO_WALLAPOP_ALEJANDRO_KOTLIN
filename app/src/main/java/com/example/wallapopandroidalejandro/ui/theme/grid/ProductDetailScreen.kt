package com.example.wallapopandroidalejandro.ui.theme.grid

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.wallapopandroidalejandro.data.Product

@Composable
fun ProductDetailScreen(product: Product?) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (product != null) {
            Text(text = "Detalle de: ${product.name} (ID: ${product.id})")
        } else {
            Text(text = "Producto no encontrado")
        }
    }
}