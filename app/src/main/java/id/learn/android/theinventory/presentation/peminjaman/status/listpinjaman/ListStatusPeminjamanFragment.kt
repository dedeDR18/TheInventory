package id.learn.android.theinventory.presentation.peminjaman.status.listpinjaman

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import id.learn.android.theinventory.R
import id.learn.android.theinventory.databinding.FragmentListStatusPeminjamanBinding
import id.learn.android.theinventory.databinding.FragmentWelcomeBinding


class ListStatusPeminjamanFragment : Fragment() {

    private var _binding: FragmentListStatusPeminjamanBinding? = null
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
        _binding = FragmentListStatusPeminjamanBinding.inflate(inflater, container, false)
        val view = binding.root
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        binding.btnToDetail.setOnClickListener {
            navController.navigate(R.id.action_listStatusPeminjamanFragment_to_detailStatusPeminjamanFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}