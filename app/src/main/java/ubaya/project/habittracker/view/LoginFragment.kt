package ubaya.project.habittracker.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.room.InvalidationTracker
import ubaya.project.habittracker.R
import ubaya.project.habittracker.databinding.FragmentLoginBinding
import ubaya.project.habittracker.viewmodel.LoginViewModel

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel
    private val loginAccounts = arrayOf(
        arrayOf("student", "123")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        if(viewModel.isLoggenIn()){
            findNavController().navigate(R.id.action_loginFragment_to_dashboardFragment)
            return
        }
        binding.btnLogin.setOnClickListener {
            checkLogin()
        }
        observeViewModel()
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
        viewModel.login(username, password)

//        val isLoginValid = loginAccounts.any { account ->
//            account[0] == username && account[1] == password
//        }
//
//        if (isLoginValid) {
//            findNavController().navigate(R.id.action_loginFragment_to_dashboardFragment)
//        } else {
//            binding.textInputLayout2.error = "Username atau password salah"
//        }
    }
    fun observeViewModel(){
        viewModel.loginResultLD.observe(viewLifecycleOwner, Observer{
            if(it == true){
                findNavController().navigate(R.id.action_loginFragment_to_dashboardFragment)
            } else{
                binding.textInputLayout2.error = "Username atau password salah"
            }
        })
        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                binding.btnLogin.isEnabled = false
            } else {
                binding.btnLogin.isEnabled = true
            }
        })
    }
}
