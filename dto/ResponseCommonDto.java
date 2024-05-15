package com.example.dbclpm.dto;

public class ResponseCommonDto {
    String message;
    int status;
    Object data;
    
    public ResponseCommonDto() {}
    

    public ResponseCommonDto(String message, int status, Object data) {
        super();
        this.message = message;
        this.status = status;
        this.data = data;
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseCommonDto [message=" + message + ", status=" + status + ", data=" + data + "]";
    }
}
