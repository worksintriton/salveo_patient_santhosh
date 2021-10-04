package com.salveo.mysalveo.responsepojo;

import java.util.List;

public class PetAppointmentCreateResponse {


    /**
     * Status : Success
     * Message : Appointment Added successfully
     * Data : {"doc_attched":[{"file":"http://google.pdf"}],"_id":"5fc5c4fe8385de561aeaba5b","doctor_id":"5fb62a1924583828f10f8731","appointment_UID":"PET-1606796542514","booking_date":"19/11/2020","booking_time":"12:22 pm","booking_date_time":"19/11/2020 12:22 pm","communication_type":"","msg_id":"PET-1606796542514","video_id":"http://vidoe.com","user_id":"5fb6162a211fce241eaf53a9","pet_id":"5fb38ea334f6014ea9013d30","problem_info":"problem info","appoinment_status":"Incomplete","start_appointment_status":"Not Started","end_appointment_status":"Not End","doc_feedback":"doc feedback","doc_rate":"5","user_feedback":"user feedback","user_rate":"4.5","display_date":"19/11/2020 01:00 PM","server_date_time":"09/12/2020 03:00 PM","payment_method":"Card","prescription_details":"","vaccination_details":"","appointment_types":"Normal","allergies":"this is","payment_id":"1234567890","amount":"400","__v":0}
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
         * doc_attched : [{"file":"http://google.pdf"}]
         * _id : 5fc5c4fe8385de561aeaba5b
         * doctor_id : 5fb62a1924583828f10f8731
         * appointment_UID : PET-1606796542514
         * booking_date : 19/11/2020
         * booking_time : 12:22 pm
         * booking_date_time : 19/11/2020 12:22 pm
         * communication_type :
         * msg_id : PET-1606796542514
         * video_id : http://vidoe.com
         * user_id : 5fb6162a211fce241eaf53a9
         * pet_id : 5fb38ea334f6014ea9013d30
         * problem_info : problem info
         * appoinment_status : Incomplete
         * start_appointment_status : Not Started
         * end_appointment_status : Not End
         * doc_feedback : doc feedback
         * doc_rate : 5
         * user_feedback : user feedback
         * user_rate : 4.5
         * display_date : 19/11/2020 01:00 PM
         * server_date_time : 09/12/2020 03:00 PM
         * payment_method : Card
         * prescription_details :
         * vaccination_details :
         * appointment_types : Normal
         * allergies : this is
         * payment_id : 1234567890
         * amount : 400
         * __v : 0
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
        private int __v;
        private List<DocAttchedBean> doc_attched;




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


        public int get__v() {
            return __v;
        }

        public void set__v(int __v) {
            this.__v = __v;

        }


        public List<DocAttchedBean> getDoc_attched() {
            return doc_attched;
        }

        public void setDoc_attched(List<DocAttchedBean> doc_attched) {
            this.doc_attched = doc_attched;

        }

        public static class DocAttchedBean  {
            /**
             * file : http://google.pdf
             */

            private String file;


            public String getFile() {
                return file;
            }

            public void setFile(String file) {
                this.file = file;

            }
        }
    }
}
