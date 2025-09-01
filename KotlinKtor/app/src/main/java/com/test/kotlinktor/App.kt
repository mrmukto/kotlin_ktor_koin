package com.test.kotlinktor

import android.app.Application
import com.test.kotlinktor.di.Modules.networkModule
import com.test.kotlinktor.di.Modules.repositoryModule
import com.test.kotlinktor.di.Modules.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    networkModule,
                    repositoryModule,
                    viewModelModule
                )
            )
        }
    }
}
