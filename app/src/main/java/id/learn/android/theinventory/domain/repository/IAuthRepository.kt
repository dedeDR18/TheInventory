package id.learn.android.theinventory.domain.repository

import androidx.lifecycle.LiveData
import com.google.firebase.auth.AuthResult
import id.learn.android.theinventory.data.firebase.Result
import id.learn.android.theinventory.domain.model.Barang
import id.learn.android.theinventory.domain.model.Peminjaman
import id.learn.android.theinventory.domain.model.User
import kotlinx.coroutines.flow.Flow


interface IAuthRepository {
    fun createUser(
        email: String,
        password: String,
        nama: String,
        nim: Long,
        kelas: String,
        noHp: String
    ): LiveData<User>

    fun login(email: String, password: String): LiveData<User?>
    fun logout(): Boolean
    fun fetchDataBarang(): LiveData<List<Barang>>
    fun fetchUserProfile(): LiveData<User>
    fun createPeminjaman(dataPeminjaman: Peminjaman): LiveData<Boolean>
    fun getListPeminjaman(): LiveData<List<Peminjaman>>
    fun updateStatusPeminjaman(dataPeminjaman: Peminjaman, status:String): LiveData<Boolean>
    fun getListHistoryPeminjamanForAdmin(): LiveData<List<Peminjaman>>
    fun updateStatusHistoryForAdmin(dataPeminjaman: Peminjaman, status:String): LiveData<Boolean>
}