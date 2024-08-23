package com.codeseek.core;

import com.codeseek.model.Wallet;
import com.codeseek.model.WalletEntry;
import org.web3j.crypto.Bip32ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.utils.Numeric;

import java.util.List;
import java.util.stream.IntStream;

public class EthereumWalletGenerator implements WalletGenerator {

    @Override
    public Wallet generateWallet() {
        Bip32ECKeyPair masterKeyPair = Bip32ECKeyPair.generateKeyPair(Numeric.hexStringToByteArray(SEED));

        List<WalletEntry> walletEntries = IntStream.range(0, 10)
                .mapToObj(i -> createWalletEntry(masterKeyPair, i))
                .toList();
        return new Wallet(
                MNEMONIC,
                Numeric.hexStringToByteArray(SEED),
                Numeric.toHexStringWithPrefix(masterKeyPair.getPrivateKey()),
                Numeric.toHexStringWithPrefix(masterKeyPair.getPublicKey()),
                masterKeyPair.getChainCode(),
                walletEntries,
                masterKeyPair.getDepth(),
                masterKeyPair.getChildNumber(),
                masterKeyPair.getParentFingerprint());
    }

    private static WalletEntry createWalletEntry(Bip32ECKeyPair masterKeyPair, int index) {
        Bip32ECKeyPair derivedKeyPair = Bip32ECKeyPair.deriveKeyPair(masterKeyPair, derivePath(index));

        return new WalletEntry(
                "m/44'/60'/" + index + "'/0/0",
                Keys.toChecksumAddress("0x" + Keys.getAddress(derivedKeyPair)),
                Numeric.toHexStringWithPrefix(derivedKeyPair.getPrivateKey()),
                Numeric.toHexStringWithPrefix(derivedKeyPair.getPublicKey())
        );
    }

    private static int[] derivePath(int index) {
        return new int[]{
                44 | Bip32ECKeyPair.HARDENED_BIT,
                60 | Bip32ECKeyPair.HARDENED_BIT,
                index | Bip32ECKeyPair.HARDENED_BIT,
                0,
                0
        };
    }
}
