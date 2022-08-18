package com.explain.nikestore.feature.favorites

import androidx.lifecycle.MutableLiveData
import com.explain.nikestore.common.NikeCompletableObserver
import com.explain.nikestore.common.NikeSingleObserver
import com.explain.nikestore.common.NikeViewModel
import com.explain.nikestore.data.Product
import com.explain.nikestore.data.repo.ProductRepository
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class FavoriteProductsViewModel(private val productRepository: ProductRepository) :
    NikeViewModel() {

    val productsLiveData = MutableLiveData<List<Product>>()

    init {
        productRepository.getFavoriteProducts()
            .subscribeOn(Schedulers.io())
            .subscribe(object : NikeSingleObserver<List<Product>>(compositeDisposable) {
                override fun onSuccess(t: List<Product>) {
                    productsLiveData.postValue(t)
                }
            })
    }

    fun removeFromFavorites(product: Product) {
        productRepository.deleteFromFavorites(product)
            .subscribeOn(Schedulers.io())
            .subscribe(object : NikeCompletableObserver(compositeDisposable) {
                override fun onComplete() {
                    Timber.i("removeFromFavorites compeleted")
                }
            })
    }
}