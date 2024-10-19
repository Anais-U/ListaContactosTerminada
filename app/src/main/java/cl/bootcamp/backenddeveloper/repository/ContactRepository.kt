package cl.bootcamp.backenddeveloper.repository

import android.util.Log
import cl.bootcamp.backenddeveloper.model.Contact
import cl.bootcamp.backenddeveloper.room.ContactDao
import kotlinx.coroutines.flow.Flow


class ContactRepository(private val contactDao: ContactDao) {

    suspend fun insert(contact: Contact) {
        contactDao.insert(contact)
    }

    suspend fun update(contact: Contact) {
        contactDao.update(contact)
    }

    suspend fun delete(contact: Contact) {
        contactDao.delete(contact)
    }

    fun getAllContacts(): Flow<List<Contact>> {
        return contactDao.getAllContacts()
    }

    suspend fun getContactById(id: Long): Contact? {
        return contactDao.getContactById(id)
    }
}