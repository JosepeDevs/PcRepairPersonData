package com.josepdevs.Domain.service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.SecureRandom;

public class FileReaderService {

    /**
     * Accessor for HMAC key - Block serialization and storage as String in JVM memory
     */
    private transient byte[] keyHMAC = null;

    /**
     * Accessor for Ciphering key - Block serialization, required to de-cipher and see the original content
     */
    private transient KeysetHandle keyCiphering = null;

    /**
     * Accessor for Issuer ID - Block serialization
     */
    private transient String issuerID = null;

    /**
     * Random data generator
     */
    private SecureRandom secureRandom = new SecureRandom();

    /**
     * Handler for token ciphering two way opperation to 
     */
    private TokenCipher tokenCipher;

    /**
     * Handler for token revokation
     */
    private TokenRevoker tokenRevoker;

    /**
     * Constructor - Load keys and issuer ID
     *
     * @throws Exception If any issue occur during keys loading or DB driver loading
     */
    
    public FileReaderService() throws Exception {
    //Load keys from configuration text/json files in order to avoid to store keys as String in JVM memory
    this.keyHMAC = Files.readAllBytes(Paths.get("src", "main", "conf", "key-hmac.txt"));
    this.keyCiphering = CleartextKeysetHandle.read(JsonKeysetReader.withFile(Paths.get("src", "main", "conf", "key-ciphering.json").toFile()));

    //Load issuer ID from configuration text file
    this.issuerID = Files.readAllLines(Paths.get("src", "main", "conf", "issuer-id.txt")).get(0);

    //Init token ciphering and revokation handlers
    this.tokenCipher = new TokenCipher();
    this.tokenRevoker = new TokenRevoker();
        
    }
}
