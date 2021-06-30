package id.learn.android.theinventory.presentation.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.learn.android.theinventory.domain.model.User
import id.learn.android.theinventory.domain.usecase.Usecase

class ProfileViewModel(private val usecase: Usecase): ViewModel(){

    private var mutableUserProfile = MutableLiveData<User>()
    val userProfile : LiveData<User> get() = mutableUserProfile

    fun setUserProfile(){
        mutableUserProfile = usecase.doGetProfile() as MutableLiveData<User>
    }
}