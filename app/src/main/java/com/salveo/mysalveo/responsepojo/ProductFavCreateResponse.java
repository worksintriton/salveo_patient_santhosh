package com.salveo.mysalveo.responsepojo;

public class ProductFavCreateResponse {


    /**
     * Status : Success
     * Message : Fav Added successfully
     * Data : {"_id":"609a956d06a4c17a9e13486c","user_id":"604081d12c2b43125f8cb840","product_id":"602e11404775fa0735d7bf40","mobile_type":"","delete_status":false,"updatedAt":"2021-05-11T14:32:13.187Z","createdAt":"2021-05-11T14:32:13.187Z","__v":0}
     * Code : 200
     */

    private String Status;
    private String Message;
    /**
     * _id : 609a956d06a4c17a9e13486c
     * user_id : 604081d12c2b43125f8cb840
     * product_id : 602e11404775fa0735d7bf40
     * mobile_type :
     * delete_status : false
     * updatedAt : 2021-05-11T14:32:13.187Z
     * createdAt : 2021-05-11T14:32:13.187Z
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
        private String product_id;
        private String mobile_type;
        private boolean delete_status;
        private String updatedAt;
        private String createdAt;
        private int __v;

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

        public String getProduct_id() {
            return product_id;
        }

        public void setProduct_id(String product_id) {
            this.product_id = product_id;
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
    }
}
