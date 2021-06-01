package com.store.exception;

public class ResourceAlreadyExist extends RuntimeException {

    public ResourceAlreadyExist(String message) {
        super("Resource with id: " + message + " already exist");
    }
}
