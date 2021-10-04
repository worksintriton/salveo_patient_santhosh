package com.salveo.mysalveo.responsepojo;

import java.util.List;

public class SPSpecificServiceDetailsResponse {


    /**
     * Status : Success
     * Message : Service Provider List
     * alert_msg : Around 15 kms no recorde found, shall i show above 15 kms
     * Data : {"Service_Details":{"_id":"5fe185d61996f651f5133693","image_path":"http://54.212.108.156:3000/api/uploads/1624556230013.png","title":"Pet Walking","count":0},"Service_provider":[{"_id":"60b0b86d67f25056fe286c90","image":"http://54.212.108.156:3000/api/uploads/60b0b7b267f25056fe286c8f2805150112","service_provider_name":"Dinesh sp","thumbnail_image":"http://54.212.108.156:3000/api/uploads/1624014101733.jpeg","service_price":0,"service_offer":0,"city_name":"","bus_service_list":[{"amount":200,"time_slots":"45 mins","bus_service_list":" Dog Walking"},{"amount":300,"time_slots":"15 mins","bus_service_list":" Dog Grooming"}],"service_place":"Eswaran Koil Street,, Harbour quaters, Tirusulam, Chennai, Tamil Nadu 600043, India","distance":269.84,"rating_count":5,"comments_count":12},{"_id":"60b4ea2d541a437c7b9c6102","image":"http://54.212.108.156:3000/api/uploads/60b4e9cc541a437c7b9c61013105192216","service_provider_name":"Cary Carole","thumbnail_image":"http://54.212.108.156:3000/api/uploads/1624014273333.jpeg","service_price":0,"service_offer":0,"city_name":"","bus_service_list":[{"amount":300,"time_slots":"30 mins","bus_service_list":" Dog Walking"},{"amount":259,"time_slots":"15 mins","bus_service_list":" Dog Grooming"},{"amount":300,"time_slots":"15 mins","bus_service_list":" Dog Training"}],"service_place":"ARM College Road, Vadamelpakkam, Tamil Nadu 603204, India","distance":243.73,"rating_count":5,"comments_count":12},{"_id":"60b4eb0c541a437c7b9c6105","image":"http://54.212.108.156:3000/api/uploads/60b4eab6541a437c7b9c61043105192600","service_provider_name":"Clark Charlie ","thumbnail_image":"http://54.212.108.156:3000/api/uploads/1624014285183.jpeg","service_price":0,"service_offer":0,"city_name":"","bus_service_list":[{"amount":300,"time_slots":"45 mins","bus_service_list":" Dog Walking"},{"amount":300,"time_slots":"15 mins","bus_service_list":" Dog Grooming"}],"service_place":"Palavedu Pettai Aeri Karai Road, Palavedu, Tamil Nadu 602024, India","distance":278.12,"rating_count":5,"comments_count":12},{"_id":"60b4ec5e541a437c7b9c6109","image":"http://54.212.108.156:3000/api/uploads/60b4ebe8541a437c7b9c61083105193140","service_provider_name":"dean eve","thumbnail_image":"http://54.212.108.156:3000/api/uploads/1624014296213.jpeg","service_price":0,"service_offer":0,"city_name":"","bus_service_list":[{"amount":500,"time_slots":"1 hrs","bus_service_list":" Dog Walking"},{"amount":500,"time_slots":"15 mins","bus_service_list":" Dog Grooming"},{"amount":400,"time_slots":"15 mins","bus_service_list":" Dog Training"}],"service_place":"50, Old GST Rd, Ponnan Nagar, Tambaram West, Irumbuliyur, Chennai, Tamil Nadu 600059, India","distance":260.54,"rating_count":5,"comments_count":12},{"_id":"60b7800933155d6d5beed8c8","image":"http://54.212.108.156:3000/api/uploads/1622638591264.jpeg","service_provider_name":"Sandy pet shop","thumbnail_image":"http://54.212.108.156:3000/api/uploads/1624014307333.jpeg","service_price":0,"service_offer":0,"city_name":"","bus_service_list":[{"amount":100,"bus_service_list":" Dog Walking","time_slots":"15 mins"},{"amount":200,"bus_service_list":" Dog Grooming","time_slots":"15 mins"}],"service_place":"Salem 133, Vmr Theatre Back Side Rd, Shevapet, Salem, Tamil Nadu 636002, India","distance":85.67,"rating_count":5,"comments_count":12},{"_id":"60b783d133155d6d5beed8da","image":"http://54.212.108.156:3000/api/uploads/1622639541685.jpeg","service_provider_name":"Samm Pet Clinic","thumbnail_image":"http://54.212.108.156:3000/api/uploads/1624014322134.jpeg","service_price":0,"service_offer":0,"city_name":"","bus_service_list":[{"amount":100,"bus_service_list":" Dog Walking","time_slots":"15 mins"},{"amount":200,"bus_service_list":" Dog Grooming","time_slots":"15 mins"}],"service_place":"Salem 133, Vmr Theatre Back Side Rd, Shevapet, Salem, Tamil Nadu 636002, India","distance":85.67,"rating_count":5,"comments_count":12}]}
     * banner : [{"title":"title1","image_path":"http://54.212.108.156:3000/api/uploads/60b4dc30541a437c7b9c60f43105182553"},{"title":"title1","image_path":"http://54.212.108.156:3000/api/uploads/60b4dc30541a437c7b9c60f43105182553"},{"title":"title1","image_path":"http://54.212.108.156:3000/api/uploads/60b4dc30541a437c7b9c60f43105182553"},{"title":"title1","image_path":"http://54.212.108.156:3000/api/uploads/60b4dc30541a437c7b9c60f43105182553"}]
     * Code : 200
     */

