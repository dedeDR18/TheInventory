package id.learn.android.theinventory.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.learn.android.theinventory.domain.model.User
import id.learn.android.theinventory.domain.usecase.Usecase

class LoginViewModel(private val usecase:Usecase): ViewModel(){
    var currentUser: LiveData<User>? = null

    fun login(email: String, password:String){
        currentUser = usecase.login(email,password)
    }
}