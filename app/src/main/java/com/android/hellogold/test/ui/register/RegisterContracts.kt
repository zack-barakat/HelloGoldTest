package com.android.hellogold.test.ui.register

import com.android.hellogold.test.ui.base.BasePresenter
import com.android.hellogold.test.ui.base.BaseView

interface RegisterContracts {

    interface View : BaseView {
        fun showEmailError(error: String)

        fun openDashboardScreen()
    }

    interface Presenter<V : View> : BasePresenter<V> {
        fun onRegisterClick(email: String, isTnc: Boolean)
    }
}
