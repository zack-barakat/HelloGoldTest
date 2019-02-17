package com.android.hellogold.test.ui.dashboard

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.android.hellogold.test.R
import com.android.hellogold.test.data.model.GoldPrice
import com.android.hellogold.test.ui.base.BaseMvpActivity
import com.android.hellogold.test.ui.base.BasePresenter
import kotlinx.android.synthetic.main.activity_dashboard.*
import javax.inject.Inject

class DashboardActivity : BaseMvpActivity(), DashboardContracts.View {

    @Inject
    lateinit var mPresenter: DashboardContracts.Presenter<DashboardContracts.View>

    private val mAdapter = GoldPricesAdapter()

    public override fun onCreate(savedInstanceState: Bundle?) {
        activityComponent?.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        setupLayout()
        mPresenter.onAttachView(this)
    }

    override fun onStart() {
        super.onStart()
        mPresenter.onStart()
    }

    override fun sendExtrasToPresenter(extras: Bundle) {

    }

    private fun setupLayout() {
        supportActionBar?.title = getString(R.string.title_dashboard)
        swipeRefreshPricesLayout.setOnRefreshListener { mPresenter.onPullToRefresh() }
        btnRefreshPrice.setOnClickListener { mPresenter.onRefreshButtonClick() }
        setupPricesRecyclerView()
    }

    private fun setupPricesRecyclerView() {
        rvPricesHistory.adapter = mAdapter
        val linearLayoutManager = LinearLayoutManager(this)
        rvPricesHistory.layoutManager = linearLayoutManager
        val dividerItemDecoration = DividerItemDecoration(this, linearLayoutManager.orientation)
        rvPricesHistory.addItemDecoration(dividerItemDecoration)
    }

    public override fun getPresenter(): BasePresenter<*> {
        return mPresenter
    }

    override fun showProgress() {
        swipeRefreshPricesLayout.isRefreshing = true
    }

    override fun hideProgress() {
        swipeRefreshPricesLayout.isRefreshing = false
    }

    override fun showEmail(email: String) {
        tvEmail.text = email
    }

    override fun showLatestPrice(goldPrice: GoldPrice) {
        mAdapter.addPrice(goldPrice)
        rvPricesHistory.scrollToPosition(0)
    }

    override fun showPrices(prices: List<GoldPrice>) {
        mAdapter.refreshPrices(prices)
    }
}
