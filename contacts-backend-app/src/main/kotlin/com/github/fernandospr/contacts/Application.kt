package com.github.fernandospr.contacts

import com.github.fernandospr.contacts.plugins.configureRouting
import com.github.fernandospr.contacts.plugins.configureSerialization
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 8080, module = Application::module).start(wait = true)
}

fun Application.module() {
    configureSerialization()
    configureRouting()
}