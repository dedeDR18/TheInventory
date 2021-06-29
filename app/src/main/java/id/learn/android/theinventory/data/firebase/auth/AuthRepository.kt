package id.learn.android.theinventory.data.firebase.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import id.learn.android.theinventory.domain.model.User
import id.learn.android.theinventory.domain.repository.IAuthRepository
import id.learn.android.theinventory.utils.HelperClass.logErrorMessage


class AuthRepository(private val mAuth: FirebaseAuth, val realtimeDb: FirebaseDatabase) :
    IAuthRepository {
    override fun createUser(email: String, password: String, nama:String, nim:Int, kelas: String, noHp:String): LiveData<User> {
        val newUserMutableLiveData = MutableLiveData<User>()
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { taskAuth ->
                if (taskAuth.isSuccessful) {
                    val user = User(
                        nama = nama,
                        nim = nim,
                        noHp = noHp,
                        kelas = kelas,
                        role = "Mahasiswa"
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

    override fun login(email: String, password: String): LiveData<User> {
        val currentUser = MutableLiveData<User>()
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { taskLogin ->
                if (taskLogin.isSuccessful) {
                    realtimeDb.reference.child("Users").child(mAuth.currentUser!!.uid).get()
                        .addOnSuccessListener { result ->
                            val nama = result.child("nama").value.toString()
                            val nim = result.child("nim").value.toString().toInt()
                            val kelas = result.child("kelas").value.toString()
                            val noHp = result.child("noHp").value.toString()
                            val role = result.child("role").value.toString()

                            Log.d("LOGIN TEST", "nama = $nama")

                            val user = User(
                                nama = nama,
                                nim = nim,
                                kelas = kelas,
                                noHp = noHp,
                                role = role
                            )

                            currentUser.value = user


                        }.addOnFailureListener { e ->
                            Log.d("LOGIN TEST", "gagal ngambil data")
                        }
                } else {
                    Log.d("LOGIN TEST", "gagal login")
                }
            }
        return currentUser
    }

    override fun logout(): Boolean {
        mAuth.signOut()
        return true
    }


}