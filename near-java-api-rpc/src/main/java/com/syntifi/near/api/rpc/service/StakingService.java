package com.syntifi.near.api.rpc.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.syntifi.near.api.common.model.common.EncodedHash;
import com.syntifi.near.api.common.model.key.PrivateKey;
import com.syntifi.near.api.common.model.key.PublicKey;
import com.syntifi.near.api.rpc.NearClient;
import com.syntifi.near.api.rpc.model.transaction.Action;
import com.syntifi.near.api.rpc.model.transaction.FunctionCallAction;
import com.syntifi.near.api.rpc.model.transaction.TransactionAwait;
import com.syntifi.near.api.rpc.model.transaction.TransferAction;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;

/**
 * Staking service provides methods to easily work with the staking of funds to validators
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.2.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StakingService {

    /**
     * Sends a {@link TransferAction} function call to stake a certain amount to the given staking
     * pool and keeps waiting for result using {@link NearClient#sendTransactionAwait(String)}
     *
     * @param nearClient       the near service instance to use
     * @param stakingPool       string Id of the pool to stake
     * @param stakingAmount     the amount to stake
     * @param accountId         the signer accountId
     * @param accountPubliKey   signer public key
     * @param accountPrivateKey signer private key
     * @return {@link TransactionAwait} object with the result
     * @throws GeneralSecurityException thrown if failed to sign the transaction
     */
    public static TransactionAwait depositAndStakeTokens(NearClient nearClient, String stakingPool,
                                                         BigInteger stakingAmount, String accountId,
                                                         PublicKey accountPubliKey, PrivateKey accountPrivateKey)
            throws GeneralSecurityException {
        List<Action> actions = StakingService.createActionArrayToStakeWithStakingPool(stakingAmount);
        return nearClient.sendTransactionAwait(BaseService.prepareTransactionForActionList(
                nearClient, accountId, stakingPool, accountPubliKey, accountPrivateKey,
                actions));
    }

    /**
     * Sends a {@link TransferAction} function call to unstake a certain amount from the staking
     * pool and keeps waiting for result using  {@link NearClient#sendTransactionAwait(String)}
     *
     * @param nearClient       the near service instance to use
     * @param stakingPool       string Id of the pool to stake
     * @param stakingAmount     the amount to stake
     * @param accountId         the signer accountId
     * @param accountPubliKey   signer public key
     * @param accountPrivateKey signer private key
     * @return {@link TransactionAwait} object with the result
     * @throws GeneralSecurityException thrown if failed to sign the transaction
     */
    public static TransactionAwait unStakeTokens(NearClient nearClient, String stakingPool,
                                                 BigInteger stakingAmount, String accountId,
                                                 PublicKey accountPubliKey, PrivateKey accountPrivateKey)
            throws GeneralSecurityException {
        List<Action> actions = StakingService.createActionArrayToUnstakeFromStakingPool(stakingAmount);
        return nearClient.sendTransactionAwait(BaseService.prepareTransactionForActionList(
                nearClient, accountId, stakingPool, accountPubliKey, accountPrivateKey,
                actions));
    }

    /**
     * Sends a {@link TransferAction} function call waiting for result
     * using  {@link NearClient#sendTransactionAwait(String)}
     *
     * @param network           Near network of choice
     * @param stakingPool       string Id of the pool to stake
     * @param stakingAmount     the amount to stake
     * @param accountId         the signer accountId
     * @param accountPubliKey   signer public key
     * @param accountPrivateKey signer private key
     * @return {@link TransactionAwait} object with the result
     * @throws GeneralSecurityException thrown if failed to sign the transaction
     */
    public static TransactionAwait createNamedAccount(Network network, String stakingPool,
                                                      BigInteger stakingAmount, String accountId,
                                                      PublicKey accountPubliKey, PrivateKey accountPrivateKey) throws GeneralSecurityException {
        NearClient nearClient = NearClient.usingNetwork(network);

        return StakingService.depositAndStakeTokens(nearClient, stakingPool, stakingAmount, accountId,
                accountPubliKey, accountPrivateKey);
    }


    /**
     * Sends a {@link TransferAction} function call waiting for
     * result using  {@link NearClient#sendTransactionAwait(String)}
     *
     * @param nearClient       the near service instance to use
     * @param stakingPool       string Id of the pool to stake
     * @param stakingAmount     the amount to stake
     * @param accountId         the signer accountId
     * @param accountPubliKey   signer public key
     * @param accountPrivateKey signer private key
     * @return transaction hash
     * @throws GeneralSecurityException thrown if failed to sign the transaction
     */
    public static EncodedHash depositAndStakeTokensAsync(NearClient nearClient, String stakingPool,
                                                         BigInteger stakingAmount, String accountId,
                                                         PublicKey accountPubliKey, PrivateKey accountPrivateKey)
            throws GeneralSecurityException {
        List<Action> actions = StakingService.createActionArrayToStakeWithStakingPool(stakingAmount);
        return EncodedHash.builder()
                .encodedHash(
                        nearClient.sendTransactionAsync(BaseService.prepareTransactionForActionList(
                                nearClient, accountId, stakingPool, accountPubliKey, accountPrivateKey,
                                actions)))
                .build();
    }


    /**
     * Sends a {@link TransferAction} function call waiting for result
     * using  {@link NearClient#sendTransactionAwait(String)}
     *
     * @param network           Near network of choice
     * @param stakingPool       string Id of the pool to stake
     * @param stakingAmount     the amount to stake
     * @param accountId         the signer accountId
     * @param accountPubliKey   signer public key
     * @param accountPrivateKey signer private key
     * @return transaction hash
     * @throws GeneralSecurityException thrown if failed to sign the transaction
     */
    public static EncodedHash depositAndStakeTokensAsync(Network network, String stakingPool,
                                                         BigInteger stakingAmount, String accountId,
                                                         PublicKey accountPubliKey, PrivateKey accountPrivateKey) throws GeneralSecurityException {
        NearClient nearClient = NearClient.usingNetwork(network);

        return StakingService.depositAndStakeTokensAsync(nearClient, stakingPool, stakingAmount, accountId,
                accountPubliKey, accountPrivateKey);
    }

    /**
     * Internal method to create a list of actions needed to stake with a staking pool
     *
     * @param stakingAmount amount to stake
     * @return list of actions
     */
    private static List<Action> createActionArrayToStakeWithStakingPool(BigInteger stakingAmount) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode args = mapper.createObjectNode();
        args.put("amount", stakingAmount);

        return Arrays.asList(FunctionCallAction.builder()
                .methodName("deposit_and_stake")
                .args(args.toString())
                .gas(30000000000000L)
                .deposit(stakingAmount)
                .build());
    }

    /**
     * Internal method to create a list of actions needed to unstake a certain
     * amount with a staking pool
     *
     * @param stakingAmount amount to stake
     * @return list of actions
     */
    private static List<Action> createActionArrayToUnstakeFromStakingPool(BigInteger stakingAmount) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode args = mapper.createObjectNode();
        args.put("amount", stakingAmount.toString());

        return Arrays.asList(FunctionCallAction.builder()
                .methodName("unstake")
                .args(args.toString())
                .deposit(BigInteger.valueOf(0L))
                .gas(30000000000000L)
                .build());
    }


    /**
     * Internal method to create a list of actions needed to unstake a certain
     * amount with a staking pool
     *
     * @return list of actions
     */
    private static List<Action> createActionArrayToUnstakeAllFromStakingPool() {
        return Arrays.asList(FunctionCallAction.builder()
                .methodName("unstake_all")
                .args("{}")
                .deposit(BigInteger.valueOf(0L))
                .gas(30000000000000L)
                .build());
    }

}
