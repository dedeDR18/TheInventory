package id.learn.android.theinventory.presentation.login

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import id.learn.android.theinventory.R
import id.learn.android.theinventory.databinding.FragmentLoginBinding
import id.learn.android.theinventory.presentation.main.MainActivity
import id.learn.android.theinventory.presentation.main.MainViewModel
import id.learn.android.theinventory.utils.UserManager
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private val vm: LoginViewModel by viewModel()
    private val vmMain: MainViewModel by activityViewModels()
    private var isEmailValid = false
    private var isPasswordValid = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)



        loginValidation()

        binding.btnLogin.setOnClickListener {
            //
            val email = binding.otfEmail.editText!!.text.toString()
            val password = binding.otfPassword.editText!!.text.toString()
            binding.pbLogin.visibility = View.VISIBLE
            binding.otfEmail.clearFocus()
            binding.otfPassword.clearFocus()


            vm.login(email, password)
            vm.currentUser!!.observe(viewLifecycleOwner, Observer { currentUser ->
                if (currentUser != null) {
                    binding.pbLogin.visibility = View.GONE
                    vmMain.setUser(currentUser)
//                    (activity as MainActivity).saveUserRole(currentUser.role)
//                    (activity as MainActivity).updateUserRole()

                    navController.navigate(R.id.action_loginFragment_to_homeFragment)
                } else {
                    binding.pbLogin.visibility = View.GONE
                    Toast.makeText(requireActivity(), "Login gagal, periksa kembali email & password anda...", Toast.LENGTH_LONG)
                        .show()
                }
            })
        }
    }

    private fun loginValidation() {

        binding.btnLogin.isEnabled = false
        binding.otfEmail.editText!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (android.util.Patterns.EMAIL_ADDRESS.matcher(text).matches()) {
                    isEmailValid = true
                    binding.otfEmail.error = null
                    binding.otfEmail.isErrorEnabled = false
                    enableButtonLogin()
                    Log.d("TAG TEXT", "text matches")
                } else {
                    isEmailValid = false
                    binding.otfEmail.error = "Periksa format email"
                    Log.d("TAG TEXT", "text not matches")
                    enableButtonLogin()
                }
            }

            override fun afterTextChanged(text: Editable?) {

            }

        })

        binding.otfPassword.editText!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (text!!.length < 6) {
                    isPasswordValid = false
                    binding.otfPassword.error = "Password tidak boleh kurang dari 6 karakter"
                    enableButtonLogin()
                } else {
                    binding.otfPassword.error = null
                    isPasswordValid = true
                    binding.otfPassword.isErrorEnabled = false
                    enableButtonLogin()
                }
            }

            override fun afterTextChanged(text: Editable?) {

            }

        })

    }

    private fun enableButtonLogin() {
        binding.btnLogin.isEnabled = isEmailValid && isPasswordValid
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.clear()
    }

    override fun onStart() {
        super.onStart()
        if (FirebaseAuth.getInstance().currentUser != null){
            navController.navigate(R.id.action_loginFragment_to_homeFragment)
        }
    }


    override fun onResume() {
        super.onResume()
        (activity as MainActivity).setBottomNavViewVisibility(false)
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity).supportActionBar?.show()
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}