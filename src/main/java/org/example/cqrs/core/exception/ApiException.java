package org.example.cqrs.core.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@Getter
@RequiredArgsConstructor
public class ApiException extends RuntimeException {

    private final ExceptionCode code;
    private final Map<String, Object> details;

}
