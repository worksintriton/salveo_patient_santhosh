package com.salveo.mysalveo.responsepojo;

import java.util.List;

public class PrescriptionFetchResponse {


    /**
     * Status : Success
     * Message : Prescription detail
     * Data : {"doctorname":"Maddy Sam","doctor_speci":[{"specialization":"General Consultant"},{"specialization":"Virology"},{"specialization":"Pathology"},{"specialization":"Microbiology"},{"specialization":"Theriogenology"},{"specialization":"Internal Medicine"},{"specialization":"Behavior"},{"specialization":"Radiation Oncology"},{"specialization":"Equine Medicine"},{"specialization":"XYZ"},{"specialization":"Emergency Services"},{"specialization":"Dermitology"},{"specialization":"Medicinal Specialist"},{"specialization":"KARNATAKA"}],"web_name":"www.petfolio.com","phone_number":"+91-9988776655","app_logo":"http://54.212.108.156:3000/api/uploads/logo.png","owner_name":"Maddy","relation":"children","name":"Sam","gender":"Male","age":26,"height":"5","weight":"60","anymedicalinfo":"no medical information about the patient","diagnosis":"Urinary System","sub_diagnosis":" Kidney stone ","allergies":"","Doctor_Comments":"take medicine regularly","digital_sign":"http://54.212.108.156:3000/api/uploads/1630923134431.jpg","Prescription_data":[{"Quantity":"2","Tablet_name":"dolo 650","consumption":"Morning - 1, Afternoon - 0, Night - 1"}],"_id":"613751328465476195b69d36","doctor_id":"6135e70590b45623fcf93a60","Appointment_ID":"613750648465476195b69d2f","Treatment_Done_by":"","user_id":"61374ff68465476195b69d2d","Prescription_type":"PDF","PDF_format":"http://13.57.9.246:3000/api/public/s/public/202dd617-81be-42fb-b9b4-0099da4feb85.pdf","Prescription_img":"","Date":"07/09/2021 05:16 PM","delete_status":false,"updatedAt":"2021-09-07T11:46:58.934Z","createdAt":"2021-09-07T11:46:58.934Z","health_issue_title":"General Checkup","__v":0}
     * Code : 200
     */

    private String Status;
    private String Message;
    /**
     * doctorname : Maddy Sam
     * doctor_speci : [{"specialization":"General Consultant"},{"specialization":"Virology"},{"specialization":"Pathology"},{"specialization":"Microbiology"},{"specialization":"Theriogenology"},{"specialization":"Internal Medicine"},{"specialization":"Behavior"},{"specialization":"Radiation Oncology"},{"specialization":"Equine Medicine"},{"specialization":"XYZ"},{"specialization":"Emergency Services"},{"specialization":"Dermitology"},{"specialization":"Medicinal Specialist"},{"specialization":"KARNATAKA"}]
     * web_name : www.petfolio.com
     * phone_number : +91-9988776655
     * app_logo : http://54.212.108.156:3000/api/uploads/logo.png
     * owner_name : Maddy
     * relation : children
     * name : Sam
     * gender : Male
     * age : 26
     * height : 5
     * weight : 60
     * anymedicalinfo : no medical information about the patient
     * diagnosis : Urinary System
     * sub_diagnosis :  Kidney stone
     * allergies :
     * Doctor_Comments : take medicine regularly
     * digital_sign : http://54.212.108.156:3000/api/uploads/1630923134431.jpg
     * Prescription_data : [{"Quantity":"2","Tablet_name":"dolo 650","consumption":"Morning - 1, Afternoon - 0, Night - 1"}]
     * _id : 613751328465476195b69d36
     * doctor_id : 6135e70590b45623fcf93a60
     * Appointment_ID : 613750648465476195b69d2f
     * Treatment_Done_by :
     * user_id : 61374ff68465476195b69d2d
     * Prescription_type : PDF
     * PDF_format : http://13.57.9.246:3000/api/public/s/public/202dd617-81be-42fb-b9b4-0099da4feb85.pdf
     * Prescription_img :
     * Date : 07/09/2021 05:16 PM
     * delete_status : false
     * updatedAt : 2021-09-07T11:46:58.934Z
     * createdAt : 2021-09-07T11:46:58.934Z
     * health_issue_title : General Checkup
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
        private String doctorname;
        private String web_name;
        private String phone_number;
        private String app_logo;
        private String owner_name;
        private String relation;
        private String name;
        private String gender;
        private int age;
        private String height;
        private String weight;
        private String anymedicalinfo;
        private String diagnosis;
        private String sub_diagnosis;
        private String allergies;
        private String Doctor_Comments;
        private String digital_sign;
        private String _id;
        private String doctor_id;
        private String Appointment_ID;
        private String Treatment_Done_by;
        private String user_id;
        private String Prescription_type;
        private String PDF_format;
        private String Prescription_img;
        private String Date;
        private boolean delete_status;
        private String updatedAt;
        private String createdAt;
        private String health_issue_title;
        private int __v;
        /**
         * specialization : General Consultant
         */

