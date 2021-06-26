package id.learn.android.theinventory.presentation.daftar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
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



    //firebase stuff
    private lateinit var mAuth: FirebaseAuth
    private lateinit var realtimeDb: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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



        binding.btnDaftar.setOnClickListener {
            //navController.navigate(R.id.action_daftarFragment_to_homeFragment)
            binding.pbDaftar.visibility = View.VISIBLE
            vm.createUser(email,pass)
            vm.createdUserLiveData!!.observe(viewLifecycleOwner, Observer { user ->
                if(user.isNew){
                    binding.pbDaftar.visibility = View.GONE
                    Toast.makeText(requireActivity(), "berhasil", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireActivity(), "gagal", Toast.LENGTH_SHORT).show()
                    binding.pbDaftar.visibility = View.GONE
                }
            })

//            mAuth.createUserWithEmailAndPassword(email, pass)
//                .addOnCompleteListener { task ->
//                    if (task.isSuccessful){
//                        val user = User(
//                            id = 1,
//                            nama = "Dede Dari Rahamadi",
//                            nim = 10114183,
//                            noHp = "089606185656",
//                            kelas = "mawar 1",
//                            username = "ddrddr"
//                        )
//
//                        realtimeDb.getReference("Users")
//                            .child(FirebaseAuth.getInstance().currentUser!!.uid)
//                            .setValue(user)
//                            .addOnCompleteListener { task ->
//                                if (task.isSuccessful){
//                                    binding.pbDaftar.visibility = View.GONE
//                                    Toast.makeText(requireActivity(), "berhasil", Toast.LENGTH_SHORT).show()
//                                } else {
//                                    binding.pbDaftar.visibility = View.GONE
//                                    Toast.makeText(requireActivity(), "Gagal", Toast.LENGTH_SHORT).show()
//                                }
//                            }
//                    }
//                }
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}