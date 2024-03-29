package com.android.hellogold.test.ui.splash

import android.os.Bundle
import com.android.hellogold.test.R
import com.android.hellogold.test.ui.base.BaseMvpActivity
import com.android.hellogold.test.ui.base.BasePresenter
import com.android.hellogold.test.ui.dashboard.DashboardActivity
import com.android.hellogold.test.ui.register.RegisterActivity
import org.jetbrains.anko.startActivity
import javax.inject.Inject

class SplashActivity : BaseMvpActivity(), SplashContracts.View {

    @Inject
    lateinit var mPresenter: SplashContracts.Presenter<SplashContracts.View>

    public override fun onCreate(savedInstanceState: Bundle?) {
        activityComponent?.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setupLayout()
        mPresenter.onAttachView(this)
    }

    override fun sendExtrasToPresenter(extras: Bundle) {

    }

    protected fun setupLayout() {

    }

    public override fun getPresenter(): BasePresenter<*> {
        return mPresenter
    }

    override fun showDashboardScreen() {
        startActivity<DashboardActivity>()
    }

    override fun showRegisterScreen() {
        startActivity<RegisterActivity>()
    }
}
