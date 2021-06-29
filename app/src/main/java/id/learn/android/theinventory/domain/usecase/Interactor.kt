package id.learn.android.theinventory.domain.usecase

import androidx.lifecycle.LiveData
import id.learn.android.theinventory.domain.model.User
import id.learn.android.theinventory.domain.repository.IAuthRepository

class Interactor (private val repository: IAuthRepository): Usecase{
    override fun doCreateUser(email: String, password: String, nama:String, nim:Int, kelas: String, noHp:String) = repository.createUser(email,password,nama,nim,kelas,noHp)
    override fun doLogin(email: String, password: String) = repository.login(email,password)
    override fun doLogout() = repository.logout()

}