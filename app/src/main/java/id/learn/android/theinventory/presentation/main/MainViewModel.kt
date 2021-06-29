package id.learn.android.theinventory.presentation.main

import androidx.lifecycle.ViewModel
import id.learn.android.theinventory.domain.usecase.Usecase

class MainViewModel(private val usecase: Usecase): ViewModel(){

    fun logout(){
        usecase.doLogout()
    }
}