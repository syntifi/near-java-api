package com.syntifi.near.api.model.key;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.syntifi.crypto.key.encdec.Base58;
import com.syntifi.near.api.exception.NoSuchTypeException;
import com.syntifi.near.borshj.annotation.BorshField;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.lang.reflect.InvocationTargetException;

/**
 * KeySig holds information about a key or signature.
 * Also, it handles to disassemble and assemble of key and signature structure.
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.0.1
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class KeySig {

    private static final String SEPARATOR = ":";

    @BorshField(order = 1)
    @JsonIgnore
    protected KeyType type;

    @BorshField(order = 2)
    @JsonIgnore
    protected byte[] data;

    public KeySig(KeyType keyType, byte[] data) {
        this.type = keyType;
        this.data = data;
    }

    /**
     * Reads from key or signature from base-58 string
     *
     * @param encodedBase58String a string base-58 encoded with the key or signature
     * @param clazz               return class type
     * @param <T>                 the parameter return class
     * @return a PublicKey, PrivateKey or Signature object with type and data
     */
    public static <T extends KeySig> T fromEncodedBase58String(String encodedBase58String, Class<T> clazz) {
        KeyType keyType;
        byte[] data;

        String[] key = encodedBase58String.split(SEPARATOR);
        try {
            keyType = KeyType.valueOf(key[0].toUpperCase());
        } catch (Exception e) {
            throw new NoSuchTypeException(String.format("No implementation found for key type %s", key[0]), e);
        }

        data = Base58.decode(key[1]);

        try {
            return clazz.getDeclaredConstructor(KeyType.class, byte[].class).newInstance(keyType, data);
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            throw new NoSuchTypeException(String.format("Class %s instantiation failed for needed constructor", clazz.getSimpleName()), e);
        }
    }

    public String toEncodedBase58String() {
        return this.getType().toString().toLowerCase() + SEPARATOR + this;
    }

    @Override
    public String toString() {
        return Base58.encode(data);
    }
}

