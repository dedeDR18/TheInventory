package id.learn.android.theinventory.domain.usecase

import androidx.lifecycle.LiveData
import id.learn.android.theinventory.domain.model.User
import id.learn.android.theinventory.domain.repository.IAuthRepository

class Interactor (private val repository: IAuthRepository): Usecase{
    override fun doCreateUser(email: String, password: String) = repository.createUser(email,password)
    override fun login(email: String, password: String) = repository.login(email,password)

}