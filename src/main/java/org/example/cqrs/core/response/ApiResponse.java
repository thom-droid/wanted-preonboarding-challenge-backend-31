package org.example.cqrs.core.response;

import lombok.Builder;
import org.example.cqrs.core.code.Response;

@Builder
public record ApiResponse<T>(boolean success, T data, String message) {

    public static <T> ApiResponse<T> of() {
        return new ApiResponse<>(true, null, null);
    }

    public static <T> ApiResponse<T> of(T data) {
        return new ApiResponse<>(true, data, Response.SUCCESS.getMessage());
    }

}
