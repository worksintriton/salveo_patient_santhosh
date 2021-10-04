package com.salveo.mysalveo.responsepojo;

import java.util.List;

public class SPAppointmentResponse {

    /**
     * Status : Success
     * Message : New Appointment List
     * Data : [{"sp_attched":[],"sp_business_info":[],"_id":"5fe43fd828da3a5a85c4e16d","sp_id":"5fe1e675094d0471dabf9295","appointment_UID":"PET-1608794072560","booking_date":"01-12-2020","booking_time":"07:00 PM","booking_date_time":"01-12-2020 07:00 PM","user_id":{"_id":"5fd841a67aa4cc1c6a1e5636","first_name":"Kumar","last_name":"Dk","user_email":"iddineshkumar@gmail.com","user_phone":"6383791451","date_of_reg":"15/12/2020 10:25 AM","user_type":1,"user_status":"complete","otp":401174,"fb_token":"cUn_DCONTxiGpGGz3OkbTF:APA91bHyMBzQbjPWY90u_jH9KZFLvDJNuk3YlTSXMPEAvp2dXaXJb4fV1W2u9ZEnONnESBis5OS2SoQPZvfyN_zeS1T43Sa_ySOO2RtMnKihTWpINsoGv83ndCa5PUW2Q7kBT6ExIXNq","device_id":"","device_type":"","mobile_type":"Android","delete_status":false,"__v":0,"updatedAt":"2020-12-24T06:13:14.527Z"},"pet_id":{"_id":"5fd842507aa4cc1c6a1e5637","user_id":"5fd841a67aa4cc1c6a1e5636","pet_img":"http://52.25.163.13:3000/api/uploads/5fd841a67aa4cc1c6a1e563615-12-2020 10:29 AMPetfolio1.jpg","pet_name":"DOG","pet_type":"Dog","pet_breed":"Bull dog","pet_gender":"Male","pet_color":"Gray","pet_weight":5,"pet_age":1,"vaccinated":true,"last_vaccination_date":"14-12-2020","default_status":true,"date_and_time":"15-12-2020 10:27 AM","mobile_type":"Android","__v":0},"appoinment_status":"Incomplete","start_appointment_status":"Not Started","end_appointment_status":"Not End","user_feedback":"","user_rate":"0","display_date":"","server_date_time":"","payment_method":"","appointment_types":"","allergies":"cbbnmk","payment_id":"","amount":"0","service_name":"asdfasdf","service_amount":"200","completed_at":"","missed_at":"","mobile_type":"Android","delete_status":false,"updatedAt":"2020-12-24T07:14:32.566Z","createdAt":"2020-12-24T07:14:32.564Z","__v":0}]
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

    public static class DataBean {
        /**
         * sp_attched : []
         * sp_business_info : []
         * _id : 5fe43fd828da3a5a85c4e16d
         * sp_id : 5fe1e675094d0471dabf9295
         * appointment_UID : PET-1608794072560
         * booking_date : 01-12-2020
         * booking_time : 07:00 PM
         * booking_date_time : 01-12-2020 07:00 PM
         * user_id : {"_id":"5fd841a67aa4cc1c6a1e5636","first_name":"Kumar","last_name":"Dk","user_email":"iddineshkumar@gmail.com","user_phone":"6383791451","date_of_reg":"15/12/2020 10:25 AM","user_type":1,"user_status":"complete","otp":401174,"fb_token":"cUn_DCONTxiGpGGz3OkbTF:APA91bHyMBzQbjPWY90u_jH9KZFLvDJNuk3YlTSXMPEAvp2dXaXJb4fV1W2u9ZEnONnESBis5OS2SoQPZvfyN_zeS1T43Sa_ySOO2RtMnKihTWpINsoGv83ndCa5PUW2Q7kBT6ExIXNq","device_id":"","device_type":"","mobile_type":"Android","delete_status":false,"__v":0,"updatedAt":"2020-12-24T06:13:14.527Z"}
         * pet_id : {"_id":"5fd842507aa4cc1c6a1e5637","user_id":"5fd841a67aa4cc1c6a1e5636","pet_img":"http://52.25.163.13:3000/api/uploads/5fd841a67aa4cc1c6a1e563615-12-2020 10:29 AMPetfolio1.jpg","pet_name":"DOG","pet_type":"Dog","pet_breed":"Bull dog","pet_gender":"Male","pet_color":"Gray","pet_weight":5,"pet_age":1,"vaccinated":true,"last_vaccination_date":"14-12-2020","default_status":true,"date_and_time":"15-12-2020 10:27 AM","mobile_type":"Android","__v":0}
         * appoinment_status : Incomplete
         * start_appointment_status : Not Started
         * end_appointment_status : Not End
         * user_feedback :
         * user_rate : 0
         * display_date :
         * server_date_time :
         * payment_method :
         * appointment_types :
         * allergies : cbbnmk
         * payment_id :
         * amount : 0
         * service_name : asdfasdf
         * service_amount : 200
         * completed_at :
         * missed_at :
         * mobile_type : Android
         * delete_status : false
         * updatedAt : 2020-12-24T07:14:32.566Z
         * createdAt : 2020-12-24T07:14:32.564Z
         * __v : 0
         */

        private String _id;
        private String sp_id;
        private String appointment_UID;
        private String booking_date;
        private String booking_time;
        private String booking_date_time;
        private UserIdBean user_id;
        private PetIdBean pet_id;
        private String appoinment_status;
        private String start_appointment_status;
        private String end_appointment_status;
        private String user_feedback;
        private String user_rate;
        private String display_date;
        private String server_date_time;
        private String payment_method;
        private String appointment_types;
        private String allergies;
        private String payment_id;
        private String amount;
        private String service_name;
        private String service_amount;
        private String completed_at;
        private String missed_at;
        private String mobile_type;
        private boolean delete_status;
        private String updatedAt;
        private String createdAt;
        private int __v;
        private List<?> sp_attched;
        private List<?> sp_business_info;


        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;

        }


        public String getSp_id() {
            return sp_id;
        }

        public void setSp_id(String sp_id) {
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


        public List<?> getSp_business_info() {
            return sp_business_info;
        }

        public void setSp_business_info(List<?> sp_business_info) {
            this.sp_business_info = sp_business_info;
        }

        public static class UserIdBean  {
            /**
             * _id : 5fd841a67aa4cc1c6a1e5636
             * first_name : Kumar
             * last_name : Dk
             * user_email : iddineshkumar@gmail.com
             * user_phone : 6383791451
             * date_of_reg : 15/12/2020 10:25 AM
             * user_type : 1
             * user_status : complete
             * otp : 401174
             * fb_token : cUn_DCONTxiGpGGz3OkbTF:APA91bHyMBzQbjPWY90u_jH9KZFLvDJNuk3YlTSXMPEAvp2dXaXJb4fV1W2u9ZEnONnESBis5OS2SoQPZvfyN_zeS1T43Sa_ySOO2RtMnKihTWpINsoGv83ndCa5PUW2Q7kBT6ExIXNq
             * device_id :
             * device_type :
             * mobile_type : Android
             * delete_status : false
             * __v : 0
             * updatedAt : 2020-12-24T06:13:14.527Z
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
            private String fb_token;
            private String device_id;
            private String device_type;
            private String mobile_type;
            private boolean delete_status;
            private int __v;
            private String updatedAt;


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


            public int get__v() {
                return __v;
            }

            public void set__v(int __v) {
                this.__v = __v;
            }


            public String getUpdatedAt() {
                return updatedAt;
            }

            public void setUpdatedAt(String updatedAt) {
                this.updatedAt = updatedAt;
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
    }
}
