package com.syntifi.near.api.rpc.service.contract.staking;

import com.fasterxml.jackson.databind.JsonNode;
import com.syntifi.near.api.common.model.key.PrivateKey;
import com.syntifi.near.api.common.model.key.PublicKey;
import com.syntifi.near.api.rpc.NearClient;
import com.syntifi.near.api.rpc.model.transaction.TransactionAwait;
import com.syntifi.near.api.rpc.service.contract.common.FunctionCallResult;
import com.syntifi.near.api.rpc.service.contract.common.annotation.ContractMethod;
import com.syntifi.near.api.rpc.service.contract.common.annotation.ContractMethodType;
import com.syntifi.near.api.rpc.service.contract.common.annotation.ContractParameter;
import com.syntifi.near.api.rpc.service.contract.common.annotation.ContractParameterType;

import java.math.BigInteger;

/**
 * Contract function call object for Staking
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.2.0
 */
public interface StakingService {

    /**
     * Builds a contract function call and returns a typed result
     *
     * @param nearClient        the near service instance to use for the contract call
     * @param contractAccountId the contract's account id
     * @param accountIdParam    the arguments for the view method
     * @return a typed function call result
     */
    @ContractMethod(type = ContractMethodType.VIEW, name = "get_account_total_balance")
    FunctionCallResult<BigInteger> viewAccountTotalBalance(NearClient nearClient, String contractAccountId,
                                                           @ContractParameter("account_id") String accountIdParam);

    @ContractMethod(type = ContractMethodType.VIEW, name = "get_account_staked_balance")
    FunctionCallResult<BigInteger> viewAccountStakedBalance(NearClient nearClient, String contractAccountId,
                                                            @ContractParameter("account_id") String accountIdParam);

    @ContractMethod(type = ContractMethodType.VIEW, name = "get_account_unstaked_balance")
    FunctionCallResult<BigInteger> viewAccountUnstakedBalance(NearClient nearClient, String contractAccountId,
                                                              @ContractParameter("account_id") String accountIdParam);

    @ContractMethod(type = ContractMethodType.VIEW, name = "is_account_unstaked_balance_available")
    FunctionCallResult<Boolean> isAccountUnstakedBalanceAvailable(NearClient nearClient, String contractAccountId,
                                                                     @ContractParameter("account_id") String accountIdParam);

    @ContractMethod(type = ContractMethodType.VIEW, name = "get_total_staked_balance")
    FunctionCallResult<BigInteger> viewTotalStakedBalance(NearClient nearClient, String contractAccountId,
                                                          @ContractParameter("account_id") String accountIdParam);

    @ContractMethod(type = ContractMethodType.VIEW, name = "get_owner_id")
    FunctionCallResult<String> viewOwnerId(NearClient nearClient, String contractAccountId,
                                               @ContractParameter("account_id") String accountIdParam);

    /**
     * Returns the fee fraction charged by the staking farm (eg. x%)
     *
     * @param nearClient        the near service instance to use for the contract call
     * @param contractAccountId the contract's account id
     * @param accountIdParam    the arguments for the view method
     * @return a json node of the form {"numerator": numeric, "denominator": numeric}
     */
    @ContractMethod(type = ContractMethodType.VIEW, name = "get_reward_fee_fraction")
    FunctionCallResult<JsonNode> viewRewardFeeFraction(NearClient nearClient, String contractAccountId,
                                                       @ContractParameter("account_id") String accountIdParam);

    @ContractMethod(type = ContractMethodType.VIEW, name = "get_farms")
    FunctionCallResult<BigInteger> viewFarms(NearClient nearClient, String contractAccountId,
                                             @ContractParameter("account_id") String accountIdParam);

    @ContractMethod(type = ContractMethodType.VIEW, name = "get_farm")
    FunctionCallResult<BigInteger> viewFarm(NearClient nearClient, String contractAccountId,
                                            @ContractParameter("account_id") String accountIdParam);

    @ContractMethod(type = ContractMethodType.VIEW, name = "get_active_farms")
    FunctionCallResult<BigInteger> viewActiveFarms(NearClient nearClient, String contractAccountId,
                                                   @ContractParameter("account_id") String accountIdParam);

    @ContractMethod(type = ContractMethodType.VIEW, name = "get_unclaimed_reward")
    FunctionCallResult<BigInteger> viewUnclaimedReward(NearClient nearClient, String contractAccountId,
                                                       @ContractParameter("account_id") String accountIdParam);

    @ContractMethod(type = ContractMethodType.VIEW, name = "get_pool_summary")
    FunctionCallResult<BigInteger> viewPoolSummary(NearClient nearClient, String contractAccountId,
                                                   @ContractParameter("account_id") String accountIdParam);

    @ContractMethod(type = ContractMethodType.CALL, name = "deposit")
    TransactionAwait callDeposit(NearClient nearClient, String contractAccountId,
                                 @ContractParameter(value = "amount", type = {ContractParameterType.DEPOSIT, ContractParameterType.ARGUMENT}) BigInteger amount,
                                 @ContractParameter(type = ContractParameterType.ACCOUNT_ID) String accountId,
                                 @ContractParameter(type = ContractParameterType.PUBLIC_KEY) PublicKey publicKey,
                                 @ContractParameter(type = ContractParameterType.PRIVATE_KEY) PrivateKey privateKey);

