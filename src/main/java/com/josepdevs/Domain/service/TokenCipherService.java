package com.josepdevs.Domain.service;

import jakarta.xml.bind.DatatypeConverter;

public class TokenCipherService {

/**
 * Handle ciphering and deciphering of the token using AES-GCM.
 * <p>
 * Use Google TINK to handle ciphering operations in order to use crypto built-in best practices provided by this library.
 *
 * @see "https://github.com/google/tink/blob/master/docs/JAVA-HOWTO.md"
 * @see "https://github.com/google/tink/blob/master/docs/PRIMITIVES.md#authenticated-encryption-with-associated-data"
 * @see "https://en.wikipedia.org/wiki/Galois/Counter_Mode"
 */
public class TokenCipher {
	   
	TokenCipher throws Exception{
		AeadConfig.register();
	}
}
    /**
     * Constructor - Register AEAD configuration
     *
     * @throws Exception If any issue occur during AEAD configuration registration
     */
     

    /**
     * Cipher a JWT
     *
     * @param jwt          Token to cipher
     * @param keysetHandle Pointer to the keyset handle
     * @return The ciphered version of the token encoded in HEX
     * @throws Exception If any issue occur during token ciphering operation
     */
    public String cipherToken(String jwt, KeysetHandle keysetHandle) throws Exception {
        //Verify parameters
        if (jwt == null || jwt.isEmpty() || keysetHandle == null) {
            throw new IllegalArgumentException("Both parameters must be specified !");
        }

        //Get the primitive
        Aead aead = AeadFactory.getPrimitive(keysetHandle);

        //Cipher the token
        byte[] cipheredToken = aead.encrypt(jwt.getBytes(), null);

        return DatatypeConverter.printHexBinary(cipheredToken);
    }

    /**
     * Decipher a JWT
     *
     * @param jwtInHex     Token to decipher encoded in HEX
     * @param keysetHandle Pointer to the keyset handle
     * @return The token in clear text
     * @throws Exception If any issue occur during token deciphering operation
     */
    public String decipherToken(String jwtInHex, KeysetHandle keysetHandle) throws Exception {
        //Verify parameters
        if (jwtInHex == null || jwtInHex.isEmpty() || keysetHandle == null) {
            throw new IllegalArgumentException("Both parameters must be specified !");
        }

        //Decode the ciphered token
        byte[] cipheredToken = DatatypeConverter.parseHexBinary(jwtInHex);

        //Get the primitive
        Aead aead = AeadFactory.getPrimitive(keysetHandle);

        //Decipher the token
        byte[] decipheredToken = aead.decrypt(cipheredToken, null);

        return new String(decipheredToken);
    }
}
