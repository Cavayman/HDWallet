package com.codeseek.core;

import com.codeseek.model.Wallet;
import com.codeseek.model.WalletEntry;
import lombok.SneakyThrows;
import org.bitcoinj.core.Address;
import org.bitcoinj.core.Utils;
import org.bitcoinj.crypto.ChildNumber;
import org.bitcoinj.crypto.DeterministicKey;
import org.bitcoinj.crypto.HDKeyDerivation;
import org.bitcoinj.crypto.HDPath;
import org.bitcoinj.params.MainNetParams;
import org.bitcoinj.script.Script;
import org.bitcoinj.wallet.DeterministicKeyChain;
import org.bitcoinj.wallet.DeterministicSeed;

import java.util.List;
import java.util.stream.IntStream;

public class SolanaWalletGenerator implements WalletGenerator {

    @Override
    @SneakyThrows
    public Wallet generateWallet() {
        DeterministicSeed seed = new DeterministicSeed(MNEMONIC, Utils.HEX.decode(SEED), "", 0L);
        DeterministicKeyChain keyChain = DeterministicKeyChain.builder().seed(seed).build();

        List<WalletEntry> walletEntries = IntStream.range(0, 10)
                .mapToObj(i -> createWalletEntry(keyChain, i))
                .toList();

        DeterministicKey masterKey = keyChain.getKeyByPath(HDPath.parsePath("44H/501H/0H"), true);

        return new Wallet(
                MNEMONIC,
                Utils.HEX.decode(SEED),
                masterKey.getPrivateKeyAsHex(),
                masterKey.getPublicKeyAsHex(),
                masterKey.getChainCode(),
                walletEntries,
                masterKey.getDepth(),
                masterKey.getChildNumber().i(),
                masterKey.getParentFingerprint());
    }

    private WalletEntry createWalletEntry(DeterministicKeyChain keyChain, int index) {
        DeterministicKey masterKey = keyChain.getKeyByPath(HDPath.parsePath("44H/501H/" + index + "H/0"), true);

        DeterministicKey derivedKey = HDKeyDerivation.deriveChildKey(masterKey, new ChildNumber(0, false));
        Address address = Address.fromKey(MainNetParams.get(), derivedKey, Script.ScriptType.P2PKH);

        return new WalletEntry(
                "m/44'/501'/" + index + "'/0/0",
                address.toString(),
                derivedKey.getPrivateKeyAsHex(),
                derivedKey.getPublicKeyAsHex()
        );
    }
}
