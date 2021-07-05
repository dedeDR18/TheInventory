package id.learn.android.theinventory.presentation.daftar

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import id.learn.android.theinventory.R
import id.learn.android.theinventory.databinding.FragmentDaftarBinding
import id.learn.android.theinventory.databinding.FragmentIsiDataPeminjamanBinding
import id.learn.android.theinventory.databinding.FragmentWelcomeBinding
import id.learn.android.theinventory.domain.model.User
import org.koin.android.ext.android.bind
import org.koin.android.viewmodel.ext.android.viewModel

class DaftarFragment : Fragment() {

    private var _binding: FragmentDaftarBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private val vm:DaftarViewModel by viewModel()
    private var email = "d@gmail.com"
    private var pass = "dededede"
    private var isEmailValid = false
    private var isPasswordValid = false
    private var isNamaValid = false
    private var isNimValid = false
    private var isKelasValid = false
    private var isNoHpValid = true





    //firebase stuff
    private lateinit var mAuth: FirebaseAuth
    private lateinit var realtimeDb: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDaftarBinding.inflate(inflater, container, false)
        val view = binding.root
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        //firebase stuff
        mAuth = FirebaseAuth.getInstance()
        realtimeDb = FirebaseDatabase.getInstance()

        formValidation()


        binding.btnDaftar.setOnClickListener {

            val email = binding.otfEmail.editText!!.text.toString()
            val password = binding.otfPassword.editText!!.text.toString()
            val nama = binding.otfFullname.editText!!.text.toString()
            val kelas = binding.otfKelas.editText!!.text.toString()
            val nim = binding.otfNim.editText!!.text.toString().toLong()
            val noHp = if(binding.otfNohp.editText!!.text.toString().isEmpty()) "-" else binding.otfNohp.editText!!.text.toString()
            binding.pbDaftar.visibility = View.VISIBLE
            clearFocusTextField()
            vm.createUser(email,password,nama,nim,kelas,noHp)
            vm.createdUserLiveData!!.observe(viewLifecycleOwner, Observer { user ->
                if(user.role.isNotEmpty()){
                    binding.pbDaftar.visibility = View.GONE
                    Toast.makeText(requireActivity(), "berhasil", Toast.LENGTH_SHORT).show()
                    navController.navigate(R.id.action_daftarFragment_to_loginFragment)
                } else {
                    Toast.makeText(requireActivity(), "gagal", Toast.LENGTH_SHORT).show()
                    binding.pbDaftar.visibility = View.GONE
                }
            })

        }


    }

    private fun enableButtonLogin() {
        binding.btnDaftar.isEnabled = isEmailValid && isPasswordValid && isNamaValid && isNimValid && isNoHpValid && isKelasValid
    }

    private fun clearFocusTextField(){
        binding.otfEmail.clearFocus()
        binding.otfPassword.clearFocus()
        binding.otfFullname.clearFocus()
        binding.otfNim.clearFocus()
        binding.otfKelas.clearFocus()
        binding.otfNohp.clearFocus()
    }


    private fun formValidation(){
        binding.btnDaftar.isEnabled = false
        //name
        binding.otfFullname.editText!!.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(nama: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (nama!!.length > 50){
                    isNamaValid = false
                    binding.otfFullname.error = "Nama tidak boleh lebih dari 50 karakter"
                    enableButtonLogin()
                } else {
                    isNamaValid = true
                    binding.otfFullname.error = null
                    binding.otfFullname.isErrorEnabled = false
                    enableButtonLogin()
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

        //nim
        binding.otfNim.editText!!.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(nim: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (nim!!.length != 11){
                    isNimValid = false
                    binding.otfNim.error = "Periksa kembali NIM anda"
                    enableButtonLogin()
                } else {
                    isNimValid = true
                    binding.otfNim.error = null
                    binding.otfNim.isErrorEnabled = false
                    enableButtonLogin()
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

        //kelas
        binding.otfKelas.editText!!.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(kelas: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (kelas!!.length > 3){
                    isKelasValid = false
                    binding.otfKelas.error = "Periksa kembali kelas anda"
                    enableButtonLogin()
                } else {
                    isKelasValid = true
                    binding.otfKelas.error = null
                    binding.otfKelas.isErrorEnabled = false
                    enableButtonLogin()
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

        //nohp
        binding.otfNohp.editText!!.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(nohp: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (nohp!!.length > 14){
                    isNoHpValid = true
                    binding.otfNohp.error = "Periksa kembali no Hp anda"
                    enableButtonLogin()
                } else {
                    isNoHpValid = true
                    binding.otfNohp.error = null
                    binding.otfNohp.isErrorEnabled = false
                    enableButtonLogin()
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

        //email
        binding.otfEmail.editText!!.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(email: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    isEmailValid = true
                    binding.otfEmail.error = null
                    binding.otfEmail.isErrorEnabled = false
                    enableButtonLogin()

                } else {
                    isEmailValid = false
                    binding.otfEmail.error = "Periksa format email"
                    enableButtonLogin()
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

        //password
        binding.otfPassword.editText!!.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(password: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (password!!.length < 6) {
                    isPasswordValid = false
                    binding.otfPassword.error = "Password tidak boleh kurang dari 6 karakter"
                    enableButtonLogin()
                } else {
                    binding.otfPassword.error = null
                    isPasswordValid = true
                    binding.otfPassword.isErrorEnabled = false
                    enableButtonLogin()
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

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