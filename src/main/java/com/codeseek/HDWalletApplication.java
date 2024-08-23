package com.codeseek;

import com.codeseek.core.EthereumWalletGenerator;
import com.codeseek.core.SolanaWalletGenerator;
import com.codeseek.core.WalletGenerator;
import com.codeseek.model.Wallet;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.SneakyThrows;

public class HDWalletApplication {

    public static void main(String[] args) {
        WalletGenerator ethereumWalletGenerator = new EthereumWalletGenerator();
        WalletGenerator solanaWalletGenerator = new SolanaWalletGenerator();

        Wallet ethereumWallet = ethereumWalletGenerator.generateWallet();
        Wallet solanaWallet = solanaWalletGenerator.generateWallet();

        printWallet("Ethereum Wallet", ethereumWallet);
        printWallet("Solana Wallet", solanaWallet);
    }

    @SneakyThrows
    private static void printWallet(String walletName, Wallet wallet) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        System.out.println("\n===== " + walletName + " =====\n");
        System.out.println(objectMapper.writeValueAsString(wallet));
    }
}
