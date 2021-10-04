package com.salveo.mysalveo.responsepojo;

import java.util.List;

public class MedicalHistoryResponse {

    /**
     * Status : Success
     * Message : Medical history
     * Data : [{"vet_image":"http://54.212.108.156:3000/api/uploads/1614851396738.jpeg","vet_name":"DineshKumar Deva","vet_spec":[{"specialization":"Surgeon"},{"specialization":"Internal Medicine Physician"},{"specialization":"Psychiatrist"},{"specialization":"Testing Spef"},{"specialization":"Dermatologist"}],"pet_name":"TEENU","pet_id":"60407c392c2b43125f8cb83c","appointement_id":"604090e72c2b43125f8cb84e","appointment_date":"23-10-2021 11:00 AM","allergies":"fever","vacination":true,"communication_type":"Online","prescrip_type":"PDF"},{"vet_image":"http://54.212.108.156:3000/api/uploads/1614842236226.jpg","vet_name":"Dr .Albert Doctor","vet_spec":[{"specialization":"Surgeon"},{"specialization":"Internal Medicine Physician"}],"pet_name":"TEENU","pet_id":"60407c392c2b43125f8cb83c","appointement_id":"6040abd72c2b43125f8cb87b","appointment_date":"04-03-2021 05:45 PM","allergies":"fever","vacination":true,"communication_type":"Online","prescrip_type":"PDF"},{"vet_image":"http://54.212.108.156:3000/api/uploads/1614851396738.jpeg","vet_name":"DineshKumar Deva","vet_spec":[{"specialization":"Surgeon"},{"specialization":"Internal Medicine Physician"},{"specialization":"Psychiatrist"},{"specialization":"Testing Spef"},{"specialization":"Dermatologist"}],"pet_name":"TEENU","pet_id":"60407c392c2b43125f8cb83c","appointement_id":"6041d4acf79be871d19049d5","appointment_date":"05-03-2021 08:00 PM","allergies":"fever","vacination":true,"communication_type":"Online","prescrip_type":"PDF"},{"vet_image":"http://54.212.108.156:3000/api/uploads/1614842236226.jpg","vet_name":"Dr .Albert Doctor","vet_spec":[{"specialization":"Surgeon"},{"specialization":"Internal Medicine Physician"}],"pet_name":"TEENU","pet_id":"60407c392c2b43125f8cb83c","appointement_id":"6051ba0b82dacc767f6fe6be","appointment_date":"17-03-2021 02:00 PM","allergies":"injection ","vacination":true,"communication_type":"Online","prescrip_type":"PDF"},{"vet_image":"http://54.212.108.156:3000/api/uploads/1614842236226.jpg","vet_name":"Dr .Albert Doctor","vet_spec":[{"specialization":"Surgeon"},{"specialization":"Internal Medicine Physician"}],"pet_name":"TEENU","pet_id":"60407c392c2b43125f8cb83c","appointement_id":"6051dfec61e3301d1a5105b1","appointment_date":"17-03-2021 04:45 PM","allergies":"fevrr","vacination":true,"communication_type":"Online","prescrip_type":"PDF"},{"vet_image":"http://54.212.108.156:3000/api/uploads/1614842236226.jpg","vet_name":"Dr .Albert Doctor","vet_spec":[{"specialization":"Surgeon"},{"specialization":"Internal Medicine Physician"}],"pet_name":"TEENU","pet_id":"60407c392c2b43125f8cb83c","appointement_id":"60544deff4448e09a8a24c9d","appointment_date":"22-03-2021 08:00 PM","allergies":"fever","vacination":true,"communication_type":"Online","prescrip_type":"PDF"},{"vet_image":"http://54.212.108.156:3000/api/uploads/1614842236226.jpg","vet_name":"Dr .Albert Doctor","vet_spec":[{"specialization":"Surgeon"},{"specialization":"Internal Medicine Physician"}],"pet_name":"TEENU","pet_id":"60407c392c2b43125f8cb83c","appointement_id":"6065b038eaf33967d7897cd3","appointment_date":"01-04-2021 08:00 PM","allergies":"fever","vacination":true,"communication_type":"Online","prescrip_type":"PDF"},{"vet_image":"http://54.212.108.156:3000/api/uploads/1614851396738.jpeg","vet_name":"DineshKumar Deva","vet_spec":[{"specialization":"Surgeon"},{"specialization":"Internal Medicine Physician"},{"specialization":"Psychiatrist"},{"specialization":"Testing Spef"},{"specialization":"Dermatologist"}],"pet_name":"TEENU","pet_id":"60407c392c2b43125f8cb83c","appointement_id":"60695d4a0934f7487ed7c493","appointment_date":"04-04-2021 11:30 PM","allergies":"fever","vacination":true,"communication_type":"Online","prescrip_type":"PDF"},{"vet_image":"http://54.212.108.156:3000/api/uploads/1614842236226.jpg","vet_name":"Dr .Albert Doctor","vet_spec":[{"specialization":"Surgeon"},{"specialization":"Internal Medicine Physician"}],"pet_name":"TEENU","pet_id":"60407c392c2b43125f8cb83c","appointement_id":"608f95260ce4f06a62055b5b","appointment_date":"03-05-2021 03:30 PM","allergies":"fever","vacination":true,"communication_type":"Online","prescrip_type":"PDF"},{"vet_image":"http://54.212.108.156:3000/api/uploads/1614842236226.jpg","vet_name":"Dr .Albert Doctor","vet_spec":[{"specialization":"Surgeon"},{"specialization":"Internal Medicine Physician"}],"pet_name":"TEENU","pet_id":"60407c392c2b43125f8cb83c","appointement_id":"608fafcb0ce4f06a62055b5e","appointment_date":"03-05-2021 07:45 PM","allergies":"fever","vacination":true,"communication_type":"Visit","prescrip_type":"PDF"}]
     * Code : 200
     */

