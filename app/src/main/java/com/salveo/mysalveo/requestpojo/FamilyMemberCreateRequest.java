package com.salveo.mysalveo.requestpojo;

import java.util.List;

public class FamilyMemberCreateRequest {


    /**
     * user_id : 123123123
     * relation : Father
     * name : Mohammed imthiyas
     * gender : Male
     * age : 23
     * height : 12.2
     * weight : 122
     * anymedicalinfo : this is not good for health
     * pic : [{"image":"http://Google.com"}]
     */

    private String user_id;
    private String relation;
    private String name;
    private String gender;
    private int age;
    private String height;
    private String weight;
    private String anymedicalinfo;
    /**
     * image : http://Google.com
     */

    private List<PicBean> pic;

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

    public List<PicBean> getPic() {
        return pic;
    }

    public void setPic(List<PicBean> pic) {
        this.pic = pic;
    }

    public static class PicBean {
        private String image;

        public PicBean(String image) {

            this.image=image;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }
}
