package id.learn.android.theinventory.presentation.peminjaman.pilihbarang

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import id.learn.android.theinventory.R
import id.learn.android.theinventory.databinding.FragmentListBarangBinding
import id.learn.android.theinventory.databinding.FragmentWelcomeBinding
import id.learn.android.theinventory.presentation.databarang.BarangAdapter
import id.learn.android.theinventory.utils.ListBarang
import org.koin.android.viewmodel.ext.android.viewModel


class PilihBarangFragment : Fragment() {

    private var _binding: FragmentListBarangBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private val vm: PilihBarangViewModel by viewModel()
    private val listChosenViewModel:ListChosenViewModel by activityViewModels()
    private lateinit var pilihBarangAdapter: PilihBarangAdapter
    private val listChoosen = ArrayList<ListBarang>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        initRv()
        vm.getDataBarang()
        binding.pbPilihBarang.visibility = View.VISIBLE
        binding.btnSimpan.isVisible = false

        vm.listBarang!!.observe(viewLifecycleOwner, Observer { data ->
            data?.let {
                val arrayBarang = ArrayList<ListBarang>()
                data.forEach { data ->
                    val lb = ListBarang(
                        nama = data.namaBarang,
                        merk = data.merkBarang,
                        itemCount = 0
                    )
                    arrayBarang.add(lb)
                }
                binding.btnSimpan.isVisible = true
                binding.pbPilihBarang.visibility = View.GONE
                pilihBarangAdapter.setData(arrayBarang)
            }
        })

        onAddButtonClick()
        onMinusButtonCLick()


        binding.btnSimpan.setOnClickListener {
            listChosenViewModel.setListChoosen(listChoosen)
            navController.popBackStack()
        }

    }

    fun initRv() = binding.rvPilihbarang.apply {
        pilihBarangAdapter = PilihBarangAdapter()

        layoutManager = LinearLayoutManager(requireActivity())
        adapter = pilihBarangAdapter
    }

    private fun onAddButtonClick() {
        pilihBarangAdapter.onAddButtonCLick = { data ->
            val item = ListBarang(
                nama = data.nama,
                merk = data.merk,
                itemCount = 0
            )
            listChoosen.add(item)
        }

    }

    private fun onMinusButtonCLick() {
        pilihBarangAdapter.onMinusButtonCLick = { data ->
           listChoosen.remove(data)
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