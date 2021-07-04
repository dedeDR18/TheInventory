package id.learn.android.theinventory.presentation.peminjaman.detail

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import id.learn.android.theinventory.R
import id.learn.android.theinventory.databinding.FragmentIsiDataPeminjamanBinding
import id.learn.android.theinventory.presentation.peminjaman.pilihbarang.ListChosenViewModel
import java.text.SimpleDateFormat
import java.util.*


class IsiDataPeminjamanFragment : Fragment() {

    private var _binding: FragmentIsiDataPeminjamanBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private val listChosenViewModel: ListChosenViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentIsiDataPeminjamanBinding.inflate(inflater, container, false)
        val view = binding.root
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        binding.btnPilihBarang.setOnClickListener {
            navController.navigate(R.id.action_isiDataPeminjamanFragment_to_listBarangFragment)
        }

        binding.btnKirim.setOnClickListener {
            checkAllField()
        }

        binding.btnReset.setOnClickListener {
            resetField()
        }

        val calendar = Calendar.getInstance()


        binding.btnTanggalPinjam.setOnClickListener {
            val datePicker = DatePickerDialog.OnDateSetListener { picker, year, month, day ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, day)
                formatTanggal(calendar, binding.tvTanggalPeminjaman)
            }
            getTanggal(datePicker, calendar)
        }

        binding.btnTanggalPengembalian.setOnClickListener {
            val datePicker = DatePickerDialog.OnDateSetListener { picker, year, month, day ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, day)
                formatTanggal(calendar, binding.tvTanggalPengembalian)
            }
            getTanggal(datePicker, calendar)
        }

        observePilihanBarang()
    }

    private fun observePilihanBarang() {
        listChosenViewModel.listChosen.observe(viewLifecycleOwner, Observer { it ->
            it?.let { barangYangDipilih ->
                val barang = barangYangDipilih.groupingBy { it.nama }.eachCount()
                var textBarang = barang.toString()
                textBarang = textBarang.substring(1, textBarang.length - 1)
                binding.tvBarangDipinjam.text = textBarang
            }
        })
    }

    private fun checkAllField(){
        val nama = binding.otfFullname.editText!!.text.toString().isNotEmpty()
        val nim = binding.otfNim.editText!!.text.toString().isNotEmpty()
        val barang = binding.tvBarangDipinjam.text.toString().isNotEmpty()
        val tglPinjam = binding.tvTanggalPeminjaman.text.toString().isNotEmpty()
        val tglKembali = binding.tvTanggalPengembalian.text.toString().isNotEmpty()

        if (nama && nim && barang && tglKembali && tglPinjam){
            Toast.makeText(requireActivity(), "Berhasil simpan", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireActivity(), "Mohon untuk melengkapi data...", Toast.LENGTH_SHORT).show()
        }
    }

    private fun formatTanggal(calendar: Calendar, textView: TextView){
        val myFormat = "dd-MM-yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.UK)
        textView.text = sdf.format(calendar.time)
    }

    fun getTanggal(datePicker: DatePickerDialog.OnDateSetListener, calendar: Calendar) {
        DatePickerDialog(
            requireActivity(),
            datePicker,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    private fun resetField(){
        binding.tvTanggalPeminjaman.text = ""
        binding.tvTanggalPengembalian.text = ""
        binding.otfFullname.editText!!.text.clear()
        binding.otfNim.editText!!.text.clear()
        binding.tvBarangDipinjam.text = ""
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}