package com.android.hellogold.test.ui.dashboard

import com.android.hellogold.test.data.IDataManager
import com.android.hellogold.test.ui.base.BaseMvpPresenter
import com.android.hellogold.test.ui.base.ErrorView
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class DashboardPresenter @Inject
constructor(dataManager: IDataManager) : BaseMvpPresenter<DashboardContracts.View>(dataManager),
    DashboardContracts.Presenter<DashboardContracts.View> {

    override fun onAttachView(view: DashboardContracts.View) {
        super.onAttachView(view)
        val email = if (mUserRepository.getUserEmail() != null) "Hello ${mUserRepository.getUserEmail()}" else "Hello"
        view.showEmail(email)
        fetchAndShowRecentPrices()
    }

    private fun fetchAndShowRecentPrices() {
        // if we don't have past prices then we will fetch 4 prices from API
        if (mPriceRepository.getGoldPrices().size < 4) {
            view.showProgress()
            val disposable = Observable.interval(0, 100, TimeUnit.MILLISECONDS, Schedulers.computation())
                .take(4)
                .flatMap { mPriceRepository.getSpotPrice() }
                .subscribe({}, {
                    view.hideProgress()
                    handleApiError(it, ErrorView.ERROR_TOAST)
                }, {
                    view.showPrices(mPriceRepository.getGoldPrices())
                    view.hideProgress()
                })
            addToSubscription(disposable)
        } else {
            view.showPrices(mPriceRepository.getGoldPrices())
        }
    }

    override fun onStart() {
        getLatestSpotPrice()
    }

    override fun onPullToRefresh() {
        getLatestSpotPrice()
    }

    override fun onRefreshButtonClick() {
        getLatestSpotPrice()
    }

    private fun getLatestSpotPrice() {
        view.showProgress()
        val disposable = mPriceRepository.getSpotPrice()
            .subscribe({
                view.hideProgress()
                if (it.isSuccess() && it.goldPrice != null) {
                    view.showLatestPrice(it.goldPrice)
                }
            }, {
                view.hideProgress()
                handleApiError(it, ErrorView.ERROR_TOAST)
            })
        addToSubscription(disposable)
    }
}
