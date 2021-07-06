package id.learn.android.theinventory.presentation.admin.detailhistory

import android.os.Bundle
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
import id.learn.android.theinventory.R
import id.learn.android.theinventory.databinding.FragmentDetailHistoryAdminBinding
import id.learn.android.theinventory.databinding.FragmentDetailPeminjamanAdminBinding
import id.learn.android.theinventory.domain.model.Peminjaman
import id.learn.android.theinventory.presentation.admin.peminjaman.detail.DetailPeminjamanAdminViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class DetailHistoryAdminFragment : Fragment() {

    private var _binding: FragmentDetailHistoryAdminBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private val vm: DetailHistoryAdminViewModel by viewModel()
    var dataHistory: Peminjaman? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { bundle ->
            dataHistory = bundle.getParcelable("dataPeminjaman")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailHistoryAdminBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOptionMenuStatus()

        binding.peminjaman = dataHistory

        binding.btnSimpan.setOnClickListener {
            binding.pgDetailHistoryPeminjamanAdmin.isVisible = true
            vm.updateStatusPeminjaman(
                dataHistory!!,
                binding.statusHistory.editText?.text.toString()
            )
            vm.result?.observe(viewLifecycleOwner, Observer { result ->
                if (result) {
                    binding.pgDetailHistoryPeminjamanAdmin.isVisible = false
                    Toast.makeText(
                        requireActivity(),
                        "berhasil mengubah status peminjaman",
                        Toast.LENGTH_LONG
                    )
                        .show()
                } else {
                    binding.pgDetailHistoryPeminjamanAdmin.isVisible = false
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
        val items = listOf("selesai", "bermasalah")
        val adapter = ArrayAdapter(requireContext(), R.layout.item_list_status, items)
        (binding.statusHistory.editText as? AutoCompleteTextView)?.setAdapter(adapter)
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