package com.salveo.mysalveo.responsepojo;

import java.util.List;

public class PetTypeListResponse {

    /**
     * Status : Success
     * Message : PET type Details
     * Data : {"usertypedata":[{"_id":"602d1bf4562e0916bc9b3215","pet_type_title":" Dogs - (Indian & Foreign) ","date_and_time":"2/17/2021, 7:06:51 PM","delete_status":false,"updatedAt":"2021-06-11T09:36:11.921Z","createdAt":"2021-02-17T13:36:52.141Z","__v":0,"pet_type_img":"http://54.212.108.156:3000/api/uploads/1623404171138.jpeg"},{"_id":"602d1c6b562e0916bc9b321d","pet_type_title":"Cats","date_and_time":"2/17/2021, 7:08:50 PM","delete_status":false,"updatedAt":"2021-06-11T09:37:14.383Z","createdAt":"2021-02-17T13:38:51.432Z","__v":0,"pet_type_img":"http://54.212.108.156:3000/api/uploads/1623404233548.jpeg"},{"_id":"602d1cf4562e0916bc9b3224","pet_type_title":"Small pets - (Guinea Pigs, Hamsters, Iguanas, Mice and Rats, Rabbit, Turtles)","date_and_time":"2/17/2021, 7:11:08 PM","delete_status":false,"updatedAt":"2021-06-11T09:38:41.852Z","createdAt":"2021-02-17T13:41:08.956Z","__v":0,"pet_type_img":"http://54.212.108.156:3000/api/uploads/1623404171138.jpeg"},{"_id":"602d1d73562e0916bc9b322a","pet_type_title":" Birds- Domestic birds (CHICKEN, DUCKS, FANCY HEN, GEESE, GUINEA FOWL, QUAIL, TURKEY)","date_and_time":"2/17/2021, 7:13:15 PM","delete_status":false,"updatedAt":"2021-06-11T09:38:47.487Z","createdAt":"2021-02-17T13:43:15.527Z","__v":0,"pet_type_img":"http://54.212.108.156:3000/api/uploads/1623404171138.jpeg"},{"_id":"602d1dd5562e0916bc9b3232","pet_type_title":"Fishes","date_and_time":"2/17/2021, 7:14:52 PM","delete_status":false,"updatedAt":"2021-06-11T09:38:49.642Z","createdAt":"2021-02-17T13:44:53.219Z","__v":0,"pet_type_img":"http://54.212.108.156:3000/api/uploads/1623404171138.jpeg"},{"_id":"602d1f52562e0916bc9b323d","pet_type_title":"Horses","date_and_time":"2/17/2021, 7:21:14 PM","delete_status":false,"updatedAt":"2021-06-11T09:39:35.978Z","createdAt":"2021-02-17T13:51:14.862Z","__v":0,"pet_type_img":"http://54.212.108.156:3000/api/uploads/1623404171138.jpeg"},{"_id":"609529568e1ce6680227614e","pet_type_title":"Farm Animals- (BUFFALO, CAMELS, CATTLE, DONKEYS, GOATS, PIGS,  SHEEP)","date_and_time":"5/7/2021, 5:19:37 PM","delete_status":false,"updatedAt":"2021-06-11T09:38:53.579Z","createdAt":"2021-05-07T11:49:42.208Z","__v":0,"pet_type_img":"http://54.212.108.156:3000/api/uploads/1623404171138.jpeg"},{"_id":"609529708e1ce6680227614f","pet_type_title":"Birds- Pet birds","date_and_time":"5/7/2021, 5:20:03 PM","delete_status":false,"updatedAt":"2021-06-11T09:39:26.452Z","createdAt":"2021-05-07T11:50:08.315Z","__v":0,"pet_type_img":"http://54.212.108.156:3000/api/uploads/1623404171138.jpeg"},{"_id":"6095383f8e1ce668022761de","pet_type_title":"Birds- Domestic birds ","date_and_time":"5/7/2021, 6:23:20 PM","delete_status":false,"updatedAt":"2021-06-11T09:39:31.394Z","createdAt":"2021-05-07T12:53:19.172Z","__v":0,"pet_type_img":"http://54.212.108.156:3000/api/uploads/1623404171138.jpeg"},{"_id":"60953cfe13144b6dfa31589b","pet_type_title":"Dogs ","date_and_time":"5/7/2021, 6:43:30 PM","delete_status":false,"updatedAt":"2021-06-11T09:39:22.417Z","createdAt":"2021-05-07T13:13:34.819Z","__v":0,"pet_type_img":"http://54.212.108.156:3000/api/uploads/1623404171138.jpeg"},{"_id":"6095406213144b6dfa3158b5","pet_type_title":"Angel Fishes","date_and_time":"5/7/2021, 6:57:57 PM","delete_status":false,"updatedAt":"2021-06-11T09:39:17.450Z","createdAt":"2021-05-07T13:28:02.866Z","__v":0,"pet_type_img":"http://54.212.108.156:3000/api/uploads/1623404171138.jpeg"},{"_id":"6095407e13144b6dfa3158b7","pet_type_title":"Arowana","date_and_time":"5/7/2021, 6:58:26 PM","delete_status":false,"updatedAt":"2021-06-11T09:39:12.729Z","createdAt":"2021-05-07T13:28:30.790Z","__v":0,"pet_type_img":"http://54.212.108.156:3000/api/uploads/1623404171138.jpeg"},{"_id":"6095408513144b6dfa3158b9","pet_type_title":"Bettas","date_and_time":"5/7/2021, 6:58:33 PM","delete_status":false,"updatedAt":"2021-06-11T09:39:08.746Z","createdAt":"2021-05-07T13:28:37.956Z","__v":0,"pet_type_img":"http://54.212.108.156:3000/api/uploads/1623404171138.jpeg"},{"_id":"6095409313144b6dfa3158bb","pet_type_title":"Cat Fishes","date_and_time":"5/7/2021, 6:58:46 PM","delete_status":false,"updatedAt":"2021-06-11T09:39:05.690Z","createdAt":"2021-05-07T13:28:51.416Z","__v":0,"pet_type_img":"http://54.212.108.156:3000/api/uploads/1623404171138.jpeg"},{"_id":"6095420913144b6dfa3158ce","pet_type_title":"Small pets - Hamsters","date_and_time":"5/7/2021, 7:05:00 PM","delete_status":false,"updatedAt":"2021-06-11T09:38:57.687Z","createdAt":"2021-05-07T13:35:05.314Z","__v":0,"pet_type_img":"http://54.212.108.156:3000/api/uploads/1623404171138.jpeg"},{"_id":"6095421f13144b6dfa3158d0","pet_type_title":"Small pets - Iguanas","date_and_time":"5/7/2021, 7:05:23 PM","delete_status":false,"updatedAt":"2021-06-11T09:40:03.121Z","createdAt":"2021-05-07T13:35:27.947Z","__v":0,"pet_type_img":"http://54.212.108.156:3000/api/uploads/1623404171138.jpeg"},{"_id":"6095423913144b6dfa3158d3","pet_type_title":"Small pets - Mice and Rats","date_and_time":"5/7/2021, 7:05:48 PM","delete_status":false,"updatedAt":"2021-06-11T09:39:55.338Z","createdAt":"2021-05-07T13:35:53.750Z","__v":0,"pet_type_img":"http://54.212.108.156:3000/api/uploads/1623404171138.jpeg"},{"_id":"6095424a13144b6dfa3158d5","pet_type_title":"Small pets - Rabbit","date_and_time":"5/7/2021, 7:06:06 PM","delete_status":false,"updatedAt":"2021-06-11T09:39:51.669Z","createdAt":"2021-05-07T13:36:10.913Z","__v":0,"pet_type_img":"http://54.212.108.156:3000/api/uploads/1623404171138.jpeg"},{"_id":"6095426213144b6dfa3158d6","pet_type_title":"Small pets - Turtles","date_and_time":"5/7/2021, 7:06:29 PM","delete_status":false,"updatedAt":"2021-06-11T09:39:48.580Z","createdAt":"2021-05-07T13:36:34.117Z","__v":0,"pet_type_img":"http://54.212.108.156:3000/api/uploads/1623404171138.jpeg"},{"_id":"60c32cde8cd21617f8103415","pet_type_title":"Testing","date_and_time":"6/11/2021, 2:59:01 PM","delete_status":false,"updatedAt":"2021-06-11T09:35:48.962Z","createdAt":"2021-06-11T09:29:02.026Z","__v":0,"pet_type_img":"http://54.212.108.156:3000/api/uploads/1623404146930.jpeg"},{"_id":"60c32e128cd21617f8103416","pet_type_title":"Testings","pet_type_img":"http://54.212.108.156:3000/api/uploads/1623404036215.jpeg","date_and_time":"6/11/2021, 3:04:10 PM","delete_status":false,"updatedAt":"2021-06-11T09:34:10.661Z","createdAt":"2021-06-11T09:34:10.661Z","__v":0}],"product_categories":[{"_id":"5fec1424ea832e2e73c1fc78","img_path":"http://52.25.163.13:3000/api/uploads/template%20(2).jpg","product_cate":"Dog  Food","img_index":0,"show_status":true,"date_and_time":"Fri Feb 19 2021 11:06:01 GMT+0530 (India Standard Time)","delete_status":true,"updatedAt":"2021-02-19T05:36:00.643Z","createdAt":"2020-12-30T05:46:12.099Z","__v":0},{"_id":"5fec14a5ea832e2e73c1fc79","img_path":"http://52.25.163.13:3000/api/uploads/template%20(3).jpg","product_cate":"Cat Food","img_index":0,"show_status":true,"date_and_time":"Thu Feb 18 2021 15:05:46 GMT+0530 (India Standard Time)","delete_status":true,"updatedAt":"2021-02-18T09:35:47.194Z","createdAt":"2020-12-30T05:48:21.363Z","__v":0},{"_id":"5fec1573ea832e2e73c1fc7a","img_path":"http://52.25.163.13:3000/api/uploads/template%20(4).jpg","product_cate":"Cow food","img_index":0,"show_status":true,"date_and_time":"Thu Feb 18 2021 15:05:55 GMT+0530 (India Standard Time)","delete_status":true,"updatedAt":"2021-02-18T09:35:55.761Z","createdAt":"2020-12-30T05:51:47.787Z","__v":0},{"_id":"5fec22eeea832e2e73c1fc7b","img_path":"http://52.25.163.13:3000/api/uploads/template%20(5).jpg","product_cate":"Bird Food","img_index":0,"show_status":true,"date_and_time":"Thu Feb 18 2021 15:06:04 GMT+0530 (India Standard Time)","delete_status":true,"updatedAt":"2021-02-18T09:36:04.572Z","createdAt":"2020-12-30T06:49:18.019Z","__v":0},{"_id":"602ccc8518da357d363da7cb","img_path":"http://54.212.108.156:3000/api/uploads/resize-1613548631141238608collar.jpg","product_cate":"PET COLLARS & LEASHES","img_index":0,"show_status":true,"date_and_time":"2/17/2021, 1:27:57 PM","delete_status":false,"updatedAt":"2021-02-17T07:57:57.193Z","createdAt":"2021-02-17T07:57:57.193Z","__v":0}]}
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
        /**
         * _id : 602d1bf4562e0916bc9b3215
         * pet_type_title :  Dogs - (Indian & Foreign)
         * date_and_time : 2/17/2021, 7:06:51 PM
         * delete_status : false
         * updatedAt : 2021-06-11T09:36:11.921Z
         * createdAt : 2021-02-17T13:36:52.141Z
         * __v : 0
         * pet_type_img : http://54.212.108.156:3000/api/uploads/1623404171138.jpeg
         */

