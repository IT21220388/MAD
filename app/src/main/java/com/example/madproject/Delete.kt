package com.example.madproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.madproject.databinding.ActivityDeleteBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError


class Delete : AppCompatActivity() {
    private lateinit var binding: ActivityDeleteBinding
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val BACKReadAcButton = findViewById<Button>(R.id.BACK_read)
        BACKReadAcButton.setOnClickListener {
            val Intent = Intent(this,Read_Data::class.java)
            startActivity(Intent)
        }


        database = FirebaseDatabase.getInstance().getReference("USER")
        binding.deleteData.setOnClickListener {
            val username = binding.dUsername.text.toString().trim()
            deleteUserData(username)
        }
    }
    private fun deleteUserData(username: String){
        val userQuery: Query = database.orderByChild("username").equalTo(username)

        userQuery.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot){
                for (userSnapshot in dataSnapshot.children) {
                    userSnapshot.ref.removeValue()
                }
                Toast.makeText(applicationContext, "Data deleted successfully", Toast.LENGTH_SHORT).show()
            }
            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(applicationContext, "Error deleting data", Toast.LENGTH_LONG).show()
            }
        })
    }
}