package com.example.madproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.madproject.databinding.ActivityReadDataBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class read_data : AppCompatActivity() {
    private lateinit var binding: ActivityReadDataBinding
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReadDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val   updateDataAcButton = findViewById<Button>(R.id.update_profile)
        updateDataAcButton.setOnClickListener {
            val Intent = Intent(this,update::class.java)
            startActivity(Intent)
        }
        val   deleteDataAcButton = findViewById<Button>(R.id.delete_profile)
        deleteDataAcButton.setOnClickListener {
            val Intent = Intent(this,delete_data::class.java)
            startActivity(Intent)
        }

        binding.readdataBtn.setOnClickListener {
            val username: String = binding.userName.text.toString()
            if (username.isNotEmpty()) {
                readData(username)
            } else {
                Toast.makeText(this, "Please enter the username", Toast.LENGTH_SHORT).show()

            }
        }
    }

    private fun readData(username: String) {
        database = FirebaseDatabase.getInstance().getReference("user")
        database.child(username).get().addOnSuccessListener { dataSnapshot ->
            if (dataSnapshot.exists()) {
                val name = dataSnapshot.child("name").value.toString()
                val phone = dataSnapshot.child("phone").value.toString()
                val email = dataSnapshot.child("email").value.toString()
                val username = dataSnapshot.child("username").value.toString()
                val type = dataSnapshot.child("type").value.toString()
                Toast.makeText(this, "Successfully Read", Toast.LENGTH_SHORT).show()
                binding.userName.text.clear()
                binding.Name.text = name
                binding.Phone.text = phone
                binding.Email.text = email
                binding.Type.text = type

            } else {
                Toast.makeText(this, "User Doesn't Exist", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(this, "Error reading data", Toast.LENGTH_SHORT).show()
        }
    }

}