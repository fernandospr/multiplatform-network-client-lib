package com.github.fernandospr.contacts

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.coroutines.launch

class ContactDetailViewModel(private val repository: ContactsRepository) : ViewModel() {

    private val _contact = MutableLiveData<Contact>()
    val contact: LiveData<Contact> = _contact

    private val _contactDeleted = MutableLiveData<Unit>()
    val contactDeleted = _contactDeleted

    fun getContact(id: String) {
        viewModelScope.launch {
            try {
                val contact = repository.get(id)
                _contact.value = contact
            } catch (e: Exception) {
                Log.e("ContactDetailViewModel", "getContact: ${e.message}")
            }
        }
    }

    fun deleteContact(id: String) {
        viewModelScope.launch {
            try {
                repository.delete(id)
                _contactDeleted.value = Unit
            } catch (e: Exception) {
                Log.e("ContactDetailViewModel", "deleteContact: ${e.message}")
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
                return ContactDetailViewModel(
                    (application as MyApplication).repository
                ) as T
            }
        }
    }
}