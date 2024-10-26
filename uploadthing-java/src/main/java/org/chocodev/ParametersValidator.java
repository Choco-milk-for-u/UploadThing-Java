package org.chocodev;

import java.util.Set;

import org.chocodev.Error.UTApiException;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class ParametersValidator {
    private final Validator validator;

    public ParametersValidator() {
        ValidatorFactory factory = Validation.byDefaultProvider()
                .configure()
                .messageInterpolator(new ParameterMessageInterpolator())
                .buildValidatorFactory();
        validator = factory.getValidator();
    }

    public <TException extends UTApiException> void validate(TException Exception, Object param) throws TException {
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

    public <TException extends UTApiException> void validate(TException Exception, Object... params) throws TException {
        for (Object param : params) {
            validate(Exception, param);
        }
    }
}
