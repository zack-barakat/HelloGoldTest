package com.android.hellogold.test.ui.register

import android.os.Bundle
import com.android.hellogold.test.R
import com.android.hellogold.test.extensions.onTextChanged
import com.android.hellogold.test.ui.base.BaseMvpActivity
import com.android.hellogold.test.ui.base.BasePresenter
import kotlinx.android.synthetic.main.activity_register.*
import javax.inject.Inject

class RegisterActivity : BaseMvpActivity(), RegisterContracts.View {

    @Inject
    lateinit var mPresenter: RegisterContracts.Presenter<RegisterContracts.View>

    public override fun onCreate(savedInstanceState: Bundle?) {
        activityComponent?.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        setupLayout()
        mPresenter.onAttachView(this)
    }

    override fun sendExtrasToPresenter(extras: Bundle) {
    }

    private fun setupLayout() {
        etEmail.onTextChanged { _, _, _, _ ->
            tiEmail.error = null
            tiEmail.isErrorEnabled = false
        }
        btnRegister.setOnClickListener {
            mPresenter.onRegisterClick(etEmail.text.toString(), cbTnc.isChecked)
        }
    }

    public override fun getPresenter(): BasePresenter<*> = mPresenter

    override fun showEmailError(error: String) {
        tiEmail.isErrorEnabled = true
        tiEmail.error = error
    }

    override fun openMainScreen() {
    }
}
