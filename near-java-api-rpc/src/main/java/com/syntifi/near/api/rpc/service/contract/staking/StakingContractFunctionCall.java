package com.syntifi.near.api.rpc.service.contract.staking;

import com.syntifi.near.api.rpc.NearClient;
import com.syntifi.near.api.rpc.service.contract.AccountIdParam;
import com.syntifi.near.api.rpc.service.contract.ContractMethod;
import com.syntifi.near.api.rpc.service.contract.ContractMethodCaller;
import com.syntifi.near.api.rpc.service.contract.FunctionCallResult;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.IOException;
import java.math.BigInteger;

/**
 * Contract function call object for Staking
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.2.0
 */
public class StakingContractFunctionCall {

    /**
     * Builds a contract function call and returns a typed result
     *
     * @param nearClient       the near service instance to use for the contract call
     * @param contractAccountId the contract's account id
     * @param accountIdParam    the arguments for the target method
     * @return a typed function call result
     * @throws IOException thrown if failed to deserialize result to target class
     */
    public static FunctionCallResult<BigInteger> forAccountTotalBalance(NearClient nearClient, String contractAccountId, AccountIdParam accountIdParam) throws IOException {
        return ContractMethodCaller
                .callFor(StakingMethodCaller.ViewMethod.GET_ACCOUNT_TOTAL_BALANCE, BigInteger.class, nearClient, contractAccountId, accountIdParam);
    }

    /**
     * Contract method definitions
     */
    public static class StakingMethodCaller extends ContractMethodCaller {
        @Getter
        @AllArgsConstructor
        public enum ViewMethod implements ContractMethod {
            GET_ACCOUNT_STAKED_BALANCE("get_account_staked_balance"),
            GET_ACCOUNT_UNSTAKED_BALANCE("get_account_unstaked_balance"),
            GET_ACCOUNT_TOTAL_BALANCE("get_account_total_balance"),
            IS_ACCOUNT_UNSTAKED_BALANCE_AVAILABLE("is_account_unstaked_balance_available"),
            GET_TOTAL_STAKED_BALANCE("get_total_staked_balance"),
            GET_OWNER_ID("get_owner_id"),
            GET_REWARD_FEE_FRACTION("get_reward_fee_fraction"),
            GET_FARMS("get_farms"),
            GET_FARM("get_farm"),
            GET_ACTIVE_FARMS("get_active_farms"),
            GET_UNCLAIMED_REWARD("get_unclaimed_reward"),
            GET_POOL_SUMMARY("get_pool_summary");

            private final String methodName;
        }

        @Getter
        @AllArgsConstructor
        public enum ChangeMethod implements ContractMethod {
            PING("ping"),
            DEPOSIT("deposit"),
            DEPOSIT_AND_STAKE("deposit_and_stake"),
            DEPOSIT_TO_STAKING_POOL("deposit_to_staking_pool"),
            STAKE("stake"),
            STAKE_ALL("stake_all"),
            UNSTAKE("unstake"),
            WITHDRAW("withdraw"),
            CLAIM("claim");

            private final String methodName;
        }
    }

    public static class LockupMethods {
        @Getter
        @AllArgsConstructor
        public enum ViewMethod implements ContractMethod {
            GET_BALANCE("get_balance"),
            GET_LOCKED_AMOUNT("get_locked_amount"),
            GET_OWNERS_BALANCE("get_owners_balance"),
            GET_STAKING_POOL_ACCOUNT_ID("get_staking_pool_account_id"),
            GET_KNOWN_DEPOSITED_BALANCE("get_known_deposited_balance");

            private final String methodName;
        }
    }
}
