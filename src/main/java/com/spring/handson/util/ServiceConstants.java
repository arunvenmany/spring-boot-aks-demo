package com.spring.handson.util;

public interface ServiceConstants {


    String STATUS_ERROR = "Status is not 200 OK";

    String SCHEMA_ERROR = "Response is not matching with schema";

    String MOCK_ID_1 = "id1";

    String MOCK_USER_NAME_1 = "test-user-name-1";

    String MOCK_PASSWORD_1 = "test-password-1";

    String MOCK_FIRST_NAME_1 = "test-first-name-1";

    String MOCK_LAST_NAME_1 = "test-last-name-1";

    int MOCK_AGE_1 = 25;

    int MOCK_SALARY_1 = 20000;

    String MOCK_ID_2 = "id2";

    String MOCK_USER_NAME_2 = "test-user-name-2";

    String MOCK_PASSWORD_2 = "test-password-2";

    String MOCK_FIRST_NAME_2 = "test-first-name-2";

    String MOCK_LAST_NAME_2 = "test-last-name-2";

    int MOCK_AGE_2 = 26;

    int MOCK_SALARY_2 = 30000;

    String GET_USER_LISTING_200_SCHEMA_JSON = "user-listing.schema.json";

    String GET_USER_LISTING_200_EXAMPLE_JSON = "user-listing.example.json";

    String GET_USER_LISTING_200_EXAMPLE_EMPTY_JSON = "user-listing-empty.example.json";

    String GET_USER_LISTING_ERROR_SCHEMA_JSON = "user-listing-error.schema.json";

    String GET_USER_LISTING_MONGO_ERROR_JSON = "user-listing-mongo-error.example.json";

    String GET_USER_LISTING_RUNTIME_ERROR_JSON = "user-listing-runtime-error.example.json";

    String GET_USER_LISTING_ENDPOINT = "/users";
}
