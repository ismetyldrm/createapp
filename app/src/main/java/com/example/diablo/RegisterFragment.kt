package com.example.diablo

import LoginFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.textfield.TextInputEditText

class RegisterFragment : Fragment() {

    private lateinit var credentialsManager: CredentialsManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_register, container, false)

        // Initialize CredentialsManager
        credentialsManager = CredentialsManager(requireContext())

        // Link views
        val fullNameInput = view.findViewById<TextInputEditText>(R.id.fullNameEditText)
        val emailInput = view.findViewById<TextInputEditText>(R.id.validEmailEditText)
        val phoneNumberInput = view.findViewById<TextInputEditText>(R.id.phoneNumberEditText)
        val passwordInput = view.findViewById<TextInputEditText>(R.id.strongPasswordEditText)
        val termsCheckBox = view.findViewById<MaterialCheckBox>(R.id.rememberMeCheckBox)
        val registerButton = view.findViewById<MaterialButton>(R.id.nextButton)
        val loginTextView = view.findViewById<TextView>(R.id.alreadyMemberTextView) // Corrected reference


        registerButton.setOnClickListener {
            val fullName = fullNameInput.text.toString().trim()
            val email = emailInput.text.toString().trim()
            val phoneNumber = phoneNumberInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()
            val agreedToTerms = termsCheckBox.isChecked

            // Validate fields
            if (fullName.isEmpty()) {
                Toast.makeText(requireContext(), "Full name cannot be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!credentialsManager.isEmailValid(email)) {
                Toast.makeText(requireContext(), "Invalid email format", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                Toast.makeText(requireContext(), "Password cannot be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!agreedToTerms) {
                Toast.makeText(requireContext(), "You must agree to the terms and conditions", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            val isRegistered = credentialsManager.register(email, password)
            if (isRegistered) {
                Toast.makeText(requireContext(), "Registration successful! You can now log in.", Toast.LENGTH_SHORT).show()
                parentFragmentManager.popBackStack() // Navigate back to LoginFragment
            } else {
                Toast.makeText(requireContext(), "Email is already in use. Try another.", Toast.LENGTH_SHORT).show()
            }
        }


        loginTextView.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, LoginFragment()) // Use correct fragment container ID
                .addToBackStack(null)
                .commit()
        }

        return view
    }
}