package com.syntifi.near.api.rpc.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.syntifi.near.api.common.model.common.EncodedHash;
import com.syntifi.near.api.common.model.key.PrivateKey;
import com.syntifi.near.api.common.model.key.PublicKey;
import com.syntifi.near.api.rpc.model.accesskey.AccessKey;
import com.syntifi.near.api.rpc.model.accesskey.permission.FullAccessPermission;
import com.syntifi.near.api.rpc.model.identifier.Finality;
import com.syntifi.near.api.rpc.model.transaction.*;
import com.syntifi.near.api.rpc.service.exception.NearServiceException;

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
public class AccountService {

    /**
     * Sends a {@link TransferAction} transaction calling a contract to create_account
     * and waits for result using  {@link NearService#sendTransactionAwait(String)}
     *
     * @param nearService          the near service instance to use
     * @param newAccountId         human-readable id of new account
     * @param newAccountPublicKey  public key of new account
     * @param amountToNewAccount   the amount to transfer
     * @param creatorAccountId     human-readable id of creatot account
     * @param creatorPublicKey     signer/account creator public key
     * @param creatorPrivateKey    signer/account creator privatekey
     * @return {@link TransactionAwait} object with the result
     * @throws GeneralSecurityException thrown if failed to sign the transaction
     * @throws NearServiceException any NEAR-RPC exception
     */
    public static TransactionAwait createNamedAccount(NearService nearService, String newAccountId,
                                                      PublicKey newAccountPublicKey, BigInteger amountToNewAccount,
                                                      String creatorAccountId, PublicKey creatorPublicKey,
                                                      PrivateKey creatorPrivateKey)
            throws GeneralSecurityException, NearServiceException {
        long nonce = 0L;
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode args = mapper.createObjectNode();
        args.put("new_account_id", newAccountId);
        args.put("new_public_key", newAccountPublicKey.getJsonPublicKey());

        List<Action> actions = Arrays.asList(FunctionCallAction.builder()
                .methodName("create_account")
                .args(args.toString())
                .gas(BigInteger.valueOf(300000000000000L))
                .deposit(amountToNewAccount == null ? "" : amountToNewAccount.toString())
                .build());
        //TODO: receiverId can be either testnet or near (depending on the network)
        return nearService.sendTransactionAwait(BaseService.prepareTransactionForActionList(
                nearService, creatorAccountId, "testnet", creatorPublicKey, creatorPrivateKey,
                actions, nonce));
    }

    /**
     * Internal method to create a list of actions needed to generate a new subaccount
     *
     * @param nearService          the near service instance to use
     * @param amountToNewAccount   the amount to transfer
     * @param creatorAccountId     human-readable id of creatot account
     * @param creatorPublicKey     signer/account creator public key
     * @return List of actions
     */
    private static List<Action> createActionArrayToCreateSubAccount(NearService nearService, BigInteger amountToNewAccount,
                                                                    String creatorAccountId, PublicKey creatorPublicKey) {
        AccessKey accessKey = nearService.viewAccessKey(Finality.FINAL, creatorAccountId, creatorPublicKey.toEncodedBase58String());
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

    /**
     * Sends a {@link TransferAction} transaction with the needed actions to generate a sub account
     * and waits for result using  {@link NearService#sendTransactionAwait(String)}
     *
     * @param nearService          the near service instance to use
     * @param newAccountId         human-readable id of new account
     * @param amountToNewAccount   the amount to transfer
     * @param creatorAccountId     human-readable id of creatot account
     * @param creatorPublicKey     signer/account creator public key
     * @param creatorPrivateKey    signer/account creator privatekey
     * @return {@link TransactionAwait} object with the result
     * @throws GeneralSecurityException thrown if failed to sign the transaction
     */
    public static TransactionAwait createSubAccount(NearService nearService, String newAccountId,
                                                    BigInteger amountToNewAccount, String creatorAccountId,
                                                    PublicKey creatorPublicKey, PrivateKey creatorPrivateKey)
            throws GeneralSecurityException {
        List<Action> actions = AccountService.createActionArrayToCreateSubAccount(nearService, amountToNewAccount,
                creatorAccountId, creatorPublicKey);

        return nearService.sendTransactionAwait(BaseService.prepareTransactionForActionList(
                nearService, creatorAccountId, newAccountId, creatorPublicKey, creatorPrivateKey,
                actions));
    }

    /**
     * Sends a {@link TransferAction} transaction async using {@link NearService#sendTransactionAsync(String)}
     * with the needed actions to generate a sub account and waits for result using
     * {@link NearService#sendTransactionAwait(String)}
     *
     * @param nearService          the near service instance to use
     * @param newAccountId         human-readable id of new account
     * @param amountToNewAccount   the amount to transfer
     * @param creatorAccountId     human-readable id of creatot account
     * @param creatorPublicKey     signer/account creator public key
     * @param creatorPrivateKey    signer/account creator privatekey
     * @return transatcion hash
     * @throws GeneralSecurityException thrown if failed to sign the transaction
     */
    public static EncodedHash createSubAccountAsync(NearService nearService, String newAccountId,
                                                    BigInteger amountToNewAccount, String creatorAccountId,
                                                    PublicKey creatorPublicKey, PrivateKey creatorPrivateKey) throws GeneralSecurityException {
        List<Action> actions = AccountService.createActionArrayToCreateSubAccount(nearService, amountToNewAccount,
                creatorAccountId, creatorPublicKey);
        return EncodedHash.builder()
                .encodedHash(nearService.sendTransactionAsync(BaseService.prepareTransactionForActionList(
                        nearService, creatorAccountId, newAccountId, creatorPublicKey, creatorPrivateKey,
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
        return;
    }
}
