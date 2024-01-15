package com.github.fernandospr.contacts

import kotlinx.serialization.Serializable
import kotlin.experimental.ExperimentalObjCName
import kotlin.native.ObjCName

@OptIn(ExperimentalObjCName::class)
@ObjCName(name = "Contact", exact = true)
@Serializable
data class Contact(val id: String, val name: String, val number: String)