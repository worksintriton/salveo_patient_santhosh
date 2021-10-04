package com.salveo.mysalveo.responsepojo;

import java.util.List;

public class CatgoryGetListResponse {

    /**
     * Status : Success
     * Message : product categories screen  List
     * Data : [{"_id":"5fec1424ea832e2e73c1fc78","product_cate_name":"Dog  Food"},{"_id":"5fec14a5ea832e2e73c1fc79","product_cate_name":"Cat Food"},{"_id":"5fec1573ea832e2e73c1fc7a","product_cate_name":"Cow food"},{"_id":"5fec22eeea832e2e73c1fc7b","product_cate_name":"Bird Food"},{"_id":"602ccc8518da357d363da7cb","product_cate_name":"PET COLLARS & LEASHES"}]
     * Code : 200
     */

    private String Status;
    private String Message;
    private int Code;
    /**
     * _id : 5fec1424ea832e2e73c1fc78
     * product_cate_name : Dog  Food
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
        private String product_cate_name;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getProduct_cate_name() {
            return product_cate_name;
        }

        public void setProduct_cate_name(String product_cate_name) {
            this.product_cate_name = product_cate_name;
        }
    }
}
