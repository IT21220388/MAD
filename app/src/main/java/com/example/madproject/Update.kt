package com.example.madproject

import android.content.Intent
import android.media.session.MediaSession.Token
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.madproject.databinding.ActivityUpdateBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Update : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //back read page
        val backAcButton = findViewById<Button>(R.id.back_read)
        backAcButton.setOnClickListener {
            val Intent = Intent(this,Read_Data::class.java)
            startActivity(Intent)
        }


        binding.UpdateData.setOnClickListener {
            val name = binding.name.text.toString()
            val phone = binding.phone.text.toString()
            val username = binding.username.text.toString()
            val email = binding.email.text.toString()
            val item = binding.item.text.toString()

            updateData(name, phone, username, email, item)
        }
    }

    private fun updateData(
        name: String,
        phone: String,
        username: String,
        email: String,
        item: String
    ) {
        database = FirebaseDatabase.getInstance().getReference("USER")
        val user = mapOf<String, String>(
            "name" to name,
            "phone" to phone,
            "Username" to username,
            "email" to email,
            "item" to item
        )
        database.child(username).updateChildren(user).addOnSuccessListener {
            binding.email.text.clear()
            binding.name.text.clear()
            binding.phone.text.clear()
            binding.username.text.clear()
            binding.email.text.clear()
            binding.item.text.clear()
            Toast.makeText(this, "Successfuly Updated", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this, "Failed to Update", Toast.LENGTH_SHORT).show()
        }
    }
}
