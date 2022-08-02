package com.skynest.uitesting.api;

import io.restassured.response.Response;

import java.net.URISyntaxException;

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
}
