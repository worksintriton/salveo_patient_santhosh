package com.salveo.mysalveo.responsepojo;

import java.util.List;

public class FetchDoctorPaymDetaResponse {


    /**
     * Status : Success
     * Message : Appointment Payment Details
     * Data : [{"_id":"604090e72c2b43125f8cb84e","doctor_id":"603e2a7b2c2b43125f8cb805","appointment_UID":"PET-1614844135518","booking_date_time":"23-10-2021 11:00 AM","appoinment_status":"Missed","communication_type":"Online","display_date":"2021-03-04 22:15:00","payment_id":"pay_GiV3kPF2LG4UEK","amount":"500","mobile_type":"Android","reshedule_status":"Yes"},{"_id":"604091072c2b43125f8cb851","doctor_id":"603e2a7b2c2b43125f8cb805","appointment_UID":"PET-1614844167662","booking_date_time":"04-03-2021 10:30 PM","appoinment_status":"Completed","communication_type":"Online","display_date":"2021-03-04 22:30:00","payment_id":"pay_GiV4JgPhEHLDyn","amount":"500","mobile_type":"Android"},{"_id":"604091442c2b43125f8cb854","doctor_id":"603e2a7b2c2b43125f8cb805","appointment_UID":"PET-1614844228461","booking_date_time":"04-03-2021 11:30 PM","appoinment_status":"Missed","communication_type":"Online","display_date":"2021-03-04 23:30:00","payment_id":"pay_GiV4ucw5kckJ2M","amount":"500","mobile_type":"Android"},{"_id":"6040bde62c2b43125f8cb89d","doctor_id":"603e2a7b2c2b43125f8cb805","appointment_UID":"PET-1614855654462","booking_date_time":"04-03-2021 10:45 PM","appoinment_status":"Missed","communication_type":"Online","display_date":"2021-03-04 22:45:00","payment_id":"pay_GiYKVY6xqi3HHh","amount":"500","mobile_type":"Android"},{"_id":"6041d4acf79be871d19049d5","doctor_id":"603e2a7b2c2b43125f8cb805","appointment_UID":"PET-1614927020866","booking_date_time":"05-03-2021 08:00 PM","appoinment_status":"Missed","communication_type":"Online","display_date":"2021-03-05 20:00:00","payment_id":"pay_GisatjOzjA9FK7","amount":"500","mobile_type":"Android"},{"_id":"604774d0eae806553e9738f2","doctor_id":"603e2a7b2c2b43125f8cb805","appointment_UID":"PET-1615295696615","booking_date_time":"09-03-2021 07:45 PM","appoinment_status":"Missed","communication_type":"Online","display_date":"2021-03-09 19:45:00","payment_id":"pay_GkZHjm6rEKGkOt","amount":"500","mobile_type":"IOS"},{"_id":"60504262cdef38628f812fc4","doctor_id":"603e2a7b2c2b43125f8cb805","appointment_UID":"PET-1615872610813","booking_date_time":"16-03-2021 07:15 PM","appoinment_status":"Missed","communication_type":"Online","display_date":"2021-03-16 19:15:00","payment_id":"pay_GnD6aeeAxZiBhz","amount":"500","mobile_type":"Android"},{"_id":"60544dcbf4448e09a8a24c9a","doctor_id":"603e2a7b2c2b43125f8cb805","appointment_UID":"PET-1616137675819","booking_date_time":"27-03-2021 10:00 PM","appoinment_status":"Missed","communication_type":"Online","display_date":"2021-03-27 22:00:00","payment_id":"pay_GoQNFAXTG2T6jg","amount":"500","mobile_type":"IOS"},{"_id":"60695d4a0934f7487ed7c493","doctor_id":"603e2a7b2c2b43125f8cb805","appointment_UID":"PET-1617517898855","booking_date_time":"04-04-2021 11:30 PM","appoinment_status":"Missed","communication_type":"Online","display_date":"2021-04-04 23:30:00","payment_id":"pay_GukIpwDztiH8a8","amount":"500","mobile_type":"Android"},{"_id":"6098fcb84b47b379682a743a","doctor_id":"603e2a7b2c2b43125f8cb805","appointment_UID":"PET-1620638904266","booking_date_time":"10-05-2021 11:30 PM","appoinment_status":"Missed","communication_type":"Online","display_date":"2021-05-10 23:30:00","payment_id":"pay_H92Xsf9tqpb2oo","amount":"500","mobile_type":"Android","reshedule_status":""}]
     * Code : 200
     */

    private String Status;
    private String Message;
    private int Code;
    /**
     * _id : 604090e72c2b43125f8cb84e
     * doctor_id : 603e2a7b2c2b43125f8cb805
     * appointment_UID : PET-1614844135518
     * booking_date_time : 23-10-2021 11:00 AM
     * appoinment_status : Missed
     * communication_type : Online
     * display_date : 2021-03-04 22:15:00
     * payment_id : pay_GiV3kPF2LG4UEK
     * amount : 500
     * mobile_type : Android
     * reshedule_status : Yes
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
        private String _id;
        private String doctor_id;
        private String appointment_UID;
        private String booking_date_time;
        private String appoinment_status;
        private String communication_type;
        private String display_date;
        private String payment_id;
        private String amount;
        private String mobile_type;
        private String reshedule_status;

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

        public String getBooking_date_time() {
            return booking_date_time;
        }

        public void setBooking_date_time(String booking_date_time) {
            this.booking_date_time = booking_date_time;
        }

        public String getAppoinment_status() {
            return appoinment_status;
        }

        public void setAppoinment_status(String appoinment_status) {
            this.appoinment_status = appoinment_status;
        }

        public String getCommunication_type() {
            return communication_type;
        }

        public void setCommunication_type(String communication_type) {
            this.communication_type = communication_type;
        }

        public String getDisplay_date() {
            return display_date;
        }

        public void setDisplay_date(String display_date) {
            this.display_date = display_date;
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

        public String getMobile_type() {
            return mobile_type;
        }

        public void setMobile_type(String mobile_type) {
            this.mobile_type = mobile_type;
        }

        public String getReshedule_status() {
            return reshedule_status;
        }

        public void setReshedule_status(String reshedule_status) {
            this.reshedule_status = reshedule_status;
        }
    }
}
