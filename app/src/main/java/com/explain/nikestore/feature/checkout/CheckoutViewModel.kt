package com.explain.nikestore.feature.checkout

import androidx.lifecycle.MutableLiveData
import com.explain.nikestore.common.NikeSingleObserver
import com.explain.nikestore.common.NikeViewModel
import com.explain.nikestore.common.asyncNetworkRequest
import com.explain.nikestore.data.Checkout
import com.explain.nikestore.data.repo.order.OrderRepository

class CheckoutViewModel(orderId: Int, orderRepository: OrderRepository) :
    NikeViewModel() {
    val checkoutLiveData = MutableLiveData<Checkout>()

    init {
        orderRepository.checkout(orderId)
            .asyncNetworkRequest()
            .subscribe(object : NikeSingleObserver<Checkout>(compositeDisposable) {
                override fun onSuccess(t: Checkout) {
                    checkoutLiveData.value = t
                }
            })
    }
}