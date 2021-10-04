package com.salveo.mysalveo.responsepojo;

import java.util.List;

public class DoctorDetailsByUserIdResponse {


    /**
     * Status : Success
     * Message : Docotor Details
     * Data : {"education_details":[{"education":"BDS","year":"2021"}],"experience_details":[{"company":"GH Vetnerian","from":"2020","to":"2021","yearsofexperience":1}],"specialization":[{"specialization":"General Consultant"},{"specialization":"Virology"},{"specialization":"Microbiology"},{"specialization":"Pathology"},{"specialization":"Theriogenology"},{"specialization":"Equine Medicine"},{"specialization":"Radiation Oncology"},{"specialization":"Behavior"},{"specialization":"Surgery"},{"specialization":"Nutrition"},{"specialization":"Dentistry"},{"specialization":"Laboratory Animal Medicine"},{"specialization":"Radiology"},{"specialization":"Emergency and Critical Care"},{"specialization":"Orthopaedics"}],"pet_handled":[{"pet_handled":"Dogs - (Indian & Foreign) "},{"pet_handled":"Small pets - (Guinea Pigs, Hamsters, Iguanas, Mice and Rats, Rabbit, Turtles)"},{"pet_handled":"Fishes"},{"pet_handled":"Pet Birds"},{"pet_handled":"Cats"},{"pet_handled":"Farm Animals (Cattle, Goat, Pig, Sheep, Buffalo, Donkey)"},{"pet_handled":"Horses"},{"pet_handled":"Domestic Birds"}],"clinic_pic":[{"clinic_pic":"http://54.212.108.156:3000/api/uploads/1626176483388.jpg"}],"certificate_pic":[{"certificate_pic":"http://54.212.108.156:3000/api/uploads/1626176494505.pdf"}],"govt_id_pic":[{"govt_id_pic":"http://54.212.108.156:3000/api/uploads/1626176501807.pdf"}],"photo_id_pic":[{"photo_id_pic":"http://54.212.108.156:3000/api/uploads/1626176508397.pdf"}],"_id":"60ed7c13e22ae154a8915bd9","user_id":"60ed7afee22ae154a8915bd8","dr_title":"Dr","dr_name":"Maddy San","clinic_name":"Maddy San Pet Clinic","clinic_loc":"Salem 133, Vmr Theatre Back Side Rd, Shevapet, Salem, Tamil Nadu 636002, India","clinic_lat":11.6617776,"clinic_long":78.1375217,"thumbnail_image":"","profile_status":true,"profile_verification_status":"Verified","slot_type":"","date_and_time":"13/07/2021 05:12:10","signature":"http://54.212.108.156:3000/api/uploads/1626176527437.jpg","mobile_type":"Android","communication_type":"Online","live_status":"Not Live","live_by":"","delete_status":false,"consultancy_fees":200,"calender_status":true,"comments":0,"city_name":"Salem","rating":5,"doctor_exp":1,"doctor_info":"HardWorking Guy","clinic_no":"987987989901","doctor_id":"DR12345678","updatedAt":"2021-07-13T11:45:57.272Z","createdAt":"2021-07-13T11:42:11.273Z","__v":0}
     * Code : 200
     */

