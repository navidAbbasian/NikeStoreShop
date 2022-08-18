package com.explain.nikestore.data.repo.order

import com.explain.nikestore.data.Checkout
import com.explain.nikestore.data.OrderHistoryItem
import com.explain.nikestore.data.SubmitOrderResult
import io.reactivex.Single

interface OrderRepository {

    fun submit(
        firstName: String,
        lastName: String,
        postalCode: String,
        phoneNumber: String,
        address: String,
        paymentMethod: String
    ): Single<SubmitOrderResult>

    fun checkout(orderId: Int): Single<Checkout>

    fun list():Single<List<OrderHistoryItem>>
}