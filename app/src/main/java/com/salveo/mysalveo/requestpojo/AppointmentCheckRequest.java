package com.salveo.mysalveo.requestpojo;

public class AppointmentCheckRequest {

    /**
     * Booking_Date : 02-12-2020
     * Booking_Time : 09:00 AM
     * doctor_id : 5fc4eb2c913fec4204e4b15d
     */

    private String Booking_Date;
    private String Booking_Time;
    private String doctor_id;


    public String getBooking_Date() {
        return Booking_Date;
    }

    public void setBooking_Date(String Booking_Date) {
        this.Booking_Date = Booking_Date;

    }


    public String getBooking_Time() {
        return Booking_Time;
    }

    public void setBooking_Time(String Booking_Time) {
        this.Booking_Time = Booking_Time;

    }

    public String getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(String doctor_id) {
        this.doctor_id = doctor_id;
    }
}
