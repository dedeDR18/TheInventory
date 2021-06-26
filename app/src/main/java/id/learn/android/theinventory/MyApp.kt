package id.learn.android.theinventory

import android.app.Application
import id.learn.android.theinventory.di.repositoryModule
import id.learn.android.theinventory.di.usecaseModule
import id.learn.android.theinventory.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApp : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApp)
            modules(
                listOf(
                    repositoryModule,
                    usecaseModule,
                    viewModelModule
                )
            )
        }
    }
}