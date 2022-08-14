package com.example.nikestore.feature.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.nikestore.R
import com.example.nikestore.common.EXTRA_KEY_DATA
import com.example.nikestore.data.Banner
import com.example.nikestore.services.ImageLoadingService
import com.example.nikestore.view.NikeImageView
import org.koin.android.ext.android.inject

class BannerFragment : Fragment() {
    val imageLoadingService: ImageLoadingService by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val imageView = inflater.inflate(R.layout.fragment_banner, container, false) as NikeImageView
        imageLoadingService.load(imageView,)
        return
    }

    companion object {
        fun newInstance(banner: Banner){
            val bannerFragment=BannerFragment()
            val bundle=Bundle()
            bundle.putParcelable(EXTRA_KEY_DATA,banner)
        }
    }

}