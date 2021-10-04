package com.salveo.mysalveo.requestpojo;

public class PetNoShowRequest {

    /**
     * _id : 5fc639ea72fc42044bfa1683
     * appoint_patient_st :
     * appoinment_status : Completed
     */

    private String _id;
    private String appoint_patient_st;
    private String appoinment_status;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getAppoint_patient_st() {
        return appoint_patient_st;
    }

    public void setAppoint_patient_st(String appoint_patient_st) {
        this.appoint_patient_st = appoint_patient_st;
    }

    public String getAppoinment_status() {
        return appoinment_status;
    }

    public void setAppoinment_status(String appoinment_status) {
        this.appoinment_status = appoinment_status;
    }
}
