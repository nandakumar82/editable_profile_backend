package com.sparks.editable_profile.exception;

/**
 * Created by Nandak on Dec, 2019
 */
public class RecordNotFoundException extends RuntimeException {

    public RecordNotFoundException() {
        super("");
    }

    /**
     * @param message
     */
    public RecordNotFoundException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public RecordNotFoundException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public RecordNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
