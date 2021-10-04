package com.salveo.mysalveo.responsepojo;

import java.util.List;

public class SPAppointmentDetailsResponse {


    /**
     * Status : Success
     * Message : New Appointment List
     * Data : {"sp_attched":[],"sp_business_info":[{"bus_service_list":[{"amount":120,"bus_service_list":"walking","time_slots":"15 mins"}],"bus_spec_list":[{"bus_spec_list":"Specialization - 1"},{"bus_spec_list":"Specialization - 3"}],"bus_service_gall":[{"bus_service_gall":"http://52.25.163.13:3000/api/uploads/601ccbe2a66e453d0df7a44e05-02-2021 10:35 AMPetfolio1.jpg"}],"bus_certif":[{"bus_certif":"http://52.25.163.13:3000/api/uploads/601ccbe2a66e453d0df7a44e05-02-2021 10:35 AM0001019010010862809_12132020_01022021.PDF"}],"_id":"601cd568a66e453d0df7a456","user_id":"601ccbe2a66e453d0df7a44e","bus_user_name":"DineshSP","bus_user_email":"dinesh@gmail.com","bussiness_name":"GROMMING","bus_user_phone":"9842670812","bus_profile":"http://52.25.163.13:3000/api/uploads/601ccbe2a66e453d0df7a44e05-02-2021 10:35 AM0001019010010862809_12132020_01022021.PDF","bus_proof":"http://52.25.163.13:3000/api/uploads/601ccbe2a66e453d0df7a44e05-02-2021 10:35 AM0001019010010862809_12132020_01022021.PDF","date_and_time":"05/02/2021 11:03 AM","mobile_type":"Android","profile_status":true,"profile_verification_status":"Verified","sp_loc":"Unnamed Road, Tamil Nadu 621006, India","sp_lat":11.0560398,"sp_long":78.6327327,"delete_status":false,"calender_status":true,"updatedAt":"2021-02-08T05:20:04.961Z","createdAt":"2021-02-05T05:19:36.674Z","__v":0}],"_id":"6020da50a66e453d0df7a497","sp_id":{"_id":"601ccbe2a66e453d0df7a44e","first_name":"DineshSP","last_name":"Dk","user_email":"dinesh@gmail.com","user_phone":"9842670812","date_of_reg":"05/02/2021 10:08 AM","user_type":2,"user_status":"complete","otp":123456,"profile_img":"","user_email_verification":false,"fb_token":"cELxwqkwQjKYJJSpADJKxC:APA91bHTeYJWuW5ZcRNYS0aObCue_sMQ-8YjFQlmFPwgSpQONjwsEqyEXEE_MmQNfaMnBtpmnRLHHGUGGrO8rES4aJ-kcDR207v5jyqYHimo2iETm5cxHMpBseHPO09q_Pj-AlTQYtEh","device_id":"","device_type":"","mobile_type":"Android","delete_status":false,"updatedAt":"2021-02-08T06:29:51.309Z","createdAt":"2021-02-05T04:38:58.059Z","__v":0},"appointment_UID":"SP-1612765776138","booking_date":"08-02-2021","booking_time":"10:15 PM","booking_date_time":"08-02-2021 10:15 PM","user_id":{"_id":"601b91c0204c595ee52582f9","first_name":"Kumar","last_name":"Dk","user_email":"dinesh@gmail.com","user_phone":"6383791451","date_of_reg":"04/02/2021 11:48 AM","user_type":1,"user_status":"complete","otp":123456,"profile_img":"","user_email_verification":false,"fb_token":"cELxwqkwQjKYJJSpADJKxC:APA91bHTeYJWuW5ZcRNYS0aObCue_sMQ-8YjFQlmFPwgSpQONjwsEqyEXEE_MmQNfaMnBtpmnRLHHGUGGrO8rES4aJ-kcDR207v5jyqYHimo2iETm5cxHMpBseHPO09q_Pj-AlTQYtEh","device_id":"","device_type":"","mobile_type":"Android","delete_status":false,"updatedAt":"2021-02-08T06:50:17.144Z","createdAt":"2021-02-04T06:18:40.524Z","__v":0},"pet_id":{"_id":"601b91e8204c595ee52582fa","user_id":"601b91c0204c595ee52582f9","pet_img":"http://52.25.163.13:3000/api/uploads/601b91c0204c595ee52582f904-02-2021 11:49 AMPetfolio1.jpg","pet_name":"Teenu","pet_type":"Dog","pet_breed":"Indian Pariah","pet_gender":"Male","pet_color":"Gray","pet_weight":5,"pet_age":1,"vaccinated":true,"last_vaccination_date":"03-02-2021","default_status":true,"date_and_time":"04-02-2021 11:49 AM","mobile_type":"Android","delete_status":false,"updatedAt":"2021-02-04T06:19:37.016Z","createdAt":"2021-02-04T06:19:20.459Z","__v":0},"additional_info":"","appoinment_status":"Incomplete","start_appointment_status":"Not Started","end_appointment_status":"Not End","sp_feedback":"","sp_rate":"","user_feedback":"","user_rate":"","display_date":"08-02-2021 11:59 AM","server_date_time":"","payment_id":"pay_GYyt3LJwH859ml","payment_method":"Online","service_name":"walking","service_amount":"200","service_time":"15 mins","completed_at":"","missed_at":"","mobile_type":"Android","delete_status":false,"updatedAt":"2021-02-08T06:29:36.143Z","createdAt":"2021-02-08T06:29:36.141Z","__v":0}
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
         * sp_attched : []
         * sp_business_info : [{"bus_service_list":[{"amount":120,"bus_service_list":"walking","time_slots":"15 mins"}],"bus_spec_list":[{"bus_spec_list":"Specialization - 1"},{"bus_spec_list":"Specialization - 3"}],"bus_service_gall":[{"bus_service_gall":"http://52.25.163.13:3000/api/uploads/601ccbe2a66e453d0df7a44e05-02-2021 10:35 AMPetfolio1.jpg"}],"bus_certif":[{"bus_certif":"http://52.25.163.13:3000/api/uploads/601ccbe2a66e453d0df7a44e05-02-2021 10:35 AM0001019010010862809_12132020_01022021.PDF"}],"_id":"601cd568a66e453d0df7a456","user_id":"601ccbe2a66e453d0df7a44e","bus_user_name":"DineshSP","bus_user_email":"dinesh@gmail.com","bussiness_name":"GROMMING","bus_user_phone":"9842670812","bus_profile":"http://52.25.163.13:3000/api/uploads/601ccbe2a66e453d0df7a44e05-02-2021 10:35 AM0001019010010862809_12132020_01022021.PDF","bus_proof":"http://52.25.163.13:3000/api/uploads/601ccbe2a66e453d0df7a44e05-02-2021 10:35 AM0001019010010862809_12132020_01022021.PDF","date_and_time":"05/02/2021 11:03 AM","mobile_type":"Android","profile_status":true,"profile_verification_status":"Verified","sp_loc":"Unnamed Road, Tamil Nadu 621006, India","sp_lat":11.0560398,"sp_long":78.6327327,"delete_status":false,"calender_status":true,"updatedAt":"2021-02-08T05:20:04.961Z","createdAt":"2021-02-05T05:19:36.674Z","__v":0}]
         * _id : 6020da50a66e453d0df7a497
         * sp_id : {"_id":"601ccbe2a66e453d0df7a44e","first_name":"DineshSP","last_name":"Dk","user_email":"dinesh@gmail.com","user_phone":"9842670812","date_of_reg":"05/02/2021 10:08 AM","user_type":2,"user_status":"complete","otp":123456,"profile_img":"","user_email_verification":false,"fb_token":"cELxwqkwQjKYJJSpADJKxC:APA91bHTeYJWuW5ZcRNYS0aObCue_sMQ-8YjFQlmFPwgSpQONjwsEqyEXEE_MmQNfaMnBtpmnRLHHGUGGrO8rES4aJ-kcDR207v5jyqYHimo2iETm5cxHMpBseHPO09q_Pj-AlTQYtEh","device_id":"","device_type":"","mobile_type":"Android","delete_status":false,"updatedAt":"2021-02-08T06:29:51.309Z","createdAt":"2021-02-05T04:38:58.059Z","__v":0}
         * appointment_UID : SP-1612765776138
         * booking_date : 08-02-2021
         * booking_time : 10:15 PM
         * booking_date_time : 08-02-2021 10:15 PM
         * user_id : {"_id":"601b91c0204c595ee52582f9","first_name":"Kumar","last_name":"Dk","user_email":"dinesh@gmail.com","user_phone":"6383791451","date_of_reg":"04/02/2021 11:48 AM","user_type":1,"user_status":"complete","otp":123456,"profile_img":"","user_email_verification":false,"fb_token":"cELxwqkwQjKYJJSpADJKxC:APA91bHTeYJWuW5ZcRNYS0aObCue_sMQ-8YjFQlmFPwgSpQONjwsEqyEXEE_MmQNfaMnBtpmnRLHHGUGGrO8rES4aJ-kcDR207v5jyqYHimo2iETm5cxHMpBseHPO09q_Pj-AlTQYtEh","device_id":"","device_type":"","mobile_type":"Android","delete_status":false,"updatedAt":"2021-02-08T06:50:17.144Z","createdAt":"2021-02-04T06:18:40.524Z","__v":0}
         * pet_id : {"_id":"601b91e8204c595ee52582fa","user_id":"601b91c0204c595ee52582f9","pet_img":"http://52.25.163.13:3000/api/uploads/601b91c0204c595ee52582f904-02-2021 11:49 AMPetfolio1.jpg","pet_name":"Teenu","pet_type":"Dog","pet_breed":"Indian Pariah","pet_gender":"Male","pet_color":"Gray","pet_weight":5,"pet_age":1,"vaccinated":true,"last_vaccination_date":"03-02-2021","default_status":true,"date_and_time":"04-02-2021 11:49 AM","mobile_type":"Android","delete_status":false,"updatedAt":"2021-02-04T06:19:37.016Z","createdAt":"2021-02-04T06:19:20.459Z","__v":0}
         * additional_info :
         * appoinment_status : Incomplete
         * start_appointment_status : Not Started
         * end_appointment_status : Not End
         * sp_feedback :
         * sp_rate :
         * user_feedback :
         * user_rate :
         * display_date : 08-02-2021 11:59 AM
         * server_date_time :
         * payment_id : pay_GYyt3LJwH859ml
         * payment_method : Online
         * service_name : walking
         * service_amount : 200
         * service_time : 15 mins
         * completed_at :
         * missed_at :
         * mobile_type : Android
         * delete_status : false
         * updatedAt : 2021-02-08T06:29:36.143Z
         * createdAt : 2021-02-08T06:29:36.141Z
         * __v : 0
         */

