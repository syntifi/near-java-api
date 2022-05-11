package com.syntifi.near.api.rpc.service;

import com.googlecode.jsonrpc4j.JsonRpcFixedParam;
import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import com.googlecode.jsonrpc4j.JsonRpcMethod;
import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.googlecode.jsonrpc4j.JsonRpcParamsPassMode;
import com.googlecode.jsonrpc4j.ProxyUtil;
import com.syntifi.near.api.common.service.NearObjectMapper;
import com.syntifi.near.api.rpc.model.accesskey.AccessKey;
import com.syntifi.near.api.rpc.model.accesskey.AccessKeyChanges;
import com.syntifi.near.api.rpc.model.accesskey.AccessKeyList;
import com.syntifi.near.api.rpc.model.accesskey.Key;
import com.syntifi.near.api.rpc.model.account.Account;
import com.syntifi.near.api.rpc.model.account.AccountChanges;
import com.syntifi.near.api.rpc.model.block.Block;
import com.syntifi.near.api.rpc.model.block.BlockChanges;
import com.syntifi.near.api.rpc.model.block.Chunk;
import com.syntifi.near.api.rpc.model.contract.ContractCode;
import com.syntifi.near.api.rpc.model.contract.ContractCodeChanges;
import com.syntifi.near.api.rpc.model.contract.ContractFunctionCallResult;
import com.syntifi.near.api.rpc.model.contract.ContractState;
import com.syntifi.near.api.rpc.model.contract.ContractStateChanges;
import com.syntifi.near.api.rpc.model.gas.GasPrice;
import com.syntifi.near.api.rpc.model.identifier.Finality;
import com.syntifi.near.api.rpc.model.network.NetworkInfo;
import com.syntifi.near.api.rpc.model.network.NodeStatus;
import com.syntifi.near.api.rpc.model.network.ValidationStatus;
import com.syntifi.near.api.rpc.model.protocol.GenesisConfig;
import com.syntifi.near.api.rpc.model.protocol.ProtocolConfig;
import com.syntifi.near.api.rpc.model.transaction.Receipt;
import com.syntifi.near.api.rpc.model.transaction.TransactionAwait;
import com.syntifi.near.api.rpc.model.transaction.TransactionStatus;
import com.syntifi.near.api.rpc.service.exception.NearServiceExceptionResolver;
import com.syntifi.near.api.rpc.service.exception.NearServiceException;
import com.syntifi.near.api.rpc.service.exception.NearServiceExceptionResolver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.TreeMap;

/**
 * RPC client for the Near network
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.0.1
 */
public interface NearService {

    /**
     * Queries network and returns block for given height or hash.
     *
     * @param finality the finality param
     * @return the data holding object
     * @throws NearServiceException rpc call error exception
     */
    @JsonRpcMethod("block")
    Block getBlock(@JsonRpcParam("finality") Finality finality) throws NearServiceException;

    /**
     * Queries network and returns block for given height or hash.
     *
     * @param blockHash the block's hash
     * @return the data holding object
     * @throws NearServiceException rpc call error exception
     */
    @JsonRpcMethod("block")
    Block getBlock(@JsonRpcParam("block_id") String blockHash) throws NearServiceException;

    /**
     * Queries network and returns block for given height or hash.
     *
     * @param blockHeight the block's height
     * @return the data holding object
     * @throws NearServiceException rpc call error exception
     */
    @JsonRpcMethod("block")
    Block getBlock(@JsonRpcParam("block_id") long blockHeight) throws NearServiceException;

    /**
     * @param finality the finality param
     * @return the data holding object
     * @throws NearServiceException rpc call error exception
     */
    @JsonRpcMethod("EXPERIMENTAL_changes_in_block")
    BlockChanges getBlockChanges(@JsonRpcParam("finality") Finality finality) throws NearServiceException;

    /**
     * @param blockHash the block's hash
     * @return the data holding object
     * @throws NearServiceException rpc call error exception
     */
    @JsonRpcMethod("EXPERIMENTAL_changes_in_block")
    BlockChanges getBlockChanges(@JsonRpcParam("block_id") String blockHash) throws NearServiceException;

