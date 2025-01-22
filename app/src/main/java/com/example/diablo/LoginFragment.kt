import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.diablo.CredentialsManager
import com.example.diablo.MyApp
import com.example.diablo.R
import RecyclerFragment
import com.example.diablo.RegisterFragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class LoginFragment : Fragment() {

    private lateinit var credentialsManager: CredentialsManager

    @SuppressLint("CommitTransaction")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_login, container, false)


        credentialsManager = (requireActivity().application as MyApp).credentialsManager

        val emailInput = view.findViewById<TextInputEditText>(R.id.emailEditText)
        val passwordInput = view.findViewById<TextInputEditText>(R.id.passwordEditText)
        val nextButton = view.findViewById<MaterialButton>(R.id.nextButton)
        val newMemberTextView = view.findViewById<TextView>(R.id.newMemberTextView)

        nextButton.setOnClickListener {
            val email = emailInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()


            if (email.isEmpty() || !credentialsManager.isEmailValid(email)) {
                Toast.makeText(requireContext(), "Please enter a valid email address", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            if (password.isEmpty() || !credentialsManager.isPasswordValid(password)) {
                Toast.makeText(requireContext(), "Password cannot be empty or less than 6 characters", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            if (credentialsManager.isValidUser(email, password)) {
                Toast.makeText(requireContext(), "Login Successful!", Toast.LENGTH_SHORT).show()
                (requireActivity().application as MyApp).credentialsManager.login(email, password)

                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, RecyclerFragment())
                    .addToBackStack(null)
                    .commit()
            } else {
                Toast.makeText(requireContext(), "Invalid email or password", Toast.LENGTH_SHORT).show()
            }
        }


        newMemberTextView.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, RegisterFragment())
                .addToBackStack(null)
                .commit()
        }

        return view
    }
}