    private String Status;
    private String Message;
    /**
     * education_details : [{"education":"BDS","year":"2021"}]
     * experience_details : [{"company":"GH Vetnerian","from":"2020","to":"2021","yearsofexperience":1}]
     * specialization : [{"specialization":"General Consultant"},{"specialization":"Virology"},{"specialization":"Microbiology"},{"specialization":"Pathology"},{"specialization":"Theriogenology"},{"specialization":"Equine Medicine"},{"specialization":"Radiation Oncology"},{"specialization":"Behavior"},{"specialization":"Surgery"},{"specialization":"Nutrition"},{"specialization":"Dentistry"},{"specialization":"Laboratory Animal Medicine"},{"specialization":"Radiology"},{"specialization":"Emergency and Critical Care"},{"specialization":"Orthopaedics"}]
     * pet_handled : [{"pet_handled":"Dogs - (Indian & Foreign) "},{"pet_handled":"Small pets - (Guinea Pigs, Hamsters, Iguanas, Mice and Rats, Rabbit, Turtles)"},{"pet_handled":"Fishes"},{"pet_handled":"Pet Birds"},{"pet_handled":"Cats"},{"pet_handled":"Farm Animals (Cattle, Goat, Pig, Sheep, Buffalo, Donkey)"},{"pet_handled":"Horses"},{"pet_handled":"Domestic Birds"}]
     * clinic_pic : [{"clinic_pic":"http://54.212.108.156:3000/api/uploads/1626176483388.jpg"}]
     * certificate_pic : [{"certificate_pic":"http://54.212.108.156:3000/api/uploads/1626176494505.pdf"}]
     * govt_id_pic : [{"govt_id_pic":"http://54.212.108.156:3000/api/uploads/1626176501807.pdf"}]
     * photo_id_pic : [{"photo_id_pic":"http://54.212.108.156:3000/api/uploads/1626176508397.pdf"}]
     * _id : 60ed7c13e22ae154a8915bd9
     * user_id : 60ed7afee22ae154a8915bd8
     * dr_title : Dr
     * dr_name : Maddy San
     * clinic_name : Maddy San Pet Clinic
     * clinic_loc : Salem 133, Vmr Theatre Back Side Rd, Shevapet, Salem, Tamil Nadu 636002, India
     * clinic_lat : 11.6617776
     * clinic_long : 78.1375217
     * thumbnail_image :
     * profile_status : true
     * profile_verification_status : Verified
     * slot_type :
     * date_and_time : 13/07/2021 05:12:10
     * signature : http://54.212.108.156:3000/api/uploads/1626176527437.jpg
     * mobile_type : Android
     * communication_type : Online
     * live_status : Not Live
     * live_by :
     * delete_status : false
     * consultancy_fees : 200
     * calender_status : true
     * comments : 0
     * city_name : Salem
     * rating : 5
     * doctor_exp : 1
     * doctor_info : HardWorking Guy
     * clinic_no : 987987989901
     * doctor_id : DR12345678
     * updatedAt : 2021-07-13T11:45:57.272Z
     * createdAt : 2021-07-13T11:42:11.273Z
     * __v : 0
     */

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
        private String _id;
        private String user_id;
        private String dr_title;
        private String dr_name;
        private String clinic_name;
        private String clinic_loc;
        private double clinic_lat;
        private double clinic_long;
        private String thumbnail_image;
        private boolean profile_status;
        private String profile_verification_status;
        private String slot_type;
        private String date_and_time;
        private String signature;
        private String mobile_type;
        private String communication_type;
        private String live_status;
        private String live_by;
        private boolean delete_status;
        private int consultancy_fees;
        private boolean calender_status;
        private int comments;
        private String city_name;
        private int rating;
        private int doctor_exp;
        private String doctor_info;
        private String clinic_no;
        private String doctor_id;
        private String updatedAt;
        private String createdAt;
        private int __v;
        /**
         * education : BDS
         * year : 2021
         */

        private List<EducationDetailsBean> education_details;
        /**
         * company : GH Vetnerian
         * from : 2020
         * to : 2021
         * yearsofexperience : 1
         */

        private List<ExperienceDetailsBean> experience_details;
        /**
         * specialization : General Consultant
         */

        private List<SpecializationBean> specialization;
        /**
         * pet_handled : Dogs - (Indian & Foreign)
         */

        private List<PetHandledBean> pet_handled;
        /**
         * clinic_pic : http://54.212.108.156:3000/api/uploads/1626176483388.jpg
         */

        private List<ClinicPicBean> clinic_pic;
        /**
         * certificate_pic : http://54.212.108.156:3000/api/uploads/1626176494505.pdf
         */

