package com.github.fernandospr.contacts

import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*

class ContactsClient(baseUrl: String) {

    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json()
        }
    }

    private val contactsApiUrl = "$baseUrl/api/contacts"

    @NativeCoroutines
    suspend fun getAll(): List<Contact> = httpClient.get(contactsApiUrl).body<List<Contact>>()

    @NativeCoroutines
    suspend fun add(contact: Contact) {
        val result = httpClient.post(contactsApiUrl) {
            setBody(contact)
            contentType(ContentType.Application.Json)
        }
        if (!result.status.isSuccess()) {
            throw RuntimeException("Couldn't add Contact $contact")
        }
    }

    @NativeCoroutines
    suspend fun get(contactId: String): Contact {
        return httpClient.get("$contactsApiUrl/$contactId").body<Contact>()
    }

    @NativeCoroutines
    suspend fun delete(contactId: String) {
        val result = httpClient.delete("$contactsApiUrl/$contactId")
        if (!result.status.isSuccess()) {
            throw RuntimeException("Couldn't delete Contact with id $contactId")
        }
    }
}