package com.salveo.mysalveo.requestpojo;

public class SignupRequest {

    /**
     * first_name : mohammed
     * last_name : imthiyas
     * user_email : m@gmail.com
     * user_phone : 987987989
     * user_type : 1
     * date_of_reg : 23/10/2019 12:12:00
     * user_email_verification :true
     * mobile_type : "Android"
     * user_email_verification: true
     * ref_code
     */

    private String first_name;
    private String last_name;
    private String user_email;
    private String user_phone;
    private int user_type;
    private String date_of_reg;
    private String mobile_type;
    private boolean user_email_verification ;
    private String ref_code;

    public String getRef_code() {
        return ref_code;
    }

    public void setRef_code(String ref_code) {
        this.ref_code = ref_code;
    }

    public boolean isUser_email_verification() {
        return user_email_verification;
    }

    public void setUser_email_verification(boolean user_email_verification) {
        this.user_email_verification = user_email_verification;
    }

    public String getMobile_type() {
        return mobile_type;
    }

    public void setMobile_type(String mobile_type) {
        this.mobile_type = mobile_type;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public int getUser_type() {
        return user_type;
    }

    public void setUser_type(int user_type) {
        this.user_type = user_type;
    }

    public String getDate_of_reg() {
        return date_of_reg;
    }

    public void setDate_of_reg(String date_of_reg) {
        this.date_of_reg = date_of_reg;
    }
}
