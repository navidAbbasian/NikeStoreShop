package com.explain.nikestore.feature

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import com.explain.nikestore.common.EXTRA_KEY_DATA
import com.explain.nikestore.common.NikeSingleObserver
import com.explain.nikestore.common.NikeViewModel
import com.explain.nikestore.common.asyncNetworkRequest
import com.explain.nikestore.data.Comment
import com.explain.nikestore.data.Product
import com.explain.nikestore.data.repo.CartRepository
import com.explain.nikestore.data.repo.CommentRepository
import io.reactivex.Completable

class ProductDetailViewModel(bundle: Bundle, commentRepository: CommentRepository,val cartRepository: CartRepository) :
    NikeViewModel() {
    val productLiveData = MutableLiveData<Product>()
    val commentsLiveData = MutableLiveData<List<Comment>>()

    init {
        productLiveData.value = bundle.getParcelable(EXTRA_KEY_DATA)
        progressBarLiveData.value = true
        commentRepository.getAll(productLiveData.value!!.id)
            .asyncNetworkRequest()
            .doFinally { progressBarLiveData.value = false }
            .subscribe(object : NikeSingleObserver<List<Comment>>(compositeDisposable) {
                override fun onSuccess(t: List<Comment>) {
                    commentsLiveData.value = t
                }
            })
    }

    fun onAddToCartBtn():Completable = cartRepository.addToCart(productLiveData.value!!.id).ignoreElement()
}