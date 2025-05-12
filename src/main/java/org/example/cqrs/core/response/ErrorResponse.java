package org.example.cqrs.core.response;

import org.example.cqrs.core.exception.ApiException;
import org.example.cqrs.core.exception.ExceptionCode;

import java.util.Map;

public record ErrorResponse(boolean success, ErrorDetail error) {

    public static ErrorResponse of() {
        return new ErrorResponse(false, null);
    }

    public static ErrorResponse of(ErrorDetail error) {
        return new ErrorResponse(false, error);
    }

    public static ErrorResponse of(ApiException apiException) {
        ExceptionCode code = apiException.getCode();
        return of(new ErrorDetail(code.getCode(), code.getMessage(), apiException.getDetails()));
    }

    public record ErrorDetail(int code, String message, Map<String, Object> details) {

    }

}
