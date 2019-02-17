package com.android.hellogold.test.ui.splash

import com.android.hellogold.test.ui.base.BasePresenter
import com.android.hellogold.test.ui.base.BaseView

interface SplashContracts {

    interface View : BaseView {
        fun showDashboardScreen()

        fun showRegisterScreen()
    }

    interface Presenter<V : View> : BasePresenter<V>
}
