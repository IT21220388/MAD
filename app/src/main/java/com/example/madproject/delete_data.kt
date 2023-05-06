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


class delete_data : AppCompatActivity() {
    private lateinit var binding: ActivityDeleteDataBinding
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeleteDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //connect to button
        val   BackDataAcButton = findViewById<Button>(R.id.back)
        BackDataAcButton.setOnClickListener {
            val Intent = Intent(this,read_data::class.java)
            startActivity(Intent)
        }
        //connect database to delete account
        database = FirebaseDatabase.getInstance().getReference("user")
        binding.delete.setOnClickListener {
            val username = binding.etUsername.text.toString().trim()
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
                Toast.makeText(applicationContext, "User data deleted successfully", Toast.LENGTH_SHORT).show()
            }
            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(applicationContext, "Error deleting user data", Toast.LENGTH_LONG).show()
            }
        })
    }
}