        private List<UsertypedataBean> usertypedata;
        /**
         * _id : 5fec1424ea832e2e73c1fc78
         * img_path : http://52.25.163.13:3000/api/uploads/template%20(2).jpg
         * product_cate : Dog  Food
         * img_index : 0
         * show_status : true
         * date_and_time : Fri Feb 19 2021 11:06:01 GMT+0530 (India Standard Time)
         * delete_status : true
         * updatedAt : 2021-02-19T05:36:00.643Z
         * createdAt : 2020-12-30T05:46:12.099Z
         * __v : 0
         */

        private List<ProductCategoriesBean> product_categories;

        public List<UsertypedataBean> getUsertypedata() {
            return usertypedata;
        }

        public void setUsertypedata(List<UsertypedataBean> usertypedata) {
            this.usertypedata = usertypedata;
        }

        public List<ProductCategoriesBean> getProduct_categories() {
            return product_categories;
        }

        public void setProduct_categories(List<ProductCategoriesBean> product_categories) {
            this.product_categories = product_categories;
        }

        public static class UsertypedataBean {
            private String _id;
            private String pet_type_title;
            private String date_and_time;
            private boolean delete_status;
            private String updatedAt;
            private String createdAt;
            private int __v;
            private String pet_type_img;

            public String get_id() {
                return _id;
            }

