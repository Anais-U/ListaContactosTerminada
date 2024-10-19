package cl.bootcamp.backenddeveloper.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.bootcamp.backenddeveloper.repository.ContactRepository
import cl.bootcamp.backenddeveloper.model.Contact
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ContactViewModel @Inject constructor(private val repository: ContactRepository) : ViewModel() {
    // StateFlow para mantener la lista de contactos
    private val _contacts = MutableStateFlow<List<Contact>>(emptyList())
    val contacts: StateFlow<List<Contact>> get() = _contacts

    init {
        getAllContacts()
    }

    private fun getAllContacts() {
        viewModelScope.launch {
            // Obtiene todos los contactos del repositorio
            repository.getAllContacts().collect { contactList ->
                _contacts.value = contactList

            }
        }
    }

    fun insert(contact: Contact) {
        viewModelScope.launch {
            repository.insert(contact)
            // Asegura que se actualice la lista de contactos emitiendo la nueva lista
            _contacts.update { currentList -> currentList + contact }
        }
    }

    fun update(contact: Contact) {
        viewModelScope.launch {
            repository.update(contact)
            // Actualiza el contacto en la lista local sin volver a obtener todos los contactos
            _contacts.update { currentList ->
                currentList.map {
                    if (it.id == contact.id) contact else it
                }
            }
        }
    }

    fun delete(contact: Contact) {
        viewModelScope.launch {
            repository.delete(contact)
            // Elimina el contacto de la lista local sin volver a obtener todos los contactos
            _contacts.update { currentList -> currentList.filter { it.id != contact.id } }
        }
    }
}