package cl.bootcamp.backenddeveloper.repository

import cl.bootcamp.backenddeveloper.room.Contact
import cl.bootcamp.backenddeveloper.room.ContactDao

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

    suspend fun getAllContacts(): List<Contact> {
        return contactDao.getAllContacts()
    }

    suspend fun getContactById(id: Long): Contact? {
        return contactDao.getContactById(id)
    }
}