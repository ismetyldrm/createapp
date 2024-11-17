package com.example.diablo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.diablo.databinding.ActivitySecondPage2Binding

class SecondPageActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondPage2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondPage2Binding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.newMemberTextView.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}
