package id.learn.android.theinventory.presentation.peminjaman.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import id.learn.android.theinventory.R
import id.learn.android.theinventory.databinding.FragmentIsiDataPeminjamanBinding
import id.learn.android.theinventory.databinding.FragmentWelcomeBinding


class IsiDataPeminjamanFragment : Fragment() {

    private var _binding: FragmentIsiDataPeminjamanBinding? = null
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
        _binding = FragmentIsiDataPeminjamanBinding.inflate(inflater, container, false)
        val view = binding.root
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        binding.btnPilihBarang.setOnClickListener {
            navController.navigate(R.id.action_isiDataPeminjamanFragment_to_listBarangFragment)
        }

        binding.btnKirim.setOnClickListener {
            navController.popBackStack()
        }

        binding.btnReset.setOnClickListener {
            Toast.makeText(requireActivity(), "Reset", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}