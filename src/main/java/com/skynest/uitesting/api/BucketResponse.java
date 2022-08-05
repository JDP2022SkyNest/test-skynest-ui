package com.skynest.uitesting.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BucketResponse {
    private UUID bucketId;
    private UUID createdById;
    private String name;
    private UUID companyId;
    private String description;
    private int size;
    private Boolean isPublic;
    private String deletedOn;
    private List<String> tags;
}
