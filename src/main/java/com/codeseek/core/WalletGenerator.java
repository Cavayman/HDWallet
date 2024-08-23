package com.codeseek.core;

import com.codeseek.model.Wallet;

public interface WalletGenerator {
    String SEED = "0386b00a15fe5cbbb5714a053689c226d16dbf86b34fac60c5cc76a9a36b709ee5200615f557000ee79715a67e6c62ab6861ef3a7099179dff532fad0c4a0d2d";
    String MNEMONIC = "moon call borrow staff hood catch else egg famous surround original below resist observe enact";

    Wallet generateWallet();
}
