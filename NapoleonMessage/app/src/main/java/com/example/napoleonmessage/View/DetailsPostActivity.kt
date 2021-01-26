package com.example.napoleonmessage.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.napoleonmessage.R
import com.example.napoleonmessage.ViewModel.ViewModelDetailsActivity

class DetailsPostActivity : AppCompatActivity() {

    private lateinit var viewModel: ViewModelDetailsActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_post)
        val nameUser: TextView = findViewById(R.id.userName)
        val emailUser: TextView = findViewById(R.id.emailUser)
        val phoneUser: TextView = findViewById(R.id.PhoneUser)
        val websiteUser: TextView = findViewById(R.id.websiteUser)
        val titleMesage: TextView = findViewById(R.id.titleMessage)
        val message: TextView = findViewById(R.id.bodyMessage)
        val idUser = intent.getIntExtra(EXTRA_USER_ID, 0)
        val titlePost = intent.getStringExtra(EXTRA_POST_TITLE)
        val messagePost = intent.getStringExtra(EXTRA_POST_MESSAGE)
        viewModel = ViewModelProvider(this).get(ViewModelDetailsActivity::class.java)
        viewModel.detailsPost.observe(this, Observer {
            nameUser.text = it.name
            emailUser.text = it.email
            phoneUser.text = it.phone
            websiteUser.text = it.website
            titleMesage.text = titlePost
            message.text = messagePost
        })
        viewModel.getDetailsUser(idUser)
    }
}