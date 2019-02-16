package com.android.hellogold.test;

import android.app.Application;
import com.android.hellogold.test.di.component.ApplicationComponent;
import com.android.hellogold.test.di.component.DaggerApplicationComponent;

public class App extends Application {

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        initDagger();
    }

    protected void initDagger() {

        ApplicationComponent appComponent = DaggerApplicationComponent
                .builder()
                .application(this)
                .build();

        setComponent(appComponent);
    }

    public ApplicationComponent getComponent() {
        return component;
    }

    public void setComponent(ApplicationComponent component) {
        this.component = component;
        component.inject(this);
    }

}
