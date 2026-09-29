package com.example.wallapopandroidalejandro.navigation

sealed class Screen(val route: String) {

    // 1. Pantalla principal (Carrusel + Grid)
    object Home : Screen("home")

    // 2. 🆕 Pantalla de formulario de compra, que recibe el ID del producto
    object BuyForm : Screen("buy_form/{productId}") {
        // Función para construir la ruta con el ID real
        fun createRoute(productId: Int) = "buy_form/$productId"
    }

    // ... (otras rutas si las tienes) ...
}