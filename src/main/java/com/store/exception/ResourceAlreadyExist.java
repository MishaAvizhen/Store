package com.store.exception;

public class ResourceAlreadyExist extends RuntimeException {

    public ResourceAlreadyExist(Long id) {
        super("Resource with id: " + id + " already exist");
    }

    public ResourceAlreadyExist(String message) {
        super(message);
    }
}