    private String Status;
    private String Message;
    private int Code;
    /**
     * vet_image : http://54.212.108.156:3000/api/uploads/1614851396738.jpeg
     * vet_name : DineshKumar Deva
     * vet_spec : [{"specialization":"Surgeon"},{"specialization":"Internal Medicine Physician"},{"specialization":"Psychiatrist"},{"specialization":"Testing Spef"},{"specialization":"Dermatologist"}]
     * pet_name : TEENU
     * pet_id : 60407c392c2b43125f8cb83c
     * appointement_id : 604090e72c2b43125f8cb84e
     * appointment_date : 23-10-2021 11:00 AM
     * allergies : fever
     * vacination : true
     * communication_type : Online
     * prescrip_type : PDF
     */

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
        private String vet_image;
        private String vet_name;
        private String pet_name;
        private String pet_id;
        private String appointement_id;
        private String appointment_date;
        private String allergies;
        private boolean vacination;
        private String communication_type;
        private String prescrip_type;
        /**
         * specialization : Surgeon
         */

        private List<VetSpecBean> vet_spec;

        public String getVet_image() {
            return vet_image;
        }

        public void setVet_image(String vet_image) {
            this.vet_image = vet_image;
        }

        public String getVet_name() {
            return vet_name;
        }

        public void setVet_name(String vet_name) {
            this.vet_name = vet_name;
        }

        public String getPet_name() {
            return pet_name;
        }

        public void setPet_name(String pet_name) {
            this.pet_name = pet_name;
        }

        public String getPet_id() {
            return pet_id;
        }

        public void setPet_id(String pet_id) {
            this.pet_id = pet_id;
        }

        public String getAppointement_id() {
            return appointement_id;
        }

        public void setAppointement_id(String appointement_id) {
            this.appointement_id = appointement_id;
        }

        public String getAppointment_date() {
            return appointment_date;
        }

        public void setAppointment_date(String appointment_date) {
            this.appointment_date = appointment_date;
        }

        public String getAllergies() {
            return allergies;
        }

        public void setAllergies(String allergies) {
            this.allergies = allergies;
        }

        public boolean isVacination() {
            return vacination;
        }

        public void setVacination(boolean vacination) {
            this.vacination = vacination;
        }

        public String getCommunication_type() {
            return communication_type;
        }

        public void setCommunication_type(String communication_type) {
            this.communication_type = communication_type;
        }

        public String getPrescrip_type() {
            return prescrip_type;
        }

        public void setPrescrip_type(String prescrip_type) {
            this.prescrip_type = prescrip_type;
        }

        public List<VetSpecBean> getVet_spec() {
            return vet_spec;
        }

        public void setVet_spec(List<VetSpecBean> vet_spec) {
            this.vet_spec = vet_spec;
        }

        public static class VetSpecBean {
            private String specialization;

            public String getSpecialization() {
                return specialization;
            }

            public void setSpecialization(String specialization) {
                this.specialization = specialization;
            }
        }
    }
}