    @ContractMethod(type = ContractMethodType.CALL, name = "withdraw")
    TransactionAwait callWithdraw(NearClient nearClient, String contractAccountId, @ContractParameter("amount") BigInteger amount,
                                  @ContractParameter(type = ContractParameterType.ACCOUNT_ID) String accountId,
                                  @ContractParameter(type = ContractParameterType.PUBLIC_KEY) PublicKey publicKey,
                                  @ContractParameter(type = ContractParameterType.PRIVATE_KEY) PrivateKey privateKey);

    @ContractMethod(type = ContractMethodType.CALL, name = "stake")
    TransactionAwait callStake(NearClient nearClient, String contractAccountId, @ContractParameter("amount") BigInteger amount,
                               @ContractParameter(type = ContractParameterType.ACCOUNT_ID) String accountId,
                               @ContractParameter(type = ContractParameterType.PUBLIC_KEY) PublicKey publicKey,
                               @ContractParameter(type = ContractParameterType.PRIVATE_KEY) PrivateKey privateKey);

    @ContractMethod(type = ContractMethodType.CALL, name = "unstake")
    TransactionAwait callUnstake(NearClient nearClient, String contractAccountId, @ContractParameter("amount") String amount,
                                 @ContractParameter(type = ContractParameterType.ACCOUNT_ID) String accountId,
                                 @ContractParameter(type = ContractParameterType.PUBLIC_KEY) PublicKey publicKey,
                                 @ContractParameter(type = ContractParameterType.PRIVATE_KEY) PrivateKey privateKey);

    @ContractMethod(type = ContractMethodType.CALL, name = "deposit_and_stake")
    TransactionAwait callDepositAndStake(NearClient nearClient, String contractAccountId,
                                         @ContractParameter(value = "amount", type = {ContractParameterType.DEPOSIT, ContractParameterType.ARGUMENT}) BigInteger amount,
                                         @ContractParameter(type = ContractParameterType.ACCOUNT_ID) String accountId,
                                         @ContractParameter(type = ContractParameterType.PUBLIC_KEY) PublicKey publicKey,
                                         @ContractParameter(type = ContractParameterType.PRIVATE_KEY) PrivateKey privateKey);

    @ContractMethod(type = ContractMethodType.CALL, name = "stake_all")
    TransactionAwait callStakeAll(NearClient nearClient, String contractAccountId, @ContractParameter("amount") BigInteger amount,
                                  @ContractParameter(type = ContractParameterType.ACCOUNT_ID) String accountId,
                                  @ContractParameter(type = ContractParameterType.PUBLIC_KEY) PublicKey publicKey,
                                  @ContractParameter(type = ContractParameterType.PRIVATE_KEY) PrivateKey privateKey);

    @ContractMethod(type = ContractMethodType.CALL, name = "deposit_to_staking_pool")
    TransactionAwait callDepositToStakingPool(NearClient nearClient, String contractAccountId, @ContractParameter("amount") BigInteger amount,
                                              @ContractParameter(type = ContractParameterType.ACCOUNT_ID) String accountId,
                                              @ContractParameter(type = ContractParameterType.PUBLIC_KEY) PublicKey publicKey,
                                              @ContractParameter(type = ContractParameterType.PRIVATE_KEY) PrivateKey privateKey);

    @ContractMethod(type = ContractMethodType.CALL, name = "claim")
    TransactionAwait callClaim(NearClient nearClient, String contractAccountId, @ContractParameter("amount") BigInteger amount,
                               @ContractParameter(type = ContractParameterType.ACCOUNT_ID) String accountId,
                               @ContractParameter(type = ContractParameterType.PUBLIC_KEY) PublicKey publicKey,
                               @ContractParameter(type = ContractParameterType.PRIVATE_KEY) PrivateKey privateKey);

    @ContractMethod(type = ContractMethodType.CALL, name = "ping")
    TransactionAwait callPing(NearClient nearClient, String contractAccountId, @ContractParameter("amount") BigInteger amount,
                              @ContractParameter(type = ContractParameterType.ACCOUNT_ID) String accountId,
                              @ContractParameter(type = ContractParameterType.PUBLIC_KEY) PublicKey publicKey,
                              @ContractParameter(type = ContractParameterType.PRIVATE_KEY) PrivateKey privateKey);

//    public static class LockupMethods {
//        @Getter
//        @AllArgsConstructor
//        public enum ViewMethodClass implements ContractMethodClass {
//            GET_BALANCE("view_balance"),
//            GET_LOCKED_AMOUNT("view_locked_amount"),
//            GET_OWNERS_BALANCE("view_owners_balance"),
//            GET_STAKING_POOL_ACCOUNT_ID("view_staking_pool_account_id"),
//            GET_KNOWN_DEPOSITED_BALANCE("view_known_deposited_balance");
//
//            private final String methodName;
//        }
//    }
}
