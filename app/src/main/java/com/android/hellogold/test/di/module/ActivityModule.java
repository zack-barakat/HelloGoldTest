package com.android.hellogold.test.di.module;

import android.app.Activity;
import android.content.Context;
import com.android.hellogold.test.di.qualifiers.ActivityContext;
import com.android.hellogold.test.ui.dashboard.DashboardContracts;
import com.android.hellogold.test.ui.dashboard.DashboardPresenter;
import com.android.hellogold.test.ui.register.RegisterContracts;
import com.android.hellogold.test.ui.register.RegisterPresenter;
import com.android.hellogold.test.ui.splash.SplashContracts;
import com.android.hellogold.test.ui.splash.SplashPresenter;
import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return activity;
    }

    @Provides
    Activity provideActivity() {
        return activity;
    }

    @Provides
    SplashContracts.Presenter<SplashContracts.View> provideSplashPresenter(SplashPresenter splashPresenter) {
        return splashPresenter;
    }

    @Provides
    RegisterContracts.Presenter<RegisterContracts.View> provideRegisterPresenter(RegisterPresenter registerPresenter) {
        return registerPresenter;
    }

    @Provides
    DashboardContracts.Presenter<DashboardContracts.View> provideDashboardPresenter(DashboardPresenter dashboardPresenter) {
        return dashboardPresenter;
    }
}
