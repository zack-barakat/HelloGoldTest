package com.android.hellogold.test.di.module;


import com.android.hellogold.test.data.AppErrorHelper;
import com.android.hellogold.test.data.DataManager;
import com.android.hellogold.test.data.IAppErrorHelper;
import com.android.hellogold.test.data.IDataManager;
import com.android.hellogold.test.di.scopes.ApplicationScope;
import dagger.Module;
import dagger.Provides;

/**
 * Created by zack_barakat
 */

@Module(includes = ApiModule.class)
public class DataManagerModule {

    @Provides
    @ApplicationScope
    public IDataManager provideDataManager(DataManager manager) {
        return manager;
    }

    @Provides
    @ApplicationScope
    public IAppErrorHelper provideErrorHelper(AppErrorHelper errorHelper) {
        return errorHelper;
    }
}
