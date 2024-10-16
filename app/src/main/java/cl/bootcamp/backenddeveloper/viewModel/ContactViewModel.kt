package cl.bootcamp.backenddeveloper.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.bootcamp.backenddeveloper.repository.ContactRepository
import cl.bootcamp.backenddeveloper.room.Contact
import kotlinx.coroutines.launch

class ContactViewModel(private val repository: ContactRepository) : ViewModel() {
    fun insert(contact: Contact) {
        viewModelScope.launch {
            repository.insert(contact)

        }
    }
    fun update(contact: Contact) {
        viewModelScope.launch {
            repository.update(contact)
        }
    }

    fun delete (contact: Contact) {
        viewModelScope.launch {
            repository.delete(contact)
        }
    }
    suspend fun getAllContacts(): List<Contact> {
        return repository.getAllContacts()
    }
}