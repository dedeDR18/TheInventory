package id.learn.android.theinventory.presentation.peminjaman.status.listpinjaman

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.learn.android.theinventory.domain.model.Barang
import id.learn.android.theinventory.domain.model.Peminjaman
import id.learn.android.theinventory.domain.usecase.Usecase

class StatusPeminjamanViewModel(private val usecase: Usecase): ViewModel(){

    var listPeminjaman: LiveData<List<Peminjaman>>? = null

    fun getListPeminjaman(){
        listPeminjaman = usecase.doGetListPeminjaman()
    }
}