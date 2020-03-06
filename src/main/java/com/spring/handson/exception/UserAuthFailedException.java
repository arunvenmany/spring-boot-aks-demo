package com.spring.handson.exception;

import java.util.function.Supplier;

public class UserAuthFailedException extends RuntimeException implements Supplier<UserAuthFailedException> {
    public UserAuthFailedException(String message) {
        super(message);
    }

    public UserAuthFailedException() {

    }

    @Override
    public UserAuthFailedException get() {
        return new UserAuthFailedException();
    }
}
