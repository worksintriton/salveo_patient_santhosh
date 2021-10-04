package com.salveo.mysalveo.requestpojo;

import java.util.List;

public class PetAddImageRequest {


    /**
     * _id : 603e098e2c2b43125f8cb7f8
     * pet_img : [{"pet_img":"http://54.212.108.156:3000/api/uploads/Pic_empty.jpg"},{"pet_img":"http://54.212.108.156:3000/api/uploads/Pic_empty.jpg"}]
     */

    private String _id;
    private List<PetImgBean> pet_img;


    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;

    }


    public List<PetImgBean> getPet_img() {
        return pet_img;
    }

    public void setPet_img(List<PetImgBean> pet_img) {
        this.pet_img = pet_img;

    }

    public static class PetImgBean {
        /**
         * pet_img : http://54.212.108.156:3000/api/uploads/Pic_empty.jpg
         */

        private String pet_img;

        public PetImgBean(String pet_img) {

            this.pet_img = pet_img;
        }

        public PetImgBean() {

        }


        public String getPet_img() {
            return pet_img;
        }

        public void setPet_img(String pet_img) {
            this.pet_img = pet_img;

        }
    }
}
