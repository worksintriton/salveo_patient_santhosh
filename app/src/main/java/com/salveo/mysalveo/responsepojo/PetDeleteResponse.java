package com.salveo.mysalveo.responsepojo;

import java.util.List;

public class PetDeleteResponse {


    /**
     * Status : Success
     * Message : Update Successfully
     * Data : {"pic":[{"image":"http://54.212.108.156:3000/api/uploads/1630662421790.jpg"}],"_id":"6131ef187fcc57715cde80da","user_id":"6131dc387fcc57715cde80d7","relation":"children","name":"sandy","gender":"Male","age":26,"height":"5","weight":"60","anymedicalinfo":"no comments","delete_status":true,"createdAt":"2021-09-03T09:47:04.238Z","updatedAt":"2021-09-03T11:20:20.927Z","__v":0}
     * Code : 200
     */

    private String Status;
    private String Message;
    /**
     * pic : [{"image":"http://54.212.108.156:3000/api/uploads/1630662421790.jpg"}]
     * _id : 6131ef187fcc57715cde80da
     * user_id : 6131dc387fcc57715cde80d7
     * relation : children
     * name : sandy
     * gender : Male
     * age : 26
     * height : 5
     * weight : 60
     * anymedicalinfo : no comments
     * delete_status : true
     * createdAt : 2021-09-03T09:47:04.238Z
     * updatedAt : 2021-09-03T11:20:20.927Z
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
        private String relation;
        private String name;
        private String gender;
        private int age;
        private String height;
        private String weight;
        private String anymedicalinfo;
        private boolean delete_status;
        private String createdAt;
        private String updatedAt;
        private int __v;
        /**
         * image : http://54.212.108.156:3000/api/uploads/1630662421790.jpg
         */

        private List<PicBean> pic;

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

        public String getRelation() {
            return relation;
        }

        public void setRelation(String relation) {
            this.relation = relation;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getAnymedicalinfo() {
            return anymedicalinfo;
        }

        public void setAnymedicalinfo(String anymedicalinfo) {
            this.anymedicalinfo = anymedicalinfo;
        }

        public boolean isDelete_status() {
            return delete_status;
        }

        public void setDelete_status(boolean delete_status) {
            this.delete_status = delete_status;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public int get__v() {
            return __v;
        }

        public void set__v(int __v) {
            this.__v = __v;
        }

        public List<PicBean> getPic() {
            return pic;
        }

        public void setPic(List<PicBean> pic) {
            this.pic = pic;
        }

        public static class PicBean {
            private String image;

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }
        }
    }
}

