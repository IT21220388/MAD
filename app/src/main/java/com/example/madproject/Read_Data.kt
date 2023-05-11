package com.example.madproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.madproject.databinding.ActivityReadDataBinding

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Read_Data : AppCompatActivity() {
    private lateinit var binding:ActivityReadDataBinding
    private lateinit var database:DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityReadDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //connect update page
        val UpdateAcButton = findViewById<Button>(R.id.deleteData)
        UpdateAcButton.setOnClickListener {
            val Intent= Intent(this,Update::class.java)
            startActivity(Intent)
        }
        val DeleteAcButton = findViewById<Button>(R.id.delete)
        DeleteAcButton.setOnClickListener {
            val Intent = Intent(this,Delete::class.java)
            startActivity(Intent)
        }
        val BackAcButton = findViewById<Button>(R.id.back)
        BackAcButton.setOnClickListener {
            val Intent = Intent(this,Donate::class.java)
        }


        //set read button
        binding.read.setOnClickListener {
            val email:String= binding.Remail.text.toString()
            if(email.isNotEmpty()){
                readData(email)
            }else{
                Toast.makeText(this,"Please enter the email",Toast.LENGTH_SHORT).show()
            }

        }
    }


    private fun readData(email:String){
        database=FirebaseDatabase.getInstance().getReference("USER")
        database.child(email).get().addOnSuccessListener { dataSnapshot ->
            if(dataSnapshot.exists()){
                val name = dataSnapshot.child("name").value.toString()
                val phone= dataSnapshot.child("phone").value.toString()
                val address = dataSnapshot.child("address").value.toString()
                val email = dataSnapshot.child("email").value.toString()
                val item = dataSnapshot.child("item").value.toString()

                Toast.makeText(this,"Read Successfull",Toast.LENGTH_SHORT).show()
                binding.Remail.text.clear()
                binding.Phone.text=phone
                binding.Name.text = name
                binding.Address.text=address
                binding.Items.text=item

            }else{
                Toast.makeText(this,"User Does Not Exist",Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(this,"Error Reading Data",Toast.LENGTH_SHORT).show()
        }
    }
}