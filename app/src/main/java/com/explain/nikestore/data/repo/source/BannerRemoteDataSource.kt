package com.explain.nikestore.data.repo.source

import com.explain.nikestore.data.Banner
import com.explain.nikestore.services.http.ApiService
import io.reactivex.Single

class BannerRemoteDataSource(val apiService: ApiService) : BannerDataSource {
    override fun getBanners(): Single<List<Banner>> = apiService.getBanners()
}