package id.learn.android.theinventory.data.firebase.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import id.learn.android.theinventory.domain.model.Barang
import id.learn.android.theinventory.domain.model.Peminjaman
import id.learn.android.theinventory.domain.model.User
import id.learn.android.theinventory.domain.repository.IAuthRepository
import id.learn.android.theinventory.utils.HelperClass.logErrorMessage
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class AuthRepository(private val mAuth: FirebaseAuth, val realtimeDb: FirebaseDatabase) :
    IAuthRepository {
    override fun createUser(
        email: String,
        password: String,
        nama: String,
        nim: Long,
        kelas: String,
        noHp: String
    ): LiveData<User> {
        val newUserMutableLiveData = MutableLiveData<User>()
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { taskAuth ->
                if (taskAuth.isSuccessful) {
                    val user = User(
                        nama = nama,
                        nim = nim,
                        noHp = noHp,
                        kelas = kelas,
                        role = "Mahasiswa",
                        email = email
                    )

                    realtimeDb.getReference("Users")
                        .child(FirebaseAuth.getInstance().currentUser!!.uid)
                        .setValue(user)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                newUserMutableLiveData.value = user
                            } else {
                                logErrorMessage(task.exception!!.message.toString())
                            }
                        }
                } else {
                    logErrorMessage(taskAuth.exception!!.message.toString())
                }
            }
        return newUserMutableLiveData
    }

    override fun login(email: String, password: String): LiveData<User?> {
        val currentUser = MutableLiveData<User?>()
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { taskLogin ->
                if (taskLogin.isSuccessful) {
                    realtimeDb.reference.child("Users").child(mAuth.currentUser!!.uid).get()
                        .addOnSuccessListener { result ->
                            val nama = result.child("nama").value.toString()
                            val nim = result.child("nim").value.toString().toLong()
                            val kelas = result.child("kelas").value.toString()
                            val noHp = result.child("noHp").value.toString()
                            val role = result.child("role").value.toString()

                            Log.d("LOGIN TEST", "nama = $nama")

                            val user = User(
                                nama = nama,
                                nim = nim,
                                kelas = kelas,
                                noHp = noHp,
                                role = role,
                                email = email
                            )

                            currentUser.value = user


                        }.addOnFailureListener { e ->
                            Log.d("LOGIN TEST", "gagal ngambil data")
                        }
                } else {
                    currentUser.value = null
                    Log.d("LOGIN TEST", "gagal login")
                }
            }
        return currentUser
    }

    override fun logout(): Boolean {
        mAuth.signOut()
        return true
    }

    override fun fetchDataBarang(): LiveData<List<Barang>> {
        val liveDataListBarang = MutableLiveData<List<Barang>>()
        realtimeDb.reference.child("DataBarang")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val listBarang = ArrayList<Barang>()
                    for (snapshot in dataSnapshot.children) {
                        val barang = Barang(
                            idBarang = snapshot.key!!.toInt(),
                            namaBarang = snapshot.child("namaBarang").value.toString(),
                            stokBarang = snapshot.child("stok").value.toString().toInt(),
                            merkBarang = snapshot.child("merk").value.toString()
                        )
                        listBarang.add(barang)
                    }
                    liveDataListBarang.value = listBarang
                }

                override fun onCancelled(error: DatabaseError) {
                    logErrorMessage("gagal mengambil data barang")
                }

            })
        return liveDataListBarang
    }

    override fun fetchUserProfile(): LiveData<User> {
        val userProfile = MutableLiveData<User>()
        realtimeDb.reference.child("Users").child(mAuth.currentUser!!.uid)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val user = User(
                        nama = snapshot.child("nama").value.toString(),
                        nim = snapshot.child("nim").value.toString().toLong(),
                        kelas = snapshot.child("kelas").value.toString(),
                        noHp = snapshot.child("noHp").value.toString(),
                        role = snapshot.child("nama").value.toString(),
                        email = snapshot.child("email").value.toString()
                    )
                    userProfile.value = user
                }

                override fun onCancelled(error: DatabaseError) {
                    logErrorMessage("gagal mengambil data profile user")
                }

            })
        return userProfile
    }

    override fun createPeminjaman(dataPeminjaman: Peminjaman): LiveData<Boolean> {
        val berhasil = MutableLiveData<Boolean>()
        realtimeDb.reference.child("DataPeminjaman")
        .child(dataPeminjaman.idPeminjaman.toString())
            .setValue(dataPeminjaman)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    berhasil.value = true
                } else {
                    berhasil.value = false
                    logErrorMessage(task.exception!!.message.toString())
                }
            }
        return berhasil
    }

    override fun getListPeminjaman(): LiveData<List<Peminjaman>> {
        val liveDataListPeminjaman = MutableLiveData<List<Peminjaman>>()
        realtimeDb.reference.child("DataPeminjaman")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val listPeminjaman = ArrayList<Peminjaman>()
                    for (snapshot in dataSnapshot.children) {
                        val peminjaman = Peminjaman(
                            idPeminjaman = snapshot.child("idPeminjaman").value.toString(),
                            idMahasiswaPeminjam = snapshot.child("idMahasiswaPeminjam").value.toString().toLong(),
                            namaPeminjam = snapshot.child("namaPeminjam").value.toString(),
                            barang = snapshot.child("barang").value.toString(),
                            tanggalPeminjaman = snapshot.child("tanggalPeminjaman").value.toString(),
                            tanggalPengembalian = snapshot.child("tanggalPengembalian").value.toString(),
                            status = snapshot.child("status").value.toString(),
                        )
                        listPeminjaman.add(peminjaman)
                    }
                    liveDataListPeminjaman.value = listPeminjaman
                }

                override fun onCancelled(error: DatabaseError) {
                    logErrorMessage("gagal mengambil data daftar peminjaman")
                }

            })
        return liveDataListPeminjaman
    }


}