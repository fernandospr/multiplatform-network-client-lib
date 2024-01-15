package com.github.fernandospr.contacts

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.github.fernandospr.contacts.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private val vm: ContactDetailViewModel by viewModels { ContactDetailViewModel.Factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        vm.contactDeleted.observe(this) {
            finish()
        }

        val contactId = requireNotNull(intent.getStringExtra(CONTACT_ID))
        vm.getContact(contactId)

        vm.contact.observe(this) { contact ->
            binding.apply {
                textViewId.text = contact.id
                textViewName.text = contact.name
                textViewNumber.text = contact.number
                buttonDelete.setOnClickListener {
                    vm.deleteContact(contact.id)
                }
            }
        }
    }

    companion object {
        const val CONTACT_ID = "CONTACT_ID"
    }
}

