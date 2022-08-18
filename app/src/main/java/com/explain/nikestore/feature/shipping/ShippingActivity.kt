package com.explain.nikestore.feature.shipping

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.explain.nikestore.R
import com.explain.nikestore.common.EXTRA_KEY_DATA
import com.explain.nikestore.common.EXTRA_KEY_ID
import com.explain.nikestore.common.NikeSingleObserver
import com.explain.nikestore.common.openUrlInCustomTab
import com.explain.nikestore.data.PurchaseDetail
import com.explain.nikestore.data.SubmitOrderResult
import com.explain.nikestore.feature.cart.CartItemAdapter
import com.explain.nikestore.feature.checkout.CheckOutActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_shipping.*
import java.lang.IllegalStateException
import org.koin.android.viewmodel.ext.android.viewModel

class ShippingActivity : AppCompatActivity() {
    val viewModel: ShippingViewModel by viewModel()
    val compositeDisposable = CompositeDisposable()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shipping)

        val purchaseDetail = intent.getParcelableExtra<PurchaseDetail>(EXTRA_KEY_DATA)
            ?: throw IllegalStateException("purchase detail cannot be null")

        val viewHolder = CartItemAdapter.PurchaseDetailViewHolder(purchaseDetailView)
        viewHolder.bind(
            purchaseDetail.totalPrice,
            purchaseDetail.shipping_cost,
            purchaseDetail.payable_price
        )

        val onClickListener = View.OnClickListener {
            viewModel.submitOrder(
                firstNameEt.text.toString(),
                lastNameEt.text.toString(),
                postalCodeEt.text.toString(),
                phoneNumberEt.text.toString(),
                addressEt.text.toString(),
                if (it.id == R.id.onlinePaymentBtn) PAYMENT_METHOD_ONLINE else PAYMENT_METHOD_COD
            ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : NikeSingleObserver<SubmitOrderResult>(compositeDisposable) {
                    override fun onSuccess(t: SubmitOrderResult) {
                        if (t.bank_gateway_url.isNotEmpty()) {
                            openUrlInCustomTab(this@ShippingActivity, t.bank_gateway_url)
                        } else {
                            startActivity(
                                Intent(
                                    this@ShippingActivity,
                                    CheckOutActivity::class.java
                                ).apply {
                                    putExtra(EXTRA_KEY_ID, t.order_id)
                                }
                            )
                        }
                        finish()

                    }
                })
        }

        onlinePaymentBtn.setOnClickListener(onClickListener)
        codBtn.setOnClickListener(onClickListener)
    }
}