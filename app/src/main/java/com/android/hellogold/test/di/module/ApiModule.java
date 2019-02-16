package com.android.hellogold.test.di.module;

import android.content.Context;
import com.android.hellogold.test.BuildConfig;
import com.android.hellogold.test.data.network.IApiHelper;
import com.android.hellogold.test.data.network.RxErrorHandlingCallAdapterFactory;
import com.android.hellogold.test.di.qualifiers.ApplicationContext;
import com.android.hellogold.test.di.scopes.ApplicationScope;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.orhanobut.logger.Logger;
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


    public static final String URL = "https://staging.hellogold.com/api";

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
    public HttpLoggingInterceptor httpLoggingInterceptor(HttpLoggingInterceptor.Logger logger) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(logger);
        logging.setLevel(
                BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        return logging;
    }

    @Provides
    @ApplicationScope
    HttpLoggingInterceptor.Logger provideLogger() {

        if (BuildConfig.DEBUG) {
            return new HttpLoggingInterceptor.Logger() {
                private StringBuilder mMessage = new StringBuilder();

                @Override
                public void log(String message) {
                    try {
                        if (message.startsWith("--> POST") || message.startsWith("--> GET")) {

                            mMessage.append(message.concat("\n"));
                        }
                        if ((message.startsWith("{") && message.endsWith("}"))
                                || (message.startsWith("[") && message.endsWith("]"))) {
                            message = new GsonBuilder()
                                    .setPrettyPrinting()
                                    .create()
                                    .toJson(new JsonParser().parse(message));
                            mMessage.append(message.concat("\n"));
                            Logger.d(mMessage.toString());
                            mMessage.setLength(0);
                        }
                        if (message.contains("UnknownHostException") ||
                                message.contains("timeout") ||
                                message.contains("Bad Request")) {
                            mMessage.append(message.concat("\n"));
                            Logger.d(mMessage.toString());
                            mMessage.setLength(0);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
        }
        return message -> {
        };
    }
}
