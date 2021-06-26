package id.learn.android.theinventory.data.firebase.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import id.learn.android.theinventory.domain.model.User
import id.learn.android.theinventory.domain.repository.IAuthRepository
import id.learn.android.theinventory.utils.HelperClass.logErrorMessage


class AuthRepository(private val mAuth: FirebaseAuth, val realtimeDb: FirebaseDatabase) :
    IAuthRepository {
    override fun createUser(email: String, password: String): LiveData<User> {
        val newUserMutableLiveData = MutableLiveData<User>()
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { taskAuth ->
                if (taskAuth.isSuccessful) {
                    val user = User(
                        id = 1,
                        nama = "Dede Dari Rahamadi",
                        nim = 10114183,
                        noHp = "089606185656",
                        kelas = "mawar 1",
                        username = "ddrddr"
                    )

                    realtimeDb.getReference("Users")
                        .child(FirebaseAuth.getInstance().currentUser!!.uid)
                        .setValue(user)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                user.isNew = true
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


}