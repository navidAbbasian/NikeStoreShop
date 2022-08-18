package com.explain.nikestore.data.repo.source

import com.explain.nikestore.data.Product
import io.reactivex.Completable
import io.reactivex.Single

interface ProductDataSource {
    fun getProducts(sort:Int): Single<List<Product>>

    fun getFavoriteProducts(): Single<List<Product>>

    fun addToFavorites(product: Product): Completable

    fun deleteFromFavorites(product: Product): Completable
}