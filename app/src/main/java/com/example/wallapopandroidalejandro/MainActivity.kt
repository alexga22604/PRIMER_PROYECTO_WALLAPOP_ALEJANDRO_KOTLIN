package com.example.wallapopandroidalejandro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.*
import com.example.wallapopandroidalejandro.ui.theme.WallapopAndroidAlejandroTheme
import com.example.wallapopandroidalejandro.ui.theme.grid.CartViewModel
import com.example.wallapopandroidalejandro.navigation.NavGraph

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WallapopAndroidAlejandroTheme {
                val navController = rememberNavController()
                val cartViewModel: CartViewModel = viewModel()

                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                val currentRoute = currentDestination?.route

                Scaffold(
                    // 1. BARRA SUPERIOR DESPLEGABLE
                    topBar = {
                        if (cartViewModel.isLoggedIn) {
                            // Estado local para controlar el menú
                            var showMenu by remember { mutableStateOf(false) }

                            CenterAlignedTopAppBar(
                                title = { Text("WALLAPOP", fontWeight = FontWeight.Bold) },
                                actions = {
                                    // Icono de los tres puntos arriba a la derecha
                                    IconButton(onClick = { showMenu = true }) {
                                        Icon(Icons.Default.MoreVert, contentDescription = "Menú")
                                    }

                                    // El Menú que se despliega
                                    DropdownMenu(
                                        expanded = showMenu,
                                        onDismissRequest = { showMenu = false }
                                    ) {
                                        DropdownMenuItem(
                                            text = { Text("Inicio") },
                                            leadingIcon = { Icon(Icons.Default.Home, null) },
                                            onClick = {
                                                showMenu = false
                                                navController.navigate("grid")
                                            }
                                        )
                                        DropdownMenuItem(
                                            text = { Text("Carrito") },
                                            leadingIcon = { Icon(Icons.Default.ShoppingCart, null) },
                                            onClick = {
                                                showMenu = false
                                                navController.navigate("cart")
                                            }
                                        )
                                        HorizontalDivider()
                                        DropdownMenuItem(
                                            text = { Text("Cerrar sesión", color = Color.Red) },
                                            leadingIcon = { Icon(Icons.Default.ExitToApp, null, tint = Color.Red) },
                                            onClick = {
                                                showMenu = false
                                                cartViewModel.logout()
                                                navController.navigate("login") {
                                                    popUpTo(0)
                                                }
                                            }
                                        )
                                    }
                                },
                                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                                    containerColor = MaterialTheme.colorScheme.surface
                                )
                            )
                        }
                    },

                    // 2. BARRA INFERIOR
                    bottomBar = {
                        if (cartViewModel.isLoggedIn) {
                            NavigationBar {
                                NavigationBarItem(
                                    icon = { Icon(Icons.Default.Home, contentDescription = null) },
                                    label = { Text("Inicio") },
                                    selected = currentDestination?.hierarchy?.any { it.route == "grid" } == true,
                                    onClick = {
                                        navController.navigate("grid") {
                                            popUpTo(navController.graph.findStartDestination().id) {
                                            }
                                            launchSingleTop = true

                                        }
                                    }
                                )

                                NavigationBarItem(
                                    icon = {
                                        BadgedBox(badge = {
                                            if (cartViewModel.items.isNotEmpty()) {
                                                Badge { Text(cartViewModel.items.size.toString()) }
                                            }
                                        }) {
                                            Icon(Icons.Default.ShoppingCart, contentDescription = null)
                                        }
                                    },
                                    label = { Text("Carrito") },
                                    selected = currentDestination?.hierarchy?.any { it.route == "cart" } == true,
                                    onClick = { navController.navigate("cart") }
                                )

                                NavigationBarItem(
                                    icon = { Icon(Icons.Default.Person, contentDescription = null) },
                                    label = { Text("Usuario") },
                                    selected = currentDestination?.hierarchy?.any { it.route == "profile" } == true,
                                    onClick = { navController.navigate("profile") }
                                )
                            }
                        }
                    },

                    // 3. BOTÓN FLOTANTE AÑADIR PRODUCTO
                    floatingActionButton = {
                        if (cartViewModel.isLoggedIn && currentRoute == "grid") {
                            FloatingActionButton(
                                onClick = { navController.navigate("add_product") },
                                containerColor = Color(0xFF13C1AC),
                                contentColor = Color.White
                            ) {
                                Icon(Icons.Default.Add, contentDescription = "Vender producto")
                            }
                        }
                    }
                ) { innerPadding ->
                    NavGraph(
                        navController = navController,
                        cartViewModel = cartViewModel,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}