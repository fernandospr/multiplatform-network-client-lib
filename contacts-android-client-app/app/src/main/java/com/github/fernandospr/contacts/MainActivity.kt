package com.github.fernandospr.contacts

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.fernandospr.contacts.databinding.ActivityMainBinding
import com.github.fernandospr.contacts.databinding.ContactBinding

class MainActivity : AppCompatActivity() {

    private val vm: ContactsViewModel by viewModels { ContactsViewModel.Factory }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        vm.contacts.observe(this) {
            showContacts(it)
        }

        binding.apply {
            fab.setOnClickListener {
                addContact()
            }
            swipeRefreshLayout.setOnRefreshListener {
                vm.getAll()
            }
        }
    }

    private fun showContacts(
        contactList: List<Contact>
    ) {
        binding.apply {
            swipeRefreshLayout.isRefreshing = false;
            recyclerView.apply {
                adapter = ItemsAdapter(contactList) {
                    showContactDetail(it)
                }
                layoutManager = LinearLayoutManager(this@MainActivity)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        vm.getAll()
    }

    private fun showContactDetail(contact: Contact) {
        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtra(DetailActivity.CONTACT_ID, contact.id)
        }
        startActivity(intent)
    }

    private fun addContact() {
        val intent = Intent(this, NewActivity::class.java)
        startActivity(intent)
    }
}

class ItemsAdapter(
    private val items: List<Contact>,
    private val onItemClick: (Contact) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ContactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ContactViewHolder).bind(items[position])
    }

    inner class ContactViewHolder(binding: ContactBinding) : RecyclerView.ViewHolder(binding.root) {
        private val root = binding.root
        private val textViewId = binding.textViewId
        private val textViewName = binding.textViewName
        private val textViewNumber = binding.textViewNumber

        fun bind(contact: Contact) {
            textViewId.text = "Id: " + contact.id
            textViewName.text = "Name: " + contact.name
            textViewNumber.text = "Number: " + contact.number
            root.setOnClickListener {
                onItemClick(contact)
            }
        }
    }
}