    /**
     * @param blockHeight the block's height
     * @return the data holding object
     * @throws NearServiceException rpc call error exception
     */
    @JsonRpcMethod("EXPERIMENTAL_changes_in_block")
    BlockChanges getBlockChanges(@JsonRpcParam("block_id") long blockHeight) throws NearServiceException;

    /**
     * Returns details of a specific chunk. You can run a block details query to get
     * a valid chunk hash.
     *
     * @param chunkId the id of the chunk
     * @return the data holding object
     * @throws NearServiceException rpc call error exception
     */
    @JsonRpcMethod("chunk")
    Chunk getChunkDetails(@JsonRpcParam("chunk_id") String chunkId) throws NearServiceException;

    /**
     * Returns details of a specific chunk. You can run a block details query to get
     * a valid chunk hash.
     *
     * @param blockId the block hash
     * @param shardId the id of the shard
     * @return the data holding object
     * @throws NearServiceException rpc call error exception
     */
    @JsonRpcMethod("chunk")
    Chunk getChunkDetails(@JsonRpcParam("block_id") String blockId,
                          @JsonRpcParam("shard_id") long shardId) throws NearServiceException;

    /**
     * Returns details of a specific chunk. You can run a block details query to get
     * a valid chunk hash.
     *
     * @param blockId the block height
     * @param shardId the id of the shard
     * @return the data holding object
     * @throws NearServiceException rpc call error exception
     */
    @JsonRpcMethod("chunk")
    Chunk getChunkDetails(@JsonRpcParam("block_id") long blockId,
                          @JsonRpcParam("shard_id") long shardId) throws NearServiceException;

    /**
     * Returns general status of a given node (sync status, near core node version,
     * protocol version, etc.), and the current set of validators.
     *
     * @return the data holding object
     * @throws NearServiceException rpc call error exception
     */
    @JsonRpcMethod("status")
    NodeStatus getNodeStatus() throws NearServiceException;

    /**
     * Returns the current state of node network connections (active peers,
     * transmitted data, etc.)
     *
     * @return the data holding object
     * @throws NearServiceException rpc call error exception
     */
    @JsonRpcMethod("network_info")
    NetworkInfo getNetworkInfo() throws NearServiceException;

    /**
     * Queries active validators on the network returning details and the state of
     * validation on the blockchain.
     *
     * @param blockHash the block's hash
     * @return the data holding object
     * @throws NearServiceException rpc call error exception
     */
    @JsonRpcMethod(value = "validators", paramsPassMode = JsonRpcParamsPassMode.ARRAY)
    ValidationStatus getNetworkValidationStatus(String blockHash) throws NearServiceException;

    /**
     * Queries active validators on the network returning details and the state of
     * validation on the blockchain.
     *
     * @param blockHeight the block's height
     * @return the data holding object
     * @throws NearServiceException rpc call error exception
     */
    @JsonRpcMethod(value = "validators", paramsPassMode = JsonRpcParamsPassMode.ARRAY)
    ValidationStatus getNetworkValidationStatus(long blockHeight) throws NearServiceException;

    /**
     * Returns gas price for a specific block_height or block_hash.
     * - Using [null] will return the most recent block's gas price.
     *
     * @param blockHash the block's hash
     * @return the data holding object
     * @throws NearServiceException rpc call error exception
     */
    @JsonRpcMethod(value = "gas_price", paramsPassMode = JsonRpcParamsPassMode.ARRAY)
    GasPrice getGasPrice(String blockHash) throws NearServiceException;

    /**
     * Returns gas price for a specific block_height or block_hash.
     *
     * @param blockHeight the block's height
     * @return the data holding object
     * @throws NearServiceException rpc call error exception
     */
    @JsonRpcMethod(value = "gas_price", paramsPassMode = JsonRpcParamsPassMode.ARRAY)
    GasPrice getGasPrice(long blockHeight) throws NearServiceException;

    /**
     * Returns current genesis configuration.
     *
     * @return the data holding object
     * @throws NearServiceException rpc call error exception
     */
    @JsonRpcMethod("EXPERIMENTAL_genesis_config")
    GenesisConfig getGenesisConfig() throws NearServiceException;

