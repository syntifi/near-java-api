package com.syntifi.near.api.rpc.service;

import com.syntifi.crypto.key.Ed25519PrivateKey;
import com.syntifi.crypto.key.Ed25519PublicKey;
import com.syntifi.near.api.common.model.key.KeyType;
import com.syntifi.near.api.common.model.key.PrivateKey;
import com.syntifi.near.api.common.model.key.PublicKey;

import java.io.IOException;

/**
 * Key service provides methods to easily work with private and public keys
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.2.0
 */
public class KeyService {

    /**
     * Returns a private key derived from a hierarchical deterministic key
     *
     * @param seed seed byte array
     * @return Private Key
     * @throws IOException from crypto-keys library
     */
    public static PrivateKey deriveFromSeed(byte[] seed) throws IOException {
        Ed25519PrivateKey pk = Ed25519PrivateKey.deriveFromSeed(seed, new int[]{44, 397, 0});
        PrivateKey privateKey = new PrivateKey();
        privateKey.setData(pk.getKey());
        privateKey.setType(KeyType.ED25519);
        return privateKey;
    }

    /**
     * Returns a private key generated using secure random
     *
     * @return private key
     */
    public static PrivateKey deriveRandomKey() {
        Ed25519PrivateKey pk = Ed25519PrivateKey.deriveRandomKey();
        PrivateKey privateKey = new PrivateKey();
        privateKey.setData(pk.getKey());
        privateKey.setType(KeyType.ED25519);
        return privateKey;
    }

    /**
     * Returns a public key generated form the private key
     *
     * @param privateKey private key
     * @return public key
     */
    public static PublicKey derivePublicKey(PrivateKey privateKey) {
        Ed25519PublicKey pk = (Ed25519PublicKey) privateKey.getPrivateKey().derivePublicKey();
        PublicKey publicKey = new PublicKey();
        publicKey.setData(pk.getKey());
        publicKey.setType(KeyType.ED25519);
        return publicKey;
    }
}
