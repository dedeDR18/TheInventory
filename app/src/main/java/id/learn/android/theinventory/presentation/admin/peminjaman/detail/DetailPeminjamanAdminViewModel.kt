package id.learn.android.theinventory.presentation.admin.peminjaman.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.learn.android.theinventory.domain.model.Peminjaman
import id.learn.android.theinventory.domain.usecase.Usecase

class DetailPeminjamanAdminViewModel (private val usecase: Usecase): ViewModel(){
    var result: LiveData<Boolean>? = null



    fun updateStatusPeminjaman(dataPeminjaman:Peminjaman, status:String){
        result = usecase.doUpdateStatusPeminjaman(dataPeminjaman, status)
    }
}