package com.syntifi.near.api.rpc.service.contract.staking;

import com.syntifi.near.api.rpc.NearClient;
import com.syntifi.near.api.rpc.service.contract.common.annotation.ContractMethod;
import com.syntifi.near.api.rpc.service.contract.common.annotation.ContractMethodType;
import com.syntifi.near.api.rpc.service.contract.common.FunctionCallResult;
import com.syntifi.near.api.rpc.service.contract.common.param.AccountIdParam;

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
     * @param nearClient       the near service instance to use for the contract call
     * @param contractAccountId the contract's account id
     * @param accountIdParam    the arguments for the target method
     * @return a typed function call result
     */
    @ContractMethod(type = ContractMethodType.VIEW, name="get_account_total_balance")
    FunctionCallResult<BigInteger> getAccountTotalBalance(NearClient nearClient, String contractAccountId, AccountIdParam accountIdParam);

    @ContractMethod(type = ContractMethodType.VIEW, name="get_account_staked_balance")
    FunctionCallResult<BigInteger> getAccountStakedBalance(NearClient nearClient, String contractAccountId, AccountIdParam accountIdParam);

    @ContractMethod(type = ContractMethodType.VIEW, name="get_account_unstaked_balance")
    FunctionCallResult<BigInteger> getAccountUnstakedBalance(NearClient nearClient, String contractAccountId, AccountIdParam accountIdParam);

    @ContractMethod(type = ContractMethodType.VIEW, name="is_account_unstaked_balance_available")
    FunctionCallResult<BigInteger> isAccountUnstakedBalanceAvailable(NearClient nearClient, String contractAccountId, AccountIdParam accountIdParam);

    @ContractMethod(type = ContractMethodType.VIEW, name="get_total_staked_balance")
    FunctionCallResult<BigInteger> getTotalStakedBalance(NearClient nearClient, String contractAccountId, AccountIdParam accountIdParam);

    @ContractMethod(type = ContractMethodType.VIEW, name="get_owner_id")
    FunctionCallResult<BigInteger> getOwnerId(NearClient nearClient, String contractAccountId, AccountIdParam accountIdParam);

    @ContractMethod(type = ContractMethodType.VIEW, name="get_reward_fee_action")
    FunctionCallResult<BigInteger> getRewardFeeAction(NearClient nearClient, String contractAccountId, AccountIdParam accountIdParam);

    @ContractMethod(type = ContractMethodType.VIEW, name="get_farms")
    FunctionCallResult<BigInteger> getFarms(NearClient nearClient, String contractAccountId, AccountIdParam accountIdParam);

    @ContractMethod(type = ContractMethodType.VIEW, name="get_farm")
    FunctionCallResult<BigInteger> getFarm(NearClient nearClient, String contractAccountId, AccountIdParam accountIdParam);

    @ContractMethod(type = ContractMethodType.VIEW, name="get_active_farms")
    FunctionCallResult<BigInteger> getActiveFarms(NearClient nearClient, String contractAccountId, AccountIdParam accountIdParam);

    @ContractMethod(type = ContractMethodType.VIEW, name="get_unclaimed_reward")
    FunctionCallResult<BigInteger> getUnclaimedReward(NearClient nearClient, String contractAccountId, AccountIdParam accountIdParam);

    @ContractMethod(type = ContractMethodType.VIEW, name="get_pool_summary")
    FunctionCallResult<BigInteger> getPoolSummary(NearClient nearClient, String contractAccountId, AccountIdParam accountIdParam);

    /*
     * Sends a {@link TransferAction} function call to stake a certain amount to the given staking
     * pool and keeps waiting for result using {@link NearClient#sendTransactionAwait(String)}
     *
     * @param nearClient        the near service instance to use
     * @param stakingPool       string Id of the pool to stake
     * @param stakingAmount     the amount to stake
     * @param accountId         the signer accountId
     * @param accountPublicKey   signer public key
     * @param accountPrivateKey signer private key
     * @return {@link TransactionAwait} object with the result
     * @throws GeneralSecurityException thrown if failed to sign the transaction
     */
    /*public static TransactionAwait depositAndStakeTokens(NearClient nearClient, String stakingPool,
                                                         BigInteger stakingAmount, String accountId,
                                                         PublicKey accountPubliKey, PrivateKey accountPrivateKey)
            throws GeneralSecurityException {
        List<Action> actions = StakingServiceOld.createActionArrayToStakeWithStakingPool(stakingAmount);
        return nearClient.sendTransactionAwait(BaseService.prepareTransactionForActionList(
                nearClient, accountId, stakingPool, accountPubliKey, accountPrivateKey,
                actions));
    }


    /**
     * Contract change method definitions
      */
//    public static class StakingMethodCaller {
//        @Getter
//        @AllArgsConstructor
//        public enum ChangeMethodClass implements ContractMethodClass {
//            PING("ping"),
//            DEPOSIT("deposit"),
//            DEPOSIT_AND_STAKE("deposit_and_stake"),
//            DEPOSIT_TO_STAKING_POOL("deposit_to_staking_pool"),
//            STAKE("stake"),
//            STAKE_ALL("stake_all"),
//            UNSTAKE("unstake"),
//            WITHDRAW("withdraw"),
//            CLAIM("claim");
//
//            private final String methodName;
//        }
//    }
//
//    public static class LockupMethods {
//        @Getter
//        @AllArgsConstructor
//        public enum ViewMethodClass implements ContractMethodClass {
//            GET_BALANCE("get_balance"),
//            GET_LOCKED_AMOUNT("get_locked_amount"),
//            GET_OWNERS_BALANCE("get_owners_balance"),
//            GET_STAKING_POOL_ACCOUNT_ID("get_staking_pool_account_id"),
//            GET_KNOWN_DEPOSITED_BALANCE("get_known_deposited_balance");
//
//            private final String methodName;
//        }
//    }
}
