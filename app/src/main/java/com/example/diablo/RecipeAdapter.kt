package com.example.diablo


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class RecipeAdapter(
    private var recipeList: List<Recipe>
) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.recipe_image)
        val titleTextView: TextView = itemView.findViewById(R.id.recipe_title)
        val descriptionTextView: TextView = itemView.findViewById(R.id.recipe_description)
        val likeButton: Button = itemView.findViewById(R.id.like_button)
        val shareButton: Button = itemView.findViewById(R.id.share_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.items, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipeList[position]
        holder.imageView.setImageResource(recipe.imageResId)
        holder.titleTextView.text = recipe.title
        holder.descriptionTextView.text = recipe.description

        holder.itemView.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Clicked: ${recipe.title}", Toast.LENGTH_SHORT).show()
        }

        holder.likeButton.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Liked: ${recipe.title}", Toast.LENGTH_SHORT).show()
        }

        holder.shareButton.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Shared: ${recipe.title}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }

    // Data güncellemesi yapacak fonksiyon

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newRecipes: List<Recipe>) {
        recipeList = newRecipes
        notifyDataSetChanged() // Veriler değiştiği için listeyi yeniden bağla
    }
}

