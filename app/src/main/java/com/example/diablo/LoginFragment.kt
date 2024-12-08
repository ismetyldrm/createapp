import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.diablo.CredentialsManager
import com.example.diablo.MainActivity
import com.example.diablo.R
import com.example.diablo.RegisterFragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class LoginFragment : Fragment() {

    private lateinit var credentialsManager: CredentialsManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_login, container, false)


        credentialsManager = CredentialsManager(requireContext())


        val emailInput = view.findViewById<TextInputEditText>(R.id.emailEditText)
        val passwordInput = view.findViewById<TextInputEditText>(R.id.passwordEditText)
        val nextButton = view.findViewById<MaterialButton>(R.id.nextButton)
        val newMemberTextView = view.findViewById<TextView>(R.id.newMemberTextView)


        nextButton.setOnClickListener {
            val email = emailInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()


            if (!credentialsManager.isEmailValid(email)) {
                Toast.makeText(requireContext(), "Invalid email format", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!credentialsManager.isPasswordValid(password)) {
                Toast.makeText(requireContext(), "Password cannot be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            if (credentialsManager.isValidUser(email, password)) {
                Toast.makeText(requireContext(), "Login Successful!", Toast.LENGTH_SHORT).show()


                val intent = Intent(requireContext(), MainActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
            } else {
                Toast.makeText(requireContext(), "Invalid email or password", Toast.LENGTH_SHORT).show()
            }
        }


        newMemberTextView.setOnClickListener {
           parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, RegisterFragment()) // Use the correct container ID

        }

        return view
    }
}