    private String Status;
    private String Message;
    private String alert_msg;
    /**
     * Service_Details : {"_id":"5fe185d61996f651f5133693","image_path":"http://54.212.108.156:3000/api/uploads/1624556230013.png","title":"Pet Walking","count":0}
     * Service_provider : [{"_id":"60b0b86d67f25056fe286c90","image":"http://54.212.108.156:3000/api/uploads/60b0b7b267f25056fe286c8f2805150112","service_provider_name":"Dinesh sp","thumbnail_image":"http://54.212.108.156:3000/api/uploads/1624014101733.jpeg","service_price":0,"service_offer":0,"city_name":"","bus_service_list":[{"amount":200,"time_slots":"45 mins","bus_service_list":" Dog Walking"},{"amount":300,"time_slots":"15 mins","bus_service_list":" Dog Grooming"}],"service_place":"Eswaran Koil Street,, Harbour quaters, Tirusulam, Chennai, Tamil Nadu 600043, India","distance":269.84,"rating_count":5,"comments_count":12},{"_id":"60b4ea2d541a437c7b9c6102","image":"http://54.212.108.156:3000/api/uploads/60b4e9cc541a437c7b9c61013105192216","service_provider_name":"Cary Carole","thumbnail_image":"http://54.212.108.156:3000/api/uploads/1624014273333.jpeg","service_price":0,"service_offer":0,"city_name":"","bus_service_list":[{"amount":300,"time_slots":"30 mins","bus_service_list":" Dog Walking"},{"amount":259,"time_slots":"15 mins","bus_service_list":" Dog Grooming"},{"amount":300,"time_slots":"15 mins","bus_service_list":" Dog Training"}],"service_place":"ARM College Road, Vadamelpakkam, Tamil Nadu 603204, India","distance":243.73,"rating_count":5,"comments_count":12},{"_id":"60b4eb0c541a437c7b9c6105","image":"http://54.212.108.156:3000/api/uploads/60b4eab6541a437c7b9c61043105192600","service_provider_name":"Clark Charlie ","thumbnail_image":"http://54.212.108.156:3000/api/uploads/1624014285183.jpeg","service_price":0,"service_offer":0,"city_name":"","bus_service_list":[{"amount":300,"time_slots":"45 mins","bus_service_list":" Dog Walking"},{"amount":300,"time_slots":"15 mins","bus_service_list":" Dog Grooming"}],"service_place":"Palavedu Pettai Aeri Karai Road, Palavedu, Tamil Nadu 602024, India","distance":278.12,"rating_count":5,"comments_count":12},{"_id":"60b4ec5e541a437c7b9c6109","image":"http://54.212.108.156:3000/api/uploads/60b4ebe8541a437c7b9c61083105193140","service_provider_name":"dean eve","thumbnail_image":"http://54.212.108.156:3000/api/uploads/1624014296213.jpeg","service_price":0,"service_offer":0,"city_name":"","bus_service_list":[{"amount":500,"time_slots":"1 hrs","bus_service_list":" Dog Walking"},{"amount":500,"time_slots":"15 mins","bus_service_list":" Dog Grooming"},{"amount":400,"time_slots":"15 mins","bus_service_list":" Dog Training"}],"service_place":"50, Old GST Rd, Ponnan Nagar, Tambaram West, Irumbuliyur, Chennai, Tamil Nadu 600059, India","distance":260.54,"rating_count":5,"comments_count":12},{"_id":"60b7800933155d6d5beed8c8","image":"http://54.212.108.156:3000/api/uploads/1622638591264.jpeg","service_provider_name":"Sandy pet shop","thumbnail_image":"http://54.212.108.156:3000/api/uploads/1624014307333.jpeg","service_price":0,"service_offer":0,"city_name":"","bus_service_list":[{"amount":100,"bus_service_list":" Dog Walking","time_slots":"15 mins"},{"amount":200,"bus_service_list":" Dog Grooming","time_slots":"15 mins"}],"service_place":"Salem 133, Vmr Theatre Back Side Rd, Shevapet, Salem, Tamil Nadu 636002, India","distance":85.67,"rating_count":5,"comments_count":12},{"_id":"60b783d133155d6d5beed8da","image":"http://54.212.108.156:3000/api/uploads/1622639541685.jpeg","service_provider_name":"Samm Pet Clinic","thumbnail_image":"http://54.212.108.156:3000/api/uploads/1624014322134.jpeg","service_price":0,"service_offer":0,"city_name":"","bus_service_list":[{"amount":100,"bus_service_list":" Dog Walking","time_slots":"15 mins"},{"amount":200,"bus_service_list":" Dog Grooming","time_slots":"15 mins"}],"service_place":"Salem 133, Vmr Theatre Back Side Rd, Shevapet, Salem, Tamil Nadu 636002, India","distance":85.67,"rating_count":5,"comments_count":12}]
     */

