package com.android.hellogold.test.di.component;

import android.app.Application;
import android.content.Context;
import com.android.hellogold.test.App;
import com.android.hellogold.test.data.IAppErrorHelper;
import com.android.hellogold.test.data.IDataManager;
import com.android.hellogold.test.data.network.IApiHelper;
import com.android.hellogold.test.data.prefs.IPreferencesHelper;
import com.android.hellogold.test.data.repositories.IPriceRepository;
import com.android.hellogold.test.data.repositories.IUserRepository;
import com.android.hellogold.test.di.module.MockApiModule;
import com.android.hellogold.test.di.module.TestApplicationModule;
import com.android.hellogold.test.di.module.TestDataManagerModule;
import com.android.hellogold.test.di.qualifiers.ApplicationContext;
import com.android.hellogold.test.di.scopes.ApplicationScope;
import com.android.hellogold.test.mvp.RegisterPresenterTest;
import com.android.hellogold.test.mvp.SplashPresenterTest;
import com.android.hellogold.test.testCase.AppRobolectricTestCase;
import dagger.BindsInstance;
import dagger.Component;

@ApplicationScope
@Component(modules = {
        TestDataManagerModule.class,
        MockApiModule.class,
        TestApplicationModule.class})
public interface TestApplicationComponent extends ApplicationComponent {

    void inject(App app);

    @ApplicationContext
    Context context();

    void inject(AppRobolectricTestCase appRobolectricTestCase);

    void inject(RegisterPresenterTest registerPresenterTest);

    void inject(SplashPresenterTest splashPresenterTest);

    @Component.Builder
    interface Builder {
        @BindsInstance
        TestApplicationComponent.Builder application(Application application);

        TestApplicationComponent build();
    }

    Application application();

    IPreferencesHelper getPreferenceHelper();

    IDataManager getDataManager();

    IUserRepository getUserRepository();

    IPriceRepository getPriceRepository();

    IAppErrorHelper getAppErrorHelper();

    IApiHelper getApiHelper();
}
