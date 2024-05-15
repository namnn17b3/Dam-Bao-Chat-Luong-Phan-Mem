package com.example.dbclpm.exception;

public class AppException extends Exception {
    
    private static final long serialVersionUID = 1L;
    private String message;
    private int status;
    
    public AppException(String message, int status) {
        super(message);
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "AppException [message=" + message + ", status=" + status + "]";
    }
}
