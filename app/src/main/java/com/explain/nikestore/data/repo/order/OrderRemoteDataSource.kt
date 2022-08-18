package com.explain.nikestore.data.repo.order

import com.google.gson.JsonObject
import com.explain.nikestore.data.Checkout
import com.explain.nikestore.data.OrderHistoryItem
import com.explain.nikestore.data.SubmitOrderResult
import com.explain.nikestore.services.http.ApiService
import io.reactivex.Single

class OrderRemoteDataSource(val apiService: ApiService) : OrderDataSource {
    override fun submit(
        firstName: String,
        lastName: String,
        postalCode: String,
        phoneNumber: String,
        address: String,
        paymentMethod: String
    ): Single<SubmitOrderResult> {
        return apiService.submitOrder(JsonObject().apply {
            addProperty("first_name", firstName)
            addProperty("last_name", lastName)
            addProperty("postal_code", postalCode)
            addProperty("mobile", phoneNumber)
            addProperty("address", address)
            addProperty("payment_method", paymentMethod)
        })
    }

    override fun checkout(orderId: Int): Single<Checkout> {
        return apiService.checkout(orderId)
    }

    override fun list(): Single<List<OrderHistoryItem>> = apiService.orders()
}