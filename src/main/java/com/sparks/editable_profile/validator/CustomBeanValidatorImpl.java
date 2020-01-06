package com.sparks.editable_profile.validator;

import com.sparks.editable_profile.exception.ValidationsFatalException;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Nandak on Dec, 2019
 */
@Component
public class CustomBeanValidatorImpl implements CustomBeanValidator {
    ValidatorFactory validatorFactory;

    public CustomBeanValidatorImpl() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
    }

    @Override
    public <T> void validateFields(T object) throws ValidationsFatalException {
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<T>> failedValidations = validator.validate(object);

        if (!failedValidations.isEmpty()) {
            List<String> allErrors = failedValidations.stream().map(failure -> failure.getMessage())
                    .collect(Collectors.toList());
            throw new ValidationsFatalException("Validation failure; Invalid request.",null, allErrors);
        }
    }
}
