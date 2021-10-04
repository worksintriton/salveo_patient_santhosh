package com.salveo.mysalveo.requestpojo;

public class DoctorNewAppointmentRequest {


    private String doctor_id;
    private String current_time;

    public String getCurrent_time() {
        return current_time;
    }

    public void setCurrent_time(String current_time) {
        this.current_time = current_time;
    }

    public String getDoctor_id ()
    {
        return doctor_id;
    }

    public void setDoctor_id (String doctor_id)
    {
        this.doctor_id = doctor_id;
    }



}
