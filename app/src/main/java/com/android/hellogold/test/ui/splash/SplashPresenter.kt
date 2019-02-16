package com.android.hellogold.test.ui.splash

import com.android.hellogold.test.data.IDataManager
import com.android.hellogold.test.ui.base.BaseMvpPresenter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SplashPresenter @Inject
constructor(dataManager: IDataManager) : BaseMvpPresenter<SplashContracts.View>(dataManager),
    SplashContracts.Presenter<SplashContracts.View> {

    override fun onAttachView(view: SplashContracts.View?) {
        super.onAttachView(view)
        val disposable = Observable.just("")
            .delay(2000, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
            .subscribe({
                getView().showMainScreen()
            }, {
                getView().showMainScreen()
            })
        addToSubscription(disposable)
    }
}
