package com.example.madproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.example.madproject.databinding.ActivityDonateBinding
import com.google.firebase.database.FirebaseDatabase

class Donate : AppCompatActivity() {
    private lateinit var binding: ActivityDonateBinding
    private  lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDonateBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        //connect read data button
        val readAcButton = findViewById<Button>(R.id.readData)
        readAcButton.setOnClickListener {
            val Intent = Intent(this,Read_Data::class.java)
            startActivity(Intent)
        }
       binding.submit.setOnClickListener{
           val name =binding.name.text.toString()
           val phone=binding.phone.text.toString()
           val username=binding.username.text.toString()
           val email =binding.email.text.toString()
           val item =binding.item.text.toString()
           database= FirebaseDatabase.getInstance().getReference("USER")
              val user=User(name,phone,username,email,item)

           database.child(username).setValue(user).addOnSuccessListener{
                     binding.name.text.clear()
                    binding.phone.text.clear()
                    binding.username.text.clear()
                     binding.email.text.clear()
                     binding.item.text.clear()
               Toast.makeText(this,"Success saved",Toast.LENGTH_SHORT).show()
           }.addOnFailureListener{
               Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
           }

       }


        }
    }




