package id.learn.android.theinventory.domain.usecase

import androidx.lifecycle.LiveData
import id.learn.android.theinventory.domain.model.User

interface Usecase{
    fun doCreateUser(email:String, password:String): LiveData<User>
}