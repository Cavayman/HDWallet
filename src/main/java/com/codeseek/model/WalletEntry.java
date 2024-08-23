package com.codeseek.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WalletEntry {
    private String path;
    private String address;
    private String privateKey;
    private String publicKey;
}