        private List<CertificatePicBean> certificate_pic;
        /**
         * govt_id_pic : http://54.212.108.156:3000/api/uploads/1626176501807.pdf
         */

        private List<GovtIdPicBean> govt_id_pic;
        /**
         * photo_id_pic : http://54.212.108.156:3000/api/uploads/1626176508397.pdf
         */

        private List<PhotoIdPicBean> photo_id_pic;

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

        public String getDr_title() {
            return dr_title;
        }

        public void setDr_title(String dr_title) {
            this.dr_title = dr_title;
        }

        public String getDr_name() {
            return dr_name;
        }

        public void setDr_name(String dr_name) {
            this.dr_name = dr_name;
        }

        public String getClinic_name() {
            return clinic_name;
        }

        public void setClinic_name(String clinic_name) {
            this.clinic_name = clinic_name;
        }

        public String getClinic_loc() {
            return clinic_loc;
        }

        public void setClinic_loc(String clinic_loc) {
            this.clinic_loc = clinic_loc;
        }

        public double getClinic_lat() {
            return clinic_lat;
        }

        public void setClinic_lat(double clinic_lat) {
            this.clinic_lat = clinic_lat;
        }

        public double getClinic_long() {
            return clinic_long;
        }

        public void setClinic_long(double clinic_long) {
            this.clinic_long = clinic_long;
        }

        public String getThumbnail_image() {
            return thumbnail_image;
        }

