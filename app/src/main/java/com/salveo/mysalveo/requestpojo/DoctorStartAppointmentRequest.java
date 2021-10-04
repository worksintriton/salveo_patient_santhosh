package com.salveo.mysalveo.requestpojo;

public class DoctorStartAppointmentRequest {
    /*
     * _id : 5fc639ea72fc42044bfa1683
     * appoinment_status : Completed
     */
    private String _id;
    private String start_appointment_status;

    public String getStart_appointment_status() {
        return start_appointment_status;
    }

    public void setStart_appointment_status(String start_appointment_status) {
        this.start_appointment_status = start_appointment_status;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }


}
