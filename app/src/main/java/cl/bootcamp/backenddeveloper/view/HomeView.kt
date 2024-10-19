package cl.bootcamp.backenddeveloper.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import cl.bootcamp.backenddeveloper.viewModel.ContactViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(navController: NavController, viewModel: ContactViewModel) {
    // Utiliza el StateFlow directamente desde el ViewModel
    val contacts by viewModel.contacts.collectAsState()

    // Usamos Scaffold para incluir la TopAppBar
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Lista de Contactos",
                       color =
                       MaterialTheme.colorScheme.outline)
                        },
                actions = {
                    IconButton(
                        onClick = { navController.navigate("add_contact") }
                    ) {
                        Icon(Icons.Filled.Add, contentDescription = "Agregar contacto",
                            tint = MaterialTheme.colorScheme.tertiary)
                    }
                }
            )
        }
    ) { paddingValues ->
        // Contenido de la pantalla
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)  // Aseguramos que no quede bajo la barra superior
        ) {
            // Lista de contactos
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(contacts) { contact ->
                    ContactItem(
                        contact = contact,
                        onDeleteClick = { viewModel.delete(contact) },
                        onEditClick = { navController.navigate("edit_contact/${contact.id}") }
                    )
                }
            }
        }
    }
}