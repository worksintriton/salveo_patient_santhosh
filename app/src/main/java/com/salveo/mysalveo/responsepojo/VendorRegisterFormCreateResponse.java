package com.salveo.mysalveo.responsepojo;

public class VendorRegisterFormCreateResponse {

    /**
     * Status : Success
     * Message : Vendor Updated
     * Data : {"bussiness_gallery":[],"photo_id_proof":[],"govt_id_proof":[],"certifi":[],"_id":"5fe49c7335ed19762b918519","user_id":"123123213","user_name":"Mohammed imthiyas","user_email":"mohammed@gmail.com","bussiness_name":"Mohammed","bussiness_email":"mohammed@gmail.com","bussiness":"Mohammed","bussiness_phone":"9876543210","business_reg":"Resdf","date_and_time":"23-10-2020 12:00 AM","mobile_type":"Admin","profile_status":true,"profile_verification_status":"Not Verified","bussiness_loc":12,"bussiness_lat":80,"bussiness_long":"Moolakadai","delete_status":false,"updatedAt":"2020-12-24T13:51:37.826Z","createdAt":"2020-12-24T13:49:39.726Z","__v":0}
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

    public void setData(DataBean Data) {
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
