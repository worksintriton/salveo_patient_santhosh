package com.salveo.mysalveo.responsepojo;

import java.util.List;

public class SPFilterPriceListResponse {

    /**
     * Status : Success
     * Message : SP filter price list
     * Data : [{"Display_text":"Under Rs.500","Count_value_start":0,"Count_value_end":500},{"Display_text":"Rs. 500 - Rs. 1,000","Count_value_start":500,"Count_value_end":1000},{"Display_text":"Rs. 1,000 - Rs. 2,000","Count_value_start":1000,"Count_value_end":2000},{"Display_text":"Rs. 2,000 - Rs. 3,000","Count_value_start":2000,"Count_value_end":3000},{"Display_text":"Rs. 3,000 - Above","Count_value_start":3000,"Count_value_end":1000000}]
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

    public static class DataBean  {
        /**
         * Display_text : Under Rs.500
         * Count_value_start : 0
         * Count_value_end : 500
         */

        private String Display_text;
        private int Count_value_start;
        private int Count_value_end;


        public String getDisplay_text() {
            return Display_text;
        }

        public void setDisplay_text(String Display_text) {
            this.Display_text = Display_text;

        }

        public int getCount_value_start() {
            return Count_value_start;
        }

        public void setCount_value_start(int Count_value_start) {
            this.Count_value_start = Count_value_start;

        }


        public int getCount_value_end() {
            return Count_value_end;
        }

        public void setCount_value_end(int Count_value_end) {
            this.Count_value_end = Count_value_end;
        }
    }
}
