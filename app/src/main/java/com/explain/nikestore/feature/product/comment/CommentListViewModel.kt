package com.explain.nikestore.feature.product.comment

import androidx.lifecycle.MutableLiveData
import com.explain.nikestore.common.NikeSingleObserver
import com.explain.nikestore.common.NikeViewModel
import com.explain.nikestore.common.asyncNetworkRequest
import com.explain.nikestore.data.Comment
import com.explain.nikestore.data.repo.CommentRepository

class CommentListViewModel(productId: Int, commentRepository: CommentRepository) : NikeViewModel() {
    val commentsLiveData = MutableLiveData<List<Comment>>()

    init {
        progressBarLiveData.value = true
        commentRepository.getAll(productId)
            .asyncNetworkRequest()
            .doFinally { progressBarLiveData.value = false }
            .subscribe(object : NikeSingleObserver<List<Comment>>(compositeDisposable) {
                override fun onSuccess(t: List<Comment>) {
                    commentsLiveData.value = t
                }
            })
    }
}