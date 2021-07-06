package id.learn.android.theinventory.presentation.peminjaman.detail

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import id.learn.android.theinventory.R
import id.learn.android.theinventory.databinding.FragmentIsiDataPeminjamanBinding
import id.learn.android.theinventory.domain.model.Peminjaman
import id.learn.android.theinventory.presentation.peminjaman.pilihbarang.ListChosenViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


class IsiDataPeminjamanFragment : Fragment() {

    private var _binding: FragmentIsiDataPeminjamanBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private val listChosenViewModel: ListChosenViewModel by activityViewModels()
    private val vm:IsiDataPeminjamanViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
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
        val calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+7:00"))

        binding.btnPilihBarang.setOnClickListener {
            navController.navigate(R.id.action_isiDataPeminjamanFragment_to_listBarangFragment)
        }


        binding.btnKirim.setOnClickListener {
            checkAllField(calendar)


        }

        binding.btnReset.setOnClickListener {
            resetField()
        }




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

    private fun observeSuccessSubmitPeminjaman(){
        vm.success?.observe(viewLifecycleOwner, Observer {
            it?.let{ success ->
                if (success) {
                    binding.pbIsidatapeminjaman.isVisible = false
                    Toast.makeText(requireActivity(), "Data anda sudah disimpan silahkan tunggu konfirmasi", Toast.LENGTH_SHORT).show()
                    navController.popBackStack()
                } else {
                    resetField()
                    binding.pbIsidatapeminjaman.isVisible = false
                    Toast.makeText(requireActivity(), "gagal menyimpan permintaan", Toast.LENGTH_SHORT).show()
                }
            }
        })
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

    private fun checkAllField(calendar: Calendar){
        val nama = binding.otfFullname.editText!!.text.toString().isNotEmpty()
        val nim = binding.otfNim.editText!!.text.toString().isNotEmpty()
        val barang = binding.tvBarangDipinjam.text.toString().isNotEmpty()
        val tglPinjam = binding.tvTanggalPeminjaman.text.toString().isNotEmpty()
        val tglKembali = binding.tvTanggalPengembalian.text.toString().isNotEmpty()

        if (nama && nim && barang && tglKembali && tglPinjam){
            binding.pbIsidatapeminjaman.isVisible = true

            val nim = binding.otfNim.editText!!.text.toString().toLong()
            val tanggalPeminjaman = binding.tvTanggalPeminjaman.text.toString()
            val barang = binding.tvBarangDipinjam.text.toString()
            val tglKembali = binding.tvTanggalPengembalian.text.toString()
            val nama =  binding.otfFullname.editText!!.text.toString()


            val dataPeminjaman = Peminjaman(
                idPeminjaman = setIdPeminjaman(calendar, tanggalPeminjaman, nim),
                idMahasiswaPeminjam = nim,
                namaPeminjam = nama,
                barang = barang,
                tanggalPeminjaman = tanggalPeminjaman,
                tanggalPengembalian = tglKembali,
                status = "sedang diproses"
            )

            vm.sendFormPeminjaman(dataPeminjaman)
            observeSuccessSubmitPeminjaman()
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

    private fun setIdPeminjaman(cal: Calendar, tanggalPeminjaman:String, nim:Long):String{
        val currentLocalTime: Date = cal.getTime()
        val date: DateFormat = SimpleDateFormat("HH:mm")
        date.setTimeZone(TimeZone.getTimeZone("GMT+7:00"))

        val time: String = date.format(currentLocalTime).replace(":", "")

        val tgl = tanggalPeminjaman.replace("-", "")
        return nim.toString() + tgl + time
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