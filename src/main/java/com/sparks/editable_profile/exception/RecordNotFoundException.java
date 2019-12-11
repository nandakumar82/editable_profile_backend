package com.sparks.editable_profile.exception;

/**
 * Created by Nandak on Dec, 2019
 */
public class RecordNotFoundException extends RuntimeException {

    /**
     * @param message save the message for RecordNotFound
     */
    public RecordNotFoundException(String message) {
        super(message);
    }

}
