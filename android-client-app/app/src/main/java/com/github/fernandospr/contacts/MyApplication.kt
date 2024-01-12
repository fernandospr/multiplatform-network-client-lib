package com.github.fernandospr.contacts

import android.app.Application

class MyApplication : Application() {
    val repository by lazy {
        ContactsRepository("http://10.0.2.2:8080")
    }
}