package com.example.diablo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.diablo.R

class RecipeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_recycler)

        // RecyclerView'i bul
        val recyclerView: RecyclerView = findViewById(R.id.recipeRecyclerView)

        // LinearLayoutManager kullanarak RecyclerView'i yapılandır
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Yemek verisi
        val recipes = listOf(
            Recipe(1, R.drawable.kebab6, "Kebab", "Best kebab in Lodz."),
            Recipe(2, R.drawable.hamburger1, "Hamburger", "Delicious Hamburger."),
            Recipe(3, R.drawable.pizzaa, "Pizza", "Tasty, homemade pizza.")
        )

        // Adapter ayarla
        recyclerView.adapter = RecipeAdapter(recipes)
    }
}
