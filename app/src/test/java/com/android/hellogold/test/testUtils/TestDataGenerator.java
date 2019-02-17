package com.android.hellogold.test.testUtils;

import com.android.hellogold.test.data.model.RegisterResponse;
import com.android.hellogold.test.data.model.SpotPriceResponse;
import com.google.gson.Gson;

public class TestDataGenerator {
    private static Gson mGson = new Gson();

    public static RegisterResponse getRegisterResponse_Positive() {
        return mGson.fromJson("{\n" +
                "    \"result\": \"ok\",\n" +
                "    \"data\": {\n" +
                "        \"api_token\": \"4dUTAN2RJzmwRXzEC3mHh0CQZzGSbg7OP5Zvf9sm\",\n" +
                "        \"public_key\": \"sld3-g525-25r2-f5s5\",\n" +
                "        \"api_key\": \"QKZOyVFZ25oEqUyXDfTYiwRmoYcE9E5vJnF50NlD\",\n" +
                "        \"account_number\": \"45800004768\"\n" +
                "    },\n" +
                "    \"code\": \"0000\"\n" +
                "}", RegisterResponse.class);
    }

    public static RegisterResponse getRegisterResponse_Negative() {
        return mGson.fromJson("{\n" +
                "    \"result\": \"error\",\n" +
                "    \"message\": \"Try again\"\n" +
                "}", RegisterResponse.class);
    }

    public static SpotPriceResponse getSpotPrice_Positive() {
        return mGson.fromJson(" {\n" +
                "    \"result\": \"ok\",\n" +
                "    \"data\": {\n" +
                "        \"buy\": 178.44,\n" +
                "        \"sell\": 166.68,\n" +
                "        \"spot_price\": 173.56261542882893483152759563997025,\n" +
                "        \"timestamp\": \"2019-02-17T09:37:41.416+00:00\"\n" +
                "    }\n" +
                "}", SpotPriceResponse.class);
    }

    public static SpotPriceResponse getSpotPrice_Negative() {
        return mGson.fromJson("  {\n" +
                "   \"result\": \"error\",\n" +
                "   \"message\": \"try again\"\n" +
                " }", SpotPriceResponse.class);
    }
}
