package com.ilactakip.exception;

import java.time.LocalDateTime;

public class ErrorResponse {

    private int status;
    private String message;
    private LocalDateTime timestamp;

    // 🔹 Boş constructor
    public ErrorResponse() {
        this.timestamp = LocalDateTime.now();
    }

    // 🔹 Status ve mesaj ile constructor
    public ErrorResponse(int status, String message) {
        this();
        this.status = status;
        this.message = message;
    }

    /* ================= GETTERS ================= */
    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /* ================= SETTERS ================= */
    public void setStatus(int status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}

