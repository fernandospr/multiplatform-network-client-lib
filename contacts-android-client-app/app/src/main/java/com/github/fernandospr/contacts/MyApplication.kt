package com.github.fernandospr.contacts

import android.app.Application

class MyApplication : Application() {
    val contactsClient by lazy {
        ContactsClient("http://10.0.2.2:8080")
    }
}