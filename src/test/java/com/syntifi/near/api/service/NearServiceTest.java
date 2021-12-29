package com.syntifi.near.api.service;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.net.MalformedURLException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NearServiceTest {

    private static NearService nearService;
    private static Logger LOGGER = LoggerFactory.getLogger(NearServiceTest.class);

    @BeforeAll
    public static void setUp() throws MalformedURLException {
        nearService = NearService.usingPeer("rpc.testnet.near.org");
    }

    @Order(1)
    @Test
    void getLastBlock() {
        Object block = nearService.getLastBlock("final");

        assertNotNull(block);
    }
    
    @Order(2)
    @Test
    void getBlockByHash() {
        Object block = nearService 
                .getBlock("D3w7WtUDiqdgNqjDmATfogPVihhetFGmaYLCdSLoC96A");

        assertNotNull(block);
    }

    @Order(3)
    @Test
    void getBlockByHeight() {
        Object block = nearService.getBlock(76783977);

        assertNotNull(block);
    }
}
