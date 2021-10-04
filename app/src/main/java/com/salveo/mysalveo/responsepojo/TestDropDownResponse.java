package com.salveo.mysalveo.responsepojo;

import java.util.List;

public class TestDropDownResponse {


    /**
     * Status : Success
     * Message : Pet type list
     * Data : {"Pet_type":[{"pet_type":"Dod"},{"pet_type":"Cat"},{"pet_type":"Dod 2"},{"pet_type":"Dod 3"},{"pet_type":"Dod 4"},{"pet_type":"Dod 5"},{"pet_type":"Dod 6"}],"Pet_breed":[{"pet_breed":"breed 1"},{"pet_breed":"breed 2"},{"pet_breed":"breed 3"},{"pet_breed":"breed 4"},{"pet_breed":"breed 5"},{"pet_breed":"breed 6"}],"Gender":[{"gender":"Male"},{"gender":"Female"},{"gender":"Others"}],"color":[{"color":"red"},{"color":"white"},{"color":"green"},{"color":"yellow"}],"specialzation":[{"specialzation":"specialzation - 1"},{"specialzation":"specialzation - 2"},{"specialzation":"specialzation - 3"}],"pet_handle":[{"pet_handle":"pet_handle - 1"},{"pet_handle":"pet_handle - 2"},{"pet_handle":"pet_handle - 3"}],"services":[{"services":"services - 1"},{"services":"services - 2"},{"services":"services - 3"}]}
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
        private List<PetTypeBean> Pet_type;
        private List<PetBreedBean> Pet_breed;
        private List<GenderBean> Gender;
        private List<ColorBean> color;
        private List<SpecialzationBean> specialzation;
        private List<PetHandleBean> pet_handle;
        private List<ServicesBean> services;

        public List<PetTypeBean> getPet_type() {
            return Pet_type;
        }

        public void setPet_type(List<PetTypeBean> Pet_type) {
            this.Pet_type = Pet_type;
        }

        public List<PetBreedBean> getPet_breed() {
            return Pet_breed;
        }

        public void setPet_breed(List<PetBreedBean> Pet_breed) {
            this.Pet_breed = Pet_breed;
        }

        public List<GenderBean> getGender() {
            return Gender;
        }

        public void setGender(List<GenderBean> Gender) {
            this.Gender = Gender;
        }

        public List<ColorBean> getColor() {
            return color;
        }

        public void setColor(List<ColorBean> color) {
            this.color = color;
        }

        public List<SpecialzationBean> getSpecialzation() {
            return specialzation;
        }

        public void setSpecialzation(List<SpecialzationBean> specialzation) {
            this.specialzation = specialzation;
        }

        public List<PetHandleBean> getPet_handle() {
            return pet_handle;
        }

        public void setPet_handle(List<PetHandleBean> pet_handle) {
            this.pet_handle = pet_handle;
        }

        public List<ServicesBean> getServices() {
            return services;
        }

        public void setServices(List<ServicesBean> services) {
            this.services = services;
        }

        public static class PetTypeBean {
            /**
             * pet_type : Dod
             */

            private String pet_type;

            public String getPet_type() {
                return pet_type;
            }

            public void setPet_type(String pet_type) {
                this.pet_type = pet_type;
            }
        }

        public static class PetBreedBean {
            /**
             * pet_breed : breed 1
             */

            private String pet_breed;

            public String getPet_breed() {
                return pet_breed;
            }

            public void setPet_breed(String pet_breed) {
                this.pet_breed = pet_breed;
            }
        }

        public static class GenderBean {
            /**
             * gender : Male
             */

            private String gender;

            public String getGender() {
                return gender;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }
        }

        public static class ColorBean {
            /**
             * color : red
             */

            private String color;

            public String getColor() {
                return color;
            }

            public void setColor(String color) {
                this.color = color;
            }
        }

        public static class SpecialzationBean {
            /**
             * specialzation : specialzation - 1
             */

            private String specialzation;

            public String getSpecialzation() {
                return specialzation;
            }

            public void setSpecialzation(String specialzation) {
                this.specialzation = specialzation;
            }
        }

        public static class PetHandleBean {
            /**
             * pet_handle : pet_handle - 1
             */

            private String pet_handle;

            public String getPet_handle() {
                return pet_handle;
            }

            public void setPet_handle(String pet_handle) {
                this.pet_handle = pet_handle;
            }
        }

        public static class ServicesBean {
            /**
             * services : services - 1
             */

            private String services;

            public String getServices() {
                return services;
            }

            public void setServices(String services) {
                this.services = services;
            }
        }
    }
}
