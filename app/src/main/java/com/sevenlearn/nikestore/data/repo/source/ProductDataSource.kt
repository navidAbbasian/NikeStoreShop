package com.sevenlearn.nikestore.data.repo.source

import com.sevenlearn.nikestore.data.Product
import io.reactivex.Completable
import io.reactivex.Single

interface ProductDataSource {
    fun getProducts(): Single<List<Product>>

    fun getFavoriteProducts(): Single<List<Product>>

    fun addToFavorites(): Completable

    fun deleteFromFavorites(): Completable
}