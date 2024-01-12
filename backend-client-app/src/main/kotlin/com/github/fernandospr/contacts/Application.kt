package com.github.fernandospr.contacts

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ContactsApplication

fun main(args: Array<String>) {
    runApplication<ContactsApplication>(*args)
}