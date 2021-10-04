package com.salveo.mysalveo.responsepojo;

import java.util.List;

public class AppoinmentCompleteResponse {

    /**
     * Status : Success
     * Message : Appointment Updated
     * Data : {"doc_attched":[],"doc_business_info":[{"education_details":[{"education":"MD","year":"2011"}],"experience_details":[{"company":"Apollo hospitals","from":"2015","to":"2020"}],"specialization":[{"specialization":"Testing - 1"},{"specialization":"special - 3"}],"pet_handled":[{"pet_handled":"Dog"},{"pet_handled":"Cat"},{"pet_handled":"Cow"},{"pet_handled":"Dog type 1"},{"pet_handled":"Dog\t"},{"pet_handled":"Dog type 2"}],"clinic_pic":[{"clinic_pic":"http://52.25.163.13:3000/api/uploads/5fc761dab8b7931efc42592f03-12-2020 06:47 PMPetfolio1.jpg"}],"certificate_pic":[{"certificate_pic":"http://52.25.163.13:3000/api/uploads/5fc761dab8b7931efc42592f03-12-2020 06:47 PMfaq.pdf"}],"govt_id_pic":[{"govt_id_pic":"http://52.25.163.13:3000/api/uploads/5fc761dab8b7931efc42592f03-12-2020 06:47 PM1606464394712_4893XXXXXXXXXX79_13-11-2020.PDF"}],"photo_id_pic":[{"photo_id_pic":"http://52.25.163.13:3000/api/uploads/5fc761dab8b7931efc42592f03-12-2020 06:47 PM1606464394712_4893XXXXXXXXXX79_13-11-2020.PDF"}],"_id":"5fc8e6615a976d673ec15e36","user_id":"5fc761dab8b7931efc42592f","dr_title":"Dr","dr_name":"Dk","clinic_name":"DINESH Clinic","clinic_loc":"4/3 Marriyamman Kovil street","clinic_lat":0,"clinic_long":0,"profile_status":true,"profile_verification_status":"verified","date_and_time":"03/12/2020 06:51:34","mobile_type":"Android","__v":0}],"_id":"5fc9c4e25a976d673ec15e3d","doctor_id":"5fc761dab8b7931efc42592f","appointment_UID":"PET-1607058658505","booking_date":"04-12-2020","booking_time":"12:00 AM","booking_date_time":"04-12-2020 12:00 AM","communication_type":"","msg_id":"PET-1607058658505","video_id":"","user_id":"5fc61b82b750da703e48da78","pet_id":"5fc8e2745a976d673ec15e2f","problem_info":"fever","appoinment_status":"Completed","start_appointment_status":"Not Started","end_appointment_status":"Not End","doc_feedback":"","doc_rate":"0","user_feedback":"","user_rate":"0","display_date":"","server_date_time":"","payment_method":"","prescription_details":"","vaccination_details":"","appointment_types":"","allergies":"fever","payment_id":"","amount":"0","service_name":"","service_amount":"","mobile_type":"Android","__v":0,"completed_at":"04/12/2020 11:44 AM"}
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
        /**
         * doc_attched : []
         * doc_business_info : [{"education_details":[{"education":"MD","year":"2011"}],"experience_details":[{"company":"Apollo hospitals","from":"2015","to":"2020"}],"specialization":[{"specialization":"Testing - 1"},{"specialization":"special - 3"}],"pet_handled":[{"pet_handled":"Dog"},{"pet_handled":"Cat"},{"pet_handled":"Cow"},{"pet_handled":"Dog type 1"},{"pet_handled":"Dog\t"},{"pet_handled":"Dog type 2"}],"clinic_pic":[{"clinic_pic":"http://52.25.163.13:3000/api/uploads/5fc761dab8b7931efc42592f03-12-2020 06:47 PMPetfolio1.jpg"}],"certificate_pic":[{"certificate_pic":"http://52.25.163.13:3000/api/uploads/5fc761dab8b7931efc42592f03-12-2020 06:47 PMfaq.pdf"}],"govt_id_pic":[{"govt_id_pic":"http://52.25.163.13:3000/api/uploads/5fc761dab8b7931efc42592f03-12-2020 06:47 PM1606464394712_4893XXXXXXXXXX79_13-11-2020.PDF"}],"photo_id_pic":[{"photo_id_pic":"http://52.25.163.13:3000/api/uploads/5fc761dab8b7931efc42592f03-12-2020 06:47 PM1606464394712_4893XXXXXXXXXX79_13-11-2020.PDF"}],"_id":"5fc8e6615a976d673ec15e36","user_id":"5fc761dab8b7931efc42592f","dr_title":"Dr","dr_name":"Dk","clinic_name":"DINESH Clinic","clinic_loc":"4/3 Marriyamman Kovil street","clinic_lat":0,"clinic_long":0,"profile_status":true,"profile_verification_status":"verified","date_and_time":"03/12/2020 06:51:34","mobile_type":"Android","__v":0}]
         * _id : 5fc9c4e25a976d673ec15e3d
         * doctor_id : 5fc761dab8b7931efc42592f
         * appointment_UID : PET-1607058658505
         * booking_date : 04-12-2020
         * booking_time : 12:00 AM
         * booking_date_time : 04-12-2020 12:00 AM
         * communication_type :
         * msg_id : PET-1607058658505
         * video_id :
         * user_id : 5fc61b82b750da703e48da78
         * pet_id : 5fc8e2745a976d673ec15e2f
         * problem_info : fever
         * appoinment_status : Completed
         * start_appointment_status : Not Started
         * end_appointment_status : Not End
         * doc_feedback :
         * doc_rate : 0
         * user_feedback :
         * user_rate : 0
         * display_date :
         * server_date_time :
         * payment_method :
         * prescription_details :
         * vaccination_details :
         * appointment_types :
         * allergies : fever
         * payment_id :
         * amount : 0
         * service_name :
         * service_amount :
         * mobile_type : Android
         * __v : 0
         * completed_at : 04/12/2020 11:44 AM
         */

