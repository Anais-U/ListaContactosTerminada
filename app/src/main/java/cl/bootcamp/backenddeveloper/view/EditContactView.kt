package cl.bootcamp.backenddeveloper.view


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import cl.bootcamp.backenddeveloper.model.Contact
import cl.bootcamp.backenddeveloper.viewModel.ContactViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditContactView(navController: NavController, viewModel: ContactViewModel, contactId: Long) {
    val contact = viewModel.contacts.collectAsState(initial = emptyList())
        .value.find { it.id == contactId }

    var name by remember { mutableStateOf(contact?.name ?: "") }
    var phone by remember { mutableStateOf(contact?.phone ?: "") }
    var email by remember { mutableStateOf(contact?.email ?: "") }
    var profileImage by remember { mutableStateOf(contact?.profileImage ?: "") }
    var birthDate by remember { mutableStateOf(contact?.birthDate ?: "") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Editar contacto") },
                actions = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            Icons.Filled.Home,
                            contentDescription = "Volver al inicio", tint = Color.Black
                        )
                    }
                }
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Campos de entrada para editar nombre, teléfono y email
            TextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = phone,
                onValueChange = { phone = it },
                label = { Text("Teléfono") },
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
                )
            TextField(
                value = profileImage,
                onValueChange = { profileImage = it },
                label = { Text("Imagen de Perfil") },
                modifier = Modifier.fillMaxWidth()
                )
            TextField(
                value = birthDate,
                onValueChange = { birthDate = it },
                label = { Text("Fecha de nacimiento") },
                modifier = Modifier.fillMaxWidth()
                )

            Button(
                onClick = {
                    val updatedContact = Contact(
                        id = contactId, name = name, phone = phone, email = email,
                        profileImage = profileImage, birthDate = birthDate
                    )
                    viewModel.update(updatedContact)
                    navController.popBackStack()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Actualizar Contacto")
            }
        }
    }
}