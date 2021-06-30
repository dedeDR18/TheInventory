package id.learn.android.theinventory.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import id.learn.android.theinventory.data.firebase.auth.AuthRepository
import id.learn.android.theinventory.domain.repository.IAuthRepository
import id.learn.android.theinventory.domain.usecase.Interactor
import id.learn.android.theinventory.domain.usecase.Usecase
import id.learn.android.theinventory.presentation.daftar.DaftarViewModel
import id.learn.android.theinventory.presentation.databarang.DataBarangViewModel
import id.learn.android.theinventory.presentation.login.LoginViewModel
import id.learn.android.theinventory.presentation.main.MainViewModel
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
}

private fun provideAuthInstance(): FirebaseAuth {
    return FirebaseAuth.getInstance()
}

private fun provideRealtimeDbInstance(): FirebaseDatabase {
    return FirebaseDatabase.getInstance()
}

//private fun createOkHttpClient(): OkHttpClient {
//    return OkHttpClient.Builder()
//        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
//        .connectTimeout(120, TimeUnit.SECONDS)
//        .readTimeout(120, TimeUnit.SECONDS)
//        .build()
//}
//
//private fun createRetrofit(client: OkHttpClient): ApiService {
//    return Retrofit.Builder()
//        .baseUrl("BASE_URL")
//        .addConverterFactory(GsonConverterFactory.create())
//        .client(client)
//        .build()
//        .create(ApiService::class.java)
//}