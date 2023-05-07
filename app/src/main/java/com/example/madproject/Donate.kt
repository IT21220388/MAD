package com.example.madproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.example.madproject.databinding.ActivitydonateDataBinding
import com.google.firebase.database.FirebaseDatabase

class Donate : AppCompatActivity() {
    private lateinit var binding: ActivitydonateDataBinding
    private  lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitydonateDataBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
       binding.submit.setOnClickListener{
           val name =binding.name.text.toString()
           val phone=binding.phone.text.toString()
           val address=binding.address.text.toString()
           val email =binding.email.text.toString()
           val item =binding.item.text.toString()
           database= FirebaseDatabase.getInstance().getReference("USER")
              val user=User(name,phone,address, email,item)

           database.child(email).setValue(user).addOnSuccessListener{
                     binding.name.text.clear()
                    binding.phone.text.clear()
                      binding.address.text.clear()
                     binding.email.text.clear()
                     binding.item.text.clear()
               Toast.makeText(this,"Success saved",Toast.LENGTH_SHORT).show()
           }.addOnFailureListener{
               Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
           }


       }


        }
    }




