package id.learn.android.theinventory.presentation.history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import id.learn.android.theinventory.R
import id.learn.android.theinventory.databinding.FragmentHistoryBinding
import id.learn.android.theinventory.domain.model.Peminjaman
import id.learn.android.theinventory.presentation.main.MainActivity
import id.learn.android.theinventory.presentation.main.MainViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private val vm: HistoryViewModel by viewModel()
    private lateinit var historyAdapter:HistoryAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        val view = binding.root
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userRole = (activity as MainActivity).userRole

        navController = Navigation.findNavController(view)


        if (userRole.equals("Admin")){
            settingForAdmin()
        } else {
            settingForMahasiswa()
        }
    }


    private fun settingForMahasiswa(){
        initRv()

        binding.pbHistoryPeminjaman.isVisible = true
        vm.getListHistoryPeminjaman()
        vm.listHistoryPeminjaman?.observe(viewLifecycleOwner, Observer { listHistoryPeminjaman ->
            binding.pbHistoryPeminjaman.isVisible = false
            val nim = (activity as MainActivity).userNim
            val list = ArrayList<Peminjaman>()
            listHistoryPeminjaman.forEach { itemHistory ->
                if (itemHistory.idMahasiswaPeminjam.equals(nim)){
                    list.add(itemHistory)
                }
            }
            historyAdapter.setData(list)
        })



        onItemClick()
    }


    private fun settingForAdmin(){

        initRv()

        binding.pbHistoryPeminjaman.isVisible = true
        vm.getListHistoryPeminjaman()
        vm.listHistoryPeminjaman?.observe(viewLifecycleOwner, Observer { listHistoryPeminjaman ->
            binding.pbHistoryPeminjaman.isVisible = false
            historyAdapter.setData(listHistoryPeminjaman)
        })

        onItemClick()
    }

    private fun onItemClick(){

        historyAdapter.onItemClick = { data ->
            val bundle = bundleOf(
                "dataPeminjaman" to data
            )
            val userRole = (activity as MainActivity).userRole
            if (userRole.equals("Admin")){
                navController.navigate(R.id.action_historyFragment_to_detailHistoryAdminFragment, bundle)
            } else {
                //navController.navigate(R.id.action_historyFragment_to_detailHistoryAdminFragment, bundle)
            }

        }
    }

    fun initRv() = binding.rvHistoryPeminjaman.apply {
        historyAdapter = HistoryAdapter()

        layoutManager = LinearLayoutManager(requireActivity())

        val divider = DividerItemDecoration(
            requireActivity(),
            (layoutManager as LinearLayoutManager).orientation
        )
        addItemDecoration(divider)
        adapter = historyAdapter
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.show()
        (activity as MainActivity).setBottomNavViewVisibility(true)
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