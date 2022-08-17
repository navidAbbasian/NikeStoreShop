package com.example.nikestore.feature.main

import androidx.lifecycle.MutableLiveData
import com.example.nikestore.common.NikeSingleObserver
import com.example.nikestore.common.NikeViewModel
import com.example.nikestore.data.Banner
import com.example.nikestore.data.Product
import com.example.nikestore.data.repo.BannerRepository
import com.example.nikestore.data.repo.ProductRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel(productRepository: ProductRepository, bannerRepository: BannerRepository) :
    NikeViewModel() {
    val productsLiveData = MutableLiveData<List<Product>>()
    val bannerLiveData = MutableLiveData<List<Banner>>()

    init {
        progressBarLiveData.value = true
        productRepository.getProducts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally { progressBarLiveData.value = false }
            .subscribe(object :NikeSingleObserver<List<Product>>(compositeDisposable){
                override fun onSuccess(t: List<Product>) {
                    productsLiveData.value=t
                }
            })



        bannerRepository.getBanners()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object :NikeSingleObserver<List<Banner>>(compositeDisposable){
                override fun onSuccess(t: List<Banner>) {
                    bannerLiveData.value=t
                }

            })
    }
}