        private String date_and_time;

        public String getDate_and_time() {
            return date_and_time;
        }

        public void setDate_and_time(String date_and_time) {
            this.date_and_time = date_and_time;
        }

        private String _id;
        private SpIdBean sp_id;
        private String appointment_UID;
        private String booking_date;
        private String booking_time;
        private String booking_date_time;
        private UserIdBean user_id;
        private PetIdBean pet_id;
        private String additional_info;
        private String appoinment_status;
        private String start_appointment_status;
        private String end_appointment_status;
        private String sp_feedback;
        private String sp_rate;
        private String user_feedback;
        private String user_rate;
        private String display_date;
        private String server_date_time;
        private String payment_id;
        private String payment_method;
        private String service_name;
        private String service_amount;
        private String service_time;
        private String completed_at;
        private String missed_at;
        private String mobile_type;
        private boolean delete_status;
        private String updatedAt;
        private String createdAt;
        private int __v;
        private List<?> sp_attched;
        private List<SpBusinessInfoBean> sp_business_info;


        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;

        }


        public SpIdBean getSp_id() {
            return sp_id;
        }

        public void setSp_id(SpIdBean sp_id) {
            this.sp_id = sp_id;

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


        public UserIdBean getUser_id() {
            return user_id;
        }

        public void setUser_id(UserIdBean user_id) {
            this.user_id = user_id;

        }


        public PetIdBean getPet_id() {
            return pet_id;
        }

        public void setPet_id(PetIdBean pet_id) {
            this.pet_id = pet_id;

        }


        public String getAdditional_info() {
            return additional_info;
        }

        public void setAdditional_info(String additional_info) {
            this.additional_info = additional_info;

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

        public String getSp_feedback() {
            return sp_feedback;
        }

        public void setSp_feedback(String sp_feedback) {
            this.sp_feedback = sp_feedback;

        }


        public String getSp_rate() {
            return sp_rate;
        }

        public void setSp_rate(String sp_rate) {
            this.sp_rate = sp_rate;

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


        public String getPayment_id() {
            return payment_id;
        }

        public void setPayment_id(String payment_id) {
            this.payment_id = payment_id;

        }


        public String getPayment_method() {
            return payment_method;
        }

        public void setPayment_method(String payment_method) {
            this.payment_method = payment_method;

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


        public String getService_time() {
            return service_time;
        }

        public void setService_time(String service_time) {
            this.service_time = service_time;

        }


        public String getCompleted_at() {
            return completed_at;
        }

        public void setCompleted_at(String completed_at) {
            this.completed_at = completed_at;

        }


        public String getMissed_at() {
            return missed_at;
        }

        public void setMissed_at(String missed_at) {
            this.missed_at = missed_at;

        }


        public String getMobile_type() {
            return mobile_type;
        }

        public void setMobile_type(String mobile_type) {
            this.mobile_type = mobile_type;

        }


        public boolean isDelete_status() {
            return delete_status;
        }

        public void setDelete_status(boolean delete_status) {
            this.delete_status = delete_status;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;

        }


        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;

        }


        public int get__v() {
            return __v;
        }

        public void set__v(int __v) {
            this.__v = __v;

        }


        public List<?> getSp_attched() {
            return sp_attched;
        }

        public void setSp_attched(List<?> sp_attched) {
            this.sp_attched = sp_attched;

        }


        public List<SpBusinessInfoBean> getSp_business_info() {
            return sp_business_info;
        }

        public void setSp_business_info(List<SpBusinessInfoBean> sp_business_info) {
            this.sp_business_info = sp_business_info;

        }

        public static class SpIdBean  {
            /**
             * _id : 601ccbe2a66e453d0df7a44e
             * first_name : DineshSP
             * last_name : Dk
             * user_email : dinesh@gmail.com
             * user_phone : 9842670812
             * date_of_reg : 05/02/2021 10:08 AM
             * user_type : 2
             * user_status : complete
             * otp : 123456
             * profile_img :
             * user_email_verification : false
             * fb_token : cELxwqkwQjKYJJSpADJKxC:APA91bHTeYJWuW5ZcRNYS0aObCue_sMQ-8YjFQlmFPwgSpQONjwsEqyEXEE_MmQNfaMnBtpmnRLHHGUGGrO8rES4aJ-kcDR207v5jyqYHimo2iETm5cxHMpBseHPO09q_Pj-AlTQYtEh
             * device_id :
             * device_type :
             * mobile_type : Android
             * delete_status : false
             * updatedAt : 2021-02-08T06:29:51.309Z
             * createdAt : 2021-02-05T04:38:58.059Z
             * __v : 0
             */

            private String _id;
            private String first_name;
            private String last_name;
            private String user_email;
            private String user_phone;
            private String date_of_reg;
            private int user_type;
            private String user_status;
            private int otp;
            private String profile_img;
            private boolean user_email_verification;
            private String fb_token;
            private String device_id;
            private String device_type;
            private String mobile_type;
            private boolean delete_status;
            private String updatedAt;
            private String createdAt;
            private int __v;


            public String get_id() {
                return _id;
            }

            public void set_id(String _id) {
                this._id = _id;

            }


            public String getFirst_name() {
                return first_name;
            }

            public void setFirst_name(String first_name) {
                this.first_name = first_name;
            }


            public String getLast_name() {
                return last_name;
            }

            public void setLast_name(String last_name) {
                this.last_name = last_name;
            }

            public String getUser_email() {
                return user_email;
            }

            public void setUser_email(String user_email) {
                this.user_email = user_email;
            }

            public String getUser_phone() {
                return user_phone;
            }

            public void setUser_phone(String user_phone) {
                this.user_phone = user_phone;
            }


            public String getDate_of_reg() {
                return date_of_reg;
            }

            public void setDate_of_reg(String date_of_reg) {
                this.date_of_reg = date_of_reg;
            }


            public int getUser_type() {
                return user_type;
            }

            public void setUser_type(int user_type) {
                this.user_type = user_type;
            }


            public String getUser_status() {
                return user_status;
            }

            public void setUser_status(String user_status) {
                this.user_status = user_status;
            }

            public int getOtp() {
                return otp;
            }

            public void setOtp(int otp) {
                this.otp = otp;
            }

            public String getProfile_img() {
                return profile_img;
            }

            public void setProfile_img(String profile_img) {
                this.profile_img = profile_img;
            }

            public boolean isUser_email_verification() {
                return user_email_verification;
            }

            public void setUser_email_verification(boolean user_email_verification) {
                this.user_email_verification = user_email_verification;
            }


            public String getFb_token() {
                return fb_token;
            }

            public void setFb_token(String fb_token) {
                this.fb_token = fb_token;
            }

            public String getDevice_id() {
                return device_id;
            }

            public void setDevice_id(String device_id) {
                this.device_id = device_id;
            }

            public String getDevice_type() {
                return device_type;
            }

            public void setDevice_type(String device_type) {
                this.device_type = device_type;
            }

            public String getMobile_type() {
                return mobile_type;
            }

            public void setMobile_type(String mobile_type) {
                this.mobile_type = mobile_type;
            }

            public boolean isDelete_status() {
                return delete_status;
            }

            public void setDelete_status(boolean delete_status) {
                this.delete_status = delete_status;
            }

            public String getUpdatedAt() {
                return updatedAt;
            }

            public void setUpdatedAt(String updatedAt) {
                this.updatedAt = updatedAt;

            }


            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;

            }


            public int get__v() {
                return __v;
            }

            public void set__v(int __v) {
                this.__v = __v;
            }
        }

        public static class UserIdBean {
            /**
             * _id : 601b91c0204c595ee52582f9
             * first_name : Kumar
             * last_name : Dk
             * user_email : dinesh@gmail.com
             * user_phone : 6383791451
             * date_of_reg : 04/02/2021 11:48 AM
             * user_type : 1
             * user_status : complete
             * otp : 123456
             * profile_img :
             * user_email_verification : false
             * fb_token : cELxwqkwQjKYJJSpADJKxC:APA91bHTeYJWuW5ZcRNYS0aObCue_sMQ-8YjFQlmFPwgSpQONjwsEqyEXEE_MmQNfaMnBtpmnRLHHGUGGrO8rES4aJ-kcDR207v5jyqYHimo2iETm5cxHMpBseHPO09q_Pj-AlTQYtEh
             * device_id :
             * device_type :
             * mobile_type : Android
             * delete_status : false
             * updatedAt : 2021-02-08T06:50:17.144Z
             * createdAt : 2021-02-04T06:18:40.524Z
             * __v : 0
             */

            private String _id;
            private String first_name;
            private String last_name;
            private String user_email;
            private String user_phone;
            private String date_of_reg;
            private int user_type;
            private String user_status;
            private int otp;
            private String profile_img;
            private boolean user_email_verification;
            private String fb_token;
            private String device_id;
            private String device_type;
            private String mobile_type;
            private boolean delete_status;
            private String updatedAt;
            private String createdAt;
            private int __v;


            public String get_id() {
                return _id;
            }

            public void set_id(String _id) {
                this._id = _id;

            }


            public String getFirst_name() {
                return first_name;
            }

            public void setFirst_name(String first_name) {
                this.first_name = first_name;

            }


            public String getLast_name() {
                return last_name;
            }

            public void setLast_name(String last_name) {
                this.last_name = last_name;

            }


            public String getUser_email() {
                return user_email;
            }

            public void setUser_email(String user_email) {
                this.user_email = user_email;
            }

            public String getUser_phone() {
                return user_phone;
            }

            public void setUser_phone(String user_phone) {
                this.user_phone = user_phone;
            }


            public String getDate_of_reg() {
                return date_of_reg;
            }

            public void setDate_of_reg(String date_of_reg) {
                this.date_of_reg = date_of_reg;
            }


            public int getUser_type() {
                return user_type;
            }

            public void setUser_type(int user_type) {
                this.user_type = user_type;

            }


            public String getUser_status() {
                return user_status;
            }

            public void setUser_status(String user_status) {
                this.user_status = user_status;

            }

            public int getOtp() {
                return otp;
            }

            public void setOtp(int otp) {
                this.otp = otp;
            }

            public String getProfile_img() {
                return profile_img;
            }

            public void setProfile_img(String profile_img) {
                this.profile_img = profile_img;

            }


            public boolean isUser_email_verification() {
                return user_email_verification;
            }

            public void setUser_email_verification(boolean user_email_verification) {
                this.user_email_verification = user_email_verification;
            }


            public String getFb_token() {
                return fb_token;
            }

            public void setFb_token(String fb_token) {
                this.fb_token = fb_token;

            }


            public String getDevice_id() {
                return device_id;
            }

            public void setDevice_id(String device_id) {
                this.device_id = device_id;

            }


            public String getDevice_type() {
                return device_type;
            }

            public void setDevice_type(String device_type) {
                this.device_type = device_type;

            }


            public String getMobile_type() {
                return mobile_type;
            }

            public void setMobile_type(String mobile_type) {
                this.mobile_type = mobile_type;

            }


            public boolean isDelete_status() {
                return delete_status;
            }

            public void setDelete_status(boolean delete_status) {
                this.delete_status = delete_status;

            }


            public String getUpdatedAt() {
                return updatedAt;
            }

            public void setUpdatedAt(String updatedAt) {
                this.updatedAt = updatedAt;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;

            }

            public int get__v() {
                return __v;
            }

            public void set__v(int __v) {
                this.__v = __v;
            }
        }

        public static class PetIdBean  {
            /**
             * pet_img : [{"pet_img":"http://54.212.108.156:3000/api/uploads/1614686158761.jpeg"}]
             * _id : 603e27a92c2b43125f8cb803
             * user_id : 603e27792c2b43125f8cb802
             * pet_name : DEENU
             * pet_type : Dog
             * pet_breed : Irish Setter
             * pet_gender : Male
             * pet_color : BLACK
             * pet_weight : 20.5
             * pet_age :
             * pet_dob : 03-03-2015
             * pet_spayed : true
             * pet_purebred : true
             * pet_frnd_with_dog : true
             * pet_frnd_with_cat : true
             * pet_frnd_with_kit : true
             * pet_microchipped : true
             * pet_tick_free : true
             * pet_private_part : true
             * vaccinated : true
             * last_vaccination_date : 10-02-2021
             * default_status : true
             * date_and_time : 03-03-2021 03:38 PM
             * mobile_type : Android
             * delete_status : false
             * updatedAt : 2021-03-03T10:08:39.919Z
             * createdAt : 2021-03-02T11:55:21.396Z
             * __v : 0
             */

            private String _id;
            private String user_id;
            private String pet_name;
            private String pet_type;
            private String pet_breed;
            private String pet_gender;
            private String pet_color;
            private double pet_weight;
            private String pet_age;
            private String pet_dob;
            private boolean pet_spayed;
            private boolean pet_purebred;
            private boolean pet_frnd_with_dog;
            private boolean pet_frnd_with_cat;
            private boolean pet_frnd_with_kit;
            private boolean pet_microchipped;
            private boolean pet_tick_free;
            private boolean pet_private_part;
            private boolean vaccinated;
            private String last_vaccination_date;
            private boolean default_status;
            private String date_and_time;
            private String mobile_type;
            private boolean delete_status;
            private String updatedAt;
            private String createdAt;
            private int __v;
            private List<PetNewAppointmentDetailsResponse.DataBean.PetIdBean.PetImgBean> pet_img;

            public String get_id() {
                return _id;
            }

            public void set_id(String _id) {
                this._id = _id;

            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;

            }


            public String getPet_name() {
                return pet_name;
            }

            public void setPet_name(String pet_name) {
                this.pet_name = pet_name;

            }


            public String getPet_type() {
                return pet_type;
            }

            public void setPet_type(String pet_type) {
                this.pet_type = pet_type;

            }


            public String getPet_breed() {
                return pet_breed;
            }

            public void setPet_breed(String pet_breed) {
                this.pet_breed = pet_breed;

            }


            public String getPet_gender() {
                return pet_gender;
            }

            public void setPet_gender(String pet_gender) {
                this.pet_gender = pet_gender;

            }


            public String getPet_color() {
                return pet_color;
            }

            public void setPet_color(String pet_color) {
                this.pet_color = pet_color;

            }


            public double getPet_weight() {
                return pet_weight;
            }

            public void setPet_weight(double pet_weight) {
                this.pet_weight = pet_weight;

            }


            public String getPet_age() {
                return pet_age;
            }

            public void setPet_age(String pet_age) {
                this.pet_age = pet_age;

            }


            public String getPet_dob() {
                return pet_dob;
            }

            public void setPet_dob(String pet_dob) {
                this.pet_dob = pet_dob;

            }


            public boolean isPet_spayed() {
                return pet_spayed;
            }

            public void setPet_spayed(boolean pet_spayed) {
                this.pet_spayed = pet_spayed;

            }


            public boolean isPet_purebred() {
                return pet_purebred;
            }

            public void setPet_purebred(boolean pet_purebred) {
                this.pet_purebred = pet_purebred;

            }


            public boolean isPet_frnd_with_dog() {
                return pet_frnd_with_dog;
            }

            public void setPet_frnd_with_dog(boolean pet_frnd_with_dog) {
                this.pet_frnd_with_dog = pet_frnd_with_dog;

            }


            public boolean isPet_frnd_with_cat() {
                return pet_frnd_with_cat;
            }

            public void setPet_frnd_with_cat(boolean pet_frnd_with_cat) {
                this.pet_frnd_with_cat = pet_frnd_with_cat;

            }

            public boolean isPet_frnd_with_kit() {
                return pet_frnd_with_kit;
            }

            public void setPet_frnd_with_kit(boolean pet_frnd_with_kit) {
                this.pet_frnd_with_kit = pet_frnd_with_kit;

            }


            public boolean isPet_microchipped() {
                return pet_microchipped;
            }

            public void setPet_microchipped(boolean pet_microchipped) {
                this.pet_microchipped = pet_microchipped;

            }


            public boolean isPet_tick_free() {
                return pet_tick_free;
            }

            public void setPet_tick_free(boolean pet_tick_free) {
                this.pet_tick_free = pet_tick_free;

            }


            public boolean isPet_private_part() {
                return pet_private_part;
            }

            public void setPet_private_part(boolean pet_private_part) {
                this.pet_private_part = pet_private_part;

            }


            public boolean isVaccinated() {
                return vaccinated;
            }

            public void setVaccinated(boolean vaccinated) {
                this.vaccinated = vaccinated;

            }


            public String getLast_vaccination_date() {
                return last_vaccination_date;
            }

            public void setLast_vaccination_date(String last_vaccination_date) {
                this.last_vaccination_date = last_vaccination_date;

            }


            public boolean isDefault_status() {
                return default_status;
            }

            public void setDefault_status(boolean default_status) {
                this.default_status = default_status;

            }


            public String getDate_and_time() {
                return date_and_time;
            }

            public void setDate_and_time(String date_and_time) {
                this.date_and_time = date_and_time;

            }


            public String getMobile_type() {
                return mobile_type;
            }

            public void setMobile_type(String mobile_type) {
                this.mobile_type = mobile_type;

            }


            public boolean isDelete_status() {
                return delete_status;
            }

            public void setDelete_status(boolean delete_status) {
                this.delete_status = delete_status;

            }



            public List<PetNewAppointmentDetailsResponse.DataBean.PetIdBean.PetImgBean> getPet_img() {
                return pet_img;
            }

            public void setPet_img(List<PetNewAppointmentDetailsResponse.DataBean.PetIdBean.PetImgBean> pet_img) {
                this.pet_img = pet_img;

            }

            public static class PetImgBean {
                /**
                 * pet_img : http://54.212.108.156:3000/api/uploads/1614686158761.jpeg
                 */

                private String pet_img;


                public String getPet_img() {
                    return pet_img;
                }

                public void setPet_img(String pet_img) {
                    this.pet_img = pet_img;

                }
            }
        }
        public static class SpBusinessInfoBean  {
            /**
             * bus_service_list : [{"amount":120,"bus_service_list":"walking","time_slots":"15 mins"}]
             * bus_spec_list : [{"bus_spec_list":"Specialization - 1"},{"bus_spec_list":"Specialization - 3"}]
             * bus_service_gall : [{"bus_service_gall":"http://52.25.163.13:3000/api/uploads/601ccbe2a66e453d0df7a44e05-02-2021 10:35 AMPetfolio1.jpg"}]
             * bus_certif : [{"bus_certif":"http://52.25.163.13:3000/api/uploads/601ccbe2a66e453d0df7a44e05-02-2021 10:35 AM0001019010010862809_12132020_01022021.PDF"}]
             * _id : 601cd568a66e453d0df7a456
             * user_id : 601ccbe2a66e453d0df7a44e
             * bus_user_name : DineshSP
             * bus_user_email : dinesh@gmail.com
             * bussiness_name : GROMMING
             * bus_user_phone : 9842670812
             * bus_profile : http://52.25.163.13:3000/api/uploads/601ccbe2a66e453d0df7a44e05-02-2021 10:35 AM0001019010010862809_12132020_01022021.PDF
             * bus_proof : http://52.25.163.13:3000/api/uploads/601ccbe2a66e453d0df7a44e05-02-2021 10:35 AM0001019010010862809_12132020_01022021.PDF
             * date_and_time : 05/02/2021 11:03 AM
             * mobile_type : Android
             * profile_status : true
             * profile_verification_status : Verified
             * sp_loc : Unnamed Road, Tamil Nadu 621006, India
             * sp_lat : 11.0560398
             * sp_long : 78.6327327
             * delete_status : false
             * calender_status : true
             * updatedAt : 2021-02-08T05:20:04.961Z
             * createdAt : 2021-02-05T05:19:36.674Z
             * __v : 0
             */

            private String _id;
            private String user_id;
            private String bus_user_name;
            private String bus_user_email;
            private String bussiness_name;
            private String bus_user_phone;
            private String bus_profile;
            private String bus_proof;
            private String date_and_time;
            private String mobile_type;
            private boolean profile_status;
            private String profile_verification_status;
            private String sp_loc;
            private double sp_lat;
            private double sp_long;
            private boolean delete_status;
            private boolean calender_status;
            private String updatedAt;
            private String createdAt;
            private int __v;
            private String thumbnail_image;
            private String city_name;

            public String getThumbnail_image() {
                return thumbnail_image;
            }

            public void setThumbnail_image(String thumbnail_image) {
                this.thumbnail_image = thumbnail_image;
            }

            public String getCity_name() {
                return city_name;
            }

            public void setCity_name(String city_name) {
                this.city_name = city_name;
            }

            private List<BusServiceListBean> bus_service_list;
            private List<BusSpecListBean> bus_spec_list;
            private List<BusServiceGallBean> bus_service_gall;
            private List<BusCertifBean> bus_certif;


            public String get_id() {
                return _id;
            }

            public void set_id(String _id) {
                this._id = _id;

            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;

            }

            public String getBus_user_name() {
                return bus_user_name;
            }

            public void setBus_user_name(String bus_user_name) {
                this.bus_user_name = bus_user_name;

            }


            public String getBus_user_email() {
                return bus_user_email;
            }

            public void setBus_user_email(String bus_user_email) {
                this.bus_user_email = bus_user_email;

            }


            public String getBussiness_name() {
                return bussiness_name;
            }

            public void setBussiness_name(String bussiness_name) {
                this.bussiness_name = bussiness_name;

            }


            public String getBus_user_phone() {
                return bus_user_phone;
            }

            public void setBus_user_phone(String bus_user_phone) {
                this.bus_user_phone = bus_user_phone;

            }


            public String getBus_profile() {
                return bus_profile;
            }

            public void setBus_profile(String bus_profile) {
                this.bus_profile = bus_profile;

            }


            public String getBus_proof() {
                return bus_proof;
            }

            public void setBus_proof(String bus_proof) {
                this.bus_proof = bus_proof;

            }


            public String getDate_and_time() {
                return date_and_time;
            }

            public void setDate_and_time(String date_and_time) {
                this.date_and_time = date_and_time;

            }


            public String getMobile_type() {
                return mobile_type;
            }

            public void setMobile_type(String mobile_type) {
                this.mobile_type = mobile_type;

            }


            public boolean isProfile_status() {
                return profile_status;
            }

            public void setProfile_status(boolean profile_status) {
                this.profile_status = profile_status;

            }


            public String getProfile_verification_status() {
                return profile_verification_status;
            }

            public void setProfile_verification_status(String profile_verification_status) {
                this.profile_verification_status = profile_verification_status;

            }


            public String getSp_loc() {
                return sp_loc;
            }

            public void setSp_loc(String sp_loc) {
                this.sp_loc = sp_loc;

            }


            public double getSp_lat() {
                return sp_lat;
            }

            public void setSp_lat(double sp_lat) {
                this.sp_lat = sp_lat;

            }


            public double getSp_long() {
                return sp_long;
            }

            public void setSp_long(double sp_long) {
                this.sp_long = sp_long;

            }


            public boolean isDelete_status() {
                return delete_status;
            }

            public void setDelete_status(boolean delete_status) {
                this.delete_status = delete_status;

            }


            public boolean isCalender_status() {
                return calender_status;
            }

            public void setCalender_status(boolean calender_status) {
                this.calender_status = calender_status;

            }


            public String getUpdatedAt() {
                return updatedAt;
            }

            public void setUpdatedAt(String updatedAt) {
                this.updatedAt = updatedAt;

            }


            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;

            }


            public int get__v() {
                return __v;
            }

            public void set__v(int __v) {
                this.__v = __v;

            }


            public List<BusServiceListBean> getBus_service_list() {
                return bus_service_list;
            }

            public void setBus_service_list(List<BusServiceListBean> bus_service_list) {
                this.bus_service_list = bus_service_list;

            }


            public List<BusSpecListBean> getBus_spec_list() {
                return bus_spec_list;
            }

            public void setBus_spec_list(List<BusSpecListBean> bus_spec_list) {
                this.bus_spec_list = bus_spec_list;

            }


            public List<BusServiceGallBean> getBus_service_gall() {
                return bus_service_gall;
            }

            public void setBus_service_gall(List<BusServiceGallBean> bus_service_gall) {
                this.bus_service_gall = bus_service_gall;

            }

            public List<BusCertifBean> getBus_certif() {
                return bus_certif;
            }

            public void setBus_certif(List<BusCertifBean> bus_certif) {
                this.bus_certif = bus_certif;
            }

            public static class BusServiceListBean  {
                /**
                 * amount : 120
                 * bus_service_list : walking
                 * time_slots : 15 mins
                 */

                private int amount;
                private String bus_service_list;
                private String time_slots;


                public int getAmount() {
                    return amount;
                }

                public void setAmount(int amount) {
                    this.amount = amount;

                }


                public String getBus_service_list() {
                    return bus_service_list;
                }

                public void setBus_service_list(String bus_service_list) {
                    this.bus_service_list = bus_service_list;

                }


                public String getTime_slots() {
                    return time_slots;
                }

                public void setTime_slots(String time_slots) {
                    this.time_slots = time_slots;
                }
            }

            public static class BusSpecListBean  {
                /**
                 * bus_spec_list : Specialization - 1
                 */

                private String bus_spec_list;

                public String getBus_spec_list() {
                    return bus_spec_list;
                }

                public void setBus_spec_list(String bus_spec_list) {
                    this.bus_spec_list = bus_spec_list;

                }
            }

            public static class BusServiceGallBean {
                /**
                 * bus_service_gall : http://52.25.163.13:3000/api/uploads/601ccbe2a66e453d0df7a44e05-02-2021 10:35 AMPetfolio1.jpg
                 */

                private String bus_service_gall;


                public String getBus_service_gall() {
                    return bus_service_gall;
                }

                public void setBus_service_gall(String bus_service_gall) {
                    this.bus_service_gall = bus_service_gall;
                }
            }

            public static class BusCertifBean  {
                /**
                 * bus_certif : http://52.25.163.13:3000/api/uploads/601ccbe2a66e453d0df7a44e05-02-2021 10:35 AM0001019010010862809_12132020_01022021.PDF
                 */

                private String bus_certif;


                public String getBus_certif() {
                    return bus_certif;
                }

                public void setBus_certif(String bus_certif) {
                    this.bus_certif = bus_certif;

                }
            }
        }
    }
}
