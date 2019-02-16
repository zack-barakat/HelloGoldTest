package com.android.hellogold.test.di.component;

import android.app.Application;
import android.content.Context;
import com.android.hellogold.test.App;
import com.android.hellogold.test.data.IDataManager;
import com.android.hellogold.test.di.module.ApiModule;
import com.android.hellogold.test.di.module.ApplicationModule;
import com.android.hellogold.test.di.module.DataManagerModule;
import com.android.hellogold.test.di.qualifiers.ApplicationContext;
import com.android.hellogold.test.di.scopes.ApplicationScope;
import dagger.BindsInstance;
import dagger.Component;

@ApplicationScope
@Component(modules = {
        DataManagerModule.class,
        ApiModule.class,
        ApplicationModule.class})
public interface ApplicationComponent {

    void inject(App app);

    @ApplicationContext
    Context context();

    Application application();

    IDataManager getDataManager();

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        ApplicationComponent build();
    }
}