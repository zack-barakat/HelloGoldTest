package com.android.hellogold.test.ui.base;

import android.content.Context;
import com.android.hellogold.test.data.IDataManager;
import com.android.hellogold.test.di.qualifiers.ApplicationContext;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

import java.lang.ref.WeakReference;

public abstract class BaseMvpPresenter<V extends BaseView> implements BasePresenter<V> {

    @ApplicationContext
    protected final Context mAppContext;
    protected IDataManager mDataManager;
    protected CompositeDisposable disposableSubscription = new CompositeDisposable();
    WeakReference<V> mViewWeak;

    boolean isFirstTime = true;
    private boolean isViewPaused;

    public BaseMvpPresenter(IDataManager dataManager) {
        mDataManager = dataManager;
        this.mAppContext = mDataManager.getApplicationContext();
    }

    @Override
    public void onAttachView(V view) {
        mViewWeak = new WeakReference<>(view);
        if (isFirstTime) {
            onFirstViewAttach();
            isFirstTime = false;
        } else {
            onRestoreState();
        }
    }

    protected void onRestoreState() {

    }

    /**
     * This method is called once only no matter how times the view gets recreated. useful for tracking
     */
    protected void onFirstViewAttach() {

    }

    public void addToSubscription(Disposable disposable) {
        disposableSubscription.add(disposable);
    }

    @Override
    public void onResume() {

    }

    @Override
    public boolean isViewPaused() {
        return isViewPaused;
    }

    @Override
    public void setViewPaused(boolean viewPaused) {
        this.isViewPaused = viewPaused;
    }

    @NonNull
    public V getView() {
        return mViewWeak.get();
    }

    @Override
    public void onDestroyView() {
        disposableSubscription.clear();
    }
}