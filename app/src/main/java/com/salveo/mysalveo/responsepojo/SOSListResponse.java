package com.salveo.mysalveo.responsepojo;

import java.util.List;

public class SOSListResponse {

    /**
     * Status : Success
     * Message : SOS Details
     * Data : [{"_id":0,"name":"Petfolio sos","phone":"9876543210","Edit_status":false},{"_id":1,"name":"Ambulance","phone":"9876543210","Edit_status":false},{"_id":2,"name":"Home","phone":"9876543210","Edit_status":true},{"_id":3,"name":"Imthi","phone":"98989989898","Edit_status":true}]
     * Code : 200
     */

    private String Status;
    private String Message;
    private int Code;
    /**
     * _id : 0
     * name : Petfolio sos
     * phone : 9876543210
     * Edit_status : false
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
        private int _id;
        private String name;
        private String phone;
        private boolean Edit_status;

        public int get_id() {
            return _id;
        }

        public void set_id(int _id) {
            this._id = _id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public boolean isEdit_status() {
            return Edit_status;
        }

        public void setEdit_status(boolean Edit_status) {
            this.Edit_status = Edit_status;
        }
    }
}
