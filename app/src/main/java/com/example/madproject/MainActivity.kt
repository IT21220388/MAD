package com.example.madproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.madproject.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var database:DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val   readDataAcButton = findViewById<Button>(R.id.profile)
        readDataAcButton.setOnClickListener {
            val Intent = Intent(this,read_data::class.java)
            startActivity(Intent)
        }

        binding.submit.setOnClickListener {
            val name = binding.name.text.toString()
            val phone =binding.phone.text.toString()
            val email =binding.email.text.toString()
            val type =binding.type.text.toString()
            val username =binding.username.text.toString()
            val password =binding.password.text.toString()
            database = FirebaseDatabase.getInstance().getReference("user")
            val user = user(name,phone,email,username,password,type)

            database.child(username).setValue(user).addOnSuccessListener{
                binding.name.text.clear()
                binding.phone.text.clear()
                binding.email.text.clear()
                binding.username.text.clear()
                binding.password.text.clear()
                binding.type.text.clear()

                Toast.makeText(this,"Successfully Saved",Toast.LENGTH_SHORT ).show()
            }.addOnFailureListener{
                Toast.makeText(this," Failed to save",Toast.LENGTH_SHORT ).show()
            }
        }
    }
}