    /**
     * Returns most recent protocol configuration or a specific queried block.
     * Useful for finding current storage and transaction costs.
     *
     * @param finality the finality param
     * @return the data holding object
     * @throws NearServiceException rpc call error exception
     */
    @JsonRpcMethod("EXPERIMENTAL_protocol_config")
    ProtocolConfig getProtocolConfig(@JsonRpcParam("finality") Finality finality)
            throws NearServiceException;

    /**
     * Returns most recent protocol configuration or a specific queried block.
     * Useful for finding current storage and transaction costs.
     *
     * @param blockHash the block's hash
     * @return the data holding object
     * @throws NearServiceException rpc call error exception
     */
    @JsonRpcMethod("EXPERIMENTAL_protocol_config")
    ProtocolConfig getProtocolConfig(@JsonRpcParam("block_id") String blockHash) throws NearServiceException;

    /**
     * Returns most recent protocol configuration or a specific queried block.
     * Useful for finding current storage and transaction costs.
     *
     * @param blockHeight the block's height
     * @return the data holding object
     * @throws NearServiceException rpc call error exception
     */
    @JsonRpcMethod("EXPERIMENTAL_protocol_config")
    ProtocolConfig getProtocolConfig(@JsonRpcParam("block_id") long blockHeight) throws NearServiceException;

    /**
     * Sends a transaction and immediately returns transaction hash.
     *
     * @param base64EncodedSignedTransaction the base64 encoded signed transaction
     *                                       string
     * @return the transaction hash
     * @throws NearServiceException rpc call error exception
     */
    @JsonRpcMethod(value = "broadcast_tx_async", paramsPassMode = JsonRpcParamsPassMode.ARRAY)
    String sendTransactionAsync(String base64EncodedSignedTransaction) throws NearServiceException;

    /**
     * Sends a transaction and waits until transaction is fully complete. (Has a 10-second timeout)
     *
     * @param base64EncodedSignedTransaction the base64 encoded signed transaction
     *                                       string
     * @return the data holding object
     * @throws NearServiceException rpc call error exception
     */
    @JsonRpcMethod(value = "broadcast_tx_commit", paramsPassMode = JsonRpcParamsPassMode.ARRAY)
    TransactionAwait sendTransactionAwait(String base64EncodedSignedTransaction) throws NearServiceException;

    /**
     * Queries status of a transaction by hash and returns the final transaction
     * result.
     *
     * @param transactionHash the transaction hash
     * @param senderAccountId the sender's account id
     * @return the data holding object the final transaction result
     * @throws NearServiceException rpc call error exception
     */
    @JsonRpcMethod(value = "tx", paramsPassMode = JsonRpcParamsPassMode.ARRAY)
    TransactionStatus getTransactionStatus(String transactionHash, String senderAccountId)
            throws NearServiceException;

    /**
     * Queries status of a transaction by hash, returning the final transaction
     * result and details of all receipts.
     *
     * @param transactionHash the transaction hash
     * @param senderAccountId the sender's account id
     * @return the data holding object the final transaction result and details of
     * all receipts
     * @throws NearServiceException rpc call error exception
     */
    @JsonRpcMethod(value = "EXPERIMENTAL_tx_status", paramsPassMode = JsonRpcParamsPassMode.ARRAY)
    TransactionStatus getTransactionStatusWithReceipts(String transactionHash, String senderAccountId)
            throws NearServiceException;

    /**
     * Fetches a receipt by its ID (as is, without a status or execution outcome)
     *
     * @param receiptId the receipt ID to query for info
     * @return the data holding object
     * @throws NearServiceException rpc call error exception
     */
    @JsonRpcMethod("EXPERIMENTAL_receipt")
    Receipt getTransactionReceipt(@JsonRpcParam("receipt_id") String receiptId) throws NearServiceException;

    /**
     * Returns information about a single access key for given account.
     * <p>
     * If permission of the key is FunctionCall, it will return more details such as
     * the allowance, receiver_id, and method_names.
     *
     * @param finality  the finality param
     * @param accountId the account id
     * @param publicKey the associated public key
     * @return the data holding object
     * @throws NearServiceException rpc call error exception
     */
    @JsonRpcMethod("query")
    @JsonRpcFixedParam(name = "request_type", value = "view_access_key")
    AccessKey viewAccessKey(@JsonRpcParam("finality") Finality finality,
                            @JsonRpcParam("account_id") String accountId, @JsonRpcParam("public_key") String publicKey)
            throws NearServiceException;

