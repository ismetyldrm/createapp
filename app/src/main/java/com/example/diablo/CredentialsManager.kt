package com.example.diablo

import android.content.Context
import android.content.SharedPreferences
import android.util.Patterns
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CredentialsManager(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("user_credentials", Context.MODE_PRIVATE)

    private val _loginState = MutableStateFlow(isUserLoggedIn())
    val loginState: StateFlow<Boolean> = _loginState


    fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }


    fun isPasswordValid(password: String): Boolean {
        return password.isNotEmpty()
    }


    fun register(email: String, password: String): Boolean {

        if (sharedPreferences.contains(email)) {
            return false
        }

        sharedPreferences.edit()
            .putString(email, password)
            .apply()
        return true
    }


    fun isValidUser(email: String, password: String): Boolean {
        val savedPassword = sharedPreferences.getString(email, null)
        return savedPassword == password
    }


    fun login(email: String, password: String): Boolean {
        if (isValidUser(email, password)) {
            _loginState.value = true
            saveLoginState(true)
            return true
        }
        return false
    }


    fun logout() {
        _loginState.value = false
        saveLoginState(false)
    }


    private fun saveLoginState(isLoggedIn: Boolean) {
        sharedPreferences.edit()
            .putBoolean("isLoggedIn", isLoggedIn)
            .apply()
    }


    private fun isUserLoggedIn(): Boolean {
        return sharedPreferences.getBoolean("isLoggedIn", false)
    }
}

