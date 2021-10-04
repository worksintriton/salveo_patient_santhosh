package com.salveo.mysalveo.responsepojo;

public class SPCreateAppointmentResponse {


    /**
     * Status : Success
     * Message : SP Appointment Added successfully
     * Data : {"sp_attched":[],"sp_business_info":[],"_id":"5ffc024b0c1d7055c1a419f7","sp_id":"5ff7ef9b1c72093650a13a10","appointment_UID":"PET-1610351179703","booking_date":"23/10/2020","booking_time":"12:00 AM","booking_date_time":"23/10/2020 12:00 AM","user_id":"5fd841a67aa4cc1c6a1e5636","pet_id":"5fdc46be1e5d8b0eb31c3699","appoinment_status":"Incomplete","start_appointment_status":"Not Started","end_appointment_status":"Not End","user_feedback":"","user_rate":"","display_date":"23/10/2020 10:10 AM","server_date_time":"23/10/2020 10:10 AM","payment_method":"Card","payment_id":"12345","service_name":"Grooming","service_amount":"200","completed_at":"","missed_at":"","mobile_type":"Admin","delete_status":false,"updatedAt":"2021-01-11T07:46:19.707Z","createdAt":"2021-01-11T07:46:19.707Z","__v":0}
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

    public static class DataBean{}
}
