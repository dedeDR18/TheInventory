package id.learn.android.theinventory.presentation.admin.detailhistory

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.learn.android.theinventory.domain.model.Peminjaman
import id.learn.android.theinventory.domain.usecase.Usecase

class DetailHistoryAdminViewModel(private val usecase: Usecase): ViewModel(){
    var result: LiveData<Boolean>? = null

    fun updateStatusPeminjaman(dataPeminjaman: Peminjaman, status:String){
        result = usecase.doUpdateStatusHistoryAdmin(dataPeminjaman, status)
    }
}