package com.salveo.mysalveo.responsepojo;

import java.util.List;

public class GetFamilyMemberResponse {


    /**
     * Status : Success
     * Message : Family Members List
     * Data : [{"name":"mother"},{"name":"mom"},{"name":"father"},{"name":"dad"},{"name":"parent"},{"name":"children"}]
     * Code : 200
     */

    private String Status;
    private String Message;
    private int Code;
    /**
     * name : mother
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
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
