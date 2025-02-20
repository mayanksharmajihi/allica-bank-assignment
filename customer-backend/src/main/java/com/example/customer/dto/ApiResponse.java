package com.example.customer.dto;

import java.util.Objects;

public class ApiResponse<T> {
    private boolean success;
    private String message;
    private String status;
    private T data;

    // Default constructor
    public ApiResponse() {
        this.success = false;
    }

    // All args constructor
    public ApiResponse(boolean success, String message, String status, T data) {
        this.success = success;
        this.message = message;
        this.status = status;
        this.data = data;
    }

    // Static factory methods
    public static <T> ApiResponse<T> success(T data, String message) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setData(data);
        response.setMessage(message);
        response.setStatus("200");
        return response;
    }

    public static <T> ApiResponse<T> error(String message, String status) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setSuccess(false);
        response.setMessage(message);
        response.setStatus(status);
        return response;
    }

    // Getters and setters
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    // equals, hashCode, and toString
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApiResponse<?> that = (ApiResponse<?>) o;
        return success == that.success &&
               Objects.equals(message, that.message) &&
               Objects.equals(status, that.status) &&
               Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(success, message, status, data);
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
               "success=" + success +
               ", message='" + message + '\'' +
               ", status='" + status + '\'' +
               ", data=" + data +
               '}';
    }
}
