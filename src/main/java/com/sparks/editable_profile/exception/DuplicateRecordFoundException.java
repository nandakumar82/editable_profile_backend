package com.sparks.editable_profile.exception;

/**
 * Created by Nandak on Dec, 2019
 */
public class DuplicateRecordFoundException extends RuntimeException {

    /**
     * @param message save the message for Duplicate Record
     */
    public DuplicateRecordFoundException(String message) {
        super(message);
    }

}
