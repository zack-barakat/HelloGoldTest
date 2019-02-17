package com.android.hellogold.test.mvp;

import com.android.hellogold.test.data.model.RegisterResponse;
import com.android.hellogold.test.testCase.AppRobolectricTestCase;
import com.android.hellogold.test.testUtils.TestDataGenerator;
import com.android.hellogold.test.ui.register.RegisterContracts;
import com.android.hellogold.test.ui.register.RegisterPresenter;
import io.reactivex.Observable;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import javax.inject.Inject;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class RegisterPresenterTest extends AppRobolectricTestCase {

    @Inject
    RegisterPresenter presenter;
    @Mock
    RegisterContracts.View view;

    @Before
    public void setUp() throws Exception {
        component().inject(this);
        super.setUp();
        presenter = spy(presenter);
    }

    @Test
    public void onRegisterError_shouldShowError() {
        //Given
        presenter.onAttachView(view);
        String email = "test@gmail.com";
        when(apiHelper.registerUser(any())).thenReturn(Observable.error(new Exception("test")));
        //When
        presenter.onRegisterClick(email, true);
        //verify
        verify(view).showError(anyString(), anyInt());
        verify(view, never()).showEmailError(anyString());
        verify(view, never()).openDashboardScreen();
    }


    @Test
    public void onRegisterError_shouldNotStoreUserDetails() {
        //Given
        presenter.onAttachView(view);
        String email = "test@gmail.com";
        RegisterResponse registerResponse = TestDataGenerator.getRegisterResponse_Positive();
        when(apiHelper.registerUser(any())).thenReturn(Observable.error(new Exception("test")));
        //When
        presenter.onRegisterClick(email, true);
        //verify
        verify(preferenceHelper, never()).setUserLoggedIn(true);
        verify(preferenceHelper, never()).setEmail(email);
        verify(preferenceHelper, never()).setApiToken(registerResponse.getData().getApiToken());
        verify(preferenceHelper, never()).setApiKey(registerResponse.getData().getApiKey());
    }

    @Test
    public void onRegisterInvalidEmail_shouldInvalidEmail() {
        //Given
        presenter.onAttachView(view);
        when(apiHelper.registerUser(any())).thenReturn(Observable.just(TestDataGenerator.getRegisterResponse_Positive()));
        //When
        presenter.onRegisterClick(anyString(), anyBoolean());
        //verify
        verify(view, times(1)).showEmailError(anyString());
        verify(view, never()).showError(anyInt(), anyInt());
        verify(view, never()).openDashboardScreen();
    }

    @Test
    public void onRegisterSuccess_shouldOpenDashboardScreen() {
        //Given
        presenter.onAttachView(view);
        when(apiHelper.registerUser(any())).thenReturn(Observable.just(TestDataGenerator.getRegisterResponse_Positive()));
        //When
        presenter.onRegisterClick("test@gmail.com", true);
        //verify
        verify(view, times(1)).openDashboardScreen();
        verify(view, never()).showError(anyInt(), anyInt());
        verify(view, never()).showEmailError(anyString());
    }

    @Test
    public void onRegisterValidSuccess_shouldStoreUserDetails() {
        //Given
        presenter.onAttachView(view);
        String email = "test@gmail.com";
        RegisterResponse registerResponse = TestDataGenerator.getRegisterResponse_Positive();
        when(apiHelper.registerUser(any())).thenReturn(Observable.just(registerResponse));
        //When
        presenter.onRegisterClick(email, true);
        //verify
        verify(preferenceHelper).setUserLoggedIn(true);
        verify(preferenceHelper).setEmail(email);
        verify(preferenceHelper).setApiToken(registerResponse.getData().getApiToken());
        verify(preferenceHelper).setApiKey(registerResponse.getData().getApiKey());
    }


    @After
    public void unsubAll() {
        presenter.onDestroyView();
    }
}