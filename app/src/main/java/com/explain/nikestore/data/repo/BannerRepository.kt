package com.explain.nikestore.data.repo

import com.explain.nikestore.data.Banner
import io.reactivex.Single

interface BannerRepository {
    fun getBanners():Single<List<Banner>>
}