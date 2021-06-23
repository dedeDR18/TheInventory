package id.learn.android.theinventory.presentation.databarang

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import id.learn.android.theinventory.R
import id.learn.android.theinventory.databinding.FragmentDataBarangBinding
import id.learn.android.theinventory.databinding.FragmentIsiDataPeminjamanBinding
import id.learn.android.theinventory.databinding.FragmentWelcomeBinding


class DataBarangFragment : Fragment() {

    private var _binding: FragmentDataBarangBinding? = null
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
        _binding = FragmentDataBarangBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}