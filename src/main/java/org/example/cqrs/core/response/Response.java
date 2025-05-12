package org.example.cqrs.core.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Response {

    SUCCESS(1, "요청이 성공적으로 처리되었습니다."),
    FAIL(0, "에러 발생.")
    ;
    final int code;
    final String message;

}
