package com.example.wallapopandroidalejandro.data

import com.example.wallapopandroidalejandro.R

data class Product(
    val id: Int,
    val name: String,     // Nombre del producto (Variable)
    val price: Double,    // Precio del producto (Variable)
    val imageResId: Int // Ahora es un ID de recurso local
)

// ¡LISTA DE PRODUCTOS ACTUALIZADA CON LOS IDS DE RECURSO!
val sampleProducts = listOf(
    // ASIGNACIÓN DE LAS IMÁGENES ESPECÍFICAS
    Product(
        id = 1,
        name = "Silla Gaming",
        price = 120.50,
        imageResId = R.drawable.sillagaming // <-- Usando tu imagen específica
    ),
    Product(
        id = 2,
        name = "Teclado Mecánico",
        price = 85.99,
        imageResId = R.drawable.tecladogaming // <-- Usando tu imagen específica
    ),

    // Los demás productos usan un placeholder genérico
    Product(
        id = 3,
        name = "Monitor 27'' 4K",
        price = 450.00,
        imageResId = R.drawable.webcam // <-- Usando un placeholder
    ),
    Product(
        id = 4,
        name = "Ratón Inalámbrico",
        price = 35.00,
        imageResId = R.drawable.webcam // <-- Usando un placeholder
    ),
    Product(
        id = 5,
        name = "Webcam HD",
        price = 25.90,
        imageResId = R.drawable.webcam // <-- Usando un placeholder
    ),
    Product(
        id = 6,
        name = "Altavoces Bluetooth",
        price = 59.95,
        imageResId = R.drawable.webcam
    ),
)