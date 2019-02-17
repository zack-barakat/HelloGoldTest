package com.android.hellogold.test.ui.dashboard

import com.android.hellogold.test.data.model.GoldPrice
import com.android.hellogold.test.ui.base.BasePresenter
import com.android.hellogold.test.ui.base.BaseView

interface DashboardContracts {

    interface View : BaseView {

        fun showEmail(email: String)

        fun showLatestPrice(goldPrice: GoldPrice)

        fun showPrices(prices: List<GoldPrice>)
    }

    interface Presenter<V : View> : BasePresenter<V> {

        fun onStart()

        fun onPullToRefresh()

        fun onRefreshButtonClick()
    }
}
