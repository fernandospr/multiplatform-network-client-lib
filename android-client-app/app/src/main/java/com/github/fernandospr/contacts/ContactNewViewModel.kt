package com.github.fernandospr.contacts

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.coroutines.launch

class ContactNewViewModel(private val repository: ContactsRepository) : ViewModel() {

    private val _contactAdded = MutableLiveData<Unit>()
    val contactAdded = _contactAdded

    fun addContact(id: String, name: String, number: String) {
        viewModelScope.launch {
            try {
                val contact = Contact(id, name, number)
                repository.add(contact)
                _contactAdded.value = Unit
            } catch (e: Exception) {
                Log.e("ContactNewViewModel", "addContact: ${e.message}")
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                val application = checkNotNull(extras[APPLICATION_KEY])
                return ContactNewViewModel(
                    (application as MyApplication).repository
                ) as T
            }
        }
    }
}