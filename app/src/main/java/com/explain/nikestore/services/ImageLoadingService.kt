package com.explain.nikestore.services

import com.explain.nikestore.view.NikeImageView

interface ImageLoadingService {
    fun load(imageView: NikeImageView, imageUrl: String)
}