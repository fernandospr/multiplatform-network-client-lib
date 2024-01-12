package com.github.fernandospr.contacts

import kotlinx.serialization.Serializable

@Serializable
data class Contact(val id: String, val name: String, val number: String)
