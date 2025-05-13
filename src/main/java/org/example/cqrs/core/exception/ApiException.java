package org.example.cqrs.core.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.cqrs.core.code.ExceptionCode;

import java.util.Map;

@Getter
@RequiredArgsConstructor
public class ApiException extends RuntimeException {

    private final ExceptionCode code;
    private final String message;
    private final Map<String, Object> details;

}
