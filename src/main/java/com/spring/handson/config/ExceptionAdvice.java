package com.spring.handson.config;

import com.spring.handson.model.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.UncategorizedMongoDbException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

    private Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);
    @ExceptionHandler(RuntimeException.class)
    public ApiResponse handleNotFoundException(RuntimeException ex) {
        logger.error("Runtime Exception: {}",ex);

        ApiResponse apiResponse = new ApiResponse(400, "Server Error. Please check logs", null);
        return apiResponse;
    }
    @ExceptionHandler(UncategorizedMongoDbException.class)
    public ApiResponse handleMongoAccessException(RuntimeException ex) {
        logger.error("Runtime Exception: {}",ex);

        ApiResponse apiResponse = new ApiResponse(400, "Server Error due to Mongo Connectivity. Please check logs with x-transaction-id", null);
        return apiResponse;
    }

}
