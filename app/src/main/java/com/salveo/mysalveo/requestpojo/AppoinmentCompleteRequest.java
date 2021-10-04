package com.salveo.mysalveo.requestpojo;

public class AppoinmentCompleteRequest {

    /**
     * _id : 5fc639ea72fc42044bfa1683
     * completed_at : 23-10-2000 10 : 00 AM
     * appoinment_status : Completed
     */

    private String _id;
    private String completed_at;
    private String appoinment_status;

    private String doctor_comment;
    private String diagnosis;
    private String sub_diagnosis;

    public String getDoctor_comment() {
        return doctor_comment;
    }

    public void setDoctor_comment(String doctor_comment) {
        this.doctor_comment = doctor_comment;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getSub_diagnosis() {
        return sub_diagnosis;
    }

    public void setSub_diagnosis(String sub_diagnosis) {
        this.sub_diagnosis = sub_diagnosis;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }


    public String getCompleted_at() {
        return completed_at;
    }

    public void setCompleted_at(String completed_at) {
        this.completed_at = completed_at;

    }


    public String getAppoinment_status() {
        return appoinment_status;
    }

    public void setAppoinment_status(String appoinment_status) {
        this.appoinment_status = appoinment_status;
    }
}
