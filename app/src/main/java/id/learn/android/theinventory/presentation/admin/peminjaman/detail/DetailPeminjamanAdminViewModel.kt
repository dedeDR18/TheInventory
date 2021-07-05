package id.learn.android.theinventory.presentation.admin.peminjaman.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.learn.android.theinventory.domain.model.Peminjaman
import id.learn.android.theinventory.domain.usecase.Usecase

class DetailPeminjamanAdminViewModel (private val usecase: Usecase): ViewModel(){
    var dataPeminjaman: LiveData<Peminjaman>? = null

    fun getDetailPeminjaman(){
        //dataPeminjaman = usecase.doGetListPeminjaman()
    }
}