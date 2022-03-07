---
title: sbs4j
menu_item: true
menu_title: Home
layout: default
order: 1
---
# NEAR Java 8+ API

This project implements the API to interact with a NEAR Node. It wraps the Json-RPC requests and maps the results to Java objects. 

## Dependencies
- Java 8+ 
- Maven (via wrapper)

## Build instructions
```
./mvnw package
```

## Using the Maven Central repository

### Using Maven
``` xml
<dependency>
  <groupId>com.syntifi.near</groupId>
  <artifactId>near-java-api</artifactId>
  <version>0.1.0</version>
</dependency>
```

### Using gradle
``` groovy
implementation 'com.syntifi.near:near-java-api:0.1.0'
```

## References
This project used the following references:

- Official docs @ [docs.near.org](https://docs.near.org/docs/api/rpc/)

## How to

### 1. [Set-up a connection](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceHelper.java#L21)

``` java
nearService = NearService.usingPeer("archival-rpc.testnet.near.org");
```


### 2. [Access Keys](https://docs.near.org/docs/api/rpc/access-keys#access-keys)


#### 2.1 [View access key](https://docs.near.org/docs/api/rpc/access-keys#view-access-key)
Returns information about a single access key for given account.

If permission of the key is FunctionCall, it will return more details such as the allowance, receiver_id, and method_names.

#### [By finality](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L585-L588)

``` java
String accountId = "client.chainlink.testnet";
String publicKey = "ed25519:H9k5eiU4xXS3M4z8HzKJSLaZdqGdGwBG49o7orNC4eZW";

AccessKey accessKey = nearService.viewAccessKey(Finality.FINAL, accountId, publicKey);
```

#### [By height](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L610-L613)
``` java
String accountId = "client.chainlink.testnet";
String publicKey = "ed25519:H9k5eiU4xXS3M4z8HzKJSLaZdqGdGwBG49o7orNC4eZW";

AccessKey accessKey = nearService.viewAccessKey(78443365, accountId, publicKey);
```
#### [By hash](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L595-L599)
``` java
String accountId = "client.chainlink.testnet";
String publicKey = "ed25519:H9k5eiU4xXS3M4z8HzKJSLaZdqGdGwBG49o7orNC4eZW";

AccessKey accessKey = nearService.viewAccessKey("8bVg8wugs2QHqXr42oEsCYyH7jvR9pLaAP35dFqx2evU", accountId, publicKey);
```


#### 2.2 [View access key list](https://docs.near.org/docs/api/rpc/access-keys#view-access-key-list)
Returns all access keys for a given account.

#### [By finality](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L637-L639)

``` java
String accountId = "client.chainlink.testnet";

AccessKeyList accessKeyList = nearService.viewAccessKeyList(Finality.FINAL, accountId);
```

#### [By height](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L660-L662)
``` java
String accountId = "client.chainlink.testnet";

AccessKeyList accessKeyList = nearService.viewAccessKeyList(78772585, accountId);
```
#### [By hash](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L646-L649)
``` java
String accountId = "client.chainlink.testnet";

AccessKeyList accessKeyList = nearService.viewAccessKeyList("DwFpDPiQXBaX6Vw3aKazQ4nXjgzw1uk6XpUkfTSJrbXf", accountId);
```


#### 2.3 [View access key changes (single)](https://docs.near.org/docs/api/rpc/access-keys#view-access-key-changes-single)
Returns individual access key changes in a specific block. You can query multiple keys by passing an array of objects containing the account_id and public_key.

#### [By finality](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L688-L793)

``` java
Key[] keys = new Key[1];

Key key0 = new Key("example-acct.testnet", "ed25519:25KEc7t7MQohAJ4EDThd2vkksKkwangnuJFzcoiXj9oM");
keys[0] = key0;

AccessKeyChanges accessKeyChanges = nearService.viewSingleAccessKeyChanges(Finality.FINAL, keys);
```

#### [By height](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L718-L723)
``` java
Key[] keys = new Key[1];

Key key0 = new Key("example-acct.testnet", "ed25519:25KEc7t7MQohAJ4EDThd2vkksKkwangnuJFzcoiXj9oM");
keys[0] = key0;

AccessKeyChanges accessKeyChanges = nearService.viewSingleAccessKeyChanges(78433961, keys);
```
#### [By hash](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L702-L709)
``` java
Key[] keys = new Key[1];

Key key0 = new Key("example-acct.testnet", "ed25519:25KEc7t7MQohAJ4EDThd2vkksKkwangnuJFzcoiXj9oM");
keys[0] = key0;

AccessKeyChanges accessKeyChanges = nearService.viewSingleAccessKeyChanges("Cr82U81VqHgCz9LzZjPivh9t16e8es6aFCv9qvDMMH88", keys);
```


#### 2.4 [View access key changes (all)](https://docs.near.org/docs/api/rpc/access-keys#view-access-key-changes-all)
Returns changes to all access keys of a specific block. Multiple accounts can be quereied by passing an array of account_ids.

#### [By finality](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L763-L767)

``` java
String[] accountIds = new String[1];

accountIds[0] = "client.chainlink.testnet";

AccessKeyChanges accessKeyChanges = nearService.viewAllAccessKeyChanges(Finality.FINAL, accountIds);
```

#### [By height](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L791-L795)
``` java
String[] accountIds = new String[1];

accountIds[0] = "client.chainlink.testnet";

AccessKeyChanges accessKeyChanges = nearService.viewAllAccessKeyChanges(78433518, accountIds);
```
#### [By hash](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L776-L782)
``` java
String[] accountIds = new String[1];

accountIds[0] = "client.chainlink.testnet";

AccessKeyChanges accessKeyChanges = nearService.viewAllAccessKeyChanges("Ais9kPbHvk6XmEYptoEpBtyFW77V16TZNHHnYtmXWr1d",accountIds);
```


### 3. [Accounts / Contracts](https://docs.near.org/docs/api/rpc/contracts)


#### 3.1 [View account](https://docs.near.org/docs/api/rpc/contracts#view-account)
Returns basic account information.

#### [By finality](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L815)

``` java
Account account = nearService.viewAccount(Finality.FINAL, "nearkat.testnet");
```

#### [By height](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L833)
``` java
Account account = nearService.viewAccount(78439658, "nearkat.testnet");
```
#### [By hash](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L822)
``` java
Account account = nearService.viewAccount("5hyGx7LiGaeRiAN4RrKcGomi1QXHqZwKXFQf6xTmvUgb", "nearkat.testnet");
```


#### 3.2 [View account changes](https://docs.near.org/docs/api/rpc/contracts#view-account-changes)
Returns account changes from transactions in a given account.

#### [By finality](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L859-L863)

``` java
String[] accountIds = new String[1];

accountIds[0] = "nearkat.testnet";

AccountChanges accountChanges = nearService.viewAccountChanges(Finality.FINAL, accountIds);
```

#### [By height](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L890-L894)
``` java
String[] accountIds = new String[1];

accountIds[0] = "nearkat.testnet";

AccountChanges accountChanges = nearService.viewAccountChanges(78440142, accountIds);
```
#### [By hash](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L872-L877)
``` java
String[] accountIds = new String[1];

accountIds[0] = "nearkat.testnet";

AccountChanges accountChanges = nearService.viewAccountChanges("7vWp2djKLoJ3RE1sr8RzSKQtyzKpe2wZ7NCcDuFNuL7j", accountIds);
```


#### 3.3 [View contract code](https://docs.near.org/docs/api/rpc/contracts#view-contract-code)
Returns the contract code (Wasm binary) deployed to the account. Please note that the returned code will be encoded in base64.

#### [By finality](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L918)

``` java
ContractCode contractCode = nearService.viewContractCode(Finality.FINAL, "guest-book.testnet");
```

#### [By height](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L937)
``` java
ContractCode contractCode = nearService.viewContractCode(78440518, "guest-book.testnet");
```
#### [By hash](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L925-L926)
``` java
ContractCode contractCode = nearService.viewContractCode("uLxyauKPhSk1tebYKi3k69pHSaT2ZLzWy4JwtGm52pu", "guest-book.testnet");
```


#### 3.4 [View contract state](https://docs.near.org/docs/api/rpc/contracts#view-contract-state)
Returns the state (key value pairs) of a contract based on the key prefix (base64 encoded). Pass an empty string for prefix_base64 if you would like to return the entire state. Please note that the returned state will be base64 encoded as well.

#### [By finality](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L963-L964)

``` java
ContractState contractState = nearService.viewContractState(Finality.FINAL, "guest-book.testnet", "");
```

#### [By height](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L990-L991)
``` java
ContractState contractState = nearService.viewContractState(78440679, "guest-book.testnet", "");
```
#### [By hash](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L974-L975)
``` java
ContractState contractState = nearService.viewContractState("342bkjvnzoZ7FGRE5BwDVkzSRUYXAScTz3GsDB9sEHXg", "guest-book.testnet", "");
```


#### 3.5 [View contract state changes](https://docs.near.org/docs/api/rpc/contracts#view-contract-state-changes)
Returns the state change details of a contract based on the key prefix (encoded to base64). Pass an empty string for this param if you would like to return all state changes.

#### [By finality](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L1019-L1024)

``` java
String[] accountIds = new String[1];

accountIds[0] = "guest-book.testnet";

ContractStateChanges contractStateChanges = nearService.viewContractStateChanges(Finality.FINAL, accountIds, "");
```

#### [By height](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L1052-L1056)
``` java
String[] accountIds = new String[1];

accountIds[0] = "guest-book.testnet";

ContractStateChanges contractStateChanges = nearService.viewContractStateChanges(78441183, accountIds, "");
```
#### [By hash](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L1033-L1039)
``` java
String[] accountIds = new String[1];

accountIds[0] = "guest-book.testnet";

ContractStateChanges contractStateChanges = nearService.viewContractStateChanges("5KgQ8uu17bhUPnMUbkmciHxbpFvsbhwdkJu4ptRfR7Zn", accountIds, "");
```


#### 3.6 [View contract code changes](https://docs.near.org/docs/api/rpc/contracts#view-contract-code-changes)
Returns code changes made when deploying a contract. Change is returned is a base64 encoded WASM file.

#### [By finality](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L1083-L1087)

``` java
String[] accountIds = new String[1];

accountIds[0] = "dev-1602714453032-7566969";

ContractCodeChanges contractCodeChanges = nearService.viewContractCodeChanges(Finality.FINAL, accountIds);
```

#### [By height](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L1115-L1119)
``` java
String[] accountIds = new String[1];

accountIds[0] = "dev-1602714453032-7566969";

ContractCodeChanges contractCodeChanges = nearService.viewContractCodeChanges(78441560, accountIds);
```
#### [By hash](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L1096-L1102)
``` java
String[] accountIds = new String[1];

accountIds[0] = "dev-1602714453032-7566969";

ContractCodeChanges contractCodeChanges = nearService.viewContractCodeChanges("HpsjZvjtuxarKRsXGVrgB6qtuCcHRgx3Xof1gfT2Jfj7", accountIds);
```


#### 3.7 [Call a contract function](https://docs.near.org/docs/api/rpc/contracts#call-a-contract-function)
Allows you to call a contract method as a [view function](https://docs.near.org/docs/develop/contracts/as/intro#view-and-change-functions).

#### [By finality](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L1145-L1150)

``` java
ContractFunctionCallResult contractFunctionCallResult = nearService
        .callContractFunction(
                Finality.FINAL,
                "guest-book.testnet",
                "getMessages",
                "e30=");
```

#### [By height](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L1189-L1193)
``` java
ContractFunctionCallResult contractFunctionCallResult = nearService
        .callContractFunction(79272492,
                "guest-book.testnet",
                "getMessages",
                "e30=");
```
#### [By hash](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L1157-L1162)
``` java
ContractFunctionCallResult contractFunctionCallResult = nearService
        .callContractFunction(
                "J5QTB4Stz3iwtHvgr5KnVzNUgzm4J1bE5Et6JWrJPC8o",
                "guest-book.testnet",
                "getMessages",
                "e30=");
```


### 4. [Block / Chunk](https://docs.near.org/docs/api/rpc/block-chunk)


#### 4.1 [Block details](https://docs.near.org/docs/api/rpc/block-chunk#block-details)
Queries network and returns block for given height or hash. You can also use finality param to return latest block details.

#### [By finality](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L79)

``` java
Block block = nearService.getBlock(Finality.FINAL);
```

#### [By height](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L110)
``` java
Block block = nearService.getBlock(78770817);
```
#### [By hash](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L86)
``` java
Block block = nearService.getBlock("FXTWzPjqWztjHfneqJb9cBDB2QLTY1Rja4GHrswAv1b9");
```


#### 4.2 [Changes in Block](https://docs.near.org/docs/api/rpc/block-chunk#changes-in-block)
Returns changes in block for given block height or hash. You can also use finality param to return latest block details.

#### [By finality](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L134)

``` java
BlockChanges blockChanges = nearService.getBlockChanges(Finality.FINAL);
```

#### [By height](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L152)
``` java
BlockChanges blockChanges = nearService.getBlockChanges(78770674);
```

#### [By hash](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L141)
``` java
BlockChanges blockChanges = nearService.getBlockChanges("BmEZnrmov6h6rMPpWkMV2JtU1C5LP563V5Y5yXwUW2N5");
```


#### 4.3 [Chunk Details](https://docs.near.org/docs/api/rpc/block-chunk#chunk-details)
Returns details of a specific chunk. You can run a block details query to get a valid chunk hash.

#### [By finality](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L176)

``` java
Chunk chunk = nearService.getChunkDetails("9mdG2cRcV8Dsb1EoSjtya81NddjRB2stYCTVukZh7zzw");
```

#### [By block height and shard id](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L198)
``` java
Chunk chunk = nearService.getChunkDetails(78567387, 0);
```

#### [By block hash and shard id](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L187)
``` java
Chunk chunk = nearService.getChunkDetails("F1HXTzeYgYq28rgsHuKUrRbo5QTBGKFYG7rbxXkRZWXN", 0);
```


### 5. [Gas](https://docs.near.org/docs/api/rpc/gas)


#### 5.1 [Gas Price](https://docs.near.org/docs/api/rpc/gas#gas-price)
Returns gas price for a specific block_height or block_hash.

- Using [null] will return the most recent block's gas price.

### [Null](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L306)

``` java
GasPrice gasPrice = nearService.getGasPrice(null);
```

#### [By height](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L328)
``` java
GasPrice gasPrice = nearService.getGasPrice(78770817);
```

#### [By hash](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L317)
``` java
GasPrice gasPrice = nearService.getGasPrice("FXTWzPjqWztjHfneqJb9cBDB2QLTY1Rja4GHrswAv1b9");
```


### 6. [Protocol](https://docs.near.org/docs/api/rpc/protocol)


#### 6.1 [Genesis Config](https://docs.near.org/docs/api/rpc/protocol#genesis-config)
Returns current genesis configuration.

### [Example](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L352)

``` java
GenesisConfig genesisConfig = nearService.getGenesisConfig();
```


#### 6.2 [Protocol Config](https://docs.near.org/docs/api/rpc/protocol#protocol-config)
Returns most recent protocol configuration or a specific queried block. Useful for finding current storage and transaction costs.

#### [By finality](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L376)

``` java
ProtocolConfig protocolConfig = nearService.getProtocolConfig(Finality.FINAL);
```

#### [By height](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L398)
``` java
ProtocolConfig protocolConfig = nearService.getProtocolConfig(78770817);
```

#### [By hash](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L387)
``` java
ProtocolConfig protocolConfig = nearService.getProtocolConfig("FXTWzPjqWztjHfneqJb9cBDB2QLTY1Rja4GHrswAv1b9");
```


### 7. [Network](https://docs.near.org/docs/api/rpc/network)


#### 7.1 [Node Status](https://docs.near.org/docs/api/rpc/network#node-status)
Returns general status of a given node (sync status, nearcore node version, protocol version, etc), and the current set of validators.

### [Example](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L241)

``` java
NodeStatus nodeStatus = nearService.getNodeStatus();
```


#### 7.2 [Network Info](https://docs.near.org/docs/api/rpc/network#network-info)
Returns the current state of node network connections (active peers, transmitted data, etc.)

### [Example](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L222)

``` java
NetworkInfo networkInfo = nearService.getNetworkInfo();
```


#### 7.3 [Validation Status](https://docs.near.org/docs/api/rpc/network#validation-status)
Queries active validators on the network returning details and the state of validation on the blockchain.

### [Null](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L262)

``` java
ValidationStatus networkValidationStatus = nearService.getNetworkValidationStatus(null);
```

#### [By height](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L283-L286)
``` java
Block lastBlock = nearService.getBlock(Finality.OPTIMISTIC);

ValidationStatus networkValidationStatus = nearService.getNetworkValidationStatus(lastBlock.getHeader().getHeight());
```

#### [By hash](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L271-L274)
``` java
Block lastBlock = nearService.getBlock(Finality.FINAL);

ValidationStatus networkValidationStatus = nearService.getNetworkValidationStatus(lastBlock.getHeader().getHash());
```


### 8. [Transactions](https://docs.near.org/docs/api/rpc/transactions)


#### 8.1 [Send transaction (async)](https://docs.near.org/docs/api/rpc/transactions#send-transaction-async)
Sends a transaction and immediately returns transaction hash.

#### [Example](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/TransactionServiceTest.java#L132-L139)
``` java

String signerId = "syntifi-bob.testnet";
String receiverId = "syntifi-alice.testnet";
BigInteger amount = new BigInteger("100", 10);

EncodedHash transactionAsync = TransactionService
        .sendTransferActionAsync(nearService, signerId, receiverId, bobNearPublicKey, bobNearPrivateKey, amount);
```


#### 8.2 [Send transaction (await)](https://docs.near.org/docs/api/rpc/transactions#send-transaction-await)
Sends a transaction and waits until transaction is fully complete. (Has a 10 second timeout)

#### [Example](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/TransactionServiceTest.java#L90-L97)
``` java
String signerId = "syntifi-alice.testnet";
String receiverId = "syntifi-bob.testnet";
BigInteger amount = new BigInteger("100", 10);

TransactionAwait transactionAwait = TransactionService
        .sendTransferActionAwait(nearService, signerId, receiverId, aliceNearPublicKey, aliceNearPrivateKey, amount);
```


#### 8.3 [Transaction Status](https://docs.near.org/docs/api/rpc/transactions#transaction-status)
Queries status of a transaction by hash and returns the final transaction result.

#### [Example](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L479-L482)
``` java        
String transactionHash = "DwWUi6WbVHKTCDjVu4gmuQfryqjwTjrZ6ntRcKcGN6Gd";
String accountId = "isonar.testnet";

TransactionStatus transactionStatus = nearService.getTransactionStatus(transactionHash, accountId);
```


#### 8.4 [Transaction Status with Receipts](https://docs.near.org/docs/api/rpc/transactions#transaction-status-with-receipts)
Queries status of a transaction by hash, returning the final transaction result and details of all receipts.

#### [Example](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L510-L514)
``` java        
String transactionHash = "DwWUi6WbVHKTCDjVu4gmuQfryqjwTjrZ6ntRcKcGN6Gd";
String accountId = "isonar.testnet";

TransactionStatus transactionStatusWithReceipts = nearService.getTransactionStatusWithReceipts(transactionHash, accountId);
```


#### 8.5 [Receipt by ID](https://docs.near.org/docs/api/rpc/transactions#receipt-by-id)
Fetches a receipt by it's ID (as is, without a status or execution outcome)

#### [Example](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L542-L544)
``` java        
String receiptId = "8b9Vt1xH8DZecMda1YqUcMWA41NvknUJJVd2XEQikPRs";

Receipt transactionReceipt = nearService.getTransactionReceipt(receiptId);
```


### 9. Json File Wallets

#### 9.1 Loads a Wallet from a json file
Loads a Wallet object from a json file.

#### [Example](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/WalletServiceTest.java#L36)
``` java        
String fileName = "./my-wallet.json";

WalletService.writeWalletToFile(fileName, wallet)
```

#### 9.2 Writes a Wallet to a json file
Writes a Wallet object to a json file.

#### [Example](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/WalletServiceTest.java#L53)
``` java        
String fileName = "./my-wallet.json";

Wallet wallet = WalletService.loadWalletFromFile(fileName);
```
