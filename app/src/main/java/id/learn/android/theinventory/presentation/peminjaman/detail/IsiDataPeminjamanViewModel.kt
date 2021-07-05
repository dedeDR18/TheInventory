package id.learn.android.theinventory.presentation.peminjaman.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.learn.android.theinventory.domain.model.Barang
import id.learn.android.theinventory.domain.model.Peminjaman
import id.learn.android.theinventory.domain.usecase.Usecase
import id.learn.android.theinventory.utils.ListBarang

class IsiDataPeminjamanViewModel(private val usecase: Usecase):ViewModel(){
    var success: LiveData<Boolean>? = null

    fun sendFormPeminjaman(dataPemijaman:Peminjaman){
        success = usecase.doSendPeminjamanForm(dataPemijaman)
    }
}