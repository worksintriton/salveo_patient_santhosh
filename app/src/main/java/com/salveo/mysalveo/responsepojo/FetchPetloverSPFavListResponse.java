package com.salveo.mysalveo.responsepojo;

import java.util.List;

public class FetchPetloverSPFavListResponse {

    /**
     * Status : Success
     * Message : SP FAV LIST
     * Data : [{"_id":"603e25562c2b43125f8cb7ff","image":"http://54.212.108.156:3000/api/uploads/1614685509822.603e24ef2c2b43125f8cb7fe0203171509","service_provider_name":"sparrow service","user_name":"jack","service_price":0,"service_offer":0,"service_place":"Gajendra Circle Bus Stop, Indian Institute Of Technology, Chennai, Tamil Nadu 600036, India","distance":0,"rating_count":5,"comments_count":12,"fav":true}]
     * Code : 200
     */

    private String Status;
    private String Message;
    private int Code;
    /**
     * _id : 603e25562c2b43125f8cb7ff
     * image : http://54.212.108.156:3000/api/uploads/1614685509822.603e24ef2c2b43125f8cb7fe0203171509
     * service_provider_name : sparrow service
     * user_name : jack
     * service_price : 0
     * service_offer : 0
     * service_place : Gajendra Circle Bus Stop, Indian Institute Of Technology, Chennai, Tamil Nadu 600036, India
     * distance : 0
     * rating_count : 5
     * comments_count : 12
     * fav : true
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
        private String cat_id;
        private String image;

        public String getCat_id() {
            return cat_id;
        }

        public void setCat_id(String cat_id) {
            this.cat_id = cat_id;
        }

        private String service_provider_name;
        private String user_name;
        private int service_price;
        private int service_offer;
        private String service_place;
        private int distance;
        private int rating_count;
        private int comments_count;
        private boolean fav;

        private String thumbnail_image;
        public String getThumbnail_image() {
            return thumbnail_image;
        }

        public void setThumbnail_image(String thumbnail_image) {
            this.thumbnail_image = thumbnail_image;
        }

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

        public String getService_provider_name() {
            return service_provider_name;
        }

        public void setService_provider_name(String service_provider_name) {
            this.service_provider_name = service_provider_name;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public int getService_price() {
            return service_price;
        }

        public void setService_price(int service_price) {
            this.service_price = service_price;
        }

        public int getService_offer() {
            return service_offer;
        }

        public void setService_offer(int service_offer) {
            this.service_offer = service_offer;
        }

        public String getService_place() {
            return service_place;
        }

        public void setService_place(String service_place) {
            this.service_place = service_place;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public int getRating_count() {
            return rating_count;
        }

        public void setRating_count(int rating_count) {
            this.rating_count = rating_count;
        }

        public int getComments_count() {
            return comments_count;
        }

        public void setComments_count(int comments_count) {
            this.comments_count = comments_count;
        }

        public boolean isFav() {
            return fav;
        }

        public void setFav(boolean fav) {
            this.fav = fav;
        }
    }
}
