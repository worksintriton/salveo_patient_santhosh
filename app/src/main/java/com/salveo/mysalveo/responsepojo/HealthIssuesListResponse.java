package com.salveo.mysalveo.responsepojo;

import java.util.List;

public class HealthIssuesListResponse {

    /**
     * Status : Success
     * Message : Health issue Details
     * Data : [{"_id":"60c340061522a327cee3997e","health_issue_title":"Testing","health_issue_img":"http://54.212.108.156:3000/api/uploads/1623408646187.jpeg","date_and_time":"6/11/2021, 4:20:46 PM","delete_status":false,"updatedAt":"2021-06-11T10:50:46.868Z","createdAt":"2021-06-11T10:50:46.868Z","__v":0},{"_id":"60c340d16f2116298d2eed19","health_issue_title":"Testing id","health_issue_img":"http://54.212.108.156:3000/api/uploads/1623408847975.jpeg","date_and_time":"6/11/2021, 4:24:08 PM","delete_status":false,"updatedAt":"2021-06-11T10:54:09.100Z","createdAt":"2021-06-11T10:54:09.100Z","__v":0}]
     * Code : 200
     */

    private String Status;
    private String Message;
    private int Code;
    /**
     * _id : 60c340061522a327cee3997e
     * health_issue_title : Testing
     * health_issue_img : http://54.212.108.156:3000/api/uploads/1623408646187.jpeg
     * date_and_time : 6/11/2021, 4:20:46 PM
     * delete_status : false
     * updatedAt : 2021-06-11T10:50:46.868Z
     * createdAt : 2021-06-11T10:50:46.868Z
     * __v : 0
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
        private String health_issue_title;
        private String health_issue_img;
        private String date_and_time;
        private boolean delete_status;
        private String updatedAt;
        private String createdAt;
        private int __v;

        private boolean isSelected ;
        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }



        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getHealth_issue_title() {
            return health_issue_title;
        }

        public void setHealth_issue_title(String health_issue_title) {
            this.health_issue_title = health_issue_title;
        }

        public String getHealth_issue_img() {
            return health_issue_img;
        }

        public void setHealth_issue_img(String health_issue_img) {
            this.health_issue_img = health_issue_img;
        }

        public String getDate_and_time() {
            return date_and_time;
        }

        public void setDate_and_time(String date_and_time) {
            this.date_and_time = date_and_time;
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
}
