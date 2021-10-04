package com.salveo.mysalveo.responsepojo;

import java.util.List;

public class PetAppointmentResponse {

    /**
     * Status : Success
     * Message : New Appointment List
     * Data : [{"_id":"5ffd61122e664657f3ec180d","Booking_Id":"SP-1610440978290","appointment_for":"SP","photo":"http://52.25.163.13:3000/api/uploads/5ff7ef9b1c72093650a13a1008-01-2021 11:10 AMPetfolio1.jpg","clinic_name":"","pet_name":"Teenu","appointment_type":"","cost":"200","appointment_time":"13-01-2021 05:45 AM","createdAt":"2021-01-12T08:42:58.295Z","updatedAt":"2021-01-12T08:42:58.308Z","pet_type":"Cat","type":"","service_provider_name":"Grooming","Service_name":"walking","service_cost":"200","Booked_at":"13-01-2021 05:45 AM","missed_at":"","completed_at":"","status":"Incomplete"},{"_id":"5ffd48a8e51c50380bfc58a9","Booking_Id":"PET-1610434728163","appointment_for":"Doctor","photo":"http://52.25.163.13:3000/api/uploads/5ff82d47414b1052a09bb2b508-01-2021 03:30 pmIMG-20210106-WA0002.jpg","clinic_name":"sangeetha care","pet_name":"DOG","appointment_type":"Emergency","cost":"630","appointment_time":"15-01-2021 12:15 AM","createdAt":"2021-01-12T06:58:48.177Z","updatedAt":"2021-01-12T06:58:48.186Z","pet_type":"Dog","type":"","service_provider_name":"","Service_name":"","service_cost":"","Booked_at":"15-01-2021 12:15 AM","missed_at":"","completed_at":"","status":"Incomplete"},{"_id":"5ffd56411cc43649da26ed7c","Booking_Id":"PET-1610438209718","appointment_for":"Doctor","photo":"http://52.25.163.13:3000/api/uploads/5ff82d47414b1052a09bb2b508-01-2021 03:30 pmIMG-20210106-WA0002.jpg","clinic_name":"sangeetha care","pet_name":"Teenu","appointment_type":"Emergency","cost":"630","appointment_time":"12-01-2021 09:00 PM","createdAt":"2021-01-12T07:56:49.726Z","updatedAt":"2021-01-12T07:56:49.731Z","pet_type":"Cat","type":"","service_provider_name":"","Service_name":"","service_cost":"","Booked_at":"12-01-2021 09:00 PM","missed_at":"","completed_at":"","status":"Incomplete"}]
     * Code : 200
     */

    private String Status;
    private String Message;
    private int Code;
    private List<DataBean> Data;


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


    public int getCode() {
        return Code;
    }

    public void setCode(int Code) {
        this.Code = Code;
    }


    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;

    }

    public static class DataBean  {
        /**
         * _id : 5ffd61122e664657f3ec180d
         * Booking_Id : SP-1610440978290
         * appointment_for : SP
         * photo : http://52.25.163.13:3000/api/uploads/5ff7ef9b1c72093650a13a1008-01-2021 11:10 AMPetfolio1.jpg
         * clinic_name :
         * pet_name : Teenu
         * appointment_type :
         * cost : 200
         * appointment_time : 13-01-2021 05:45 AM
         * createdAt : 2021-01-12T08:42:58.295Z
         * updatedAt : 2021-01-12T08:42:58.308Z
         * pet_type : Cat
         * type :
         * service_provider_name : Grooming
         * Service_name : walking
         * service_cost : 200
         * Booked_at : 13-01-2021 05:45 AM
         * missed_at :
         * completed_at :
         * status : Incomplete
         * communication_type
         * doctor_name
         * user_id
         * doctor_id
         * sp_id
         */

        private String _id;
        private String Booking_Id;
        private String appointment_for;
        private String photo;
        private String clinic_name;
        private String pet_name;
        private String appointment_type;
        private String cost;
        private String appointment_time;
        private String createdAt;
        private String updatedAt;
        private String pet_type;
        private String type;
        private String service_provider_name;
        private String Service_name;
        private String service_cost;
        private String Booked_at;
        private String missed_at;
        private String completed_at;
        private String status;
        private String user_rate;
        private String user_feedback;
        private String communication_type;
        private String start_appointment_status;
        private String appoint_patient_st;
        private String doctor_name;
        private String user_id;
        private String doctor_id;
        private String sp_id;

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getDoctor_id() {
            return doctor_id;
        }

        public void setDoctor_id(String doctor_id) {
            this.doctor_id = doctor_id;
        }

        public String getSp_id() {
            return sp_id;
        }

        public void setSp_id(String sp_id) {
            this.sp_id = sp_id;
        }

        public String getDoctor_name() {
            return doctor_name;
        }

        public void setDoctor_name(String doctor_name) {
            this.doctor_name = doctor_name;
        }

        public String getAppoint_patient_st() {
            return appoint_patient_st;
        }

        public void setAppoint_patient_st(String appoint_patient_st) {
            this.appoint_patient_st = appoint_patient_st;
        }

        public String getStart_appointment_status() {
            return start_appointment_status;
        }

        public void setStart_appointment_status(String start_appointment_status) {
            this.start_appointment_status = start_appointment_status;
        }

        public String getCommunication_type() {
            return communication_type;
        }

        public void setCommunication_type(String communication_type) {
            this.communication_type = communication_type;
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

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;

        }


        public String getBooking_Id() {
            return Booking_Id;
        }

        public void setBooking_Id(String Booking_Id) {
            this.Booking_Id = Booking_Id;

        }


        public String getAppointment_for() {
            return appointment_for;
        }

        public void setAppointment_for(String appointment_for) {
            this.appointment_for = appointment_for;

        }


        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;

        }


        public String getClinic_name() {
            return clinic_name;
        }

        public void setClinic_name(String clinic_name) {
            this.clinic_name = clinic_name;
        }

        public String getPet_name() {
            return pet_name;
        }

        public void setPet_name(String pet_name) {
            this.pet_name = pet_name;
        }


        public String getAppointment_type() {
            return appointment_type;
        }

        public void setAppointment_type(String appointment_type) {
            this.appointment_type = appointment_type;
        }


        public String getCost() {
            return cost;
        }

        public void setCost(String cost) {
            this.cost = cost;

        }


        public String getAppointment_time() {
            return appointment_time;
        }

        public void setAppointment_time(String appointment_time) {
            this.appointment_time = appointment_time;

        }


        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }


        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;

        }


        public String getPet_type() {
            return pet_type;
        }

        public void setPet_type(String pet_type) {
            this.pet_type = pet_type;

        }


        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;

        }


        public String getService_provider_name() {
            return service_provider_name;
        }

        public void setService_provider_name(String service_provider_name) {
            this.service_provider_name = service_provider_name;

        }

        public String getService_name() {
            return Service_name;
        }

        public void setService_name(String Service_name) {
            this.Service_name = Service_name;
        }


        public String getService_cost() {
            return service_cost;
        }

        public void setService_cost(String service_cost) {
            this.service_cost = service_cost;

        }


        public String getBooked_at() {
            return Booked_at;
        }

        public void setBooked_at(String Booked_at) {
            this.Booked_at = Booked_at;
        }


        public String getMissed_at() {
            return missed_at;
        }

        public void setMissed_at(String missed_at) {
            this.missed_at = missed_at;

        }


        public String getCompleted_at() {
            return completed_at;
        }

        public void setCompleted_at(String completed_at) {
            this.completed_at = completed_at;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
