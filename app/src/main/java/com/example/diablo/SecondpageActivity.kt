package com.example.diablo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_page2)

        // Log for debugging
        Log.d("SecondPageActivity", "Activity loaded successfully!")

        // Navigation to LoginActivity
        val alreadyMemberTextView = findViewById<TextView>(R.id.newMemberTextView)
        alreadyMemberTextView.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}