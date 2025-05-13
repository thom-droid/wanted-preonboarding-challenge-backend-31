package org.example.cqrs.core.response;

import org.example.cqrs.core.code.Response;

import java.util.List;

public record PaginationResponse<T>(boolean success, PaginatedData<T> data, String message) {

    public static <T> PaginationResponse<T> of(List<T> data, Pagination pagination) {
        return new PaginationResponse<>(
                true,
                new PaginatedData<>(data, pagination),
                Response.SUCCESS.getMessage()
        );
    }

    public record PaginatedData<T>(List<T> items, Pagination pagination) {}
    public record Pagination(int totalItems, int totalPages, int currentPage, int perPage) {}
}
