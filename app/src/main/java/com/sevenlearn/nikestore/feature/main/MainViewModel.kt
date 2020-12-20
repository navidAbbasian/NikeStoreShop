package com.sevenlearn.nikestore.feature.main

import androidx.lifecycle.MutableLiveData
import com.sevenlearn.nikestore.common.NikeViewModel
import com.sevenlearn.nikestore.data.Product
import com.sevenlearn.nikestore.data.repo.ProductRepository
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class MainViewModel(productRepository: ProductRepository) : NikeViewModel() {
    val productsLiveData = MutableLiveData<List<Product>>()
    init {
        progressBarLiveData.value=true
        productRepository.getProducts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally { progressBarLiveData.value=false }
            .subscribe(object : SingleObserver<List<Product>> {
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onSuccess(t: List<Product>) {
                    productsLiveData.value = t
                }

                override fun onError(e: Throwable) {
                    Timber.e(e)
                }

            })
    }
}