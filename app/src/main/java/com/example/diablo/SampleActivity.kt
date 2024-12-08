package com.example.diablo

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class SampleActivity : AppCompatActivity() {

    private var isFragmentAVisible = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample)

        // Set initial fragment (FragmentA)
        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainer, FragmentA())
            .commit()


        val switchButton: Button = findViewById(R.id.switchButton)
        switchButton.setOnClickListener {
            switchFragment()
        }
    }

    // Function to switch between FragmentA and FragmentB
    private fun switchFragment() {
        val fragment: Fragment = if (isFragmentAVisible) {
            FragmentB()
        } else {
            FragmentA()
        }

        // Perform the fragment transaction
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment) // Replace the current fragment
            .commit()

        // Toggle the current fragment state
        isFragmentAVisible = !isFragmentAVisible
    }
}