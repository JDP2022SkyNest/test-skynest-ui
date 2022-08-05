package com.skynest.uitesting.api;

import com.skynest.uitesting.models.Bucket;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.File;
import java.net.URISyntaxException;
import java.util.UUID;

import static org.apache.http.HttpHeaders.AUTHORIZATION;

public class ApiClient extends BaseClient {

    public ApiClient(String targetDomain) throws URISyntaxException {
        super(targetDomain);
    }

    public Response login(LoginRequest loginRequest) {
        Response response = requestMaker()
                .body(loginRequest)
                .post("/public/login");
        setAuthToken(response.getHeader(AUTHORIZATION));
        return response;
    }

    public Response getAllUsers() {
        return requestMaker()
                .get("/users");
    }

    public BucketResponse createBucket(Bucket bucket) {
        Response response = requestMaker()
                .body(bucket)
                .post("/buckets");

        return response.as(BucketResponse.class);
    }

    public Response uploadTestFileToBucket(UUID bucketId) {
        return requestMaker()
                .contentType(ContentType.MULTIPART)
                .multiPart("file", new File("src/test/resources/upload_test_file.txt"))
                .pathParam("bucketId", bucketId)
                .post( "/files/bucket/{bucketId}");
    }
}
