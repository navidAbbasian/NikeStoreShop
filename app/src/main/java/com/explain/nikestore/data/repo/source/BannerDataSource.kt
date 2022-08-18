package com.explain.nikestore.data.repo.source

import com.explain.nikestore.data.Banner
import io.reactivex.Single

interface BannerDataSource {
    fun getBanners():Single<List<Banner>>
}