package com.android.hellogold.test.di.module;

import com.android.hellogold.test.data.TestApiHelper;
import com.android.hellogold.test.data.network.IApiHelper;
import com.android.hellogold.test.di.scopes.ApplicationScope;
import dagger.Module;
import dagger.Provides;

import static org.mockito.Mockito.spy;


@Module(includes = {TestApplicationModule.class})
public class MockApiModule {

    @Provides
    @ApplicationScope
    IApiHelper provideApiHelper() {
        return spy(new TestApiHelper());
    }
}

