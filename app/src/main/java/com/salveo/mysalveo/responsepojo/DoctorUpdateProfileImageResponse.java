package com.salveo.mysalveo.responsepojo;

public class DoctorUpdateProfileImageResponse {
    /**
     * Status : Success
     * Message : OTP Send to your mobile number
     * Data : {"user_details":{"_id":"5fb3c8bcfda8295ba10a7300","first_name":"DINESH","last_name":"Deva","user_email":"iddinesh@gmail.com","user_phone":"6383791451","date_of_reg":"17/11/2020 06:27:30","user_type":1,"user_status":"Incomplete","otp":225434,"fb_token":"","device_id":"","device_type":"","__v":0}}
     * Code : 200
     */

    private String Status;
    private String Message;
    private DataBean Data;
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

    public DataBean getData() {
        return Data;
    }

    public void setData( DataBean Data) {
        this.Data = Data;
    }

    public int getCode() {
        return Code;
    }

    public void setCode(int Code) {
        this.Code = Code;
    }

    public static class DataBean {



    }
}
