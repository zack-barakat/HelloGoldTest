package com.android.hellogold.test.mvp;

import com.android.hellogold.test.rule.TestSchedulersRule;
import com.android.hellogold.test.testCase.AppRobolectricTestCase;
import com.android.hellogold.test.testUtils.TestDataGenerator;
import com.android.hellogold.test.ui.dashboard.DashboardContracts;
import com.android.hellogold.test.ui.dashboard.DashboardPresenter;
import io.reactivex.Observable;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;

import javax.inject.Inject;
import java.util.concurrent.TimeUnit;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class DashboardPresenterTest extends AppRobolectricTestCase {

    @Rule
    public TestSchedulersRule rule = new TestSchedulersRule();

    @Inject
    DashboardPresenter presenter;
    @Mock
    DashboardContracts.View view;

    @Before
    public void setUp() throws Exception {
        component().inject(this);
        super.setUp();
        presenter = spy(presenter);
    }

    @Test
    public void onViewAttach_shouldShowEmail() {
        //Given
        String email = "test@gmail.com";
        when(userRepository.getUserEmail()).thenReturn(email);
        //When
        presenter.onAttachView(view);
        //verify
        verify(view).showEmail(String.format("Hello %s", email));
    }

    @Test
    public void onViewAttach_shouldShowPrices() {
        //Given
        when(apiHelper.getSpotPrice()).thenReturn(Observable.just(TestDataGenerator.getSpotPrice_Positive()));
        //When
        presenter.onAttachView(view);
        rule.advanceTimeBy(1, TimeUnit.SECONDS);
        //verify
        verify(view).showPrices(anyList());
    }

    @Test
    public void onStart_shouldShowLatestPrice() {
        //Given
        presenter.onAttachView(view);
        when(apiHelper.getSpotPrice()).thenReturn(Observable.just(TestDataGenerator.getSpotPrice_Positive()));
        //When
        presenter.onStart();
        rule.advanceTimeBy(1, TimeUnit.SECONDS);
        //verify
        verify(view).showLatestPrice(any());
    }

    @Test
    public void onPullToRefresh_shouldShowLatestPrice() {
        //Given
        presenter.onAttachView(view);
        when(apiHelper.getSpotPrice()).thenReturn(Observable.just(TestDataGenerator.getSpotPrice_Positive()));
        //When
        presenter.onPullToRefresh();
        rule.advanceTimeBy(1, TimeUnit.SECONDS);
        //verify
        verify(view).showLatestPrice(any());
    }

    @Test
    public void onRefreshButton_shouldShowLatestPrice() {
        //Given
        presenter.onAttachView(view);
        when(apiHelper.getSpotPrice()).thenReturn(Observable.just(TestDataGenerator.getSpotPrice_Positive()));
        //When
        presenter.onRefreshButtonClick();
        rule.advanceTimeBy(1, TimeUnit.SECONDS);
        //verify
        verify(view).showLatestPrice(any());
    }


    @After
    public void unsubAll() {
        presenter.onDestroyView();
    }
}