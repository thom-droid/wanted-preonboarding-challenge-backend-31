package org.example.cqrs.core.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Response {

    SUCCESS(1, "요청이 성공적으로 처리되었습니다."),
    FAIL(0, "실패했습니다.")
    ;
    final int code;
    final String message;

}
