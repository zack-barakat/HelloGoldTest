package com.android.hellogold.test.mvp;

import com.android.hellogold.test.rule.TestSchedulersRule;
import com.android.hellogold.test.testCase.AppRobolectricTestCase;
import com.android.hellogold.test.ui.splash.SplashContracts;
import com.android.hellogold.test.ui.splash.SplashPresenter;
import io.reactivex.Observable;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;

import javax.inject.Inject;
import java.util.concurrent.TimeUnit;

import static org.mockito.Mockito.*;


public class SplashPresenterTest extends AppRobolectricTestCase {

    @Rule
    public TestSchedulersRule rule = new TestSchedulersRule();

    @Mock
    SplashContracts.View mView;

    @Inject
    SplashPresenter mPresenter;

    @Before
    @Override
    public void setUp() throws Exception {
        component().inject(this);
        super.setUp();
        mPresenter = spy(mPresenter);
        assertNotNull(mPresenter);
    }

    @Test
    public void onFirstViewAttach_userLoggedIn_shouldShowDashboardScreen() {
        //Given
        doReturn(Observable.just(true)).when(userRepository).isUserLoggedIn();
        //When
        mPresenter.onAttachView(mView);
        rule.advanceTimeBy(1, TimeUnit.SECONDS);
        //Verify
        verify(mView).showDashboardScreen();
        verify(mView, never()).showRegisterScreen();
    }

    @Test
    public void onFirstViewAttach_userNotLoggedIn_shouldShowRegisterScreen() {
        //Given
        doReturn(Observable.just(false)).when(userRepository).isUserLoggedIn();
        //When
        mPresenter.onAttachView(mView);
        rule.advanceTimeBy(1, TimeUnit.SECONDS);
        //Verify
        verify(mView).showRegisterScreen();
        verify(mView, never()).showDashboardScreen();
    }

    @After
    public void unsubAll() {
        mPresenter.onDestroyView();
    }
}