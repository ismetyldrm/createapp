package com.example.diablo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.diablo.R

class RecyclerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recycler, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.recipeRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)

        val recipes = listOf(
            Recipe(1, R.drawable.kebab6, "Kebab", "Best kebab in Lodz."),
            Recipe(2, R.drawable.hamburger1, "Hamburger", "Delicious Hamburger."),
            Recipe(3, R.drawable.pizzaa, "Pizza", "Tasty, homemade pizza.")
        )

        recyclerView.adapter = RecipeAdapter(recipes)

        return view
    }
}