package com.example.diablo


import LoginFragment
import RecyclerFragment
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.diablo.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        if (savedInstanceState == null) {

            val isUserLoggedIn = checkUserLoginStatus()

            if (isUserLoggedIn) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, RecyclerFragment())
                    .commit()
            } else {
                showLoginFragment()
            }
        }
    }



    private fun checkUserLoginStatus(): Boolean {

        return false
    }
    private fun showLoginFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, LoginFragment())
            .commit()

    }


    fun switchToRegisterFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, RegisterFragment())
            .addToBackStack(null)
            .commit()
    }
}
