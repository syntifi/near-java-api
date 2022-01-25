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
```
Coming soon!
```

## References
This project used the following references:

- Official docs @ [docs.near.org](https://docs.near.org/docs/api/rpc/)

## How to

### 1. [Set-up a connection](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L67-L70)

``` java
nearService = NearService.usingPeer("archival-rpc.testnet.near.org");
```


### 2. [Access Keys](https://docs.near.org/docs/api/rpc/access-keys#access-keys)


#### 2.1 [View access key](https://docs.near.org/docs/api/rpc/access-keys#view-access-key)
Returns information about a single access key for given account.

If permission of the key is FunctionCall, it will return more details such as the allowance, receiver_id, and method_names.

#### [By finality](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L594-L597)

``` java
String accountId = "client.chainlink.testnet";
String publicKey = "ed25519:H9k5eiU4xXS3M4z8HzKJSLaZdqGdGwBG49o7orNC4eZW";

AccessKey accessKey = nearService.viewAccessKey(Finality.FINAL, accountId, publicKey);
```

#### [By height](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L619-L622)
``` java
String accountId = "client.chainlink.testnet";
String publicKey = "ed25519:H9k5eiU4xXS3M4z8HzKJSLaZdqGdGwBG49o7orNC4eZW";

AccessKey accessKey = nearService.viewAccessKey(78443365, accountId, publicKey);
```
#### [By hash](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L604-L608)
``` java
String accountId = "client.chainlink.testnet";
String publicKey = "ed25519:H9k5eiU4xXS3M4z8HzKJSLaZdqGdGwBG49o7orNC4eZW";

AccessKey accessKey = nearService.viewAccessKey("8bVg8wugs2QHqXr42oEsCYyH7jvR9pLaAP35dFqx2evU", accountId, publicKey);
```


#### 2.2 [View access key list](https://docs.near.org/docs/api/rpc/access-keys#view-access-key-list)
Returns all access keys for a given account.

#### [By finality](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L646-L648)

``` java
String accountId = "client.chainlink.testnet";

AccessKeyList accessKeyList = nearService.viewAccessKeyList(Finality.FINAL, accountId);
```

#### [By height](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L669-L671)
``` java
String accountId = "client.chainlink.testnet";

AccessKeyList accessKeyList = nearService.viewAccessKeyList(78772585, accountId);
```
#### [By hash](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L655-L658)
``` java
String accountId = "client.chainlink.testnet";

AccessKeyList accessKeyList = nearService.viewAccessKeyList("DwFpDPiQXBaX6Vw3aKazQ4nXjgzw1uk6XpUkfTSJrbXf", accountId);
```


#### 2.3 [View access key changes (single)](https://docs.near.org/docs/api/rpc/access-keys#view-access-key-changes-single)
Returns individual access key changes in a specific block. You can query multiple keys by passing an array of objects containing the account_id and public_key.

#### [By finality](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L697-L702)

``` java
Key[] keys = new Key[1];

Key key0 = new Key("example-acct.testnet", "ed25519:25KEc7t7MQohAJ4EDThd2vkksKkwangnuJFzcoiXj9oM");
keys[0] = key0;

AccessKeyChanges accessKeyChanges = nearService.viewSingleAccessKeyChanges(Finality.FINAL, keys);
```

#### [By height](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L727-L732)
``` java
Key[] keys = new Key[1];

Key key0 = new Key("example-acct.testnet", "ed25519:25KEc7t7MQohAJ4EDThd2vkksKkwangnuJFzcoiXj9oM");
keys[0] = key0;

AccessKeyChanges accessKeyChanges = nearService.viewSingleAccessKeyChanges(78433961, keys);
```
#### [By hash](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L711-L718)
``` java
Key[] keys = new Key[1];

Key key0 = new Key("example-acct.testnet", "ed25519:25KEc7t7MQohAJ4EDThd2vkksKkwangnuJFzcoiXj9oM");
keys[0] = key0;

AccessKeyChanges accessKeyChanges = nearService.viewSingleAccessKeyChanges("Cr82U81VqHgCz9LzZjPivh9t16e8es6aFCv9qvDMMH88", keys);
```


#### 2.4 [View access key changes (all)](https://docs.near.org/docs/api/rpc/access-keys#view-access-key-changes-all)
Returns changes to all access keys of a specific block. Multiple accounts can be quereied by passing an array of account_ids.

#### [By finality](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L772-L776)

``` java
String[] accountIds = new String[1];

accountIds[0] = "client.chainlink.testnet";

AccessKeyChanges accessKeyChanges = nearService.viewAllAccessKeyChanges(Finality.FINAL, accountIds);
```

#### [By height](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L800-L804)
``` java
String[] accountIds = new String[1];

accountIds[0] = "client.chainlink.testnet";

AccessKeyChanges accessKeyChanges = nearService.viewAllAccessKeyChanges(78433518, accountIds);
```
#### [By hash](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L785-L791)
``` java
String[] accountIds = new String[1];

accountIds[0] = "client.chainlink.testnet";

AccessKeyChanges accessKeyChanges = nearService.viewAllAccessKeyChanges("Ais9kPbHvk6XmEYptoEpBtyFW77V16TZNHHnYtmXWr1d",accountIds);
```


### 3. [Accounts / Contracts](https://docs.near.org/docs/api/rpc/contracts)


#### 3.1 [View account](https://docs.near.org/docs/api/rpc/contracts#view-account)
Returns basic account information.

#### [By finality](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L824)

``` java
Account account = nearService.viewAccount(Finality.FINAL, "nearkat.testnet");
```

#### [By height](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L842)
``` java
Account account = nearService.viewAccount(78439658, "nearkat.testnet");
```
#### [By hash](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L831)
``` java
Account account = nearService.viewAccount("5hyGx7LiGaeRiAN4RrKcGomi1QXHqZwKXFQf6xTmvUgb", "nearkat.testnet");
```


#### 3.2 [View account changes](https://docs.near.org/docs/api/rpc/contracts#view-account-changes)
Returns account changes from transactions in a given account.

#### [By finality](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L868-L872)

``` java
String[] accountIds = new String[1];

accountIds[0] = "nearkat.testnet";

AccountChanges accountChanges = nearService.viewAccountChanges(Finality.FINAL, accountIds);
```

#### [By height](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L899-L903)
``` java
String[] accountIds = new String[1];

accountIds[0] = "nearkat.testnet";

AccountChanges accountChanges = nearService.viewAccountChanges(78440142, accountIds);
```
#### [By hash](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L881-L886)
``` java
String[] accountIds = new String[1];

accountIds[0] = "nearkat.testnet";

AccountChanges accountChanges = nearService.viewAccountChanges("7vWp2djKLoJ3RE1sr8RzSKQtyzKpe2wZ7NCcDuFNuL7j", accountIds);
```


#### 3.3 [View contract code](https://docs.near.org/docs/api/rpc/contracts#view-contract-code)
Returns the contract code (Wasm binary) deployed to the account. Please note that the returned code will be encoded in base64.

#### [By finality](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L927)

``` java
ContractCode contractCode = nearService.viewContractCode(Finality.FINAL, "guest-book.testnet");
```

#### [By height](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L946)
``` java
ContractCode contractCode = nearService.viewContractCode(78440518, "guest-book.testnet");
```
#### [By hash](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L934-L935)
``` java
ContractCode contractCode = nearService.viewContractCode("uLxyauKPhSk1tebYKi3k69pHSaT2ZLzWy4JwtGm52pu", "guest-book.testnet");
```


#### 3.4 [View contract state](https://docs.near.org/docs/api/rpc/contracts#view-contract-state)
Returns the state (key value pairs) of a contract based on the key prefix (base64 encoded). Pass an empty string for prefix_base64 if you would like to return the entire state. Please note that the returned state will be base64 encoded as well.

#### [By finality](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L973-L974)

``` java
ContractState contractState = nearService.viewContractState(Finality.FINAL, "guest-book.testnet", "");
```

#### [By height](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L1000-L1001)
``` java
ContractState contractState = nearService.viewContractState(78440679, "guest-book.testnet", "");
```
#### [By hash](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L984-L985)
``` java
ContractState contractState = nearService.viewContractState("342bkjvnzoZ7FGRE5BwDVkzSRUYXAScTz3GsDB9sEHXg", "guest-book.testnet", "");
```


#### 3.5 [View contract state changes](https://docs.near.org/docs/api/rpc/contracts#view-contract-state-changes)
Returns the state change details of a contract based on the key prefix (encoded to base64). Pass an empty string for this param if you would like to return all state changes.

#### [By finality](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L1029-L1034)

``` java
String[] accountIds = new String[1];

accountIds[0] = "guest-book.testnet";

ContractStateChanges contractStateChanges = nearService.viewContractStateChanges(Finality.FINAL, accountIds, "");
```

#### [By height](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L1062-L1066)
``` java
String[] accountIds = new String[1];

accountIds[0] = "guest-book.testnet";

ContractStateChanges contractStateChanges = nearService.viewContractStateChanges(78441183, accountIds, "");
```
#### [By hash](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L1043-L1049)
``` java
String[] accountIds = new String[1];

accountIds[0] = "guest-book.testnet";

ContractStateChanges contractStateChanges = nearService.viewContractStateChanges("5KgQ8uu17bhUPnMUbkmciHxbpFvsbhwdkJu4ptRfR7Zn", accountIds, "");
```


#### 3.6 [View contract code changes](https://docs.near.org/docs/api/rpc/contracts#view-contract-code-changes)
Returns code changes made when deploying a contract. Change is returned is a base64 encoded WASM file.

#### [By finality](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L1093-L1097)

``` java
String[] accountIds = new String[1];

accountIds[0] = "dev-1602714453032-7566969";

ContractCodeChanges contractCodeChanges = nearService.viewContractCodeChanges(Finality.FINAL, accountIds);
```

#### [By height](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L1125-L1129)
``` java
String[] accountIds = new String[1];

accountIds[0] = "dev-1602714453032-7566969";

ContractCodeChanges contractCodeChanges = nearService.viewContractCodeChanges(78441560, accountIds);
```
#### [By hash](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L1106-L1112)
``` java
String[] accountIds = new String[1];

accountIds[0] = "dev-1602714453032-7566969";

ContractCodeChanges contractCodeChanges = nearService.viewContractCodeChanges("HpsjZvjtuxarKRsXGVrgB6qtuCcHRgx3Xof1gfT2Jfj7", accountIds);
```


#### 3.7 [Call a contract function](https://docs.near.org/docs/api/rpc/contracts#call-a-contract-function)
Allows you to call a contract method as a [view function](https://docs.near.org/docs/develop/contracts/as/intro#view-and-change-functions).

#### [By finality](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L1155-L1160)

``` java
ContractFunctionCallResult contractFunctionCallResult = nearService
        .callContractFunction(
                Finality.FINAL,
                "guest-book.testnet",
                "getMessages",
                "e30=");
```

#### [By height](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L1199-L1203)
``` java
ContractFunctionCallResult contractFunctionCallResult = nearService
        .callContractFunction(79272492,
                "guest-book.testnet",
                "getMessages",
                "e30=");
```
#### [By hash](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L1167-L1172)
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

#### [By finality](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L88)

``` java
Block block = nearService.getBlock(Finality.FINAL);
```

#### [By height](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L106)
``` java
Block block = nearService.getBlock("BCEqCXnKijpvQfTMJDn6Bh2We1v1sAZoihApTnJsd32B");
```
#### [By hash](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L95)
``` java
Block block = nearService.getBlock("FXTWzPjqWztjHfneqJb9cBDB2QLTY1Rja4GHrswAv1b9");
```


#### 4.2 [Changes in Block](https://docs.near.org/docs/api/rpc/block-chunk#changes-in-block)
Returns changes in block for given block height or hash. You can also use finality param to return latest block details.

#### [By finality](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L143)

``` java
BlockChanges blockChanges = nearService.getBlockChanges(Finality.FINAL);
```

#### [By height](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L161)
``` java
BlockChanges blockChanges = nearService.getBlockChanges(78770674);
```

#### [By hash](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L150)
``` java
BlockChanges blockChanges = nearService.getBlockChanges("BmEZnrmov6h6rMPpWkMV2JtU1C5LP563V5Y5yXwUW2N5");
```


#### 4.3 [Chunk Details](https://docs.near.org/docs/api/rpc/block-chunk#chunk-details)
Returns details of a specific chunk. You can run a block details query to get a valid chunk hash.

#### [By finality](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L185)

``` java
Chunk chunk = nearService.getChunkDetails("9mdG2cRcV8Dsb1EoSjtya81NddjRB2stYCTVukZh7zzw");
```

#### [By block height and shard id](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L207)
``` java
Chunk chunk = nearService.getChunkDetails(78567387, 0);
```

#### [By block hash and shard id](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L196)
``` java
Chunk chunk = nearService.getChunkDetails("F1HXTzeYgYq28rgsHuKUrRbo5QTBGKFYG7rbxXkRZWXN", 0);
```


### 5. [Gas](https://docs.near.org/docs/api/rpc/gas)


#### 5.1 [Gas Price](https://docs.near.org/docs/api/rpc/gas#gas-price)
Returns gas price for a specific block_height or block_hash.

- Using [null] will return the most recent block's gas price.

### [Null](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L315)

``` java
GasPrice gasPrice = nearService.getGasPrice(null);
```

#### [By height](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L337)
``` java
GasPrice gasPrice = nearService.getGasPrice(78770817);
```

#### [By hash](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L326)
``` java
GasPrice gasPrice = nearService.getGasPrice("FXTWzPjqWztjHfneqJb9cBDB2QLTY1Rja4GHrswAv1b9");
```


### 6. [Protocol](https://docs.near.org/docs/api/rpc/protocol)


#### 6.1 [Genesis Config](https://docs.near.org/docs/api/rpc/protocol#genesis-config)
Returns current genesis configuration.

### [Example](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L361)

``` java
GenesisConfig genesisConfig = nearService.getGenesisConfig();
```


#### 6.2 [Protocol Config](https://docs.near.org/docs/api/rpc/protocol#protocol-config)
Returns most recent protocol configuration or a specific queried block. Useful for finding current storage and transaction costs.

#### [By finality](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L385)

``` java
ProtocolConfig protocolConfig = nearService.getProtocolConfig(Finality.FINAL);
```

#### [By height](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L407)
``` java
ProtocolConfig protocolConfig = nearService.getProtocolConfig(78770817);
```

#### [By hash](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L396)
``` java
ProtocolConfig protocolConfig = nearService.getProtocolConfig("FXTWzPjqWztjHfneqJb9cBDB2QLTY1Rja4GHrswAv1b9");
```


### 7. [Network](https://docs.near.org/docs/api/rpc/network)


#### 7.1 [Node Status](https://docs.near.org/docs/api/rpc/network#node-status)
Returns general status of a given node (sync status, nearcore node version, protocol version, etc), and the current set of validators.

### [Example](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L251)

``` java
NodeStatus nodeStatus = nearService.getNodeStatus();
```


#### 7.2 [Network Info](https://docs.near.org/docs/api/rpc/network#network-info)
Returns the current state of node network connections (active peers, transmitted data, etc.)

### [Example](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L231)

``` java
NetworkInfo networkInfo = nearService.getNetworkInfo();
```


#### 7.3 [Validation Status](https://docs.near.org/docs/api/rpc/network#validation-status)
Queries active validators on the network returning details and the state of validation on the blockchain.

### [Null](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L271)

``` java
ValidationStatus networkValidationStatus = nearService.getNetworkValidationStatus(null);
```

#### [By height](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L292-L295)
``` java
Block lastBlock = nearService.getBlock(Finality.OPTIMISTIC);

ValidationStatus networkValidationStatus = nearService.getNetworkValidationStatus(lastBlock.getHeader().getHeight());
```

#### [By hash](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L280-L283)
``` java
Block lastBlock = nearService.getBlock(Finality.FINAL);

ValidationStatus networkValidationStatus = nearService.getNetworkValidationStatus(lastBlock.getHeader().getHash());
```


### 8. [Transactions](https://docs.near.org/docs/api/rpc/transactions)


#### 8.1 [Send transaction (async)](https://docs.near.org/docs/api/rpc/transactions#send-transaction-async)
Sends a transaction and immediately returns transaction hash.

#### [Example](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L432-L435)
``` java
String signedTransaction = "DgAAAHNlbmRlci5...Mfga8RlbysuQ8D8LlA6bQE=";

String transactionHash = nearService.sendTransactionAsync(signedTransaction);
```


#### 8.2 [Send transaction (await)](https://docs.near.org/docs/api/rpc/transactions#send-transaction-await)
Sends a transaction and waits until transaction is fully complete. (Has a 10 second timeout)

#### [Example](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L459-L461)
``` java
String signedTransaction = "DgAAAHNlbmRlci5...Mfga8RlbysuQ8D8LlA6bQE=";

TransactionAwait transaction = nearService.sendTransactionAwait(signedTransaction);
```


#### 8.3 [Transaction Status](https://docs.near.org/docs/api/rpc/transactions#transaction-status)
Queries status of a transaction by hash and returns the final transaction result.

#### [Example](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L488-L491)
``` java        
String transactionHash = "DwWUi6WbVHKTCDjVu4gmuQfryqjwTjrZ6ntRcKcGN6Gd";
String accountId = "isonar.testnet";

TransactionStatus transactionStatus = nearService.getTransactionStatus(transactionHash, accountId);
```


#### 8.4 [Transaction Status with Receipts](https://docs.near.org/docs/api/rpc/transactions#transaction-status-with-receipts)
Queries status of a transaction by hash, returning the final transaction result and details of all receipts.

#### [Example](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L519-L523)
``` java        
String transactionHash = "DwWUi6WbVHKTCDjVu4gmuQfryqjwTjrZ6ntRcKcGN6Gd";
String accountId = "isonar.testnet";

TransactionStatus transactionStatusWithReceipts = nearService.getTransactionStatusWithReceipts(transactionHash, accountId);
```


#### 8.5 [Receipt by ID](https://docs.near.org/docs/api/rpc/transactions#receipt-by-id)
Fetches a receipt by it's ID (as is, without a status or execution outcome)

#### [Example](https://github.com/syntifi/near-java-api/blob/main/src/test/java/com/syntifi/near/api/service/NearServiceTest.java#L551-L553)
``` java        
String receiptId = "8b9Vt1xH8DZecMda1YqUcMWA41NvknUJJVd2XEQikPRs";

Receipt transactionReceipt = nearService.getTransactionReceipt(receiptId);
```