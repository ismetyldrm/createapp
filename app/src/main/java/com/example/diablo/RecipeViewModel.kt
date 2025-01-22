import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diablo.R
import com.example.diablo.Recipe
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RecipeViewModel : ViewModel() {


    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState

    private val allRecipes = listOf(
        Recipe(1, R.drawable.kebab6, "Kebab", "Best kebab in Lodz."),
        Recipe(2, R.drawable.hamburger1, "Hamburger", "Delicious Hamburger."),
        Recipe(3, R.drawable.pizzaa, "Pizza", "Tasty, homemade pizza."),
        Recipe(4, R.drawable.salad, "Salad", "Healthy salad.")
    )

    private var searchJob: Job? = null

    init {
        loadAllRecipes()
    }

    private fun loadAllRecipes() {
        _uiState.value = UiState(recipes = allRecipes, isLoading = false)
    }


    fun updateSearchQuery(query: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            if (query.isNotEmpty()) {
                _uiState.value = UiState(isLoading = true)
                delay(300)
                val filteredRecipes = allRecipes.filter {
                    it.title.contains(query, ignoreCase = true) || it.description.contains(query, ignoreCase = true)
                }
                _uiState.value = UiState(recipes = filteredRecipes, isLoading = false)
            } else {
                _uiState.value = UiState(recipes = allRecipes, isLoading = false)
            }
        }
    }
}

data class UiState(
    val recipes: List<Recipe> = emptyList(),
    val isLoading: Boolean = false
)




