package com.example.diablo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.diablo.LoginActivity
import com.google.android.material.textfield.TextInputEditText

class RegisterActivity : AppCompatActivity() {

    private lateinit var credentialsManager: CredentialsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_page2)

        credentialsManager = CredentialsManager(this)

        val fullNameInput = findViewById<EditText>(R.id.fullNameEditText)
        val emailInput = findViewById<EditText>(R.id.validEmailEditText)
        val phoneNumberInput = findViewById<EditText>(R.id.phoneNumberEditText)
        val passwordInput = findViewById<EditText>(R.id.strongPasswordEditText)
        val termsCheckBox = findViewById<CheckBox>(R.id.rememberMeCheckBox)
        val nextButton = findViewById<Button>(R.id.nextButton)
        val alreadyMemberTextView = findViewById<TextView>(R.id.alreadyMemberTextView) // Button "Already a member?"

        nextButton.setOnClickListener {
            val fullName = fullNameInput.text.toString()
            val email = emailInput.text.toString()
            val phoneNumber = phoneNumberInput.text.toString()
            val password = passwordInput.text.toString()
            val agreedToTerms = termsCheckBox.isChecked

            if (fullName.isEmpty()) {
                showToast("Full name cannot be empty")
                return@setOnClickListener
            }

            if (!credentialsManager.isEmailValid(email)) {
                showToast("Invalid email format")
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                showToast("Password cannot be empty")
                return@setOnClickListener
            }

            if (!agreedToTerms) {
                showToast("You must agree to the terms and conditions")
                return@setOnClickListener
            }

            val isRegistered = credentialsManager.register(email, password)
            if (isRegistered) {
                showToast("Registration was successful! You can now log in.")

                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                showToast("The email is already in use. Please try another.")
            }
        }

        alreadyMemberTextView.setOnClickListener {
            navigateToLogin()
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun navigateToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}