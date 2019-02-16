package com.android.hellogold.test.di.component;


import com.android.hellogold.test.di.module.ActivityModule;
import com.android.hellogold.test.di.scopes.ActivityScope;
import com.android.hellogold.test.ui.base.BaseMvpActivity;
import com.android.hellogold.test.ui.splash.SplashActivity;
import dagger.Component;


@ActivityScope
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(BaseMvpActivity activity);

    void inject(SplashActivity activity);

}