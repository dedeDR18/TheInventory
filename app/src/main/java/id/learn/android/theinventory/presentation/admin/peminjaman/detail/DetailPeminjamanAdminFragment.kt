package id.learn.android.theinventory.presentation.admin.peminjaman.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import id.learn.android.theinventory.R
import id.learn.android.theinventory.databinding.FragmentDetailPeminjamanAdminBinding
import id.learn.android.theinventory.databinding.FragmentDetailStatusPeminjamanBinding
import id.learn.android.theinventory.databinding.FragmentPeminjamanAdminBinding
import id.learn.android.theinventory.domain.model.Peminjaman
import org.koin.android.viewmodel.ext.android.viewModel
import java.lang.invoke.MethodHandleInfo
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


class DetailPeminjamanAdminFragment : Fragment() {

    private var _binding: FragmentDetailPeminjamanAdminBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private val vm: DetailPeminjamanAdminViewModel by viewModel()
    var dataPeminjaman: Peminjaman? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { bundle ->
            dataPeminjaman = bundle.getParcelable("dataPeminjaman")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailPeminjamanAdminBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        setOptionMenuStatus()

        binding.peminjaman = dataPeminjaman

        binding.btnSimpan.setOnClickListener {
            binding.pgDetailPeminjamanAdmin.isVisible = true
            vm.updateStatusPeminjaman(
                dataPeminjaman!!,
                binding.statusPeminjaman.editText?.text.toString()
            )
            vm.result?.observe(viewLifecycleOwner, Observer { result ->
                if (result) {
                    binding.pgDetailPeminjamanAdmin.isVisible = false
                    Toast.makeText(
                        requireActivity(),
                        "berhasil mengubah status peminjaman",
                        Toast.LENGTH_LONG
                    )
                        .show()
                } else {
                    binding.pgDetailPeminjamanAdmin.isVisible = false
                    Toast.makeText(
                        requireActivity(),
                        "gagal mengubah status peminjaman",
                        Toast.LENGTH_LONG
                    )
                        .show()
                }
            })
        }
    }

    private fun setOptionMenuStatus() {
        val items = listOf("sedang diproses", "disetujui")
        val adapter = ArrayAdapter(requireContext(), R.layout.item_list_status, items)
        (binding.statusPeminjaman.editText as? AutoCompleteTextView)?.setAdapter(adapter)
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