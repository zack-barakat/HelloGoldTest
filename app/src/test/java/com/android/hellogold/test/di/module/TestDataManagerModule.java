package com.android.hellogold.test.di.module;

import com.android.hellogold.test.data.AppErrorHelper;
import com.android.hellogold.test.data.DataManager;
import com.android.hellogold.test.data.IAppErrorHelper;
import com.android.hellogold.test.data.IDataManager;
import com.android.hellogold.test.data.repositories.IPriceRepository;
import com.android.hellogold.test.data.repositories.IUserRepository;
import com.android.hellogold.test.data.repositories.PriceRepository;
import com.android.hellogold.test.data.repositories.UserRepository;
import com.android.hellogold.test.di.scopes.ApplicationScope;
import dagger.Module;
import dagger.Provides;

import static org.mockito.Mockito.spy;


@Module
public class TestDataManagerModule {

    @Provides
    @ApplicationScope
    IDataManager provideIAppDataManager(DataManager testDataManager) {
        return spy(testDataManager);
    }

    @Provides
    @ApplicationScope
    IUserRepository provideIUserRepository(UserRepository userRepository) {
        return spy(userRepository);
    }

    @Provides
    @ApplicationScope
    IPriceRepository provideIPriceRepository(PriceRepository priceRepository) {
        return spy(priceRepository);
    }

    @Provides
    @ApplicationScope
    IAppErrorHelper provideIAppErrorHelper(AppErrorHelper errorHelper) {
        return spy(errorHelper);
    }
}
