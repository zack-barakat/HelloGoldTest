package com.android.hellogold.test.data.network;

import com.android.hellogold.test.data.model.RegisterPayload;
import com.android.hellogold.test.data.model.RegisterResponse;
import com.android.hellogold.test.data.model.SpotPriceResponse;
import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;


/**
 * Created by zack_barakat
 */

public interface IApiHelper {


    @POST("v3/users/register.json")
    Observable<RegisterResponse> registerUser(@Body RegisterPayload body);

    @GET("v2/spot_price.json")
    Observable<SpotPriceResponse> getSpotPrice();

    class Factory {
        public static final int NETWORK_CALL_TIMEOUT = 30;

        public static IApiHelper create(Retrofit retrofit) {

            return retrofit.create(IApiHelper.class);
        }
    }
}
