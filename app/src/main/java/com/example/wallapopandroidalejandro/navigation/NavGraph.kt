package com.example.wallapopandroidalejandro.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.*
import androidx.navigation.compose.*
import com.example.wallapopandroidalejandro.ui.AddProductScreen
import com.example.wallapopandroidalejandro.ui.BuyFormScreen
import com.example.wallapopandroidalejandro.ui.CartScreen
import com.example.wallapopandroidalejandro.ui.LoginScreen
import com.example.wallapopandroidalejandro.ui.ProfileScreen
import com.example.wallapopandroidalejandro.ui.theme.grid.*

@Composable
fun NavGraph(
    navController: NavHostController,
    cartViewModel: CartViewModel,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = if (cartViewModel.isLoggedIn) "grid" else "login",
        modifier = modifier
    ) {
        composable("login") {
            LoginScreen(cartViewModel) {
                navController.navigate("grid") { popUpTo("login") { inclusive = true } }
            }
        }

        composable("grid") {
            ProductGreenScreen(
                products = cartViewModel.allProducts,
                cartViewModel = cartViewModel,
                onNavigateToDetail = { id -> navController.navigate("buy/$id") }
            )
        }

        composable("cart") { CartScreen(cartViewModel, navController) }

        composable("buy_cart") {
            BuyFormScreen(
                product = null,
                cartViewModel = cartViewModel,
                onBack = { navController.popBackStack() }
            )
        }

        composable("profile") {
            ProfileScreen(cartViewModel.userName, cartViewModel.userEmail) {
                cartViewModel.logout()
                navController.navigate("login") { popUpTo(0) }
            }
        }

        composable("add_product") {
            AddProductScreen(cartViewModel) { navController.popBackStack() }
        }

        composable(
            route = "buy/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ) { back ->
            val id = back.arguments?.getInt("productId") ?: 0
            val p = cartViewModel.allProducts.find { it.id == id }
            // Aquí pasamos el producto específico (compra directa)
            BuyFormScreen(product = p, onBack = { navController.popBackStack() })
        }
    }}