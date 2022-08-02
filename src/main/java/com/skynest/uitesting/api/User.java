package com.skynest.uitesting.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    private UUID id;
    private String email;
    private String name;
    private String surname;
    private String phoneNumber;
    private String address;
    private String roleName;
    private String positionInCompany;
    private String companyName;
    private boolean verified;
    private boolean enabled;
}
