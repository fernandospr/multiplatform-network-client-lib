package com.github.fernandospr.contacts.controllers

import com.github.fernandospr.contacts.Contact
import com.github.fernandospr.contacts.ContactsClient
import com.github.fernandospr.contacts.domain.DescriptionBody
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class ContactDescriptionsController(@Autowired private val contactsClient: ContactsClient) {

    @GetMapping("/contact-descriptions/{id}")
    suspend fun getContactDescription(@PathVariable id: String) =
        contactsClient.get(id).let { DescriptionBody("Id[${it.id}] Name[${it.name}] Number[${it.number}]") }

    @GetMapping("/contact-descriptions")
    suspend fun getContactDescriptions() =
        contactsClient.getAll().map { DescriptionBody("Id[${it.id}] Name[${it.name}] Number[${it.number}]") }

    @PostMapping("/contact-descriptions")
    suspend fun postContactDescription(@RequestBody descriptionBody: DescriptionBody): ResponseEntity<DescriptionBody> {
        val regex = Regex("\\[(.*?)\\]")
        val matches = regex.findAll(descriptionBody.description)
        val list = matches.map { it.groupValues[1] }.toList()
        val contact = Contact(list[0], list[1], list[2])
        contactsClient.add(contact)
        return ResponseEntity.status(HttpStatus.CREATED).body(descriptionBody)
    }

    @DeleteMapping("/contact-descriptions/{id}")
    suspend fun deleteContactDescription(@PathVariable id: String): ResponseEntity<Void> {
        contactsClient.delete(id)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }
}