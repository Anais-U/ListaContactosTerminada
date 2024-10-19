package cl.bootcamp.backenddeveloper.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import cl.bootcamp.backenddeveloper.model.Contact
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {

    // Inserción con manejo de conflictos
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(contact: Contact)

    // Actualización
    @Update
    suspend fun update(contact: Contact)

    // Eliminación
    @Delete
    suspend fun delete(contact: Contact)

    // Obtiene todos los contactos
    @Query("SELECT * FROM contacts")
    fun getAllContacts(): Flow<List<Contact>>

    // Obtiene un contacto por ID
    @Query("SELECT * FROM contacts WHERE id = :id LIMIT 1")
    suspend fun getContactById(id: Long): Contact?
}