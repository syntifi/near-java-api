package com.syntifi.near.api.indexer.service;

import com.syntifi.near.api.common.service.NearObjectMapper;
import com.syntifi.near.api.indexer.model.AccountIdList;
import com.syntifi.near.api.indexer.model.RecentActivity;
import com.syntifi.near.api.indexer.model.StakingDeposit;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

/**
 * Near indexer service uses http API to retrieve useful information on chain data
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.2.0
 */
public interface NearIndexerService {

    /**
     * Fetches all likely NFTs given an accountId
     *
     * @param accountId the account to search information for
     * @return a retrofit call for a AccountIdList
     */
    @GET("account/{accountId}/likelyNFTs")
    Call<AccountIdList> getAccountLikelyNFTs(@Path("accountId") String accountId);

    /**
     * Fetches all likely FTs (Tokens) given an accountId
     *
     * @param accountId the account to search information for
     * @return a retrofit call for a AccountIdList
     */
    @GET("account/{accountId}/likelyTokens")
    Call<AccountIdList> getAccountLikelyFTs(@Path("accountId") String accountId);

    /**
     * Fetches staing deposits for an account
     *
     * @param accountId the account to search information for
     * @return a retrofit call for a list of StakingDeposits
     */
    @GET("staking-deposits/{accountId}")
    Call<List<StakingDeposit>> getStakingDeposits(@Path("accountId") String accountId);

    /**
     * Fetches a list of recent activities for one account
     *
     * @param accountId the account to fetch activity
     * @return list of recent activity for the account
     */
    @GET("account/{accountId}/activity")
    Call<RecentActivity> getNearRecentActivity(@Path("accountId") String accountId);

    /**
     * NearIndexerService builder
     *
     * @param url the indexer url to connect to
     * @return the indexer service instance
     */
    static NearIndexerService usingPeer(String url) {
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

        return retrofit.create(NearIndexerService.class);
    }
}
