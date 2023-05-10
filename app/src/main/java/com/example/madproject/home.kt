package com.example.madproject


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView


class home : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
      val Account = findViewById<CardView>(R.id.account)
      Account.setOnClickListener{
          val intent = Intent(this,MainActivity::class.java)
          startActivity(intent)
      }

    }
}