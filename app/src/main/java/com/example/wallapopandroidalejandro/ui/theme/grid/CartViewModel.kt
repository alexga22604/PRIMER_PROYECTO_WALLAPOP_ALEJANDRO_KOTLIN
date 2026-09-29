package com.example.wallapopandroidalejandro.ui.theme.grid

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.example.wallapopandroidalejandro.R
import com.example.wallapopandroidalejandro.data.Product
import com.example.wallapopandroidalejandro.data.sampleProducts

class CartViewModel : ViewModel() {

    private val _allProducts = mutableStateListOf<Product>().apply { addAll(sampleProducts) }
    val allProducts: List<Product> = _allProducts

    private val _items = mutableStateListOf<Product>()
    val items: List<Product> = _items

    var userName by mutableStateOf("")
    var userEmail by mutableStateOf("")
    var isLoggedIn by mutableStateOf(false)
    var hasShownWelcomePopup by mutableStateOf(false)

    fun login(name: String, email: String) {
        userName = name
        userEmail = email
        isLoggedIn = true
    }

    fun logout() {
        userName = ""
        userEmail = ""
        isLoggedIn = false
        hasShownWelcomePopup = false
        _items.clear()
    }

    fun addProduct(product: Product) { _items.add(product) }

    fun removeProduct(product: Product) { _items.remove(product) }

    fun calculateTotal(): Double = _items.sumOf { it.price }

    fun addNewProductToStore(name: String, price: Double) {
        val newId = if (_allProducts.isEmpty()) 1 else _allProducts.maxOf { it.id } + 1
        _allProducts.add(0, Product(newId, name, price, R.drawable.webcam))
    }
}