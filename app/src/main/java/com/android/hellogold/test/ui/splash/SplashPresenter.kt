package com.android.hellogold.test.ui.splash

import com.android.hellogold.test.data.IDataManager
import com.android.hellogold.test.ui.base.BaseMvpPresenter
import javax.inject.Inject

class SplashPresenter @Inject
constructor(dataManager: IDataManager) : BaseMvpPresenter<SplashContracts.View>(dataManager),
    SplashContracts.Presenter<SplashContracts.View> {

    override fun onAttachView(view: SplashContracts.View?) {
        super.onAttachView(view)
        val disposable = mUserRepository.isUserLoggedIn()
            .subscribe({ isLoggedIn ->
                if (isLoggedIn) {
                    getView().showDashboardScreen()
                } else {
                    getView().showRegisterScreen()
                }
            }, {
                getView().showRegisterScreen()
            })
        addToSubscription(disposable)
    }
}
