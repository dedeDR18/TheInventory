package id.learn.android.theinventory.domain.usecase

import androidx.lifecycle.LiveData
import id.learn.android.theinventory.domain.model.Barang
import id.learn.android.theinventory.domain.model.Peminjaman
import id.learn.android.theinventory.domain.model.User
import id.learn.android.theinventory.domain.repository.IAuthRepository

class Interactor (private val repository: IAuthRepository): Usecase{
    override fun doCreateUser(email: String, password: String, nama:String, nim:Long, kelas: String, noHp:String) = repository.createUser(email,password,nama,nim,kelas,noHp)
    override fun doLogin(email: String, password: String) = repository.login(email,password)
    override fun doLogout() = repository.logout()
    override fun doGetDataBarang() = repository.fetchDataBarang()
    override fun doGetProfile() = repository.fetchUserProfile()
    override fun doSendPeminjamanForm(dataPeminjaman: Peminjaman): LiveData<Boolean> = repository.createPeminjaman(dataPeminjaman)
    override fun doGetListPeminjaman(): LiveData<List<Peminjaman>> = repository.getListPeminjaman()

}