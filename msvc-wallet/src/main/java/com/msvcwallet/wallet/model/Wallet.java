package com.msvcwallet.wallet.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "wallet")
public class Wallet {
    @Id
    private String id;
    private String identityNumber;
    private String typeDocument;
    private String phoneNumber;
    private String imeiPhone;
    private String email;
}







