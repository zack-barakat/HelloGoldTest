package com.android.hellogold.test.ui.register

import com.android.hellogold.test.R
import com.android.hellogold.test.data.IDataManager
import com.android.hellogold.test.data.model.RegisterPayload
import com.android.hellogold.test.extensions.isValidEmail
import com.android.hellogold.test.ui.base.BaseMvpPresenter
import com.android.hellogold.test.ui.base.ErrorView
import javax.inject.Inject

class RegisterPresenter @Inject
constructor(dataManager: IDataManager) : BaseMvpPresenter<RegisterContracts.View>(dataManager),
    RegisterContracts.Presenter<RegisterContracts.View> {

    var mEmail: String? = null

    override fun onRegisterClick(email: String, isTnc: Boolean) {
        if (!email.isValidEmail()) {
            view.showEmailError(mAppContext.getString(R.string.error_email_not_valid))
            return
        }
        mEmail = email
        val generatedRandomString = RegisterPayload.get256StringFromKey(email)
        val tnc = isTnc.toString()

        view.showProgress()
        val disposable = mUserRepository
            .registerUser(RegisterPayload(email = email, data = generatedRandomString, tnc = tnc))
            .subscribe({ response ->
                view.hideProgress()
                if (response.isSuccess()) {
                    view.openMainScreen()
                } else {
                    view.hideProgress()
                }
            }, {
                view.hideProgress()
                handleApiError(it, ErrorView.ERROR_DIALOG)
            })
        addToSubscription(disposable)
    }
}
