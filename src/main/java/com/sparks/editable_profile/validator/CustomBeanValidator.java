package com.sparks.editable_profile.validator;

/**
 * Created by Nandak on Dec, 2019
 */
public interface CustomBeanValidator {
    /**
     * Validate all annotated fields of a DTO object and collect all the validation and then throw them all at once.
     *
     * @param object
     */
    <T> void validateFields(T object);
}
