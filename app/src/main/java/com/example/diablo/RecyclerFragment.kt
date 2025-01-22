import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.diablo.MyApp
import com.example.diablo.R
import com.example.diablo.RecipeAdapter
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.flow.collectLatest

class RecyclerFragment : Fragment() {

    private val recipeViewModel: RecipeViewModel by viewModels()
    private lateinit var recipeAdapter: RecipeAdapter
    private lateinit var progressIndicator: CircularProgressIndicator
    private lateinit var logoutButton: MaterialButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_recycler, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val recyclerView: RecyclerView = view.findViewById(R.id.recipeRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recipeAdapter = RecipeAdapter(emptyList())
        recyclerView.adapter = recipeAdapter

        progressIndicator = view.findViewById(R.id.progressIndicator)


        logoutButton = view.findViewById(R.id.logoutButton)
        logoutButton.setOnClickListener {
            (requireActivity().application as MyApp).credentialsManager.logout()
        }


        val searchView: androidx.appcompat.widget.SearchView = view.findViewById(R.id.searchView)
        searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { recipeViewModel.updateSearchQuery(it) }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { recipeViewModel.updateSearchQuery(it) }
                return true
            }
        })


        lifecycleScope.launchWhenStarted {
            (requireActivity().application as MyApp).credentialsManager.loginState.collectLatest { isLoggedIn ->
                if (!isLoggedIn) {
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, LoginFragment())
                        .addToBackStack(null)
                        .commit()
                }
            }
        }


        lifecycleScope.launchWhenStarted {
            recipeViewModel.uiState.collectLatest { uiState ->
                recipeAdapter.updateData(uiState.recipes)
                progressIndicator.visibility = if (uiState.isLoading) View.VISIBLE else View.GONE
                recyclerView.visibility = if (uiState.isLoading) View.GONE else View.VISIBLE
            }
        }
    }
}


