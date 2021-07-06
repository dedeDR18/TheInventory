package id.learn.android.theinventory.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import id.learn.android.theinventory.data.firebase.auth.AuthRepository
import id.learn.android.theinventory.domain.repository.IAuthRepository
import id.learn.android.theinventory.domain.usecase.Interactor
import id.learn.android.theinventory.domain.usecase.Usecase
import id.learn.android.theinventory.presentation.admin.detailhistory.DetailHistoryAdminViewModel
import id.learn.android.theinventory.presentation.admin.peminjaman.PeminjamanAdminViewModel
import id.learn.android.theinventory.presentation.admin.peminjaman.detail.DetailPeminjamanAdminViewModel
import id.learn.android.theinventory.presentation.daftar.DaftarViewModel
import id.learn.android.theinventory.presentation.databarang.DataBarangViewModel
import id.learn.android.theinventory.presentation.history.HistoryViewModel
import id.learn.android.theinventory.presentation.login.LoginViewModel
import id.learn.android.theinventory.presentation.main.MainViewModel
import id.learn.android.theinventory.presentation.peminjaman.detail.IsiDataPeminjamanViewModel
import id.learn.android.theinventory.presentation.peminjaman.pilihbarang.ListChosenViewModel
import id.learn.android.theinventory.presentation.peminjaman.pilihbarang.PilihBarangViewModel
import id.learn.android.theinventory.presentation.peminjaman.status.listpinjaman.StatusPeminjamanViewModel
import id.learn.android.theinventory.presentation.profile.ProfileViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val repositoryModule = module {
    single<IAuthRepository> { AuthRepository(provideAuthInstance(), provideRealtimeDbInstance()) }
}

val usecaseModule = module {
    factory<Usecase> { Interactor(get()) }
}

val viewModelModule = module {
    viewModel { DaftarViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { MainViewModel(get()) }
    viewModel { DataBarangViewModel(get()) }
    viewModel { ProfileViewModel(get()) }
    viewModel { PilihBarangViewModel(get()) }
    viewModel { ListChosenViewModel() }
    viewModel { StatusPeminjamanViewModel(get()) }
    viewModel { IsiDataPeminjamanViewModel(get()) }
    viewModel { PeminjamanAdminViewModel(get()) }
    viewModel { DetailPeminjamanAdminViewModel(get()) }
    viewModel { HistoryViewModel(get()) }
    viewModel { DetailHistoryAdminViewModel(get()) }

}

private fun provideAuthInstance(): FirebaseAuth {
    return FirebaseAuth.getInstance()
}

private fun provideRealtimeDbInstance(): FirebaseDatabase {
    return FirebaseDatabase.getInstance()
}
