package com.example.nikestore.services

import com.example.nikestore.view.NikeImageView
import retrofit2.http.Url

interface ImageLoadingService {

    fun load(imageView:NikeImageView,imageUrl: String)
}