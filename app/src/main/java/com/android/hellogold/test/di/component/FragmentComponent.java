package com.android.hellogold.test.di.component;

import com.android.hellogold.test.di.module.FragmentModule;
import com.android.hellogold.test.di.scopes.FragmentScope;
import com.android.hellogold.test.ui.base.BaseMvpFragment;
import dagger.Component;


@FragmentScope
@Component(dependencies = ApplicationComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    void inject(BaseMvpFragment baseMvpFragment);

}