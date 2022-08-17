package com.example.nikestore.common

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nikestore.R
import io.reactivex.disposables.CompositeDisposable

//all fragment must extend NikeFragment
abstract class NikeFragment : Fragment(), NikeView {
    override val rootView: CoordinatorLayout?
        get() = view as CoordinatorLayout
    override val viewContext: Context?
        get() = context
}


//all activity must extend NikeActivity
abstract class NikeActivity : AppCompatActivity(), NikeView {
    override val rootView: CoordinatorLayout?
        get() = window.decorView.rootView as CoordinatorLayout?
    override val viewContext: Context?
        get() = this
}

interface NikeView {
    val rootView:CoordinatorLayout?
    val viewContext:Context?
    fun setProgressIndicator(mustShow: Boolean){
        rootView?.let {
            viewContext?.let {context ->
                var loadingView=it.findViewById<View>(R.id.loadingView)
                if (loadingView==null && mustShow){
                    loadingView=LayoutInflater.from(context).inflate(R.layout.view_loading,it,false)
                    it.addView(loadingView)
                }

                loadingView?.visibility=if (mustShow) View.VISIBLE else View.GONE

            }

        }
    }
}

//parent off all viewModel classes
abstract class NikeViewModel : ViewModel() {
    val compositeDisposable = CompositeDisposable()
    val progressBarLiveData= MutableLiveData<Boolean>()

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

}