        public void setThumbnail_image(String thumbnail_image) {
            this.thumbnail_image = thumbnail_image;
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

        public String getSlot_type() {
            return slot_type;
        }

        public void setSlot_type(String slot_type) {
            this.slot_type = slot_type;
        }

        public String getDate_and_time() {
            return date_and_time;
        }

        public void setDate_and_time(String date_and_time) {
            this.date_and_time = date_and_time;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public String getMobile_type() {
            return mobile_type;
        }

        public void setMobile_type(String mobile_type) {
            this.mobile_type = mobile_type;
        }

        public String getCommunication_type() {
            return communication_type;
        }

        public void setCommunication_type(String communication_type) {
            this.communication_type = communication_type;
        }

        public String getLive_status() {
            return live_status;
        }

        public void setLive_status(String live_status) {
            this.live_status = live_status;
        }

        public String getLive_by() {
            return live_by;
        }

        public void setLive_by(String live_by) {
            this.live_by = live_by;
        }

        public boolean isDelete_status() {
            return delete_status;
        }

        public void setDelete_status(boolean delete_status) {
            this.delete_status = delete_status;
        }

        public int getConsultancy_fees() {
            return consultancy_fees;
        }

        public void setConsultancy_fees(int consultancy_fees) {
            this.consultancy_fees = consultancy_fees;
        }

        public boolean isCalender_status() {
            return calender_status;
        }

        public void setCalender_status(boolean calender_status) {
            this.calender_status = calender_status;
        }

        public int getComments() {
            return comments;
        }

        public void setComments(int comments) {
            this.comments = comments;
        }

        public String getCity_name() {
            return city_name;
        }

        public void setCity_name(String city_name) {
            this.city_name = city_name;
        }

        public int getRating() {
            return rating;
        }

        public void setRating(int rating) {
            this.rating = rating;
        }

        public int getDoctor_exp() {
            return doctor_exp;
        }

        public void setDoctor_exp(int doctor_exp) {
            this.doctor_exp = doctor_exp;
        }

        public String getDoctor_info() {
            return doctor_info;
        }

        public void setDoctor_info(String doctor_info) {
            this.doctor_info = doctor_info;
        }

        public String getClinic_no() {
            return clinic_no;
        }

        public void setClinic_no(String clinic_no) {
            this.clinic_no = clinic_no;
        }

        public String getDoctor_id() {
            return doctor_id;
        }

        public void setDoctor_id(String doctor_id) {
            this.doctor_id = doctor_id;
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

        public List<EducationDetailsBean> getEducation_details() {
            return education_details;
        }

        public void setEducation_details(List<EducationDetailsBean> education_details) {
            this.education_details = education_details;
        }

        public List<ExperienceDetailsBean> getExperience_details() {
            return experience_details;
        }

        public void setExperience_details(List<ExperienceDetailsBean> experience_details) {
            this.experience_details = experience_details;
        }

        public List<SpecializationBean> getSpecialization() {
            return specialization;
        }

        public void setSpecialization(List<SpecializationBean> specialization) {
            this.specialization = specialization;
        }

        public List<PetHandledBean> getPet_handled() {
            return pet_handled;
        }

        public void setPet_handled(List<PetHandledBean> pet_handled) {
            this.pet_handled = pet_handled;
        }

        public List<ClinicPicBean> getClinic_pic() {
            return clinic_pic;
        }

        public void setClinic_pic(List<ClinicPicBean> clinic_pic) {
            this.clinic_pic = clinic_pic;
        }

        public List<CertificatePicBean> getCertificate_pic() {
            return certificate_pic;
        }

        public void setCertificate_pic(List<CertificatePicBean> certificate_pic) {
            this.certificate_pic = certificate_pic;
        }

        public List<GovtIdPicBean> getGovt_id_pic() {
            return govt_id_pic;
        }

        public void setGovt_id_pic(List<GovtIdPicBean> govt_id_pic) {
            this.govt_id_pic = govt_id_pic;
        }

        public List<PhotoIdPicBean> getPhoto_id_pic() {
            return photo_id_pic;
        }

        public void setPhoto_id_pic(List<PhotoIdPicBean> photo_id_pic) {
            this.photo_id_pic = photo_id_pic;
        }

        public static class EducationDetailsBean {
            private String education;
            private String year;

            public String getEducation() {
                return education;
            }

            public void setEducation(String education) {
                this.education = education;
            }

            public String getYear() {
                return year;
            }

            public void setYear(String year) {
                this.year = year;
            }
        }

        public static class ExperienceDetailsBean {
            private String company;
            private String from;
            private String to;
            private int yearsofexperience;

            public String getCompany() {
                return company;
            }

            public void setCompany(String company) {
                this.company = company;
            }

            public String getFrom() {
                return from;
            }

            public void setFrom(String from) {
                this.from = from;
            }

            public String getTo() {
                return to;
            }

            public void setTo(String to) {
                this.to = to;
            }

            public int getYearsofexperience() {
                return yearsofexperience;
            }

            public void setYearsofexperience(int yearsofexperience) {
                this.yearsofexperience = yearsofexperience;
            }
        }

        public static class SpecializationBean {
            private String specialization;

            public String getSpecialization() {
                return specialization;
            }

            public void setSpecialization(String specialization) {
                this.specialization = specialization;
            }
        }

        public static class PetHandledBean {
            private String pet_handled;

            public String getPet_handled() {
                return pet_handled;
            }

            public void setPet_handled(String pet_handled) {
                this.pet_handled = pet_handled;
            }
        }

        public static class ClinicPicBean {
            private String clinic_pic;

            public String getClinic_pic() {
                return clinic_pic;
            }

            public void setClinic_pic(String clinic_pic) {
                this.clinic_pic = clinic_pic;
            }
        }

        public static class CertificatePicBean {
            private String certificate_pic;

            public String getCertificate_pic() {
                return certificate_pic;
            }

            public void setCertificate_pic(String certificate_pic) {
                this.certificate_pic = certificate_pic;
            }
        }

        public static class GovtIdPicBean {
            private String govt_id_pic;

            public String getGovt_id_pic() {
                return govt_id_pic;
            }

            public void setGovt_id_pic(String govt_id_pic) {
                this.govt_id_pic = govt_id_pic;
            }
        }

        public static class PhotoIdPicBean {
            private String photo_id_pic;

            public String getPhoto_id_pic() {
                return photo_id_pic;
            }

            public void setPhoto_id_pic(String photo_id_pic) {
                this.photo_id_pic = photo_id_pic;
            }
        }
    }
}
