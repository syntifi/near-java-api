package com.syntifi.near.api.indexer;

import com.syntifi.near.api.common.helper.Network;
import com.syntifi.near.api.common.service.NearObjectMapper;
import com.syntifi.near.api.indexer.model.AccountIdList;
import com.syntifi.near.api.indexer.model.NearValue;
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
 * Near indexer client uses http API to retrieve useful information on chain data
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.2.0
 */
public interface NearIndexerClient {

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
     * Fetches the value of near in fiat currency
     *
     * @return the Near fiat value
     */
    @GET("/fiat")
    Call<NearValue> getNearValue();

    /**
     * Fetches a list of accounts for a given public key
     *
     * @param publicKey the account public key
     * @return list containing all account ids
     */
    @GET("publicKey/{publicKey}/accounts")
    Call<List<String>> getNearAccounts(@Path("publicKey") String publicKey);

    /**
     * NearIndexerService builder
     *
     * @param network the indexer url to connect to
     * @return the indexer service instance
     */
    static NearIndexerClient usingNetwork(Network network) {
        Headers customHeaders = new Headers.Builder()
                .add("Content-Type", "application/json")
                .add("Cache-Control", "no-cache")
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(new OkHttpClient.Builder()
                        .addInterceptor(
                                chain -> chain.proceed(chain.request().newBuilder().headers(customHeaders).build())).build())
                .baseUrl("https://" + network.getIndexerUrl())
                .addConverterFactory(JacksonConverterFactory.create(NearObjectMapper.INSTANCE))
                .build();

        return retrofit.create(NearIndexerClient.class);
    }
}
