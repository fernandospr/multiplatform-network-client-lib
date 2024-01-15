package com.github.fernandospr.contacts

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.github.fernandospr.contacts.databinding.ActivityNewBinding

class NewActivity : AppCompatActivity() {

    private val vm: ContactNewViewModel by viewModels { ContactNewViewModel.Factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityNewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        vm.contactAdded.observe(this) {
            finish()
        }

        binding.apply {
            buttonAdd.setOnClickListener {
                vm.addContact(
                    id = editTextId.text.toString(),
                    name = editTextName.text.toString(),
                    number = editTextNumber.text.toString()
                )
            }
        }
    }
}

