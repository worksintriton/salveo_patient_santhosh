package com.salveo.mysalveo.requestpojo;

public class AppoinmentCancelledRequest {

    /**
     * _id : 5fc639ea72fc42044bfa1683
     * missed_at : 23-10-2000 10 : 00 AM
     * doc_feedback : One Emergenecy work i am cancelling this appointment
     * appoinment_status : Missed
     * appoint_patient_st:Doctor Cancelled appointment
     */

    private String _id;
    private String missed_at;
    private String doc_feedback;
    private String appoinment_status;
    private String  appoint_patient_st;

    public String getAppoint_patient_st() {
        return appoint_patient_st;
    }

    public void setAppoint_patient_st(String appoint_patient_st) {
        this.appoint_patient_st = appoint_patient_st;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;

    }


    public String getMissed_at() {
        return missed_at;
    }

    public void setMissed_at(String missed_at) {
        this.missed_at = missed_at;

    }


    public String getDoc_feedback() {
        return doc_feedback;
    }

    public void setDoc_feedback(String doc_feedback) {
        this.doc_feedback = doc_feedback;

    }


    public String getAppoinment_status() {
        return appoinment_status;
    }

    public void setAppoinment_status(String appoinment_status) {
        this.appoinment_status = appoinment_status;

    }
}
