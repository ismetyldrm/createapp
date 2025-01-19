package com.example.diablo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var credentialsManager: CredentialsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        credentialsManager = CredentialsManager(this)

        val emailInput = findViewById<EditText>(R.id.validEmailEditText)
        val passwordInput = findViewById<EditText>(R.id.strongPasswordEditText)
        val nextButton = findViewById<Button>(R.id.nextButton)
        val newMemberTextView = findViewById<TextView>(R.id.newMemberTextView)

        nextButton.setOnClickListener {
            val email = emailInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()




            // Email format doğrulama
            if (!credentialsManager.isEmailValid(email)) {
                Toast.makeText(this, "Invalid email format", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Şifre doğrulama
            if (!credentialsManager.isPasswordValid(password)) {
                Toast.makeText(this, "Password cannot be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Kullanıcı doğrulama
            if (credentialsManager.isValidUser(email, password)) {
                val intent = Intent(this, RecipeActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show()
            }
        }

        // Yeni üye kaydı için yönlendirme
        newMemberTextView.setOnClickListener {
            navigateToRegister()
        }
    }

    // Kayıt ekranına yönlendirme
    private fun navigateToRegister() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
        finish()
    }




}
