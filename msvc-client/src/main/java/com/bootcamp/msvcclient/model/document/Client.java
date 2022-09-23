package com.bootcamp.msvcclient.model.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "client")
public class Client {

    @Id
    private String id;

    private String name;
    private String identityType;
    private String identityNumber;
    private String segmentType;
    private String customerType;
    private String address;
    private String phoneNumber;

    private List<Object> holderList;
    private List<Object> signatoryList;

}