    private DataBean Data;
    private int Code;
    /**
     * title : title1
     * image_path : http://54.212.108.156:3000/api/uploads/60b4dc30541a437c7b9c60f43105182553
     */

    private List<BannerBean> banner;

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

    public String getAlert_msg() {
        return alert_msg;
    }

    public void setAlert_msg(String alert_msg) {
        this.alert_msg = alert_msg;
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

    public List<BannerBean> getBanner() {
        return banner;
    }

    public void setBanner(List<BannerBean> banner) {
        this.banner = banner;
    }

    public static class DataBean {
        /**
         * _id : 5fe185d61996f651f5133693
         * image_path : http://54.212.108.156:3000/api/uploads/1624556230013.png
         * title : Pet Walking
         * count : 0
         */

        private ServiceDetailsBean Service_Details;
        /**
         * _id : 60b0b86d67f25056fe286c90
         * image : http://54.212.108.156:3000/api/uploads/60b0b7b267f25056fe286c8f2805150112
         * service_provider_name : Dinesh sp
         * thumbnail_image : http://54.212.108.156:3000/api/uploads/1624014101733.jpeg
         * service_price : 0
         * service_offer : 0
         * city_name :
         * bus_service_list : [{"amount":200,"time_slots":"45 mins","bus_service_list":" Dog Walking"},{"amount":300,"time_slots":"15 mins","bus_service_list":" Dog Grooming"}]
         * service_place : Eswaran Koil Street,, Harbour quaters, Tirusulam, Chennai, Tamil Nadu 600043, India
         * distance : 269.84
         * rating_count : 5
         * comments_count : 12
         */

        private List<ServiceProviderBean> Service_provider;

        public ServiceDetailsBean getService_Details() {
            return Service_Details;
        }

        public void setService_Details(ServiceDetailsBean Service_Details) {
            this.Service_Details = Service_Details;
        }

        public List<ServiceProviderBean> getService_provider() {
            return Service_provider;
        }

        public void setService_provider(List<ServiceProviderBean> Service_provider) {
            this.Service_provider = Service_provider;
        }

        public static class ServiceDetailsBean {
            private String _id;
            private String image_path;
            private String title;
            private int count;

            public String get_id() {
                return _id;
            }

            public void set_id(String _id) {
                this._id = _id;
            }

            public String getImage_path() {
                return image_path;
            }

            public void setImage_path(String image_path) {
                this.image_path = image_path;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }
        }

        public static class ServiceProviderBean {
            private String _id;
            private String image;
            private String service_provider_name;
            private String thumbnail_image;
            private int service_price;
            private int service_offer;
            private String city_name;
            private String service_place;
            private double distance;
            private int rating_count;
            private int comments_count;
            /**
             * amount : 200
             * time_slots : 45 mins
             * bus_service_list :  Dog Walking
             */

            private List<BusServiceListBean> bus_service_list;

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

            public String getThumbnail_image() {
                return thumbnail_image;
            }

            public void setThumbnail_image(String thumbnail_image) {
                this.thumbnail_image = thumbnail_image;
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

            public String getCity_name() {
                return city_name;
            }

            public void setCity_name(String city_name) {
                this.city_name = city_name;
            }

            public String getService_place() {
                return service_place;
            }

            public void setService_place(String service_place) {
                this.service_place = service_place;
            }

            public double getDistance() {
                return distance;
            }

            public void setDistance(double distance) {
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

            public List<BusServiceListBean> getBus_service_list() {
                return bus_service_list;
            }

            public void setBus_service_list(List<BusServiceListBean> bus_service_list) {
                this.bus_service_list = bus_service_list;
            }

            public static class BusServiceListBean {
                private int amount;
                private String time_slots;
                private String bus_service_list;

                public int getAmount() {
                    return amount;
                }

                public void setAmount(int amount) {
                    this.amount = amount;
                }

                public String getTime_slots() {
                    return time_slots;
                }

                public void setTime_slots(String time_slots) {
                    this.time_slots = time_slots;
                }

                public String getBus_service_list() {
                    return bus_service_list;
                }

                public void setBus_service_list(String bus_service_list) {
                    this.bus_service_list = bus_service_list;
                }
            }
        }
    }

    public static class BannerBean {
        private String title;
        private String image_path;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImage_path() {
            return image_path;
        }

        public void setImage_path(String image_path) {
            this.image_path = image_path;
        }
    }
}
