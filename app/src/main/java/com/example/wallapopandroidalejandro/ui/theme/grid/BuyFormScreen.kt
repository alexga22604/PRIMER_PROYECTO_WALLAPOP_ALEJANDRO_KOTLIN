package com.example.wallapopandroidalejandro.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wallapopandroidalejandro.data.Product
import com.example.wallapopandroidalejandro.ui.theme.grid.CartViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BuyFormScreen(
    product: Product? = null,
    cartViewModel: CartViewModel? = null,
    onBack: () -> Unit
) {
    var nombre by remember { mutableStateOf("") }
    var apellidos by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var direccion by remember { mutableStateOf("") }

    // Si product es null, significa que venimos del carrito
    val displayName = product?.name ?: "Total del Carrito"
    val displayPrice = product?.price ?: cartViewModel?.calculateTotal() ?: 0.0

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Resumen de compra", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f))
            ) {
                Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = displayName, style = MaterialTheme.typography.titleMedium)
                        Text(
                            text = "${displayPrice}€",
                            style = MaterialTheme.typography.headlineSmall,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }
                }
            }

            Text(text = "Datos de envío", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)


            CustomTextField(value = nombre, onValueChange = { nombre = it }, label = "Nombre", icon = Icons.Default.Person)
            CustomTextField(value = apellidos, onValueChange = { apellidos = it }, label = "Apellidos", icon = Icons.Default.Badge)
            CustomTextField(value = telefono, onValueChange = { telefono = it }, label = "Teléfono", icon = Icons.Default.Phone)
            CustomTextField(value = direccion, onValueChange = { direccion = it }, label = "Dirección completa", icon = Icons.Default.Home)

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    // Si pagamos el carrito, podríamos vaciarlo aquí antes de volver
                    onBack()
                },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                shape = MaterialTheme.shapes.medium
            ) {
                Icon(Icons.Default.ShoppingCart, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text("Pagar Ahora", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}


@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    icon: ImageVector
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        leadingIcon = { Icon(icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary) },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        shape = MaterialTheme.shapes.medium
    )
}