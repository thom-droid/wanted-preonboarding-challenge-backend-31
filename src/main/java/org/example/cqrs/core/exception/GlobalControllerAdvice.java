package org.example.cqrs.core.exception;

import org.example.cqrs.core.code.ExceptionCode;
import org.example.cqrs.core.response.ErrorResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorResponse> handleApiException(ApiException ex) {

        ErrorResponse errorResponse = ErrorResponse.of(ex);

        return ResponseEntity
                .status(ex.getCode().getHttpStatus())
                .body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        Map<String, Object> details = ex.getBindingResult().getFieldErrors().stream()
                .collect(
                        Collectors.toMap(
                                FieldError::getField,
                                fieldError -> fieldError.getDefaultMessage() + " (" + fieldError.getRejectedValue() + ")"
                        )
                );

        ErrorResponse errorResponse = ErrorResponse.of(
                new ErrorResponse.ErrorDetail(
                        ExceptionCode.INVALID_INPUT.getCode(),
                        ex.getMessage(),
                        details)
        );

        return ResponseEntity
                .status(ExceptionCode.INVALID_INPUT.getHttpStatus())
                .body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleOtherException(Exception ex) {

        ErrorResponse errorResponse = ErrorResponse.of(
                new ErrorResponse.ErrorDetail(
                        ExceptionCode.INTERNAL_ERROR.getCode(),
                        ex.getMessage(),
                        null)
        );
        return ResponseEntity.internalServerError().body(errorResponse);
    }
}
