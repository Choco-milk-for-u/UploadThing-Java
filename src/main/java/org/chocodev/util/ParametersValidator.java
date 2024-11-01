package org.chocodev.util;

import java.util.Set;

import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

public class ParametersValidator {
    private static final Validator validator = Validation.byDefaultProvider()
            .configure()
            .messageInterpolator(new ParameterMessageInterpolator())
            .buildValidatorFactory().getValidator();

    public static <TException extends Exception> void validate(TException Exception, Object param)
            throws TException {
        if (param == null) {
            throw Exception;
        }
        if (param instanceof String && String.valueOf(param).isBlank()) {
            throw Exception;
        }
        Set<ConstraintViolation<Object>> violations = validator.validate(param);

        if (!violations.isEmpty()) {
            throw Exception;
        }
    }

    public static <TException extends Exception> void validate(TException Exception, Object... params)
            throws TException {
        for (Object param : params) {
            validate(Exception, param);
        }
    }

    public static <TException extends Exception> void validateMany(TException Exception, Object[] params)
            throws TException {
        for (Object param : params) {
            validate(Exception, param);
        }
    }
}
