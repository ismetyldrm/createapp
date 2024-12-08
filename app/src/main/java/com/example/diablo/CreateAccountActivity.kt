package com.example.diablo
import android.util.Log

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.diablo.databinding.ActivityCreateAccountBinding


class CreateAccountActivity : AppCompatActivity() {


    private lateinit var binding: ActivityCreateAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.newMemberTextView.setOnClickListener {
            val intent = Intent(this, SecondPageActivity::class.java)
            startActivity(intent)
        }
    }
}