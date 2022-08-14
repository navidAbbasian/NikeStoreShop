package com.sevenlearn.nikestore.data.repo.source

import com.sevenlearn.nikestore.data.Banner
import io.reactivex.Single

interface BannerDataSource {

    fun getBanners(): Single<List<Banner>>
}