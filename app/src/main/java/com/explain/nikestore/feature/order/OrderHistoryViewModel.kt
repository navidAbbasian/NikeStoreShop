package com.explain.nikestore.feature.order

import androidx.lifecycle.MutableLiveData
import com.explain.nikestore.common.NikeSingleObserver
import com.explain.nikestore.common.NikeViewModel
import com.explain.nikestore.common.asyncNetworkRequest
import com.explain.nikestore.data.OrderHistoryItem
import com.explain.nikestore.data.repo.order.OrderRepository

class OrderHistoryViewModel(orderRepository: OrderRepository) : NikeViewModel() {
    val orders = MutableLiveData<List<OrderHistoryItem>>()

    init {
        progressBarLiveData.value = true
        orderRepository.list().asyncNetworkRequest().doFinally { progressBarLiveData.value = false }
            .subscribe(object : NikeSingleObserver<List<OrderHistoryItem>>(compositeDisposable){
                override fun onSuccess(t: List<OrderHistoryItem>) {
                    orders.value=t
                }
            })
    }
}