package com.syntifi.near.api.helper.service;

import com.syntifi.near.api.common.service.NearObjectMapper;
import com.syntifi.near.api.helper.model.NearValue;
import com.syntifi.near.api.helper.model.RecentActivity;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Near Helper service uses the http helper API to retrieve useful data
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.2.0
 */
public interface NearHelperService {

    /**
     * Fetches the value of near in fiat currency
     *
     * @return the Near fiat value
     */
    @GET("/fiat")
    Call<NearValue> getNearValue();

    /**
     * Fetches a list of recent activities for one account
     *
     * @param accountId the account to fetch activity
     * @return list of recent activity for the account
     */
    @GET("account/{accountId}/activity")
    Call<RecentActivity> getNearRecentActivity(@Path("accountId") String accountId);

    /**
     * NearHelperService builder
     *
     * @param url the helper url to connect to
     * @return the helper service instance
     */
    static NearHelperService usingPeer(String url) {
        Headers customHeaders = new Headers.Builder()
                .add("Content-Type", "application/json")
                .add("Cache-Control", "no-cache")
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(new OkHttpClient.Builder()
                        .addInterceptor(
                                chain -> chain.proceed(chain.request().newBuilder().headers(customHeaders).build())).build())
                .baseUrl("https://" + url)
                .addConverterFactory(JacksonConverterFactory.create(NearObjectMapper.INSTANCE))
                .build();

        return retrofit.create(NearHelperService.class);
    }
}
