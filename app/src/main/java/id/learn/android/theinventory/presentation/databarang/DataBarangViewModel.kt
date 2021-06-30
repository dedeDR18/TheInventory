package id.learn.android.theinventory.presentation.databarang

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.learn.android.theinventory.domain.model.Barang
import id.learn.android.theinventory.domain.model.User
import id.learn.android.theinventory.domain.usecase.Usecase

class DataBarangViewModel(private val usecase: Usecase): ViewModel(){
    var listBarang: LiveData<List<Barang>>? = null

    fun getDataBarang(){
        listBarang = usecase.doGetDataBarang()
    }
}