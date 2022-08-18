package com.explain.nikestore.feature.main

import com.explain.nikestore.common.NikeSingleObserver
import com.explain.nikestore.common.NikeViewModel
import com.explain.nikestore.data.CartItemCount
import com.explain.nikestore.data.TokenContainer
import com.explain.nikestore.data.repo.CartRepository
import io.reactivex.schedulers.Schedulers
import org.greenrobot.eventbus.EventBus

class MainViewModel(private val cartRepository: CartRepository) : NikeViewModel() {
    fun getCartItemsCount(){
        if (!TokenContainer.token.isNullOrEmpty()){
            cartRepository.getCartItemsCount()
                .subscribeOn(Schedulers.io())
                .subscribe(object :NikeSingleObserver<CartItemCount>(compositeDisposable){
                    override fun onSuccess(t: CartItemCount) {
                        EventBus.getDefault().postSticky(t)
                    }
                })
        }
    }
}