    /**
     * Returns information about a single access key for given account.
     * <p>
     * If permission of the key is FunctionCall, it will return more details such as
     * the allowance, receiver_id, and method_names.
     *
     * @param blockHash the block's hash
     * @param accountId the account id
     * @param publicKey the associated public key
     * @return the data holding object
     * @throws NearServiceException rpc call error exception
     */
    @JsonRpcMethod("query")
    @JsonRpcFixedParam(name = "request_type", value = "view_access_key")
    AccessKey viewAccessKey(@JsonRpcParam("block_id") String blockHash,
                            @JsonRpcParam("account_id") String accountId, @JsonRpcParam("public_key") String publicKey)
            throws NearServiceException;

    /**
     * Returns information about a single access key for given account.
     * <p>
     * If permission of the key is FunctionCall, it will return more details such as
     * the allowance, receiver_id, and method_names.
     *
     * @param blockHeight the block's height
     * @param accountId   the account id
     * @param publicKey   the associated public key
     * @return the data holding object
     * @throws NearServiceException rpc call error exception
     */
    @JsonRpcMethod("query")
    @JsonRpcFixedParam(name = "request_type", value = "view_access_key")
    AccessKey viewAccessKey(@JsonRpcParam("block_id") long blockHeight,
                            @JsonRpcParam("account_id") String accountId, @JsonRpcParam("public_key") String publicKey)
            throws NearServiceException;

    /**
     * Returns all access keys for a given account.
     *
     * @param finality  the finality param
     * @param accountId the account id
     * @return the data holding object
     * @throws NearServiceException rpc call error exception
     */
    @JsonRpcMethod("query")
    @JsonRpcFixedParam(name = "request_type", value = "view_access_key_list")
    AccessKeyList viewAccessKeyList(@JsonRpcParam("finality") Finality finality,
                                    @JsonRpcParam("account_id") String accountId)
            throws NearServiceException;

    /**
     * Returns all access keys for a given account.
     *
     * @param blockHash the block's hash
     * @param accountId the account id
     * @return the data holding object
     * @throws NearServiceException rpc call error exception
     */
    @JsonRpcMethod("query")
    @JsonRpcFixedParam(name = "request_type", value = "view_access_key_list")
    AccessKeyList viewAccessKeyList(@JsonRpcParam("block_id") String blockHash,
                                    @JsonRpcParam("account_id") String accountId)
            throws NearServiceException;

    /**
     * Returns all access keys for a given account.
     *
     * @param blockHeight the block's height
     * @param accountId   the account id
     * @return the data holding object
     * @throws NearServiceException rpc call error exception
     */
    @JsonRpcMethod("query")
    @JsonRpcFixedParam(name = "request_type", value = "view_access_key_list")
    AccessKeyList viewAccessKeyList(@JsonRpcParam("block_id") long blockHeight,
                                    @JsonRpcParam("account_id") String accountId)
            throws NearServiceException;

    /**
     * Returns individual access key changes in a specific block. You can query
     * multiple keys by passing an array of objects containing the account_id and
     * key.
     *
     * @param finality the finality param
     * @param keys     the key data to query for changes
     * @return the data holding object
     * @throws NearServiceException rpc call error exception
     */
    @JsonRpcMethod("EXPERIMENTAL_changes")
    @JsonRpcFixedParam(name = "changes_type", value = "single_access_key_changes")
    AccessKeyChanges viewSingleAccessKeyChanges(@JsonRpcParam("finality") Finality finality,
                                                @JsonRpcParam("keys") Key[] keys)
            throws NearServiceException;

    /**
     * Returns individual access key changes in a specific block. You can query
     * multiple keys by passing an array of objects containing the account_id and
     * key.
     *
     * @param blockHash the block's hash
     * @param keys      the key data to query for changes
     * @return the data holding object
     * @throws NearServiceException rpc call error exception
     */
    @JsonRpcMethod("EXPERIMENTAL_changes")
    @JsonRpcFixedParam(name = "changes_type", value = "single_access_key_changes")
    AccessKeyChanges viewSingleAccessKeyChanges(@JsonRpcParam("block_id") String blockHash,
                                                @JsonRpcParam("keys") Key[] keys)
            throws NearServiceException;

