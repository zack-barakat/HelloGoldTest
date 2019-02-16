package com.android.hellogold.test.di.module;

import android.content.Context;
import com.android.hellogold.test.BuildConfig;
import com.android.hellogold.test.data.network.IApiHelper;
import com.android.hellogold.test.data.network.RxErrorHandlingCallAdapterFactory;
import com.android.hellogold.test.di.qualifiers.ApplicationContext;
import com.android.hellogold.test.di.scopes.ApplicationScope;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

import static com.android.hellogold.test.data.network.IApiHelper.Factory.NETWORK_CALL_TIMEOUT;


/**
 * Created by zack_barakat
 */

@Module(includes = {ApplicationModule.class})
public class ApiModule {


    public static final String URL = "https://cws.hellogold.com/api/";

    public ApiModule() {
    }

    @Provides
    @ApplicationScope
    IApiHelper provideApiHelper(Retrofit retrofit) {
        return IApiHelper.Factory.create(retrofit);
    }

    @Provides
    @ApplicationScope
    public Retrofit retrofit(OkHttpClient okHttpClient, @ApplicationContext Context context) {
        return new Retrofit.Builder()
                .baseUrl(URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create(context))
                .build();
    }

    @Provides
    @ApplicationScope
    public OkHttpClient okHttpClient(HttpLoggingInterceptor logging) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(logging);
        builder.readTimeout(NETWORK_CALL_TIMEOUT, TimeUnit.SECONDS);
        builder.writeTimeout(NETWORK_CALL_TIMEOUT, TimeUnit.SECONDS);
        return builder.build();
    }

    @Provides
    @ApplicationScope
    public HttpLoggingInterceptor httpLoggingInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(
                BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        return logging;
    }
}
