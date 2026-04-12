package ubaya.project.habittracker.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ubaya.project.habittracker.R
import ubaya.project.habittracker.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val loginAccounts = arrayOf(
        arrayOf("student", "123")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.btnLogin.setOnClickListener {
            checkLogin()
        }
        return binding.root
    }

    private fun checkLogin() {
        val username = binding.txtUsername.text.toString().trim()
        val password = binding.txtPassword.text.toString().trim()

        binding.textInputLayout.error = null
        binding.textInputLayout2.error = null

        if (username.isEmpty()) {
            binding.textInputLayout.error = "Username belum diisi"
            return
        }

        if (password.isEmpty()) {
            binding.textInputLayout2.error = "Password belum diisi"
            return
        }

        val isLoginValid = loginAccounts.any { account ->
            account[0] == username && account[1] == password
        }

        if (isLoginValid) {
            findNavController().navigate(R.id.action_loginFragment_to_dashboardFragment)
        } else {
            binding.textInputLayout2.error = "Username atau password salah"
        }
    }
}
