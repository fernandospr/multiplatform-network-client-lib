package com.github.fernandospr.domain

import kotlinx.serialization.Serializable

@Serializable
data class Contact(val id: String, val name: String, val number: String)