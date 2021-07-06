package id.learn.android.theinventory.presentation.peminjaman.status.listpinjaman

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import id.learn.android.theinventory.R
import id.learn.android.theinventory.databinding.FragmentListStatusPeminjamanBinding
import id.learn.android.theinventory.databinding.FragmentWelcomeBinding
import id.learn.android.theinventory.domain.model.Peminjaman
import id.learn.android.theinventory.presentation.main.MainActivity
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
        setHasOptionsMenu(true)
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

        binding.pbListStatusPeminjaman.isVisible = true
        vm.getListPeminjaman()
        vm.listPeminjaman?.observe(viewLifecycleOwner, Observer {
            it?.let { listPeminjaman ->
                binding.pbListStatusPeminjaman.isVisible = false
                val nim = (activity as MainActivity).userNim
                val list = ArrayList<Peminjaman>()
                listPeminjaman.forEach { item ->
                    if (item.idMahasiswaPeminjam.equals(nim)){
                        list.add(item)
                    }
                }
                statusPeminjamanAdapter.setData(list)
            }
        })

        onItemClick()
    }


    private fun onItemClick(){

        statusPeminjamanAdapter.onItemClick = { data ->
            val bundle = bundleOf(
                "dataPeminjaman" to data
            )
            navController.navigate(R.id.action_listStatusPeminjamanFragment_to_detailStatusPeminjamanFragment, bundle)
        }
    }

    fun initRv() = binding.rvStatusPeminjaman.apply {
        statusPeminjamanAdapter = StatusPeminjamanAdapter()

       layoutManager = LinearLayoutManager(requireActivity())

        val divider = DividerItemDecoration(
            requireActivity(),
            (layoutManager as LinearLayoutManager).orientation
        )
        addItemDecoration(divider)
        adapter = statusPeminjamanAdapter
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