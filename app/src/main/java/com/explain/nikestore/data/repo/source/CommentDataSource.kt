package com.explain.nikestore.data.repo.source

import com.explain.nikestore.data.Comment
import io.reactivex.Single

interface CommentDataSource {

    fun getAll(productId:Int): Single<List<Comment>>

    fun insert(): Single<Comment>
}