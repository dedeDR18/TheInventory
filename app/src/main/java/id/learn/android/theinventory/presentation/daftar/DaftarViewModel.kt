package id.learn.android.theinventory.presentation.daftar

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.learn.android.theinventory.domain.model.User
import id.learn.android.theinventory.domain.usecase.Usecase


class DaftarViewModel(private val usecase: Usecase): ViewModel(){
//    var authenticatedUserLiveData: LiveData<User>? = null
    var createdUserLiveData: LiveData<User>? = null

    fun createUser(email:String, password:String, nama:String, nim:Long, kelas: String, noHp:String){
        createdUserLiveData = usecase.doCreateUser(email,password,nama,nim,kelas,noHp)
    }
}