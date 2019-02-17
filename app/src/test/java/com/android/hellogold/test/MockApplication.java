package com.android.hellogold.test;

import com.android.hellogold.test.di.component.DaggerTestApplicationComponent;
import com.android.hellogold.test.di.component.TestApplicationComponent;

public class MockApplication extends App {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void initDagger() {
        TestApplicationComponent applicationComponent = DaggerTestApplicationComponent.builder()
                .application(this)
                .build();
        setComponent(applicationComponent);
    }
}
