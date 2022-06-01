package com.syntifi.near.api.rpc.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.syntifi.crypto.key.encdec.Base58;
import com.syntifi.near.api.common.helper.Network;
import com.syntifi.near.api.common.model.common.EncodedHash;
import com.syntifi.near.api.common.model.key.PrivateKey;
import com.syntifi.near.api.common.model.key.PublicKey;
import com.syntifi.near.api.rpc.NearClient;
import com.syntifi.near.api.rpc.NearClientHelper;
import com.syntifi.near.api.rpc.Network;
import com.syntifi.near.api.rpc.model.accesskey.AccessKey;
import com.syntifi.near.api.rpc.model.accesskey.permission.FullAccessPermission;
import com.syntifi.near.api.rpc.model.identifier.Finality;
import com.syntifi.near.api.rpc.model.transaction.Action;
import com.syntifi.near.api.rpc.model.transaction.AddKeyAction;
import com.syntifi.near.api.rpc.model.transaction.CreateAccountAction;
import com.syntifi.near.api.rpc.model.transaction.FunctionCallAction;
import com.syntifi.near.api.rpc.model.transaction.TransactionAwait;
import com.syntifi.near.api.rpc.model.transaction.TransferAction;
import com.syntifi.near.api.rpc.service.exception.NearServiceException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Account service provides methods to easily work with the creation of accounts
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.2.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AccountService {

    /**
     * Sends a {@link TransferAction} transaction calling a contract to create_account
     * and waits for result using  {@link NearClient#sendTransactionAwait(String)}
     *
     * @param nearClient         the near service instance to use
     * @param mainDomain          network main domain (eg. near, testnet, ...)
     * @param newAccountId        human-readable id of new account
     * @param newAccountPublicKey public key of new account
     * @param amountToNewAccount  the amount to transfer
     * @param creatorAccountId    human-readable id of creatot account
     * @param creatorPublicKey    signer/account creator public key
     * @param creatorPrivateKey   signer/account creator privatekey
     * @return {@link TransactionAwait} object with the result
     * @throws GeneralSecurityException thrown if failed to sign the transaction
     * @throws NearServiceException     any NEAR-RPC exception
     */
    public static TransactionAwait createNamedAccount(NearClient nearClient, String mainDomain, String newAccountId,
                                                      PublicKey newAccountPublicKey, BigInteger amountToNewAccount,
                                                      String creatorAccountId, PublicKey creatorPublicKey,
                                                      PrivateKey creatorPrivateKey)
            throws GeneralSecurityException, NearServiceException {
        List<Action> actions = AccountService.createActionArrayToCreateNamedAccount(newAccountId, newAccountPublicKey,
                amountToNewAccount);
        return nearClient.sendTransactionAwait(BaseService.prepareTransactionForActionList(
                nearClient, creatorAccountId, mainDomain, creatorPublicKey, creatorPrivateKey,
                actions));
    }

    /**
     * Sends a {@link TransferAction} transaction calling a contract to create_account
     * and waits for result using  {@link NearClient#sendTransactionAwait(String)}
     *
     * @param network             Near network of choice
     * @param newAccountId        human-readable id of new account
     * @param newAccountPublicKey public key of new account
     * @param amountToNewAccount  the amount to transfer
     * @param creatorAccountId    human-readable id of creatot account
     * @param creatorPublicKey    signer/account creator public key
     * @param creatorPrivateKey   signer/account creator privatekey
     * @return {@link TransactionAwait} object with the result
     * @throws GeneralSecurityException thrown if failed to sign the transaction
     * @throws NearServiceException     any NEAR-RPC exception
     */
    public static TransactionAwait createNamedAccount(Network network, String newAccountId,
                                                      PublicKey newAccountPublicKey, BigInteger amountToNewAccount,
                                                      String creatorAccountId, PublicKey creatorPublicKey,
                                                      PrivateKey creatorPrivateKey) throws GeneralSecurityException {
        NearClient nearClient = NearClientHelper.getClient(network);

        return AccountService.createNamedAccount(nearClient, network.getDomain(), newAccountId,
                newAccountPublicKey, amountToNewAccount, creatorAccountId, creatorPublicKey,
                creatorPrivateKey);
    }

    /**
     * Sends a {@link TransferAction} transaction async using {@link NearClient#sendTransactionAsync(String)}
     * with the needed actions to generate a named account and waits for result using
     * {@link NearClient#sendTransactionAwait(String)}
     *
     * @param nearClient         the near service instance to use
     * @param mainDomain          network main domain (eg. near, testnet, ...)
     * @param newAccountId        human-readable id of new account
     * @param newAccountPublicKey public key of new account
     * @param amountToNewAccount  the amount to transfer
     * @param creatorAccountId    human-readable id of creatot account
     * @param creatorPublicKey    signer/account creator public key
     * @param creatorPrivateKey   signer/account creator privatekey
     * @return transaction hash
     * @throws GeneralSecurityException thrown if failed to sign the transaction
     * @throws NearServiceException     any NEAR-RPC exception
     */
    public static EncodedHash createNamedAccountAsync(NearClient nearClient, String mainDomain, String newAccountId,
                                                      PublicKey newAccountPublicKey, BigInteger amountToNewAccount,
                                                      String creatorAccountId, PublicKey creatorPublicKey,
                                                      PrivateKey creatorPrivateKey)
            throws GeneralSecurityException {
        List<Action> actions = AccountService.createActionArrayToCreateNamedAccount(newAccountId, newAccountPublicKey,
                amountToNewAccount);

        return EncodedHash.builder()
                .encodedHash(nearClient.sendTransactionAsync(BaseService.prepareTransactionForActionList(
                        nearClient, creatorAccountId, mainDomain, creatorPublicKey, creatorPrivateKey,
                        actions)))
                .build();
    }

    /**
     * Sends a {@link TransferAction} transaction async using {@link NearClient#sendTransactionAsync(String)}
     * with the needed actions to generate a named account and waits for result using
     * {@link NearClient#sendTransactionAwait(String)}
     *
     * @param network             Near network of choice
     * @param newAccountId        human-readable id of new account
     * @param newAccountPublicKey public key of new account
     * @param amountToNewAccount  the amount to transfer
     * @param creatorAccountId    human-readable id of creatot account
     * @param creatorPublicKey    signer/account creator public key
     * @param creatorPrivateKey   signer/account creator privatekey
     * @return transaction hash
     * @throws GeneralSecurityException thrown if failed to sign the transaction
     * @throws NearServiceException     any NEAR-RPC exception
     */
    public static EncodedHash createNamedAccountAsync(Network network, String newAccountId,
                                                     PublicKey newAccountPublicKey, BigInteger amountToNewAccount,
                                                     String creatorAccountId, PublicKey creatorPublicKey,
                                                     PrivateKey creatorPrivateKey)
            throws GeneralSecurityException, NearServiceException {
        NearClient nearClient = NearClientHelper.getClient(network);

        List<Action> actions = AccountService.createActionArrayToCreateNamedAccount(newAccountId, newAccountPublicKey,
                amountToNewAccount);

        return EncodedHash.builder()
                .encodedHash(nearClient.sendTransactionAsync(BaseService.prepareTransactionForActionList(
                        nearClient, creatorAccountId, network.getDomain(), creatorPublicKey, creatorPrivateKey,
                        actions)))
                .build();
    }

    /**
     * Sends a {@link TransferAction} transaction with the needed actions to generate a sub account
     * and waits for result using  {@link NearClient#sendTransactionAwait(String)}
     *
     * @param nearClient        the near service instance to use
     * @param newAccountId       human-readable id of new account
     * @param amountToNewAccount the amount to transfer
     * @param creatorAccountId   human-readable id of creatot account
     * @param creatorPublicKey   signer/account creator public key
     * @param creatorPrivateKey  signer/account creator privatekey
     * @return {@link TransactionAwait} object with the result
     * @throws GeneralSecurityException thrown if failed to sign the transaction
     */
    public static TransactionAwait createSubAccount(NearClient nearClient, String newAccountId,
                                                    BigInteger amountToNewAccount, String creatorAccountId,
                                                    PublicKey creatorPublicKey, PrivateKey creatorPrivateKey)
            throws GeneralSecurityException {
        List<Action> actions = AccountService.createActionArrayToCreateSubAccount(nearClient, amountToNewAccount,
                creatorAccountId, creatorPublicKey);

        return nearClient.sendTransactionAwait(BaseService.prepareTransactionForActionList(
                nearClient, creatorAccountId, newAccountId, creatorPublicKey, creatorPrivateKey,
                actions));
    }

    /**
     * Sends a {@link TransferAction} transaction async using {@link NearClient#sendTransactionAsync(String)}
     * with the needed actions to generate a sub account and waits for result using
     * {@link NearClient#sendTransactionAwait(String)}
     *
     * @param nearClient        the near service instance to use
     * @param newAccountId       human-readable id of new account
     * @param amountToNewAccount the amount to transfer
     * @param creatorAccountId   human-readable id of creatot account
     * @param creatorPublicKey   signer/account creator public key
     * @param creatorPrivateKey  signer/account creator privatekey
     * @return transatcion hash
     * @throws GeneralSecurityException thrown if failed to sign the transaction
     */
    public static EncodedHash createSubAccountAsync(NearClient nearClient, String newAccountId,
                                                    BigInteger amountToNewAccount, String creatorAccountId,
                                                    PublicKey creatorPublicKey, PrivateKey creatorPrivateKey) throws GeneralSecurityException {
        List<Action> actions = AccountService.createActionArrayToCreateSubAccount(nearClient, amountToNewAccount,
                creatorAccountId, creatorPublicKey);
        return EncodedHash.builder()
                .encodedHash(nearClient.sendTransactionAsync(BaseService.prepareTransactionForActionList(
                        nearClient, creatorAccountId, newAccountId, creatorPublicKey, creatorPrivateKey,
                        actions)))
                .build();
    }

    /**
     * Method to check that the human-readable account id follows the naming convention
     *
     * @param accountId human-readable account id
     * @throws NearServiceException thows if the name deviates from the regex pattern
     */
    public static void checkAccountId(String accountId) throws NearServiceException {
        Pattern regex = Pattern.compile("^(([a-z\\d]+[-_])*[a-z\\d]+.)*([a-z\\d]+[-_])*[a-z\\d]+$");
        Matcher matcher = regex.matcher(accountId);
        if (matcher.groupCount() == 0 || matcher.group(0).length() < 2 || accountId.length() > 64) {
            throw new NearServiceException("accountId does not follow the standard: minimum length is 2, " +
                    "maximum length is 64, accountId consists of accountId parts separated by . ," +
                    "and accountId part consists of lowercase alphanumeric symbols separated by either _ or - ",
                    null);
        }
    }


    /**
     * Internal method to create a list of actions needed to generate a new named account
     *
     * @param newAccountId        human-readable id of new account
     * @param newAccountPublicKey public key of new account
     * @param amountToNewAccount  the amount to transfer
     * @return List of actions
     */
    private static List<Action> createActionArrayToCreateNamedAccount(String newAccountId, PublicKey newAccountPublicKey,
                                                                      BigInteger amountToNewAccount) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode args = mapper.createObjectNode();
        args.put("new_account_id", newAccountId);
        args.put("new_public_key", Base58.encode(newAccountPublicKey.getData()));

        return Arrays.asList(FunctionCallAction.builder()
                .methodName("create_account")
                .args(args.toString())
                .gas(30000000000000L)
                .deposit(amountToNewAccount)
                .build());
    }

    /**
     * Internal method to create a list of actions needed to generate a new subaccount
     *
     * @param nearClient        the near service instance to use
     * @param amountToNewAccount the amount to transfer
     * @param creatorAccountId   human-readable id of creatot account
     * @param creatorPublicKey   signer/account creator public key
     * @return List of actions
     */
    private static List<Action> createActionArrayToCreateSubAccount(NearClient nearClient, BigInteger amountToNewAccount,
                                                                    String creatorAccountId, PublicKey creatorPublicKey) {
        AccessKey accessKey = nearClient.viewAccessKey(Finality.FINAL, creatorAccountId, creatorPublicKey.toEncodedBase58String());
        long nonce = accessKey.getNonce();
        Action[] actionsArray = new Action[]{CreateAccountAction.builder()
                .build(),
                TransferAction.builder()
                        .deposit(amountToNewAccount)
                        .build(),
                AddKeyAction.builder()
                        .publicKey(creatorPublicKey)
                        .accessKey(AccessKey.builder()
                                .nonce(nonce)
                                .permission(FullAccessPermission.FULL_ACCESS)
                                .build())
                        .build()};
        return Arrays.asList(actionsArray);
    }
}
