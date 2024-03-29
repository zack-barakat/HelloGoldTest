package com.android.hellogold.test.ui.base;

import android.content.Context;
import com.android.hellogold.test.data.ErrorAction;
import com.android.hellogold.test.data.IAppErrorHelper;
import com.android.hellogold.test.data.IDataManager;
import com.android.hellogold.test.data.repositories.IPriceRepository;
import com.android.hellogold.test.data.repositories.IUserRepository;
import com.android.hellogold.test.di.qualifiers.ApplicationContext;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

import java.lang.ref.WeakReference;

public abstract class BaseMvpPresenter<V extends BaseView> implements BasePresenter<V> {

    @ApplicationContext
    protected final Context mAppContext;
    protected IDataManager mDataManager;
    protected IAppErrorHelper mAppErrorHelper;
    protected IUserRepository mUserRepository;
    protected IPriceRepository mPriceRepository;
    protected CompositeDisposable disposableSubscription = new CompositeDisposable();
    WeakReference<V> mViewWeak;

    boolean isFirstTime = true;
    private boolean isViewPaused;

    public BaseMvpPresenter(IDataManager dataManager) {
        mDataManager = dataManager;
        this.mAppContext = mDataManager.getApplicationContext();
        this.mAppErrorHelper = mDataManager.getAppErrorHelper();
        this.mUserRepository = mDataManager.getUserRepository();
        this.mPriceRepository = mDataManager.getPriceRepository();
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


    public void handleApiError(Throwable throwable, int messageStyle) {
        ErrorAction action = mAppErrorHelper.handleAppException(throwable);
        handleErrorAction(messageStyle, action);
    }

    protected void handleErrorAction(int messageStyle, ErrorAction action) {
        switch (action.getActionType()) {
            case ErrorAction.ACTION_TYPE_SHOW_ERROR:
                getView().showError(action.getMessageContent(), messageStyle);
                break;
            case ErrorAction.ACTION_TYPE_DESTROY_VIEW:
                getView().destroyView();
                break;
        }
    }
}
