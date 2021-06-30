package id.learn.android.theinventory.presentation.databarang

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import id.learn.android.theinventory.R
import id.learn.android.theinventory.databinding.FragmentDataBarangBinding
import id.learn.android.theinventory.databinding.FragmentIsiDataPeminjamanBinding
import id.learn.android.theinventory.databinding.FragmentWelcomeBinding
import id.learn.android.theinventory.presentation.main.MainActivity
import org.koin.android.viewmodel.ext.android.viewModel


class DataBarangFragment : Fragment() {

    private var _binding: FragmentDataBarangBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private val vm:DataBarangViewModel by viewModel()
    private lateinit var barangAdapter:BarangAdapter

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRv()

        binding.pbDatabarang.visibility = View.VISIBLE
        vm.getDataBarang()
        vm.listBarang!!.observe(viewLifecycleOwner, Observer { data ->
            data?.let {
                binding.pbDatabarang.visibility = View.GONE
                barangAdapter.setData(it)
            }
        })

    }

    fun initRv() = binding.rvDatabarang.apply {
        barangAdapter = BarangAdapter()

        layoutManager = LinearLayoutManager(requireActivity())
        adapter = barangAdapter
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