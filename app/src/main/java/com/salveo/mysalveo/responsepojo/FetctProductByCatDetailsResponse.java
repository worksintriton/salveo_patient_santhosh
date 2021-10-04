package com.salveo.mysalveo.responsepojo;

import java.util.List;

public class FetctProductByCatDetailsResponse {

    /**
     * Status : Success
     * Message : product list
     * Data : [{"_id":"60a5df0f785e571920ac46f0","product_img":"http://54.212.108.156:3000/api/uploads/1621483238826.png","product_title":"food 1","product_discription":"test1","status":false},{"_id":"60b0c54767f25056fe286ca4","product_img":"http://54.212.108.156:3000/api/uploads/1622197569345.jpeg","product_title":"Good Food Dog","product_discription":"this is good food for dog","status":false},{"_id":"60b5d9756105e73126624071","product_img":"http://54.212.108.156:3000/api/uploads/1622530397531.jpeg","product_title":"Orijen Original Dry Dog Food","product_discription":"Give your dog the goodness of quality ingredients and food that is nutritionally complete. Unlock the secret to healthy and happy dog with a range of Pedigree products. Buy now! Backed by Science. Pedigree Dog Food. ","status":false}]
     * product_list_count : 3
     * Code : 200
     */

    private String Status;
    private String Message;
    private int product_list_count;
    private int Code;
    /**
     * _id : 60a5df0f785e571920ac46f0
     * product_img : http://54.212.108.156:3000/api/uploads/1621483238826.png
     * product_title : food 1
     * product_discription : test1
     * status : false
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

    public int getProduct_list_count() {
        return product_list_count;
    }

    public void setProduct_list_count(int product_list_count) {
        this.product_list_count = product_list_count;
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
        private String product_img;
        private String product_title;
        private String product_discription;
        private boolean status;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getProduct_img() {
            return product_img;
        }

        public void setProduct_img(String product_img) {
            this.product_img = product_img;
        }

        public String getProduct_title() {
            return product_title;
        }

        public void setProduct_title(String product_title) {
            this.product_title = product_title;
        }

        public String getProduct_discription() {
            return product_discription;
        }

        public void setProduct_discription(String product_discription) {
            this.product_discription = product_discription;
        }

        public boolean isStatus() {
            return status;
        }

        public void setStatus(boolean status) {
            this.status = status;
        }
    }
}
