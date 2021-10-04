package com.salveo.mysalveo.responsepojo;

import java.util.List;

public class VendorReasonListResponse {

    /**
     * Status : Success
     * Message : Cancel Status & Return Status
     * Data : {"cancel_status":[{"title":"cancel - 1"},{"title":"cancel - 2"},{"title":"cancel - 3"},{"title":"cancel - 4"},{"title":"cancel - 5"}],"return_status":[{"title":"return - 1"},{"title":"return - 2"},{"title":"return - 3"},{"title":"return - 4"},{"title":"return - 5"}],"term_cond":"http://54.212.108.156:3000/api/uploads/1615793361500.pdf"}
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

    public static class DataBean  {
        /**
         * cancel_status : [{"title":"cancel - 1"},{"title":"cancel - 2"},{"title":"cancel - 3"},{"title":"cancel - 4"},{"title":"cancel - 5"}]
         * return_status : [{"title":"return - 1"},{"title":"return - 2"},{"title":"return - 3"},{"title":"return - 4"},{"title":"return - 5"}]
         * term_cond : http://54.212.108.156:3000/api/uploads/1615793361500.pdf
         */

        private String term_cond;
        private List<CancelStatusBean> cancel_status;
        private List<ReturnStatusBean> return_status;

        public String getTerm_cond() {
            return term_cond;
        }

        public void setTerm_cond(String term_cond) {
            this.term_cond = term_cond;

        }


        public List<CancelStatusBean> getCancel_status() {
            return cancel_status;
        }

        public void setCancel_status(List<CancelStatusBean> cancel_status) {
            this.cancel_status = cancel_status;

        }

        public List<ReturnStatusBean> getReturn_status() {
            return return_status;
        }

        public void setReturn_status(List<ReturnStatusBean> return_status) {
            this.return_status = return_status;

        }

        public static class CancelStatusBean  {
            /**
             * title : cancel - 1
             */

            private String title;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;

            }
        }

        public static class ReturnStatusBean  {
            /**
             * title : return - 1
             */

            private String title;


            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;

            }
        }
    }
}
