package cl.bootcamp.backenddeveloper.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import cl.bootcamp.backenddeveloper.model.Contact
import coil.compose.rememberAsyncImagePainter

@Composable
fun ContactItem(
    contact: Contact,
    onDeleteClick: () -> Unit,
    onEditClick: () -> Unit
) {
    // Definimos la Card para el contacto
    Card(
        shape = RoundedCornerShape(8.dp),  // Esquinas redondeadas
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),  // Margen alrededor de la tarjeta
        elevation = CardDefaults.cardElevation(4.dp)  // Sombra para la tarjeta
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Imagen de perfil del contacto
            Image(
                painter = rememberAsyncImagePainter(contact.profileImage),
                contentDescription = "Imagen de perfil de ${contact.name}",
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape),  // Imagen redonda
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Column para mostrar los detalles del contacto
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(text = contact.name, modifier = Modifier.padding(4.dp))
                Text(text = contact.phone, modifier = Modifier.padding(4.dp))
                Text(text = contact.email, modifier = Modifier.padding(4.dp))
                Text(text = contact.birthDate ?: "Fecha no disponible", modifier = Modifier.padding(4.dp)) // Mostrar fecha de nacimiento
            }

            // Botón de Editar
            IconButton(onClick = onEditClick) {
                Icon(Icons.Filled.Edit, contentDescription = "Editar contacto")
            }

            // Botón de Eliminar
            IconButton(onClick = onDeleteClick) {
                Icon(Icons.Filled.Delete, contentDescription = "Eliminar contacto")
            }
        }
    }
}