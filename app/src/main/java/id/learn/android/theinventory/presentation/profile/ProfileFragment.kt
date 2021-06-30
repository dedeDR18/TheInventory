package id.learn.android.theinventory.presentation.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import id.learn.android.theinventory.R
import id.learn.android.theinventory.databinding.FragmentProfileBinding
import id.learn.android.theinventory.databinding.FragmentWelcomeBinding
import id.learn.android.theinventory.presentation.login.LoginViewModel
import id.learn.android.theinventory.presentation.main.MainActivity
import org.koin.android.viewmodel.ext.android.viewModel


class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private val vm:ProfileViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val view = binding.root
        return view

    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)


        vm.setUserProfile()
        vm.userProfile.observe(viewLifecycleOwner, Observer { profile ->
            binding.user = profile
        })

        binding.btnUbahPass.setOnClickListener {
            navController.navigate(R.id.action_profileFragment_to_ubahPassFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}