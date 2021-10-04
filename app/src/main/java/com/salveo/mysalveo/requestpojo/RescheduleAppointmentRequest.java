package com.salveo.mysalveo.requestpojo;

public class RescheduleAppointmentRequest {

    /**
     * _id : 604090e72c2b43125f8cb84e
     * already_booked_date : 23-10-2020 11:00 AM
     * reschedule_date : 23-10-2021 11:00 AM
     * booking_date : 23-10-2021
     * booking_time : 11:00 AM
     */

    private String _id;
    private String already_booked_date;
    private String reschedule_date;
    private String booking_date;
    private String booking_time;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getAlready_booked_date() {
        return already_booked_date;
    }

    public void setAlready_booked_date(String already_booked_date) {
        this.already_booked_date = already_booked_date;
    }

    public String getReschedule_date() {
        return reschedule_date;
    }

    public void setReschedule_date(String reschedule_date) {
        this.reschedule_date = reschedule_date;
    }

    public String getBooking_date() {
        return booking_date;
    }

    public void setBooking_date(String booking_date) {
        this.booking_date = booking_date;
    }

    public String getBooking_time() {
        return booking_time;
    }

    public void setBooking_time(String booking_time) {
        this.booking_time = booking_time;
    }
}
