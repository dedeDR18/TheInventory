package id.learn.android.theinventory.presentation.welcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import id.learn.android.theinventory.R
import id.learn.android.theinventory.databinding.FragmentDaftarBinding
import id.learn.android.theinventory.databinding.FragmentWelcomeBinding
import id.learn.android.theinventory.presentation.main.MainActivity

class WelcomeFragment : Fragment() {

    private var _binding: FragmentWelcomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        binding.btnDaftar.setOnClickListener {
            navController.navigate(R.id.action_welcomeFragment_to_daftarFragment)
        }

        binding.btnLogin.setOnClickListener {
            navController.navigate(R.id.action_welcomeFragment_to_loginFragment)
        }
    }

    override fun onStart() {
        super.onStart()
        if (FirebaseAuth.getInstance().currentUser != null){
            navController.navigate(R.id.action_welcomeFragment_to_homeFragment)
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.hide()
        (activity as MainActivity).setBottomNavViewVisibility(false)
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity).supportActionBar?.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //lanjutin di fragment lain

}