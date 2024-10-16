package cl.bootcamp.backenddeveloper.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface ContactDao {
    @Insert
    suspend fun insert(contact: Contact)

    @Update
    suspend fun update(contact: Contact)

    @Delete
    suspend fun delete(contact: Contact)

    @Query("SELECT * FROM contacts")
    suspend fun getAllContacts(): List<Contact>

    @Query("SELECT * FROM contacts WHERE id = :id")
    suspend fun getContactById(id: Long): Contact?
}