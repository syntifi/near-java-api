package com.syntifi.near.api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.syntifi.near.api.model.indexer.AccountIdList;
import com.syntifi.near.api.model.indexer.StakingDeposit;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

/**
 *
 */
public interface NearIndexerService {

    /**
     *
     * @param accountId
     * @return
     */
    @GET("account/{accountId}/likelyNFTs")
    Call<AccountIdList> getAccountLikelyNFTs(@Path("accountId") String accountId);

    /**
     *
     * @param accountId
     * @return
     */
    @GET("account/{accountId}/likelyTokens")
    Call<AccountIdList> getAccountLikelyFTs(@Path("accountId") String accountId);

    /**
     *
     * @param accountId
     * @return
     */
    @GET("staking-deposits/{accountId}")
    Call<List<StakingDeposit>> getStakingDeposits(@Path("accountId") String accountId);

    /**
     * NearHelperService builder
     *
     * @param url the helper url to connect to
     * @return the helper service instance
     */
    static NearIndexerService usingPeer(String url) {
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

        return retrofit.create(NearIndexerService.class);
    }
}
