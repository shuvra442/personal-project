package com.dustman.dto;


public record Status(Integer code, Object data) {

    public Status(Integer code) {
        this(code, defaultMessage(code));
    }

    private static String defaultMessage(Integer code) {
        return switch (code) {
            case 200 -> "Success";
            case 201 -> "Created";
            case 204 -> "No Content";
            case 400 -> "Bad Request";
            case 404 -> "Data Not Found";
            case 500 -> "Internal Server Error";
            case 401 -> "Authentication Error";
            default -> "Unknown Status";
        };
    }
}
