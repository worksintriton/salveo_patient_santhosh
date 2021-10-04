package com.salveo.mysalveo.requestpojo;

import java.util.List;

public class DocBusInfoUploadRequest {


    /**
     * user_id : 5fb22773e70b0d3cc5b2c19d
     * dr_title : Dr
     * dr_name : mohammed
     * clinic_name : Mohammed Clinic
     * clinic_loc : Muthamil nager Kodugaiyur
     * clinic_lat : 18.12
     * clinic_long : 80.0987
     * education_details : [{"education":"10 th pass","year":"2020"},{"education":"10 th pass","year":"2020"}]
     * experience_details : [{"company":"triton it slotuions","from":"22-10-2020","to":"22-10-2021"},{"company":"triton it slotuions","from":"22-10-2020","to":"22-10-2021"},{"company":"triton it slotuions","from":"22-10-2020","to":"22-10-2021"}]
     * specialization : [{"specialization":"Spec - 1"},{"specialization":"Spec - 1"},{"specialization":"Spec - 1"}]
     * pet_handled : [{"pet_handled":"Pet - 1"},{"pet_handled":"Pet - 1"}]
     * clinic_pic : [{"clinic_pic":""},{"clinic_pic":""}]
     * certificate_pic : [{"certificate_pic":""},{"certificate_pic":""}]
     * govt_id_pic : [{"govt_id_pic":""},{"govt_id_pic":""}]
     * photo_id_pic : [{"photo_id_pic":""},{"photo_id_pic":""}]
     * profile_status : 0
     * profile_verification_status : Not verified
     * date_and_time : 23-10-2020 11:10 AM
     * mobile_type : Android
     * communication_type  : 1
     * consultancy_fees
     * city_name : Chennai
     */

    private String user_id;
    private String dr_title;
    private String dr_name;
    private String clinic_name;
    private String clinic_loc;
    private double clinic_lat;
    private double clinic_long;

    private String mobile_type;
    private String communication_type;
    private int consultancy_fees;
    private String signature;
    private String city_name;

