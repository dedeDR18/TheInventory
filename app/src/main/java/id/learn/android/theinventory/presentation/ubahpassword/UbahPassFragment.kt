package id.learn.android.theinventory.presentation.ubahpassword

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import id.learn.android.theinventory.R
import id.learn.android.theinventory.databinding.FragmentUbahPassBinding
import id.learn.android.theinventory.databinding.FragmentWelcomeBinding


class UbahPassFragment : Fragment() {

    private var _binding: FragmentUbahPassBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUbahPassBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        binding.btnSimpan.setOnClickListener {
            navController.popBackStack()
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.clear()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}