package id.learn.android.theinventory.presentation.peminjaman.pilihbarang

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import id.learn.android.theinventory.R
import id.learn.android.theinventory.databinding.FragmentListBarangBinding
import id.learn.android.theinventory.databinding.FragmentWelcomeBinding


class ListBarangFragment : Fragment() {

    private var _binding: FragmentListBarangBinding? = null
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
        _binding = FragmentListBarangBinding.inflate(inflater, container, false)
        val view = binding.root
        return view

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}