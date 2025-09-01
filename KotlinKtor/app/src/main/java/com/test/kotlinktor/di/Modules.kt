package com.test.kotlinktor.di


import com.test.kotlinktor.data.remote.ApiService
import com.test.kotlinktor.data.remote.ApiServiceImpl
import com.test.kotlinktor.data.remote.KtorClientProvider
import com.test.kotlinktor.repository.PostRepository
import com.test.kotlinktor.ui.PostViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object Modules {
    val networkModule = module {
        single { KtorClientProvider.create(androidContext().cacheDir, isDebug = true) }
        single { "https://jsonplaceholder.typicode.com" }
        single<ApiService> { ApiServiceImpl(get(), get()) }
    }

    val repositoryModule = module {
        single { PostRepository(get()) }
    }

    val viewModelModule = module {
        viewModel { PostViewModel(get()) }
    }
}