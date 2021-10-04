package com.salveo.mysalveo.responsepojo;

public class VendorOrderUpdateResponse {

    /**
     * Status : Success
     * Message : Vendor multi product detail update
     * Data : {}
     * Code : 200
     */

    private String Status;
    private String Message;
    private int Code;

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public int getCode() {
        return Code;
    }

    public void setCode(int Code) {
        this.Code = Code;
    }
}
