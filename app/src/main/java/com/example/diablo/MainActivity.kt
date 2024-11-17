package com.example.diablo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.diablo.databinding.ActivityCreateAccountBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateAccountBinding
    private val credentialsManager = CredentialsManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.nextButton.setOnClickListener {
            validateAndProceed()
        }
    }

    private fun validateAndProceed() {
        val email = binding.validEmailEditText.text.toString().trim()
        val password = binding.strongPasswordEditText.text.toString().trim()

        var isValid = true


        if (!credentialsManager.isEmailValid(email)) {
            binding.validEmailEditText.error = "Invalid email"
            isValid = false
        } else {
            binding.validEmailEditText.error = null
        }


        if (!credentialsManager.isPasswordValid(password)) {
            binding.strongPasswordEditText.error = "Password cannot be empty"
            isValid = false
        } else {
            binding.strongPasswordEditText.error = null
        }


        if (isValid) {
            val intent = Intent(this, SecondPageActivity::class.java)
            startActivity(intent)
        }
    }
}