        private List<DoctorSpeciBean> doctor_speci;
        /**
         * Quantity : 2
         * Tablet_name : dolo 650
         * consumption : Morning - 1, Afternoon - 0, Night - 1
         */

        private List<PrescriptionDataBean> Prescription_data;

        public String getDoctorname() {
            return doctorname;
        }

        public void setDoctorname(String doctorname) {
            this.doctorname = doctorname;
        }

        public String getWeb_name() {
            return web_name;
        }

        public void setWeb_name(String web_name) {
            this.web_name = web_name;
        }

        public String getPhone_number() {
            return phone_number;
        }

        public void setPhone_number(String phone_number) {
            this.phone_number = phone_number;
        }

        public String getApp_logo() {
            return app_logo;
        }

        public void setApp_logo(String app_logo) {
            this.app_logo = app_logo;
        }

        public String getOwner_name() {
            return owner_name;
        }

        public void setOwner_name(String owner_name) {
            this.owner_name = owner_name;
        }

        public String getRelation() {
            return relation;
        }

        public void setRelation(String relation) {
            this.relation = relation;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getAnymedicalinfo() {
            return anymedicalinfo;
        }

        public void setAnymedicalinfo(String anymedicalinfo) {
            this.anymedicalinfo = anymedicalinfo;
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

        public String getAllergies() {
            return allergies;
        }

        public void setAllergies(String allergies) {
            this.allergies = allergies;
        }

        public String getDoctor_Comments() {
            return Doctor_Comments;
        }

        public void setDoctor_Comments(String Doctor_Comments) {
            this.Doctor_Comments = Doctor_Comments;
        }

        public String getDigital_sign() {
            return digital_sign;
        }

        public void setDigital_sign(String digital_sign) {
            this.digital_sign = digital_sign;
        }

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

        public String getAppointment_ID() {
            return Appointment_ID;
        }

        public void setAppointment_ID(String Appointment_ID) {
            this.Appointment_ID = Appointment_ID;
        }

        public String getTreatment_Done_by() {
            return Treatment_Done_by;
        }

        public void setTreatment_Done_by(String Treatment_Done_by) {
            this.Treatment_Done_by = Treatment_Done_by;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getPrescription_type() {
            return Prescription_type;
        }

        public void setPrescription_type(String Prescription_type) {
            this.Prescription_type = Prescription_type;
        }

        public String getPDF_format() {
            return PDF_format;
        }

        public void setPDF_format(String PDF_format) {
            this.PDF_format = PDF_format;
        }

        public String getPrescription_img() {
            return Prescription_img;
        }

        public void setPrescription_img(String Prescription_img) {
            this.Prescription_img = Prescription_img;
        }

        public String getDate() {
            return Date;
        }

        public void setDate(String Date) {
            this.Date = Date;
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

        public String getHealth_issue_title() {
            return health_issue_title;
        }

        public void setHealth_issue_title(String health_issue_title) {
            this.health_issue_title = health_issue_title;
        }

        public int get__v() {
            return __v;
        }

        public void set__v(int __v) {
            this.__v = __v;
        }

        public List<DoctorSpeciBean> getDoctor_speci() {
            return doctor_speci;
        }

        public void setDoctor_speci(List<DoctorSpeciBean> doctor_speci) {
            this.doctor_speci = doctor_speci;
        }

        public List<PrescriptionDataBean> getPrescription_data() {
            return Prescription_data;
        }

        public void setPrescription_data(List<PrescriptionDataBean> Prescription_data) {
            this.Prescription_data = Prescription_data;
        }

        public static class DoctorSpeciBean {
            private String specialization;

            public String getSpecialization() {
                return specialization;
            }

            public void setSpecialization(String specialization) {
                this.specialization = specialization;
            }
        }

        public static class PrescriptionDataBean {
            private String Quantity;
            private String Tablet_name;
            private String consumption;

            public String getQuantity() {
                return Quantity;
            }

            public void setQuantity(String Quantity) {
                this.Quantity = Quantity;
            }

            public String getTablet_name() {
                return Tablet_name;
            }

            public void setTablet_name(String Tablet_name) {
                this.Tablet_name = Tablet_name;
            }

            public String getConsumption() {
                return consumption;
            }

            public void setConsumption(String consumption) {
                this.consumption = consumption;
            }
        }
    }
}
