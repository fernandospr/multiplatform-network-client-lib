package com.github.fernandospr.plugins

import com.github.fernandospr.domain.Contact
import com.github.fernandospr.domain.contactList
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        route("/api") {
            get("/contacts/{id}") {
                val id = call.parameters["id"] ?: return@get call.respond(HttpStatusCode.BadRequest)
                val contact = contactList.find { it.id == id }
                if (contact == null) {
                    call.respond(HttpStatusCode.NotFound)
                } else {
                    call.respond(HttpStatusCode.OK, contact)
                }
            }

            get("/contacts") {
                call.respond(HttpStatusCode.OK, contactList)
            }

            post("/contacts") {
                val contact = call.receive<Contact>()
                if (contactList.find { it.id == contact.id } == null) {
                    contactList.add(contact)
                    call.respond(HttpStatusCode.Created, contact)
                } else {
                    call.respond(HttpStatusCode.BadRequest, contact)
                }
            }

            delete("/contacts/{id}") {
                val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)
                val contact = contactList.find { it.id == id }
                if (contact != null) {
                    contactList.remove(contact)
                    call.respond(HttpStatusCode.NoContent, contact)
                } else {
                    call.respond(HttpStatusCode.BadRequest)
                }
            }
        }
    }
}