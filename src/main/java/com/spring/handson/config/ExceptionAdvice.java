package com.spring.handson.config;

import com.spring.handson.exception.UserAuthFailedException;
import com.spring.handson.model.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.UncategorizedMongoDbException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

    private Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);

    @ExceptionHandler(Exception.class)
    public ApiResponse handleNotFoundException(Exception ex) {
        logger.error("Runtime Exception: {}", ex);

        ApiResponse apiResponse = new ApiResponse(400, "Server Error. Please check logs", null);
        return apiResponse;
    }

    @ExceptionHandler(UncategorizedMongoDbException.class)
    public ApiResponse handleMongoAccessException(UncategorizedMongoDbException ex) {
        logger.error("Runtime Exception: {}", ex);

        ApiResponse apiResponse = new ApiResponse(400, "Server Error due to Mongo Connectivity. Please check logs with x-transaction-id", null);
        return apiResponse;
    }

    @ExceptionHandler(UserAuthFailedException.class)
    public ApiResponse handleAuthFailedException(UserAuthFailedException ex) {
        logger.error("Runtime Exception: {}", ex);

        ApiResponse apiResponse = new ApiResponse(403, "Login Failed. Please Check credentials", null);
        return apiResponse;
    }
}
