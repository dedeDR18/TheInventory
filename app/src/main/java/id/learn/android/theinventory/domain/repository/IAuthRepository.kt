package id.learn.android.theinventory.domain.repository

import androidx.lifecycle.LiveData
import com.google.firebase.auth.AuthResult
import id.learn.android.theinventory.data.firebase.Result
import id.learn.android.theinventory.domain.model.Barang
import id.learn.android.theinventory.domain.model.User
import kotlinx.coroutines.flow.Flow


interface IAuthRepository {
    fun createUser(email: String, password: String, nama:String, nim:Int, kelas: String, noHp:String): LiveData<User>
    fun login(email: String, password: String): LiveData<User>
    fun logout():Boolean
    fun fetchDataBarang(): LiveData<List<Barang>>
}