    /**
     * Returns individual access key changes in a specific block. You can query
     * multiple keys by passing an array of objects containing the account_id and
     * key.
     *
     * @param blockHeight the block's height
     * @param keys        the key data to query for changes
     * @return the data holding object
     * @throws NearServiceException rpc call error exception
     */
    @JsonRpcMethod("EXPERIMENTAL_changes")
    @JsonRpcFixedParam(name = "changes_type", value = "single_access_key_changes")
    AccessKeyChanges viewSingleAccessKeyChanges(@JsonRpcParam("block_id") long blockHeight,
                                                @JsonRpcParam("keys") Key[] keys)
            throws NearServiceException;

    /**
     * Returns changes to all access keys of a specific block. Multiple accounts can
     * be queried by passing an array of account_ids.
     *
     * @param finality   the finality param
     * @param accountIds the account ids
     * @return the data holding object
     * @throws NearServiceException rpc call error exception
     */
    @JsonRpcMethod("EXPERIMENTAL_changes")
    @JsonRpcFixedParam(name = "changes_type", value = "all_access_key_changes")
    AccessKeyChanges viewAllAccessKeyChanges(@JsonRpcParam("finality") Finality finality,
                                             @JsonRpcParam("account_ids") String[] accountIds)
            throws NearServiceException;

    /**
     * Returns changes to all access keys of a specific block. Multiple accounts can
     * be queried by passing an array of account_ids.
     *
     * @param blockHash  the block's hash
     * @param accountIds the account ids
     * @return the data holding object
     * @throws NearServiceException rpc call error exception
     */
    @JsonRpcMethod("EXPERIMENTAL_changes")
    @JsonRpcFixedParam(name = "changes_type", value = "all_access_key_changes")
    AccessKeyChanges viewAllAccessKeyChanges(@JsonRpcParam("block_id") String blockHash,
                                             @JsonRpcParam("account_ids") String[] accountIds)
            throws NearServiceException;

    /**
     * Returns changes to all access keys of a specific block. Multiple accounts can
     * be queried by passing an array of account_ids.
     *
     * @param blockHeight the block's height
     * @param accountIds  the account ids
     * @return the data holding object
     * @throws NearServiceException rpc call error exception
     */
    @JsonRpcMethod("EXPERIMENTAL_changes")
    @JsonRpcFixedParam(name = "changes_type", value = "all_access_key_changes")
    AccessKeyChanges viewAllAccessKeyChanges(@JsonRpcParam("block_id") long blockHeight,
                                             @JsonRpcParam("account_ids") String[] accountIds)
            throws NearServiceException;

    /**
     * Returns basic account information.
     *
     * @param finality  the finality param
     * @param accountId the account id
     * @return the data holding object
     * @throws NearServiceException rpc call error exception
     */
    @JsonRpcMethod("query")
    @JsonRpcFixedParam(name = "request_type", value = "view_account")
    Account viewAccount(@JsonRpcParam("finality") Finality finality,
                        @JsonRpcParam("account_id") String accountId)
            throws NearServiceException;

    /**
     * Returns basic account information.
     *
     * @param blockHash the block's hash
     * @param accountId the account id
     * @return the data holding object
     * @throws NearServiceException rpc call error exception
     */
    @JsonRpcMethod("query")
    @JsonRpcFixedParam(name = "request_type", value = "view_account")
    Account viewAccount(@JsonRpcParam("block_id") String blockHash,
                        @JsonRpcParam("account_id") String accountId)
            throws NearServiceException;

    /**
     * Returns basic account information.
     *
     * @param blockHeight the block's height
     * @param accountId   the account id
     * @return the data holding object
     * @throws NearServiceException rpc call error exception
     */
    @JsonRpcMethod("query")
    @JsonRpcFixedParam(name = "request_type", value = "view_account")
    Account viewAccount(@JsonRpcParam("block_id") long blockHeight,
                        @JsonRpcParam("account_id") String accountId)
            throws NearServiceException;

