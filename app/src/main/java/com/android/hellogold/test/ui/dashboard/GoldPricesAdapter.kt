package com.android.hellogold.test.ui.dashboard

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.hellogold.test.R
import com.android.hellogold.test.data.model.GoldPrice
import kotlinx.android.synthetic.main.item_gold_price.view.*
import java.text.DecimalFormat


class GoldPricesAdapter : RecyclerView.Adapter<GoldPricesAdapter.GoldPriceHolder>() {

    private var mGoldPrices = mutableListOf<GoldPrice>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoldPriceHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_gold_price, parent, false)
        return GoldPriceHolder(view)
    }

    override fun getItemCount() = mGoldPrices.size

    override fun onBindViewHolder(holder: GoldPriceHolder, position: Int) {
        holder.bind(mGoldPrices[position])
    }

    fun refreshPrices(goldPrices: List<GoldPrice>) {
        mGoldPrices = goldPrices.toMutableList()
        notifyDataSetChanged()
    }

    fun addPrice(goldPrice: GoldPrice) {
        mGoldPrices.add(0, goldPrice)
        notifyItemInserted(0)
    }

    class GoldPriceHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(goldPrice: GoldPrice) {
            with(itemView) {
                val decimalFormat = DecimalFormat("0.00")
                val formattedPrice = decimalFormat.format(goldPrice.spotPrice)
                tvSpotPrice.text = String.format(context.getString(R.string.text_gold_price_per_gram), formattedPrice)
                tvPriceDate.text = goldPrice.getFormattedDate()
            }
        }
    }
}