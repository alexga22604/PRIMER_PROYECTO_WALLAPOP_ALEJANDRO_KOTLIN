package com.example.wallapopandroidalejandro.ui.theme.grid

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.material3.carousel.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import com.example.wallapopandroidalejandro.R
import com.example.wallapopandroidalejandro.data.Product

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductGreenScreen(
    products: List<Product>,
    cartViewModel: CartViewModel,
    onNavigateToDetail: (Int) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(8.dp)
        ) {
            // 1. CARRUSEL
            item(span = { GridItemSpan(maxLineSpan) }) {
                CarruselVistoso(onNavigateToDetail)
            }

            // 2. TÍTULO SECCIÓN
            item(span = { GridItemSpan(maxLineSpan) }) {
                Text(
                    "Novedades para ti",
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }

            // 3. REJILLA DE PRODUCTOS
            items(products) { product ->
                ProductItem(
                    product = product,
                    onAddToCart = { cartViewModel.addProduct(product) },
                    onBuyNow = { onNavigateToDetail(product.id) }
                )
            }
        }

        // 4. POP-UP DE BIENVENIDA
        if (!cartViewModel.hasShownWelcomePopup) {
            AlertDialog(
                onDismissRequest = { cartViewModel.hasShownWelcomePopup = true },
                title = { Text(" ¡Oferta Especial!", fontWeight = FontWeight.Bold) },
                text = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("Usa el código para un 20% de descuento en tu primera compra.")
                        Spacer(Modifier.height(16.dp))
                        Surface(
                            color = Color(0xFF13C1AC),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(
                                "BIENVENIDO20",
                                modifier = Modifier.padding(12.dp),
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                },
                confirmButton = {
                    TextButton(onClick = { cartViewModel.hasShownWelcomePopup = true }) {
                        Text("ENTENDIDO", color = Color(0xFF13C1AC))
                    }
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarruselVistoso(onProductClick: (Int) -> Unit) {
    // Datos de ejemplo para el carrusel
    val items = remember {
        listOf(
            Triple(1, R.drawable.sillagaming, "Zona Gaming"),
            Triple(2, R.drawable.tecladogaming, "Nuevos Teclados"),
            Triple(3, R.drawable.webcam, "Streaming PRO")
        )
    }

    // Contenedor con fondo
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                androidx.compose.ui.graphics.Brush.verticalGradient(
                    colors = listOf(Color(0xFF13C1AC), Color(0xFF0E9384))
                )
            )
            .padding(vertical = 20.dp)
    ) {
        Text(
            text = "¡Destacados de la semana!",
            color = Color.White,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 20.sp,
            modifier = Modifier.padding(start = 16.dp, bottom = 12.dp)
        )

        HorizontalMultiBrowseCarousel(
            state = rememberCarouselState { items.size },
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp),
            preferredItemWidth = 200.dp,
            itemSpacing = 12.dp,
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) { index ->
            val item = items[index]

            // Tarjeta del carrusel
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { onProductClick(item.first) },
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    // Imagen de fondo
                    Image(
                        painter = painterResource(item.second),
                        contentDescription = item.third,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )

                    // Capa de degradado oscuro inferior para que el texto resalte
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                androidx.compose.ui.graphics.Brush.verticalGradient(
                                    listOf(Color.Transparent, Color.Black.copy(alpha = 0.7f)),
                                    startY = 300f
                                )
                            )
                    )

                    // Texto descriptivo
                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(12.dp)
                    ) {
                        Text(
                            text = item.third,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                        Text(
                            text = "Ver más",
                            color = Color(0xFF13C1AC),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ProductItem(product: Product, onAddToCart: () -> Unit, onBuyNow: () -> Unit) {
    Card(
        modifier = Modifier.padding(8.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column {
            Image(
                painter = painterResource(product.imageResId),
                contentDescription = product.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp),
                contentScale = ContentScale.Crop
            )
            Column(Modifier.padding(8.dp)) {
                Text(product.name, fontWeight = FontWeight.Bold, maxLines = 1)
                Text("${product.price}€", color = Color(0xFF13C1AC), fontWeight = FontWeight.Bold)

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    IconButton(
                        onClick = onAddToCart,
                        modifier = Modifier.size(36.dp)
                    ) {
                        Icon(
                            Icons.Default.AddShoppingCart,
                            contentDescription = null,
                            tint = Color(0xFF13C1AC)
                        )
                    }
                    Button(
                        onClick = onBuyNow,
                        modifier = Modifier.weight(1f),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Text("Comprar", fontSize = 12.sp)
                    }
                }
            }
        }
    }
}