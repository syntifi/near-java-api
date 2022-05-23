package com.syntifi.near.api.rpc.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.syntifi.crypto.key.encdec.Hex;
import com.syntifi.near.api.common.model.key.PrivateKey;
import com.syntifi.near.api.common.model.key.PublicKey;
import com.syntifi.near.api.rpc.model.accesskey.AccessKey;
import com.syntifi.near.api.rpc.model.accesskey.permission.FullAccessPermission;
import com.syntifi.near.api.rpc.model.block.Block;
import com.syntifi.near.api.rpc.model.identifier.Finality;
import com.syntifi.near.api.rpc.model.transaction.*;
import com.syntifi.near.api.rpc.service.exception.NearServiceException;

import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccountService {

    public static TransactionAwait createImplicitAccount(NearService nearService,
                                                         PublicKey newAccountPublicKey,
                                                         PrivateKey newAccountPrivateKey) throws GeneralSecurityException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode args = mapper.createObjectNode();
        args.put("new_public_key", Hex.encode(newAccountPublicKey.getPublicKey().getKey()));

        List<Action> actions = Arrays.asList(FunctionCallAction.builder()
                .methodName("create_account")
                .args(args.asText())
                .gas(BigInteger.valueOf(300000000000000L))
                .build());
        //TODO: receiverId can be either testnet or near (depending on the network)
        return nearService.sendTransactionAwait(BaseService.prepareTransactionForActionList(
                nearService, Hex.encode(newAccountPublicKey.getPublicKey().getKey()), "testnet",
                newAccountPublicKey, newAccountPrivateKey, actions, null, null));
    }

    public static TransactionAwait createNamedAccount(NearService nearService, String newAccountId,
                                                      PublicKey newAccountPublicKey, BigInteger amountToNewAccount,
                                                      String creatorAccountId, PublicKey creatorPublicKey,
                                                      PrivateKey creatorPrivateKey)
            throws GeneralSecurityException, NearServiceException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode args = mapper.createObjectNode();
        AccountService.checkAccountId(newAccountId);
        args.put("new_account_id", newAccountId);
        args.put("new_public_key", Hex.encode(newAccountPublicKey.getPublicKey().getKey()));

        List<Action> actions = Arrays.asList(FunctionCallAction.builder()
                .methodName("create_account")
                .args(args.asText())
                .gas(BigInteger.valueOf(300000000000000L))
                .deposit(amountToNewAccount.toString())
                .build());
        //TODO: receiverId can be either testnet or near (depending on the network)
        return nearService.sendTransactionAwait(BaseService.prepareTransactionForActionList(
                nearService, creatorAccountId, "testnet", creatorPublicKey, creatorPrivateKey,
                actions, null, null));

    }

    public static TransactionAwait createSubAccount(NearService nearService, String newAccountId,
                                                    BigInteger amountToNewAccount, String creatorAccountId,
                                                    PublicKey creatorPublicKey, PrivateKey creatorPrivateKey)
            throws GeneralSecurityException {
        AccessKey accessKey = nearService.viewAccessKey(Finality.FINAL, creatorAccountId, creatorPublicKey.toEncodedBase58String());
        Block block = nearService.getBlock(Finality.FINAL);

        long nonce = accessKey.getNonce() + 1L;
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
                                .blockHeight(block.getHeader().getHeight())
                                .blockHash(block.getHeader().getHash())
                                .build())
                        .build()};
        List<Action> actions = Arrays.asList(actionsArray);

        return nearService.sendTransactionAwait(BaseService.prepareTransactionForActionList(
                nearService, creatorAccountId, newAccountId, creatorPublicKey, creatorPrivateKey,
                actions, nonce + 1L, block));
    }

        public static void checkAccountId (String accountId) throws NearServiceException {
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
