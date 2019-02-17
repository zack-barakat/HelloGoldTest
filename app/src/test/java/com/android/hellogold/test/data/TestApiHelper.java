package com.android.hellogold.test.data;

import com.android.hellogold.test.data.model.RegisterPayload;
import com.android.hellogold.test.data.model.RegisterResponse;
import com.android.hellogold.test.data.model.SpotPriceResponse;
import com.android.hellogold.test.data.network.IApiHelper;
import com.android.hellogold.test.di.scopes.ApplicationScope;
import io.reactivex.Observable;

import static org.mockito.Mockito.mock;


@ApplicationScope
public class TestApiHelper implements IApiHelper {


    @Override
    public Observable<RegisterResponse> registerUser(RegisterPayload body) {
        RegisterResponse model = mock(RegisterResponse.class);
        return Observable.just(model);
    }

    @Override
    public Observable<SpotPriceResponse> getSpotPrice() {
        SpotPriceResponse model = mock(SpotPriceResponse.class);
        return Observable.just(model);
    }
}
