package com.salveo.mysalveo.responsepojo;

import java.util.List;

public class ServiceCatResponse {

    /**
     * Status : Success
     * Message : Service Cat List
     * Data : [{"_id":"5fe185d61996f651f5133693","image":"","title":"SP - 6","sub_title":"SP - 6"},{"_id":"5fe185df1996f651f5133694","image":"","title":"SP - 1","sub_title":"SP - 1"},{"_id":"5fe185e11996f651f5133695","image":"","title":"SP - 2","sub_title":"SP - 2"},{"_id":"5fe185e31996f651f5133696","image":"","title":"SP - 3","sub_title":"SP - 3"},{"_id":"5fe185f21996f651f5133698","image":"","title":"SP - 5","sub_title":"SP - 5"},{"_id":"5fe1888df456ec58d343b83e","image":"","title":"SP - 5","sub_title":"SP - 5"},{"_id":"5fe18898f456ec58d343b83f","image":"","title":"SP - 5","sub_title":"SP - 5"},{"_id":"5fe188ba02be5b58f85fa9a0","image":"","title":"SP - 5","sub_title":"SP - 5"}]
     * Code : 200
     */

    private String Status;
    private String Message;
    private int Code;
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
        /**
         * _id : 5fe185d61996f651f5133693
         * image :
         * title : SP - 6
         * sub_title : SP - 6
         */

        private String _id;
        private String image;
        private String title;
        private String sub_title;


        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;

        }


        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }


        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }


        public String getSub_title() {
            return sub_title;
        }

        public void setSub_title(String sub_title) {
            this.sub_title = sub_title;
        }
    }
}
