package com.example.server.common.record;

public record ApiResponse<T>(
        int status,
        String message,
        T data
) {}
