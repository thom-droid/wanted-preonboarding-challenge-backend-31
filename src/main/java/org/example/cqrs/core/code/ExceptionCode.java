package org.example.cqrs.core.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ExceptionCode {

    INVALID_INPUT(5000, "잘못 입력된 데이터", HttpStatus.BAD_REQUEST),
    RESOURCE_NOT_FOUND(5001, "요청한 리소스를 찾을 수 없음", HttpStatus.NOT_FOUND),
    UNAUTHORIZED(5002, "인증되지 않은 요청", HttpStatus.UNAUTHORIZED),
    FORBIDDEN(5003, "권한이 없는 요청", HttpStatus.FORBIDDEN),
    CONFLICT(5004, "리소스 충돌 발생", HttpStatus.CONFLICT),
    INTERNAL_ERROR(5005, "서버 내부 오류", HttpStatus.INTERNAL_SERVER_ERROR),
    ;

    final int code;
    final String message;
    final HttpStatus httpStatus;

}
