package com.example.customer.exception;

import java.time.LocalDateTime;
import java.util.Objects;

public class ErrorResponse {
    private LocalDateTime timestamp;
    private String message;
    private String errorCode;
    private String path;

    // Default constructor
    public ErrorResponse() {
    }

    // All args constructor
    public ErrorResponse(LocalDateTime timestamp, String message, String errorCode, String path) {
        this.timestamp = timestamp;
        this.message = message;
        this.errorCode = errorCode;
        this.path = path;
    }

    // Getters and Setters
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    // Builder pattern
    public static ErrorResponseBuilder builder() {
        return new ErrorResponseBuilder();
    }

    // toString
    @Override
    public String toString() {
        return "ErrorResponse{" +
                "timestamp=" + timestamp +
                ", message='" + message + '\'' +
                ", errorCode='" + errorCode + '\'' +
                ", path='" + path + '\'' +
                '}';
    }

    // Builder class
    public static class ErrorResponseBuilder {
        private LocalDateTime timestamp;
        private String message;
        private String errorCode;
        private String path;

        ErrorResponseBuilder() {
        }

        public ErrorResponseBuilder timestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public ErrorResponseBuilder message(String message) {
            this.message = message;
            return this;
        }

        public ErrorResponseBuilder errorCode(String errorCode) {
            this.errorCode = errorCode;
            return this;
        }

        public ErrorResponseBuilder path(String path) {
            this.path = path;
            return this;
        }

        public ErrorResponse build() {
            return new ErrorResponse(timestamp, message, errorCode, path);
        }
    }
}
