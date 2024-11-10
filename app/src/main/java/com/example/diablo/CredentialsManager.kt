package com.example.diablo

class CredentialsManager {

    fun isEmailValid(email: String): Boolean {
        if (email.isEmpty()) return false
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        return email.matches(emailPattern.toRegex())
    }


    fun isPasswordValid(password: String): Boolean {
        return password.isNotEmpty()
    }
}
