package com.explain.nikestore.feature.order

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.explain.nikestore.R
import com.explain.nikestore.common.convertDpToPixel
import com.explain.nikestore.common.formatPrice
import com.explain.nikestore.data.OrderHistoryItem
import com.explain.nikestore.view.NikeImageView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_order_history.view.*

class OrderHistoryItemAdapter(val context: Context, val orderHistoryItems: List<OrderHistoryItem>) :
    RecyclerView.Adapter<OrderHistoryItemAdapter.ViewHolder>() {
    val layoutParams: LinearLayout.LayoutParams

    init {
        val size = convertDpToPixel(100f, context).toInt()
        val margin = convertDpToPixel(8f, context).toInt()
        layoutParams = LinearLayout.LayoutParams(size, size)
        layoutParams.setMargins(margin, 0, margin, 0)
    }

    inner class ViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(orderHistoryItem: OrderHistoryItem) {
            containerView.orderIdTv.text = orderHistoryItem.id.toString()
            containerView.orderAmountTv.text = formatPrice(orderHistoryItem.payable)
            containerView.orderProductsLl.removeAllViews()
            orderHistoryItem.order_items.forEach {
                val imageView = NikeImageView(context)
                imageView.layoutParams = layoutParams
                imageView.setImageURI(it.product.image)
                containerView.orderProductsLl.addView(imageView)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(context).inflate(
            R.layout.item_order_history, parent, false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(orderHistoryItems[position])

    override fun getItemCount(): Int = orderHistoryItems.size
}