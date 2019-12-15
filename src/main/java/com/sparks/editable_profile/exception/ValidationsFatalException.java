package com.sparks.editable_profile.exception;

import java.util.List;

/**
 * Created by Nandak on Dec, 2019
 */
public class ValidationsFatalException extends RuntimeException {
    private final List<String> details;

    public ValidationsFatalException(String message, Throwable cause, List<String> details) {
        super(message, cause);
        this.details = details;
    }

    public List<String> getDetails() {
        return details;
    }
}