    /**
     * Returns account changes from transactions in a given account.
     *
     * @param finality   the finality param
     * @param accountIds the account ids
     * @return the data holding object
     * @throws NearServiceException rpc call error exception
     */
    @JsonRpcMethod("EXPERIMENTAL_changes")
    @JsonRpcFixedParam(name = "changes_type", value = "account_changes")
    AccountChanges viewAccountChanges(@JsonRpcParam("finality") Finality finality,
                                      @JsonRpcParam("account_ids") String[] accountIds)
            throws NearServiceException;

    /**
     * Returns account changes from transactions in a given account.
     *
     * @param blockHash  the block's hash
     * @param accountIds the account ids
     * @return the data holding object
     * @throws NearServiceException rpc call error exception
     */
    @JsonRpcMethod("EXPERIMENTAL_changes")
    @JsonRpcFixedParam(name = "changes_type", value = "account_changes")
    AccountChanges viewAccountChanges(@JsonRpcParam("block_id") String blockHash,
                                      @JsonRpcParam("account_ids") String[] accountIds)
            throws NearServiceException;

    /**
     * Returns account changes from transactions in a given account.
     *
     * @param blockHeight the block's height
     * @param accountIds  the account ids
     * @return the data holding object
     * @throws NearServiceException rpc call error exception
     */
    @JsonRpcMethod("EXPERIMENTAL_changes")
    @JsonRpcFixedParam(name = "changes_type", value = "account_changes")
    AccountChanges viewAccountChanges(@JsonRpcParam("block_id") long blockHeight,
                                      @JsonRpcParam("account_ids") String[] accountIds)
            throws NearServiceException;

    /**
     * Returns the contract code (Wasm binary) deployed to the account. Please note
     * that the returned code will be encoded in base64.
     *
     * @param finality  the finality param
     * @param accountId the account id
     * @return the data holding object
     * @throws NearServiceException rpc call error exception
     */
    @JsonRpcMethod("query")
    @JsonRpcFixedParam(name = "request_type", value = "view_code")
    ContractCode viewContractCode(@JsonRpcParam("finality") Finality finality,
                                  @JsonRpcParam("account_id") String accountId)
            throws NearServiceException;

    /**
     * Returns the contract code (Wasm binary) deployed to the account. Please note
     * that the returned code will be encoded in base64.
     *
     * @param blockHash the block's hash
     * @param accountId the account id
     * @return the data holding object
     * @throws NearServiceException rpc call error exception
     */
    @JsonRpcMethod("query")
    @JsonRpcFixedParam(name = "request_type", value = "view_code")
    ContractCode viewContractCode(@JsonRpcParam("block_id") String blockHash,
                                  @JsonRpcParam("account_id") String accountId)
            throws NearServiceException;

    /**
     * Returns the contract code (Wasm binary) deployed to the account. Please note
     * that the returned code will be encoded in base64.
     *
     * @param blockHeight the block's height
     * @param accountId   the account id
     * @return the data holding object
     * @throws NearServiceException rpc call error exception
     */
    @JsonRpcMethod("query")
    @JsonRpcFixedParam(name = "request_type", value = "view_code")
    ContractCode viewContractCode(@JsonRpcParam("block_id") long blockHeight,
                                  @JsonRpcParam("account_id") String accountId)
            throws NearServiceException;

    /**
     * Returns the state (key value pairs) of a contract based on the key prefix
     * (base64 encoded). Pass an empty string for prefix_base64 if you would like to
     * return the entire state. Please note that the returned state will be base64
     * encoded as well.
     *
     * @param finality     the finality param
     * @param accountId    the account id
     * @param prefixBase64 the base64 encoded prefix
     * @return the data holding object
     * @throws NearServiceException rpc call error exception
     */
    @JsonRpcMethod("query")
    @JsonRpcFixedParam(name = "request_type", value = "view_state")
    ContractState viewContractState(@JsonRpcParam("finality") Finality finality,
                                    @JsonRpcParam("account_id") String accountId,
                                    @JsonRpcParam("prefix_base64") String prefixBase64)
            throws NearServiceException;

