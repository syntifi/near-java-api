package com.syntifi.near.api.helper.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.syntifi.near.api.helper.model.NearValue;
import com.syntifi.near.api.helper.model.RecentActivity;
import com.syntifi.near.api.common.service.NearObjectMapper;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 *
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.2.0
 */
public interface NearHelperService {

    /**
     * @return
     */
    @GET("/fiat")
    Call<NearValue> getNearValue();

    /**
     * @param username
     * @return
     */
    @GET("account/{accountId}/activity")
    Call<RecentActivity> getNearRecentActivity(@Path("accountId") String username);

    /**
     * NearHelperService builder
     *
     * @param url the helper url to connect to
     * @return the helper service instance
     */
    static NearHelperService usingPeer(String url) {
        ObjectMapper mapper = new NearObjectMapper();

        Headers customHeaders = new Headers.Builder()
                .add("Content-Type", "application/json")
                .add("Cache-Control", "no-cache")
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(new OkHttpClient.Builder()
                        .addInterceptor(
                                chain -> chain.proceed(chain.request().newBuilder().headers(customHeaders).build())).build())
                .baseUrl("https://" + url)
                .addConverterFactory(JacksonConverterFactory.create(mapper))
                .build();

        return retrofit.create(NearHelperService.class);
    }
}
