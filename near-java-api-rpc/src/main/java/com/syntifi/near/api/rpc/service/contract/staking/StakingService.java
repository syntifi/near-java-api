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
     * Contract function view call to get the account total balance
     *
     * @param nearClient        the near service instance to use for the contract call
     * @param contractAccountId the contract's account id
     * @param accountId    the arguments for the view method
     * @return a typed function call result
     */
    @ContractMethod(type = ContractMethodType.VIEW, name = "get_account_total_balance")
    FunctionCallResult<BigInteger> viewAccountTotalBalance(NearClient nearClient, String contractAccountId,
                                                           @ContractParameter("account_id") String accountId);

    /**
     * Contract function view call to get the account staked balance
     *
     * @param nearClient        the near service instance to use for the contract call
     * @param contractAccountId the contract's account id
     * @param accountId    the arguments for the view method
     * @return a typed function call result
     */
    @ContractMethod(type = ContractMethodType.VIEW, name = "get_account_staked_balance")
    FunctionCallResult<BigInteger> viewAccountStakedBalance(NearClient nearClient, String contractAccountId,
                                                            @ContractParameter("account_id") String accountId);

    /**
     * Contract function view call to get the account unstaked balance
     *
     * @param nearClient        the near service instance to use for the contract call
     * @param contractAccountId the contract's account id
     * @param accountId    the arguments for the view method
     * @return a typed function call result
     */
    @ContractMethod(type = ContractMethodType.VIEW, name = "get_account_unstaked_balance")
    FunctionCallResult<BigInteger> viewAccountUnstakedBalance(NearClient nearClient, String contractAccountId,
                                                              @ContractParameter("account_id") String accountId);

    /**
     * Contract function view call to check if the account unstaked balance is available
     *
     * @param nearClient        the near service instance to use for the contract call
     * @param contractAccountId the contract's account id
     * @param accountId    the arguments for the view method
     * @return a typed function call result
     */
    @ContractMethod(type = ContractMethodType.VIEW, name = "is_account_unstaked_balance_available")
    FunctionCallResult<Boolean> isAccountUnstakedBalanceAvailable(NearClient nearClient, String contractAccountId,
                                                                  @ContractParameter("account_id") String accountId);

    /**
     * Contract function view call to get the total staked balance
     *
     * @param nearClient        the near service instance to use for the contract call
     * @param contractAccountId the contract's account id
     * @param accountId    the arguments for the view method
     * @return a typed function call result
     */
    @ContractMethod(type = ContractMethodType.VIEW, name = "get_total_staked_balance")
    FunctionCallResult<BigInteger> viewTotalStakedBalance(NearClient nearClient, String contractAccountId,
                                                          @ContractParameter("account_id") String accountId);

    /**
     * Contract function view call to get the contract owner id
     *
     * @param nearClient        the near service instance to use for the contract call
     * @param contractAccountId the contract's account id
     * @param accountId    the arguments for the view method
     * @return a typed function call result
     */
    @ContractMethod(type = ContractMethodType.VIEW, name = "get_owner_id")
    FunctionCallResult<String> viewOwnerId(NearClient nearClient, String contractAccountId,
                                           @ContractParameter("account_id") String accountId);

    /**
     * Returns the fee fraction charged by the staking farm (eg. x%)
     *
     * @param nearClient        the near service instance to use for the contract call
     * @param contractAccountId the contract's account id
     * @param accountId    the arguments for the view method
     * @return a json node of the form {"numerator": numeric, "denominator": numeric}
     */
    @ContractMethod(type = ContractMethodType.VIEW, name = "get_reward_fee_fraction")
    FunctionCallResult<JsonNode> viewRewardFeeFraction(NearClient nearClient, String contractAccountId,
                                                       @ContractParameter("account_id") String accountId);

    /**
     * Returns the details for all farms for the given contractAccountId
     *
     * @param nearClient        the near service instance to use for the contract call
     * @param contractAccountId the contract's account id
     * @param accountId    the arguments for the view method
     * @param fromIndex         starting index
     * @param limit             limit
     * @return json nodes of the form {"name": String, "token_id": String, "amount": numeric, "start_date":numeric, "end_date": numeric}
     */
    @ContractMethod(type = ContractMethodType.VIEW, name = "get_farms")
    FunctionCallResult<JsonNode> viewFarms(NearClient nearClient, String contractAccountId,
                                           @ContractParameter("account_id") String accountId,
                                           @ContractParameter("from_index") Long fromIndex,
                                           @ContractParameter("limit") Long limit);

    /**
     * Returns the detail for a specific farm the given farmId
     *
     * @param nearClient        the near service instance to use for the contract call
     * @param contractAccountId the contract's account id
     * @param accountId    the arguments for the view method
     * @param farmId            farm Id
     * @return a json node of the form {"name": String, "token_id": String, "amount": numeric, "start_date":numeric, "end_date": numeric}
     */
    @ContractMethod(type = ContractMethodType.VIEW, name = "get_farm")
    FunctionCallResult<JsonNode> viewFarm(NearClient nearClient, String contractAccountId,
                                          @ContractParameter("account_id") String accountId,
                                          @ContractParameter("farm_id") Long farmId);

    /**
     * Returns the unclaimed reward in a given farmId
     *
     * @param nearClient        the near service instance to use for the contract call
     * @param contractAccountId the contract's account id
     * @param accountId    the arguments for the view method
     * @param farmId            farm Id
     * @return the unclaimed reward
     */
    @ContractMethod(type = ContractMethodType.VIEW, name = "get_unclaimed_reward")
    FunctionCallResult<BigInteger> viewUnclaimedReward(NearClient nearClient, String contractAccountId,
                                                       @ContractParameter("account_id") String accountId,
                                                       @ContractParameter("farm_id") Long farmId);

    /**
     * Synchronous contractCall to deposit an amount to the contractAccountId
     *
     * @param nearClient        the near service instance to use for the contract call
     * @param contractAccountId the contract's account id
     * @param amount            amount to depoist
     * @param accountId         the arguments for the view method
     * @param publicKey         the arguments for the view method
     * @param privateKey        the arguments for the view method
     * @return a TransactionAwait object
     */
    @ContractMethod(type = ContractMethodType.CALL, name = "deposit")
    TransactionAwait callDeposit(NearClient nearClient, String contractAccountId,
                                 @ContractParameter(value = "amount", type = {ContractParameterType.DEPOSIT, ContractParameterType.ARGUMENT}) BigInteger amount,
                                 @ContractParameter(type = ContractParameterType.ACCOUNT_ID) String accountId,
                                 @ContractParameter(type = ContractParameterType.PUBLIC_KEY) PublicKey publicKey,
                                 @ContractParameter(type = ContractParameterType.PRIVATE_KEY) PrivateKey privateKey);

    /**
     * Synchronous contractCall to withdraw an amount to the contractAccountId
     *
     * @param nearClient        the near service instance to use for the contract call
     * @param contractAccountId the contract's account id
     * @param amount            amount to depoist
     * @param accountId         the arguments for the view method
     * @param publicKey         the arguments for the view method
     * @param privateKey        the arguments for the view method
     * @return a TransactionAwait object
     */
    @ContractMethod(type = ContractMethodType.CALL, name = "withdraw")
    TransactionAwait callWithdraw(NearClient nearClient, String contractAccountId, @ContractParameter("amount") BigInteger amount,
                                  @ContractParameter(type = ContractParameterType.ACCOUNT_ID) String accountId,
                                  @ContractParameter(type = ContractParameterType.PUBLIC_KEY) PublicKey publicKey,
                                  @ContractParameter(type = ContractParameterType.PRIVATE_KEY) PrivateKey privateKey);

    /**
     * Synchronous contractCall to stake an amount to the contractAccountId
     *
     * @param nearClient        the near service instance to use for the contract call
     * @param contractAccountId the contract's account id
     * @param amount            amount to depoist
     * @param accountId         the arguments for the view method
     * @param publicKey         the arguments for the view method
     * @param privateKey        the arguments for the view method
     * @return a TransactionAwait object
     */
    @ContractMethod(type = ContractMethodType.CALL, name = "stake")
    TransactionAwait callStake(NearClient nearClient, String contractAccountId, @ContractParameter("amount") BigInteger amount,
                               @ContractParameter(type = ContractParameterType.ACCOUNT_ID) String accountId,
                               @ContractParameter(type = ContractParameterType.PUBLIC_KEY) PublicKey publicKey,
                               @ContractParameter(type = ContractParameterType.PRIVATE_KEY) PrivateKey privateKey);

    /**
     * Synchronous contractCall to unstake an amount to the contractAccountId
     *
     * @param nearClient        the near service instance to use for the contract call
     * @param contractAccountId the contract's account id
     * @param amount            amount to depoist
     * @param accountId         the arguments for the view method
     * @param publicKey         the arguments for the view method
     * @param privateKey        the arguments for the view method
     * @return a TransactionAwait object
     */
    @ContractMethod(type = ContractMethodType.CALL, name = "unstake")
    TransactionAwait callUnstake(NearClient nearClient, String contractAccountId, @ContractParameter("amount") String amount,
                                 @ContractParameter(type = ContractParameterType.ACCOUNT_ID) String accountId,
                                 @ContractParameter(type = ContractParameterType.PUBLIC_KEY) PublicKey publicKey,
                                 @ContractParameter(type = ContractParameterType.PRIVATE_KEY) PrivateKey privateKey);

    /**
     * Synchronous contractCall to deposit and stake an amount to the contractAccountId
     *
     * @param nearClient        the near service instance to use for the contract call
     * @param contractAccountId the contract's account id
     * @param amount            amount to depoist
     * @param accountId         the arguments for the view method
     * @param publicKey         the arguments for the view method
     * @param privateKey        the arguments for the view method
     * @return a TransactionAwait object
     */
    @ContractMethod(type = ContractMethodType.CALL, name = "deposit_and_stake")
    TransactionAwait callDepositAndStake(NearClient nearClient, String contractAccountId,
                                         @ContractParameter(value = "amount", type = {ContractParameterType.DEPOSIT, ContractParameterType.ARGUMENT}) BigInteger amount,
                                         @ContractParameter(type = ContractParameterType.ACCOUNT_ID) String accountId,
                                         @ContractParameter(type = ContractParameterType.PUBLIC_KEY) PublicKey publicKey,
                                         @ContractParameter(type = ContractParameterType.PRIVATE_KEY) PrivateKey privateKey);

    /**
     * Synchronous contractCall to stake all available balance in the contractAccountId
     *
     * @param nearClient        the near service instance to use for the contract call
     * @param contractAccountId the contract's account id
     * @param amount            amount to depoist
     * @param accountId         the arguments for the view method
     * @param publicKey         the arguments for the view method
     * @param privateKey        the arguments for the view method
     * @return a TransactionAwait object
     */
    @ContractMethod(type = ContractMethodType.CALL, name = "stake_all")
    TransactionAwait callStakeAll(NearClient nearClient, String contractAccountId, @ContractParameter("amount") BigInteger amount,
                                  @ContractParameter(type = ContractParameterType.ACCOUNT_ID) String accountId,
                                  @ContractParameter(type = ContractParameterType.PUBLIC_KEY) PublicKey publicKey,
                                  @ContractParameter(type = ContractParameterType.PRIVATE_KEY) PrivateKey privateKey);

    /**
     * Synchronous contractCall to claim rewards from contractAccountId
     *
     * @param nearClient        the near service instance to use for the contract call
     * @param contractAccountId the contract's account id
     * @param amount            amount to depoist
     * @param accountId         the arguments for the view method
     * @param publicKey         the arguments for the view method
     * @param privateKey        the arguments for the view method
     * @return a TransactionAwait object
     */
    @ContractMethod(type = ContractMethodType.CALL, name = "claim")
    TransactionAwait callClaim(NearClient nearClient, String contractAccountId, @ContractParameter("amount") BigInteger amount,
                               @ContractParameter(type = ContractParameterType.ACCOUNT_ID) String accountId,
                               @ContractParameter(type = ContractParameterType.PUBLIC_KEY) PublicKey publicKey,
                               @ContractParameter(type = ContractParameterType.PRIVATE_KEY) PrivateKey privateKey);

    /**
     * Synchronous contractCall to ping contractAccountId
     *
     * @param nearClient        the near service instance to use for the contract call
     * @param contractAccountId the contract's account id
     * @param amount            amount to depoist
     * @param accountId         the arguments for the view method
     * @param publicKey         the arguments for the view method
     * @param privateKey        the arguments for the view method
     * @return a TransactionAwait object
     */
    @ContractMethod(type = ContractMethodType.CALL, name = "ping")
    TransactionAwait callPing(NearClient nearClient, String contractAccountId, @ContractParameter("amount") BigInteger amount,
                              @ContractParameter(type = ContractParameterType.ACCOUNT_ID) String accountId,
                              @ContractParameter(type = ContractParameterType.PUBLIC_KEY) PublicKey publicKey,
                              @ContractParameter(type = ContractParameterType.PRIVATE_KEY) PrivateKey privateKey);

}