    /**
     * Returns the state (key value pairs) of a contract based on the key prefix
     * (base64 encoded). Pass an empty string for prefix_base64 if you would like to
     * return the entire state. Please note that the returned state will be base64
     * encoded as well.
     *
     * @param blockHash    the block's hash
     * @param accountId    the account id
     * @param prefixBase64 the base64 encoded prefix
     * @return the data holding object
     * @throws NearServiceException rpc call error exception
     */
    @JsonRpcMethod("query")
    @JsonRpcFixedParam(name = "request_type", value = "view_state")
    ContractState viewContractState(@JsonRpcParam("block_id") String blockHash,
                                    @JsonRpcParam("account_id") String accountId,
                                    @JsonRpcParam("prefix_base64") String prefixBase64)
            throws NearServiceException;

    /**
     * Returns the state (key value pairs) of a contract based on the key prefix
     * (base64 encoded). Pass an empty string for prefix_base64 if you would like to
     * return the entire state. Please note that the returned state will be base64
     * encoded as well.
     *
     * @param blockHeight  the block's height
     * @param accountId    the account id
     * @param prefixBase64 the base64 encoded prefix
     * @return the data holding object
     * @throws NearServiceException rpc call error exception
     */
    @JsonRpcMethod("query")
    @JsonRpcFixedParam(name = "request_type", value = "view_state")
    ContractState viewContractState(@JsonRpcParam("block_id") long blockHeight,
                                    @JsonRpcParam("account_id") String accountId,
                                    @JsonRpcParam("prefix_base64") String prefixBase64)
            throws NearServiceException;

    /**
     * Returns the state change details of a contract based on the key prefix
     * (encoded to base64). Pass an empty string for this param if you would like to
     * return all state changes.
     *
     * @param finality        the finality param
     * @param accountIds      the account ids
     * @param keyPrefixBase64 the base64 encoded key prefix
     * @return the data holding object
     * @throws NearServiceException rpc call error exception
     */
    @JsonRpcMethod("EXPERIMENTAL_changes")
    @JsonRpcFixedParam(name = "changes_type", value = "data_changes")
    ContractStateChanges viewContractStateChanges(@JsonRpcParam("finality") Finality finality,
                                                  @JsonRpcParam("account_ids") String[] accountIds,
                                                  @JsonRpcParam("key_prefix_base64") String keyPrefixBase64)
            throws NearServiceException;

    /**
     * Returns the state change details of a contract based on the key prefix
     * (encoded to base64). Pass an empty string for this param if you would like to
     * return all state changes.
     *
     * @param blockHash       the block's hash
     * @param accountIds      the account ids
     * @param keyPrefixBase64 the base64 encoded key prefix
     * @return the data holding object
     * @throws NearServiceException rpc call error exception
     */
    @JsonRpcMethod("EXPERIMENTAL_changes")
    @JsonRpcFixedParam(name = "changes_type", value = "data_changes")
    ContractStateChanges viewContractStateChanges(@JsonRpcParam("block_id") String blockHash,
                                                  @JsonRpcParam("account_ids") String[] accountIds,
                                                  @JsonRpcParam("key_prefix_base64") String keyPrefixBase64)
            throws NearServiceException;

    /**
     * Returns the state change details of a contract based on the key prefix
     * (encoded to base64). Pass an empty string for this param if you would like to
     * return all state changes.
     *
     * @param blockHeight     the block's height
     * @param accountIds      the account ids
     * @param keyPrefixBase64 the base64 encoded key prefix
     * @return the data holding object
     * @throws NearServiceException rpc call error exception
     */
    @JsonRpcMethod("EXPERIMENTAL_changes")
    @JsonRpcFixedParam(name = "changes_type", value = "data_changes")
    ContractStateChanges viewContractStateChanges(@JsonRpcParam("block_id") long blockHeight,
                                                  @JsonRpcParam("account_ids") String[] accountIds,
                                                  @JsonRpcParam("key_prefix_base64") String keyPrefixBase64)
            throws NearServiceException;

    /**
     * Returns code changes made when deploying a contract. Change is returned is a
     * base64 encoded WASM file.
     *
     * @param finality   the finality param
     * @param accountIds the account ids
     * @return the data holding object
     * @throws NearServiceException rpc call error exception
     */
    @JsonRpcMethod("EXPERIMENTAL_changes")
    @JsonRpcFixedParam(name = "changes_type", value = "contract_code_changes")
    ContractCodeChanges viewContractCodeChanges(@JsonRpcParam("finality") Finality finality,
                                                @JsonRpcParam("account_ids") String[] accountIds)
            throws NearServiceException;

