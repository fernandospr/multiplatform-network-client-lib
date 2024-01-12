package com.github.fernandospr.contacts

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Configuration {

    @Value("\${contacts.baseUrl}")
    lateinit var contactsBaseUrl: String

    @Bean
    fun getRepository() = ContactsRepository(contactsBaseUrl)
}