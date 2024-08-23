package com.codeseek.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Wallet {
    private String mnemonicPhrase;
    private byte[] seed;
    private String masterPrivateKey;
    private String masterPublicKey;
    private byte[] chainCode;
    private int depth;
    private int childNumber;
    private int parentFingerprint;
    private List<WalletEntry> walletEntries;

    public Wallet(String mnemonicPhrase, byte[] seed, String masterPrivateKey, String masterPublicKey, byte[] chainCode,
                  List<WalletEntry> walletEntries, int depth, int childNumber, int parentFingerprint) {
        this.mnemonicPhrase = mnemonicPhrase;
        this.seed = seed;
        this.masterPrivateKey = masterPrivateKey;
        this.masterPublicKey = masterPublicKey;
        this.chainCode = chainCode;
        this.walletEntries = walletEntries;
        this.depth = depth;
        this.childNumber = childNumber;
        this.parentFingerprint = parentFingerprint;
    }
}
