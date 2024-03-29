package com.syntifi.near.api.rpc.service.nft;

import com.syntifi.near.api.common.key.AbstractKeyTest;
import com.syntifi.near.api.common.model.key.PrivateKey;
import com.syntifi.near.api.common.model.key.PublicKey;
import com.syntifi.near.api.rpc.model.transaction.SuccessValueStatus;
import com.syntifi.near.api.rpc.model.transaction.TransactionAwait;
import com.syntifi.near.api.rpc.service.contract.common.ContractClient;
import com.syntifi.near.api.rpc.service.contract.common.ContractMethodProxyClient;
import com.syntifi.near.api.rpc.service.contract.common.FunctionCallResult;
import com.syntifi.near.api.rpc.service.contract.nft.NFTService;
import com.syntifi.near.api.rpc.service.contract.nft.model.NFTContract;
import com.syntifi.near.api.rpc.service.contract.nft.model.NFTToken;
import com.syntifi.near.api.rpc.service.contract.nft.model.NFTTokenList;
import com.syntifi.near.api.rpc.service.contract.nft.model.NFTTokenMediaURL;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;

import static com.syntifi.near.api.rpc.NearClientArchivalNetHelper.nearClient;
import static org.junit.jupiter.api.Assertions.*;

public class NFTServiceTest extends AbstractKeyTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(NFTServiceTest.class);

    private static final NFTService service = ContractClient.createClientProxy(NFTService.class, new ContractMethodProxyClient());

    // INFO: There must be a delay for the blockchain to process each call
    @BeforeEach
    void wait_for_network() throws InterruptedException {
        Thread.sleep(1000);
    }

    @Test
    void callContractFunction_NFTContractFunctionCall_forTotalSupply_return_list() {
        FunctionCallResult<String> result = service.getTotalSupply(nearClient,
                "paras-token-v2.testnet");

        assertNotNull(result.getResult());
    }

    //TODO: CHECK THIS TEST, GETTING AN ERROR ON GasLimit
    /*@Test
    void callContractFunction_NFTContractFunctionCall_forTokens_return_list() {
        FunctionCallResult<JsonNode> result = service.getTokens(nearClient,
                "deadmau55.mintspace2.testnet", "1");

        assertNotNull(result.getResult());
    }*/

    @Test
    void callContractFunction_NFTContractFunctionCall_forTokensForOwner_return_list() {
        // Other contracts to test
        // deadmau55.mintspace2.testnet
        // bananafratclub.mintspace2.testnet
        // paras-token-v2.testnet
        NFTContract contract = new NFTContract("paras-token-v2.testnet");

        contract.setMetadata(service.getMetadata(nearClient, contract.getContractId()));

        FunctionCallResult<NFTTokenList> tokenList = service.getTokensForOwner(nearClient,
                contract.getContractId(), "wallet-test.testnet", "1");

        LOGGER.debug("{}", tokenList.getContractFunctionCallResult().getResult());
        for (NFTToken token : tokenList.getResult()) {
            LOGGER.debug("Token: {}", token);
            assertNotNull(token);
            assertNotNull(token.getTokenId());

            assertDoesNotThrow(() -> token.getMediaOrReferenceURL(contract));
            NFTTokenMediaURL nftTokenMediaURL = token.getMediaOrReferenceURL(contract);
            LOGGER.debug("Token Media: {} of type {}", nftTokenMediaURL.getUrl().toString(), nftTokenMediaURL.getType().toString());
            if (nftTokenMediaURL.getUrl() == null) {
                assertEquals(NFTTokenMediaURL.Type.EMPTY, nftTokenMediaURL.getType());
            } else {
                assertNotEquals(NFTTokenMediaURL.Type.EMPTY, nftTokenMediaURL.getType());
            }
        }
    }

    @Test
    void callContractFunction_NFTContractFunctionCall_forTokensForOwner_with_limit_return_list() {
        // Other contracts to test
        // deadmau55.mintspace2.testnet
        // bananafratclub.mintspace2.testnet
        // paras-token-v2.testnet
        NFTContract contract = new NFTContract("paras-token-v2.testnet");

        contract.setMetadata(service.getMetadata(nearClient, contract.getContractId()));

        FunctionCallResult<NFTTokenList> tokenList = service.getTokensForOwner(nearClient,
                contract.getContractId(), "wallet-test.testnet", "0", 1);

        LOGGER.debug("{}", tokenList.getContractFunctionCallResult().getResult());
        for (NFTToken token : tokenList.getResult()) {
            LOGGER.debug("Token: {}", token);
            assertNotNull(token);
            assertNotNull(token.getTokenId());

            assertDoesNotThrow(() -> token.getMediaOrReferenceURL(contract));
            NFTTokenMediaURL nftTokenMediaURL = token.getMediaOrReferenceURL(contract);
            LOGGER.debug("Token Media: {} of type {}", nftTokenMediaURL.getUrl().toString(), nftTokenMediaURL.getType().toString());
            if (nftTokenMediaURL.getUrl() == null) {
                assertEquals(NFTTokenMediaURL.Type.EMPTY, nftTokenMediaURL.getType());
            } else {
                assertNotEquals(NFTTokenMediaURL.Type.EMPTY, nftTokenMediaURL.getType());
            }
        }
    }

    @Test
    void callContractFunction_NFTContractFunctionCall_forMetadata_return_list() {
        NFTContract contract = new NFTContract("paras-token-v2.testnet");
        contract.setMetadata(service.getMetadata(nearClient, contract.getContractId()));

        LOGGER.debug("{}", contract.getMetadata().getResult());
    }

    @Test
    void callContractFunction_NFTContractFunctionCall_forSupplyForOwner_return_list() {
        FunctionCallResult<String> result = service.getSupplyForOwner(nearClient,
                "paras-token-v2.testnet", "wallet-test.testnet");

        LOGGER.debug("{}", result.getResult());
    }

    @Test
    @Disabled("Improve this test to go round with test NFT")
    void callContractFunction_NFTContractFunctionCall_transfer() {
        String nftContract = "bananafratclub.mintspace2.testnet";
        String receiverAccountId = "syntifi-alice.testnet";
        String accountId = "syntifi-bob.testnet";
        String tokenId = "235";
        PrivateKey privateKey = bobNearPrivateKey;
        PublicKey publicKey = bobNearPublicKey;
        BigInteger deposit = BigInteger.ONE; // one yocto

        TransactionAwait transactionAwait = service.callTransfer(nearClient, nftContract, receiverAccountId, tokenId, accountId, publicKey, privateKey, deposit);

        assertInstanceOf(SuccessValueStatus.class, transactionAwait.getStatus());
    }
}
