package id.learn.android.theinventory.presentation.peminjaman.status.listpinjaman

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import id.learn.android.theinventory.R
import id.learn.android.theinventory.databinding.FragmentListStatusPeminjamanBinding
import id.learn.android.theinventory.databinding.FragmentWelcomeBinding
import id.learn.android.theinventory.presentation.peminjaman.pilihbarang.PilihBarangAdapter
import org.koin.android.viewmodel.ext.android.viewModel


class ListStatusPeminjamanFragment : Fragment() {

    private var _binding: FragmentListStatusPeminjamanBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private val vm: StatusPeminjamanViewModel by viewModel()
    private lateinit var statusPeminjamanAdapter: StatusPeminjamanAdapter

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

        initRv()

        onItemClick()
    }

    private fun onItemClick(){
        statusPeminjamanAdapter.onItemClick = {
            navController.navigate(R.id.action_listStatusPeminjamanFragment_to_detailStatusPeminjamanFragment)
        }
    }

    fun initRv() = binding.rvStatusPeminjaman.apply {
        statusPeminjamanAdapter = StatusPeminjamanAdapter()

       layoutManager = LinearLayoutManager(requireActivity())
        adapter = statusPeminjamanAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}