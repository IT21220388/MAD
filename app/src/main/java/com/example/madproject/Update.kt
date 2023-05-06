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

class update : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateBinding
    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val   backDataAcButton = findViewById<Button>(R.id.back_update)
        backDataAcButton.setOnClickListener {
            val Intent = Intent(this,read_data::class.java)
            startActivity(Intent)
        }

        binding.update.setOnClickListener {
            val name = binding.name.text.toString()
            val phone =binding.phone.text.toString()
            val email =binding.email.text.toString()
            val username =binding.username.text.toString()
            val type =binding.type.text.toString()

            updateData(name,phone,email,username,type)
        }
    }
    private fun updateData(name: String, phone: String, email: String, username: String, type: String ){
        database = FirebaseDatabase.getInstance().getReference("user")
        val user = mapOf<String,String>(
            "name" to name,
            "phone" to phone,
            "email" to email,
            "username" to username,
            "type" to type
        )
        database.child(username).updateChildren(user).addOnSuccessListener {
            binding.username.text.clear()
            binding.name.text.clear()
            binding.phone.text.clear()
            binding.username.text.clear()
            binding.type.text.clear()
            Toast.makeText(this,"Successfuly Updated",Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this, "Failed to Update", Toast.LENGTH_SHORT).show()
        }
    }