    /**
     * Returns code changes made when deploying a contract. Change is returned is a
     * base64 encoded WASM file.
     *
     * @param blockHash  the block's hash
     * @param accountIds the account ids
     * @return the data holding object
     * @throws NearServiceException rpc call error exception
     */
    @JsonRpcMethod("EXPERIMENTAL_changes")
    @JsonRpcFixedParam(name = "changes_type", value = "contract_code_changes")
    ContractCodeChanges viewContractCodeChanges(@JsonRpcParam("block_id") String blockHash,
                                                @JsonRpcParam("account_ids") String[] accountIds)
            throws NearServiceException;

    /**
     * Returns code changes made when deploying a contract. Change is returned is a
     * base64 encoded WASM file.
     *
     * @param blockHeight the block's height
     * @param accountIds  the account ids
     * @return the data holding object
     * @throws NearServiceException rpc call error exception
     */
    @JsonRpcMethod("EXPERIMENTAL_changes")
    @JsonRpcFixedParam(name = "changes_type", value = "contract_code_changes")
    ContractCodeChanges viewContractCodeChanges(@JsonRpcParam("block_id") long blockHeight,
                                                @JsonRpcParam("account_ids") String[] accountIds)
            throws NearServiceException;

    /**
     * Allows you to call a contract method as a view function.
     *
     * @param finality   the finality param
     * @param accountId  the account id
     * @param methodName the name of the method to call the name of the method to
     *                   call
     * @param argsBase64 the method's base64 encoded arguments
     * @return the data holding object
     * @throws NearServiceException rpc call error exception
     */
    @JsonRpcMethod("query")
    @JsonRpcFixedParam(name = "request_type", value = "call_function")
    ContractFunctionCallResult callContractFunction(@JsonRpcParam("finality") Finality finality,
                                                    @JsonRpcParam("account_id") String accountId, @JsonRpcParam("method_name") String methodName,
                                                    @JsonRpcParam("args_base64") String argsBase64)
            throws NearServiceException;

    /**
     * Allows you to call a contract method as a view function.
     *
     * @param blockHash  the block's hash
     * @param accountId  the account id
     * @param methodName the name of the method to call
     * @param argsBase64 the method's base64 encoded arguments
     * @return the data holding object
     * @throws NearServiceException rpc call error exception
     */
    @JsonRpcMethod("query")
    @JsonRpcFixedParam(name = "request_type", value = "call_function")
    ContractFunctionCallResult callContractFunction(@JsonRpcParam("block_id") String blockHash,
                                                    @JsonRpcParam("account_id") String accountId, @JsonRpcParam("method_name") String methodName,
                                                    @JsonRpcParam("args_base64") String argsBase64)
            throws NearServiceException;

    /**
     * Allows you to call a contract method as a view function.
     *
     * @param blockHeight the block's height
     * @param accountId   the account id
     * @param methodName  the name of the method to call
     * @param argsBase64  the method's base64 encoded arguments
     * @return the data holding object
     * @throws NearServiceException rpc call error exception
     */
    @JsonRpcMethod("query")
    @JsonRpcFixedParam(name = "request_type", value = "call_function")
    ContractFunctionCallResult callContractFunction(@JsonRpcParam("block_id") long blockHeight,
                                                    @JsonRpcParam("account_id") String accountId, @JsonRpcParam("method_name") String methodName,
                                                    @JsonRpcParam("args_base64") String argsBase64)
            throws NearServiceException;

    /**
     * NearService builder
     *
     * @param url the rpc peer url to connect to
     * @return the data holding object
     * @throws MalformedURLException thrown when url is invalid or unparseable
     */
    static NearService usingPeer(String url) throws MalformedURLException {
        NearObjectMapper objectMapper = new NearRpcObjectMapper();
        Map<String, String> customHeaders = new TreeMap<>();
        customHeaders.put("Content-Type", "application/json");

        JsonRpcHttpClient client = new JsonRpcHttpClient(objectMapper, new URL("https://" + url),
                customHeaders);

        client.setExceptionResolver(new NearServiceExceptionResolver());

        return ProxyUtil.createClientProxy(NearService.class.getClassLoader(), NearService.class, client);
    }
}
