package id.learn.android.theinventory.domain.usecase

import androidx.lifecycle.LiveData
import id.learn.android.theinventory.domain.model.Barang
import id.learn.android.theinventory.domain.model.Peminjaman
import id.learn.android.theinventory.domain.model.User

interface Usecase {
    fun doCreateUser(
        email: String,
        password: String,
        nama: String,
        nim: Long,
        kelas: String,
        noHp: String
    ): LiveData<User>

    fun doLogin(email: String, password: String): LiveData<User?>
    fun doLogout(): Boolean

    fun doGetDataBarang(): LiveData<List<Barang>>
    fun doGetProfile(): LiveData<User>
    fun doSendPeminjamanForm(dataPeminjaman: Peminjaman): LiveData<Boolean>

    fun doGetListPeminjaman(): LiveData<List<Peminjaman>>
    fun doUpdateStatusPeminjaman(dataPeminjaman: Peminjaman,status:String): LiveData<Boolean>
}