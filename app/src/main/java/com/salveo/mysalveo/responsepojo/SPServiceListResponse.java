package com.salveo.mysalveo.responsepojo;

import java.util.List;

public class SPServiceListResponse {

    /**
     * Status : Success
     * Message : SP Service List
     * Data : {"service_list":[{"service_list":"Service - 1"},{"service_list":"Service - 2"},{"service_list":"Service - 3"},{"service_list":"Service - 4"},{"service_list":"Service - 5"},{"service_list":"Service - 6"}],"Specialization":[{"Specialization":"Specialization - 1"},{"Specialization":"Specialization - 2"},{"Specialization":"Specialization - 3"},{"Specialization":"Specialization - 4"},{"Specialization":"Specialization - 5"},{"Specialization":"Specialization - 6"}]}
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



    public static class DataBean  {

        private boolean isSelected;

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }
        private List<ServiceListBean> service_list;
        private List<SpecializationBean> Specialization;
        private List<TimeBean> time;



        public List<ServiceListBean> getService_list() {
            return service_list;
        }

        public void setService_list(List<ServiceListBean> service_list) {
            this.service_list = service_list;

        }


        public List<SpecializationBean> getSpecialization() {
            return Specialization;
        }

        public void setSpecialization(List<SpecializationBean> Specialization) {
            this.Specialization = Specialization;

        }
        public List<TimeBean> getTime() {
            return time;
        }

        public void setTime(List<TimeBean> time) {
            this.time = time;
        }

        public static class ServiceListBean  {



            /**
             * service_list : Service - 1
             */

            private String service_list;
            private String bus_service_list;
            private String time_slots;
            private int amount;
            private boolean isChbxChecked;
            private boolean isValueAdded;

            public boolean isValueAdded() {
                return isValueAdded;
            }

            public void setValueAdded(boolean valueAdded) {
                isValueAdded = valueAdded;
            }

            public boolean isChbxChecked() {
                return isChbxChecked;
            }

            public void setChbxChecked(boolean chbxChecked) {
                isChbxChecked = chbxChecked;
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

            public int getAmount() {
                return amount;
            }

            public void setAmount(int amount) {
                this.amount = amount;
            }

            public String getService_list() {
                return service_list;
            }

            public void setService_list(String service_list) {
                this.service_list = service_list;

            }
        }

        public static class SpecializationBean {


            /**
             * Specialization : Specialization - 1
             */

            private String Specialization;


            public String getSpecialization() {
                return Specialization;
            }

            public void setSpecialization(String Specialization) {
                this.Specialization = Specialization;

            }
        }

        public static class TimeBean  {
            /**
             * time : 15 mins
             */

            private String time;


            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;

            }
        }

    }
}
