package com.spring.handson.controller;

import com.spring.handson.config.ExceptionAdvice;
import com.spring.handson.factory.UserFactory;
import com.spring.handson.service.UserService;
import com.spring.handson.util.FileUtils;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.module.mockmvc.response.MockMvcResponse;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.data.mongodb.UncategorizedMongoDbException;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static com.spring.handson.util.ServiceConstants.*;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;


    @BeforeEach
    public void setup() {
        final MockMvcBuilder mockMvcBuilder = MockMvcBuilders.standaloneSetup(userController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver()).setControllerAdvice(new ExceptionAdvice());
        RestAssuredMockMvc.standaloneSetup(mockMvcBuilder);

    }


    @Test
    public void shouldGetSuccessResponseForUserListing() throws JSONException {
        when(userService.findAll()).thenReturn(UserFactory.getUserList());
        MockMvcResponse response = RestAssuredMockMvc.given()
                .accept(APPLICATION_JSON_VALUE)
                .contentType(JSON)
                .when()
                .get(GET_USER_LISTING_ENDPOINT);

        assertAll(
                () -> assertTrue(STATUS_ERROR, response.getStatusCode() == SC_OK),
                () -> assertThat(SCHEMA_ERROR, response.body().asString(), matchesJsonSchemaInClasspath(GET_USER_LISTING_200_SCHEMA_JSON))
        );
        JSONAssert.assertEquals(response.getBody().asString(), FileUtils.readFile(GET_USER_LISTING_200_EXAMPLE_JSON), JSONCompareMode.LENIENT);
        verify(userService, times(1)).findAll();
    }

    @Test
    public void shouldGetEmptyResponseForUserListing() throws JSONException {
        when(userService.findAll()).thenReturn(new ArrayList<>());
        MockMvcResponse response = RestAssuredMockMvc.given()
                .accept(APPLICATION_JSON_VALUE)
                .contentType(JSON)
                .when()
                .get(GET_USER_LISTING_ENDPOINT);

        assertAll(
                () -> assertTrue(STATUS_ERROR, response.getStatusCode() == SC_OK),
                () -> assertThat(SCHEMA_ERROR, response.body().asString(), matchesJsonSchemaInClasspath(GET_USER_LISTING_200_SCHEMA_JSON))
        );
        JSONAssert.assertEquals(response.getBody().asString(), FileUtils.readFile(GET_USER_LISTING_200_EXAMPLE_EMPTY_JSON), JSONCompareMode.LENIENT);
        verify(userService, times(1)).findAll();
    }

    @Test
    public void shouldGetErrorResponseForUserListingForMongoError() throws JSONException {
        when(userService.findAll()).thenThrow(new UncategorizedMongoDbException("Exception While Retreiving User list from Mongo", null));
        MockMvcResponse response = RestAssuredMockMvc.given()
                .accept(APPLICATION_JSON_VALUE)
                .contentType(JSON)
                .when()
                .get(GET_USER_LISTING_ENDPOINT);

        assertAll(
                () -> assertTrue(STATUS_ERROR, response.getStatusCode() == SC_OK),
                () -> assertThat(SCHEMA_ERROR, response.body().asString(), matchesJsonSchemaInClasspath(GET_USER_LISTING_ERROR_SCHEMA_JSON))
        );
        JSONAssert.assertEquals(response.getBody().asString(), FileUtils.readFile(GET_USER_LISTING_MONGO_ERROR_JSON), JSONCompareMode.LENIENT);
        verify(userService, times(1)).findAll();
    }

    @Test
    public void shouldGetErrorResponseForUserListingForRuntimeException() throws JSONException {
        when(userService.findAll()).thenThrow(new RuntimeException("Runtime exception occured"));
        MockMvcResponse response = RestAssuredMockMvc.given()
                .accept(APPLICATION_JSON_VALUE)
                .contentType(JSON)
                .when()
                .get(GET_USER_LISTING_ENDPOINT);

        assertAll(
                () -> assertTrue(STATUS_ERROR, response.getStatusCode() == SC_OK),
                () -> assertThat(SCHEMA_ERROR, response.body().asString(), matchesJsonSchemaInClasspath(GET_USER_LISTING_ERROR_SCHEMA_JSON))
        );
        JSONAssert.assertEquals(response.getBody().asString(), FileUtils.readFile(GET_USER_LISTING_RUNTIME_ERROR_JSON), JSONCompareMode.LENIENT);
        verify(userService, times(1)).findAll();
    }

}
