package id.learn.android.theinventory.presentation.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import id.learn.android.theinventory.R
import id.learn.android.theinventory.databinding.FragmentLoginBinding
import id.learn.android.theinventory.presentation.main.MainActivity
import org.koin.android.viewmodel.ext.android.viewModel


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private val vm: LoginViewModel by viewModel()
    private val email = "d@gmail.com"
    private var pass = "dededede"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

        binding.btnLogin.setOnClickListener {
            //navController.navigate(R.id.action_loginFragment_to_homeFragment)
            vm.login(email, pass)
            vm.currentUser!!.observe(viewLifecycleOwner, Observer { currentUser ->
                if (currentUser.role.equals("admin")){
                    Toast.makeText(requireActivity(), "kamu adalah admin", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireActivity(), "kamu adalah mahasiswa", Toast.LENGTH_SHORT).show()
                }
            })
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