package com.example.madproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.madproject.databinding.ActivityDeleteDataBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError


class delete : AppCompatActivity() {
    private lateinit var binding: ActivityDeleteDataBinding
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeleteDataBinding.inflate(layoutInflater)
        setContentView(binding.root)


        database = FirebaseDatabase.getInstance().getReference("USER")
        binding.delete.setOnClickListener {
            val email = binding.etUsername.text.toString().trim()
            deleteUserData(email)
        }
    }
    private fun deleteUserData(email: String){
        val userQuery: Query = database.orderByChild("email").equalTo(email)

        userQuery.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot){
                for (userSnapshot in dataSnapshot.children) {
                    userSnapshot.ref.removeValue()
                }
                Toast.makeText(applicationContext, "User data deleted successfully", Toast.LENGTH_SHORT).show()
            }
            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(applicationContext, "Error deleting user data", Toast.LENGTH_LONG).show()
            }
        })
    }
}