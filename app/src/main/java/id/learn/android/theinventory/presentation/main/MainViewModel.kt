package id.learn.android.theinventory.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.learn.android.theinventory.domain.model.User
import id.learn.android.theinventory.domain.usecase.Usecase

class MainViewModel(private val usecase: Usecase): ViewModel(){

    private val mutableUser = MutableLiveData<User>()
    val user : LiveData<User> get() = mutableUser

    fun logout(){
        usecase.doLogout()
    }

    fun setUser(user: User){
        mutableUser.value = user
    }

}