package id.learn.android.theinventory.presentation.peminjaman.pilihbarang

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.learn.android.theinventory.domain.model.User
import id.learn.android.theinventory.utils.ListBarang

class ListChosenViewModel () : ViewModel(){
    private val mutableListChosen = MutableLiveData<List<ListBarang>?>()
    val listChosen : MutableLiveData<List<ListBarang>?> get() = mutableListChosen

    fun setListChoosen(list: List<ListBarang>){
        mutableListChosen.value = list
    }

    fun clear(){
        mutableListChosen.value = null
    }
}