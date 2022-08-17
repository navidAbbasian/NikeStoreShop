package com.example.nikestore

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import com.example.nikestore.data.repo.BannerRepository
import com.example.nikestore.data.repo.BannerRepositoryImpl
import com.example.nikestore.data.repo.ProductRepository
import com.example.nikestore.data.repo.ProductRepositoryImpl
import com.example.nikestore.data.repo.source.BannerRemoteDataSource
import com.example.nikestore.data.repo.source.ProductLocalDataSource
import com.example.nikestore.data.repo.source.ProductRemoteDataSource
import com.example.nikestore.feature.main.MainViewModel
import com.example.nikestore.feature.main.ProductListAdapter
import com.example.nikestore.services.FrescoImageLoadingService
import com.example.nikestore.services.ImageLoadingService
import com.example.nikestore.services.http.createApiServiceInstance
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import timber.log.Timber

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

        Fresco.initialize(this)

        val myModules = module {
            single { createApiServiceInstance() }
            single<ImageLoadingService> { FrescoImageLoadingService() }
            factory<ProductRepository> {
                ProductRepositoryImpl(
                    ProductRemoteDataSource(get()),
                    ProductLocalDataSource()
                )
            }
            factory { ProductListAdapter(get()) }
            factory<BannerRepository> { BannerRepositoryImpl(BannerRemoteDataSource(get())) }
            viewModel { MainViewModel(get(), get()) }
        }

        startKoin {
            androidContext(this@App)
            modules(myModules)
        }
    }
}