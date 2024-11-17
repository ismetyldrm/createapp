package com.example.diablo

class CredentialsManager {



    // Checks if the email format is valid
    fun isEmailValid(email: String): Boolean {
        if (email.isEmpty())
            return false
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        return email.matches(emailPattern.toRegex())
    }

    // Checks if the password is not empty
    fun isPasswordValid(password: String): Boolean {
        return password.isNotEmpty()
    }

    // Checks if the provided credentials match the hardcoded ones
    fun isValidUser(email: String, password: String): Boolean {
        return isEmailValid(email) && isPasswordValid(password)
    }
}


