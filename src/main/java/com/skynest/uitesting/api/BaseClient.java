package com.skynest.uitesting.api;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.internal.ResponseParserRegistrar;
import io.restassured.internal.ResponseSpecificationImpl;
import io.restassured.internal.TestSpecificationImpl;
import io.restassured.internal.log.LogRepository;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import lombok.Getter;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import static org.apache.http.HttpHeaders.AUTHORIZATION;

public class BaseClient {
    private final URI baseUri;
    private final RestAssuredConfig restAssuredConfig;
    private final LogRepository logRepository = new LogRepository();
    @Getter private String authToken;

    protected BaseClient(String baseUrl) throws URISyntaxException {
        baseUri = new URI(baseUrl);

        ObjectMapper objectMapper = new ObjectMapper()
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        ObjectMapperConfig objectMapperConfig = new ObjectMapperConfig()
                .jackson2ObjectMapperFactory((aClass, s) -> objectMapper);

        LogConfig logConfig = LogConfig.logConfig()
                .enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL);

        restAssuredConfig = RestAssuredConfig.config()
                .objectMapperConfig(objectMapperConfig)
                .logConfig(logConfig);
    }

    protected void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    private RequestSpecification createDefaultRequestSpec() {
        String initialPath = "";
        return new RequestSpecificationImpl(
                baseUri.toString(),
                baseUri.getPort(),
                initialPath,
                RestAssured.DEFAULT_AUTH,
                new ArrayList<>(),
                null,
                RestAssured.DEFAULT_URL_ENCODING_ENABLED,
                restAssuredConfig,
                logRepository,
                null,
                true);
    }

    private ResponseSpecification createDefaultResponseSpec() {
        ResponseParserRegistrar responseParserRegistrar = new ResponseParserRegistrar();
        return new ResponseSpecificationImpl(
                RestAssured.DEFAULT_BODY_ROOT_PATH,
                null,
                responseParserRegistrar,
                restAssuredConfig,
                logRepository);
    }

    private TestSpecificationImpl createDefaultTestSpec() {
        return new TestSpecificationImpl(createDefaultRequestSpec(), createDefaultResponseSpec());
    }

    protected RequestSpecification requestMaker() {

        RequestSpecification requestSpec = createDefaultTestSpec().getRequestSpecification()
                .contentType(ContentType.JSON)
                .filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

        if (authToken != null) {
            requestSpec.header(AUTHORIZATION, authToken);
        }

        return requestSpec;
    }
}
