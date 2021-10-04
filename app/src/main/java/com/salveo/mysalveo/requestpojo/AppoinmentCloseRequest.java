package com.salveo.mysalveo.requestpojo;

public class AppoinmentCloseRequest {

    /**
     * _id : 5fc639ea72fc42044bfa1683
     * start_appointment_status :
     * appoinment_status : Completed
     */

    private String _id;
    private String start_appointment_status;
    private String appoinment_status;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getStart_appointment_status() {
        return start_appointment_status;
    }

    public void setStart_appointment_status(String start_appointment_status) {
        this.start_appointment_status = start_appointment_status;
    }

    public String getAppoinment_status() {
        return appoinment_status;
    }

    public void setAppoinment_status(String appoinment_status) {
        this.appoinment_status = appoinment_status;
    }
}