        private String _id;
        private String doctor_id;
        private String appointment_UID;
        private String booking_date;
        private String booking_time;
        private String booking_date_time;
        private String communication_type;
        private String msg_id;
        private String video_id;
        private String user_id;
        private String pet_id;
        private String problem_info;
        private String appoinment_status;
        private String start_appointment_status;
        private String end_appointment_status;
        private String doc_feedback;
        private String doc_rate;
        private String user_feedback;
        private String user_rate;
        private String display_date;
        private String server_date_time;
        private String payment_method;
        private String prescription_details;
        private String vaccination_details;
        private String appointment_types;
        private String allergies;
        private String payment_id;
        private String amount;
        private String service_name;
        private String service_amount;
        private String mobile_type;
        private int __v;
        private String completed_at;
        private List<?> doc_attched;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getDoctor_id() {
            return doctor_id;
        }

        public void setDoctor_id(String doctor_id) {
            this.doctor_id = doctor_id;
        }

        public String getAppointment_UID() {
            return appointment_UID;
        }

        public void setAppointment_UID(String appointment_UID) {
            this.appointment_UID = appointment_UID;
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

        public String getBooking_date_time() {
            return booking_date_time;
        }

        public void setBooking_date_time(String booking_date_time) {
            this.booking_date_time = booking_date_time;
        }

        public String getCommunication_type() {
            return communication_type;
        }

        public void setCommunication_type(String communication_type) {
            this.communication_type = communication_type;
        }

        public String getMsg_id() {
            return msg_id;
        }

        public void setMsg_id(String msg_id) {
            this.msg_id = msg_id;
        }

        public String getVideo_id() {
            return video_id;
        }

        public void setVideo_id(String video_id) {
            this.video_id = video_id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getPet_id() {
            return pet_id;
        }

        public void setPet_id(String pet_id) {
            this.pet_id = pet_id;
        }

        public String getProblem_info() {
            return problem_info;
        }

        public void setProblem_info(String problem_info) {
            this.problem_info = problem_info;
        }

        public String getAppoinment_status() {
            return appoinment_status;
        }

        public void setAppoinment_status(String appoinment_status) {
            this.appoinment_status = appoinment_status;
        }

        public String getStart_appointment_status() {
            return start_appointment_status;
        }

        public void setStart_appointment_status(String start_appointment_status) {
            this.start_appointment_status = start_appointment_status;
        }

        public String getEnd_appointment_status() {
            return end_appointment_status;
        }

        public void setEnd_appointment_status(String end_appointment_status) {
            this.end_appointment_status = end_appointment_status;
        }

        public String getDoc_feedback() {
            return doc_feedback;
        }

        public void setDoc_feedback(String doc_feedback) {
            this.doc_feedback = doc_feedback;
        }

        public String getDoc_rate() {
            return doc_rate;
        }

        public void setDoc_rate(String doc_rate) {
            this.doc_rate = doc_rate;
        }

        public String getUser_feedback() {
            return user_feedback;
        }

        public void setUser_feedback(String user_feedback) {
            this.user_feedback = user_feedback;
        }

        public String getUser_rate() {
            return user_rate;
        }

        public void setUser_rate(String user_rate) {
            this.user_rate = user_rate;
        }

        public String getDisplay_date() {
            return display_date;
        }

        public void setDisplay_date(String display_date) {
            this.display_date = display_date;
        }

        public String getServer_date_time() {
            return server_date_time;
        }

        public void setServer_date_time(String server_date_time) {
            this.server_date_time = server_date_time;
        }

        public String getPayment_method() {
            return payment_method;
        }

        public void setPayment_method(String payment_method) {
            this.payment_method = payment_method;
        }

        public String getPrescription_details() {
            return prescription_details;
        }

        public void setPrescription_details(String prescription_details) {
            this.prescription_details = prescription_details;
        }

        public String getVaccination_details() {
            return vaccination_details;
        }

        public void setVaccination_details(String vaccination_details) {
            this.vaccination_details = vaccination_details;
        }

        public String getAppointment_types() {
            return appointment_types;
        }

        public void setAppointment_types(String appointment_types) {
            this.appointment_types = appointment_types;
        }

        public String getAllergies() {
            return allergies;
        }

        public void setAllergies(String allergies) {
            this.allergies = allergies;
        }

        public String getPayment_id() {
            return payment_id;
        }

        public void setPayment_id(String payment_id) {
            this.payment_id = payment_id;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getService_name() {
            return service_name;
        }

        public void setService_name(String service_name) {
            this.service_name = service_name;
        }

        public String getService_amount() {
            return service_amount;
        }

        public void setService_amount(String service_amount) {
            this.service_amount = service_amount;
        }

        public String getMobile_type() {
            return mobile_type;
        }

        public void setMobile_type(String mobile_type) {
            this.mobile_type = mobile_type;
        }

        public int get__v() {
            return __v;
        }

        public void set__v(int __v) {
            this.__v = __v;
        }

        public String getCompleted_at() {
            return completed_at;
        }

        public void setCompleted_at(String completed_at) {
            this.completed_at = completed_at;
        }

        public List<?> getDoc_attched() {
            return doc_attched;
        }

        public void setDoc_attched(List<?> doc_attched) {
            this.doc_attched = doc_attched;
        }
    }
}
