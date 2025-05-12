package org.example.cqrs.core.exception;

import org.example.cqrs.core.response.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorResponse> handleApiException(ApiException ex) {

        ErrorResponse errorResponse = ErrorResponse.of(ex);
        return ResponseEntity.ofNullable(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleOtherException(Exception ex) {

        ErrorResponse errorResponse = ErrorResponse.of(
                new ErrorResponse.ErrorDetail(
                        ExceptionCode.INTERNAL_ERROR.getCode(),
                        ex.getMessage(),
                        null)
        );
        return ResponseEntity.ofNullable(errorResponse);
    }
}
