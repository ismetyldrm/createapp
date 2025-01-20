import androidx.lifecycle.ViewModel
import com.example.diablo.R
import com.example.diablo.Recipe
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RecipeViewModel : ViewModel() {

    private val _recipes = MutableStateFlow<List<Recipe>>(emptyList())
    val recipes: StateFlow<List<Recipe>> = _recipes

    private val allRecipes = listOf(
        Recipe(1, R.drawable.kebab6, "Kebab", "Best kebab in Lodz."),
        Recipe(2, R.drawable.hamburger1, "Hamburger", "Delicious Hamburger."),
        Recipe(3, R.drawable.pizzaa, "Pizza", "Tasty, homemade pizza."),
        Recipe(4, R.drawable.salad, "Salad", "Healthy salad.")
    )

    init {
        _recipes.value = allRecipes // Başlangıçta tüm tarifleri yükle
    }

    fun updateSearchQuery(query: String) {
        if (query.length >= 3) {
            val filteredRecipes = allRecipes.filter {
                it.title.contains(query, ignoreCase = true) ||
                        it.description.contains(query, ignoreCase = true)
            }
            if (_recipes.value != filteredRecipes) { // Liste aynı değilse güncelle
                _recipes.value = filteredRecipes
            }
        } else if (query.isEmpty()) {
            _recipes.value = allRecipes // Arama boşsa tüm tarifleri göster
        }
    }
}




