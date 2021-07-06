package id.learn.android.theinventory.presentation.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.learn.android.theinventory.domain.model.Peminjaman
import id.learn.android.theinventory.domain.usecase.Usecase

class HistoryViewModel(private val usecase: Usecase): ViewModel(){
    var listHistoryPeminjaman: LiveData<List<Peminjaman>>? = null

    fun getListHistoryPeminjaman(){
        listHistoryPeminjaman = usecase.doGetListHistoryPeminjamanForAdmin()
    }
}