    private String doctor_info;
    private String clinic_no ;
    private String doctor_id;

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

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }



    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public int getConsultancy_fees() {
        return consultancy_fees;
    }

    public void setConsultancy_fees(int consultancy_fees) {
        this.consultancy_fees = consultancy_fees;
    }

    public String getCommunication_type() {
        return communication_type;
    }

    public void setCommunication_type(String communication_type) {
        this.communication_type = communication_type;
    }

    public String getMobile_type() {
        return mobile_type;
    }

    public void setMobile_type(String mobile_type) {
        this.mobile_type = mobile_type;
    }


    /**
     * education : 10 th pass
     * year : 2020
     */

    private List<EducationDetailsBean> education_details;
    /**
     * company : triton it slotuions
     * from : 22-10-2020
     * to : 22-10-2021
     */

    private List<ExperienceDetailsBean> experience_details;
    /**
     * specialization : Spec - 1
     */

    private List<SpecializationBean> specialization;
    /**
     * pet_handled : Pet - 1
     */

    private List<PetHandledBean> pet_handled;
    /**
     * clinic_pic :
     */

    private List<ClinicPicBean> clinic_pic;
    /**
     * certificate_pic :
     */

    private List<CertificatePicBean> certificate_pic;
    /**
     * govt_id_pic :
     */

    private List<GovtIdPicBean> govt_id_pic;
    /**
     * photo_id_pic :
     */

    private List<PhotoIdPicBean> photo_id_pic;

    private boolean profile_status;

    public boolean isProfile_status() {
        return profile_status;
    }

    public void setProfile_status(boolean profile_status) {
        this.profile_status = profile_status;
    }

    private String profile_verification_status;
    private String date_and_time;



    public DocBusInfoUploadRequest(String user_id, String dr_title, String dr_name, String clinic_name, String clinic_loc, double clinic_lat, double clinic_long, List<EducationDetailsBean> education_details, List<ExperienceDetailsBean> experience_details, List<SpecializationBean> specialization, List<PetHandledBean> pet_handled, List<ClinicPicBean> clinic_pic, List<CertificatePicBean> certificate_pic, List<GovtIdPicBean> govt_id_pic, List<PhotoIdPicBean> photo_id_pic, boolean profile_status, String profile_verification_status, String date_and_time,String mobile_type,String communication_type,int consultancy_fees, String signature,String city_name,String doctor_info,String clinic_no,String doctor_id) {
        this.user_id = user_id;
        this.dr_title = dr_title;
        this.dr_name = dr_name;
        this.clinic_name = clinic_name;
        this.clinic_loc = clinic_loc;
        this.clinic_lat = clinic_lat;
        this.clinic_long = clinic_long;
        this.education_details = education_details;
        this.experience_details = experience_details;
        this.specialization = specialization;
        this.pet_handled = pet_handled;
        this.clinic_pic = clinic_pic;
        this.certificate_pic = certificate_pic;
        this.govt_id_pic = govt_id_pic;
        this.photo_id_pic = photo_id_pic;
        this.profile_status = profile_status;
        this.profile_verification_status = profile_verification_status;
        this.date_and_time = date_and_time;
        this.mobile_type = mobile_type;
        this.communication_type = communication_type;
        this.consultancy_fees = consultancy_fees;
        this.signature = signature;
        this.city_name=city_name;
        this.doctor_info =doctor_info;
        this.doctor_id=doctor_id;
        this.clinic_no=clinic_no;
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


    public String getProfile_verification_status() {
        return profile_verification_status;
    }

    public void setProfile_verification_status(String profile_verification_status) {
        this.profile_verification_status = profile_verification_status;
    }

    public String getDate_and_time() {
        return date_and_time;
    }

    public void setDate_and_time(String date_and_time) {
        this.date_and_time = date_and_time;
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

        public EducationDetailsBean(String education, String year) {
            this.education = education;
            this.year = year;
        }

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

        public int getYearsofexperience() {
            return yearsofexperience;
        }

        public void setYearsofexperience(int yearsofexperience) {
            this.yearsofexperience = yearsofexperience;
        }

        public ExperienceDetailsBean(String company, String from, String to, int yearsofexperience) {
            this.company = company;
            this.from = from;
            this.to = to;
            this.yearsofexperience = yearsofexperience;
        }

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
    }

    public static class SpecializationBean {
        private String specialization;

        public SpecializationBean(String specialization) {
            this.specialization = specialization;
        }

        public String getSpecialization() {
            return specialization;
        }

        public void setSpecialization(String specialization) {
            this.specialization = specialization;
        }

        @Override
        public String toString() {
            return "SpecializationBean{" +
                    "specialization='" + specialization + '\'' +
                    '}';
        }
    }

    public static class PetHandledBean {
        private String pet_handled;

        public PetHandledBean(String pet_handled) {
            this.pet_handled = pet_handled;
        }

        public String getPet_handled() {
            return pet_handled;
        }

        public void setPet_handled(String pet_handled) {
            this.pet_handled = pet_handled;
        }

        @Override
        public String toString() {
            return "PetHandledBean{" +
                    "pet_handled='" + pet_handled + '\'' +
                    '}';
        }
    }

    public static class ClinicPicBean {
        private String clinic_pic;

        public ClinicPicBean(String clinic_pic) {
            this.clinic_pic = clinic_pic;
        }

        public String getClinic_pic() {
            return clinic_pic;
        }

        public void setClinic_pic(String clinic_pic) {
            this.clinic_pic = clinic_pic;
        }
    }

    public static class CertificatePicBean {

        private String certificate_pic;

        public CertificatePicBean(String certificate_pic) {
            this.certificate_pic = certificate_pic;
        }

        public String getCertificate_pic() {
            return certificate_pic;
        }

        public void setCertificate_pic(String certificate_pic) {
            this.certificate_pic = certificate_pic;
        }
    }

    public static class GovtIdPicBean {
        private String govt_id_pic;

        public GovtIdPicBean(String govt_id_pic) {
            this.govt_id_pic = govt_id_pic;
        }

        public String getGovt_id_pic() {
            return govt_id_pic;
        }

        public void setGovt_id_pic(String govt_id_pic) {
            this.govt_id_pic = govt_id_pic;
        }
    }

    public static class PhotoIdPicBean {
        private String photo_id_pic;

        public PhotoIdPicBean(String photo_id_pic) {
            this.photo_id_pic = photo_id_pic;
        }

        public String getPhoto_id_pic() {
            return photo_id_pic;
        }

        public void setPhoto_id_pic(String photo_id_pic) {
            this.photo_id_pic = photo_id_pic;
        }
    }
}