            public void set_id(String _id) {
                this._id = _id;
            }

            public String getPet_type_title() {
                return pet_type_title;
            }

            public void setPet_type_title(String pet_type_title) {
                this.pet_type_title = pet_type_title;
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

            public String getPet_type_img() {
                return pet_type_img;
            }

            public void setPet_type_img(String pet_type_img) {
                this.pet_type_img = pet_type_img;
            }

            private boolean isSelected ;
            public boolean isSelected() {
                return isSelected;
            }

            public void setSelected(boolean selected) {
                isSelected = selected;
            }
        }

        public static class ProductCategoriesBean {
            private String _id;
            private String img_path;
            private String product_cate;
            private int img_index;
            private boolean show_status;
            private String date_and_time;
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

            public String getImg_path() {
                return img_path;
            }

            public void setImg_path(String img_path) {
                this.img_path = img_path;
            }

            public String getProduct_cate() {
                return product_cate;
            }

            public void setProduct_cate(String product_cate) {
                this.product_cate = product_cate;
            }

            public int getImg_index() {
                return img_index;
            }

            public void setImg_index(int img_index) {
                this.img_index = img_index;
            }

            public boolean isShow_status() {
                return show_status;
            }

            public void setShow_status(boolean show_status) {
                this.show_status = show_status;
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
}
