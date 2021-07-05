package id.learn.android.theinventory.presentation.admin.peminjaman

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
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import id.learn.android.theinventory.R
import id.learn.android.theinventory.databinding.FragmentPeminjamanAdminBinding
import id.learn.android.theinventory.presentation.main.MainActivity
import org.koin.android.viewmodel.ext.android.viewModel


class PeminjamanAdminFragment : Fragment() {

    private var _binding: FragmentPeminjamanAdminBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private val vm: PeminjamanAdminViewModel by viewModel()
    private lateinit var peminjamanAdminAdapter: PeminjamanAdminAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPeminjamanAdminBinding.inflate(inflater, container, false)
        val view = binding.root
        return view


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        initRv()

        binding.pbPeminjamanAdmin.isVisible = true
        vm.getListPeminjaman()
        vm.listPeminjaman?.observe(viewLifecycleOwner, Observer {
            it?.let { listPeminjaman ->
                binding.pbPeminjamanAdmin.isVisible = false
                peminjamanAdminAdapter.setData(listPeminjaman)
            }
        })

        onItemClick()
    }

    private fun onItemClick(){

        peminjamanAdminAdapter.onItemClick = { data ->
            val bundle = bundleOf(
                "dataPeminjaman" to data
            )
            navController.navigate(R.id.action_peminjamanAdminFragment_to_detailPeminjamanAdminFragment, bundle)
        }
    }

    fun initRv() = binding.rvPemijamanAdmin.apply {
        peminjamanAdminAdapter = PeminjamanAdminAdapter()

        layoutManager = LinearLayoutManager(requireActivity())

        val divider = DividerItemDecoration(
            requireActivity(),
            (layoutManager as LinearLayoutManager).orientation
        )
        addItemDecoration(divider)
        adapter = peminjamanAdminAdapter
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