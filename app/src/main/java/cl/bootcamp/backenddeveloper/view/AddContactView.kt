package cl.bootcamp.backenddeveloper.view

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import cl.bootcamp.backenddeveloper.model.Contact
import cl.bootcamp.backenddeveloper.viewModel.ContactViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddContactView(navController: NavController, viewModel: ContactViewModel) {
    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var profileImage by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf("") }

    // Usamos Scaffold para incluir la TopAppBar
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Agregar Nuevo Contacto") },

                actions = {
                    IconButton(onClick = {
                        navController.popBackStack() // Opcional: acción al pulsar un botón en la TopAppBar
                    }) {
                        Icon(Icons.Filled.Close, contentDescription = "Cancelar")
                    }
                }
            )
        }
    ) { paddingValues ->
        // Contenido de la pantalla
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues) // Asegura que no quede bajo la barra superior
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(
                modifier = Modifier.height(8.dp)
            )
            TextField(
                value = phone,
                onValueChange = { phone = it },
                label = { Text("Teléfono") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(
                modifier = Modifier.height(8.dp)
            )
            TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Correo") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(
                modifier = Modifier.height(8.dp)
            )
            TextField(
                value = profileImage,
                onValueChange = { profileImage = it },
                label = { Text("URL Imagen de Perfil") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = birthDate,
                onValueChange = { birthDate = it },
                label = { Text("Fecha de Nacimiento") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            IconButton(onClick = {
                if (profileImage.isEmpty()) {
                    Log.d("AddContactView", "Error: La URL de la imagen de perfil está vacía.")
                    return@IconButton // Detener si la URL está vacía
                }
                val newContact = Contact(
                    name = name,
                    phone = phone,
                    email = email,
                    profileImage = profileImage,
                    birthDate = birthDate
                )
                Log.d("AddContactView", "Perfil de imagen: $profileImage") // Para verificar la URL
                viewModel.insert(newContact)
                navController.popBackStack()
            }) {
                Icon(Icons.Filled.Face, contentDescription = "Guardar Contacto")
            }
        }
    }
}