package com.salveo.mysalveo.responsepojo;

import java.util.List;

public class GetAddressResultResponse {


    /**
     * plus_code : {"compound_code":"2MQ3+JR Omandhur, Tamil Nadu, India","global_code":"7J3W2MQ3+JR"}
     * results : [{"address_components":[{"long_name":"Unnamed Road","short_name":"Unnamed Road","types":["route"]},{"long_name":"Tiruchirappalli","short_name":"Tiruchirappalli","types":["administrative_area_level_2","political"]},{"long_name":"Tamil Nadu","short_name":"TN","types":["administrative_area_level_1","political"]},{"long_name":"India","short_name":"IN","types":["country","political"]}],"formatted_address":"Unnamed Road, Tamil Nadu, India","geometry":{"bounds":{"northeast":{"lat":11.0476456,"lng":78.6617557},"southwest":{"lat":11.0326836,"lng":78.6445896}},"location":{"lat":11.0411159,"lng":78.65427509999999},"location_type":"GEOMETRIC_CENTER","viewport":{"northeast":{"lat":11.0476456,"lng":78.6617557},"southwest":{"lat":11.0326836,"lng":78.6445896}}},"place_id":"ChIJVwfQA_9VqjsRTujg-lB3Tiw","types":["route"]},{"address_components":[{"long_name":"Omandhur","short_name":"Omandhur","types":["locality","political"]},{"long_name":"Tiruchirappalli","short_name":"Tiruchirappalli","types":["administrative_area_level_2","political"]},{"long_name":"Tamil Nadu","short_name":"TN","types":["administrative_area_level_1","political"]},{"long_name":"India","short_name":"IN","types":["country","political"]}],"formatted_address":"Omandhur, Tamil Nadu, India","geometry":{"bounds":{"northeast":{"lat":11.0438931,"lng":78.6775803},"southwest":{"lat":11.023535,"lng":78.64390499999999}},"location":{"lat":11.0351462,"lng":78.6540576},"location_type":"APPROXIMATE","viewport":{"northeast":{"lat":11.0438931,"lng":78.6775803},"southwest":{"lat":11.023535,"lng":78.64390499999999}}},"place_id":"ChIJmzwj11L_qjsRIl7piM6mfnY","types":["locality","political"]},{"address_components":[{"long_name":"621006","short_name":"621006","types":["postal_code"]},{"long_name":"Tiruchirappalli","short_name":"Tiruchirappalli","types":["administrative_area_level_2","political"]},{"long_name":"Tamil Nadu","short_name":"TN","types":["administrative_area_level_1","political"]},{"long_name":"India","short_name":"IN","types":["country","political"]}],"formatted_address":"Tamil Nadu 621006, India","geometry":{"bounds":{"northeast":{"lat":11.0813989,"lng":78.6842856},"southwest":{"lat":10.9471545,"lng":78.5486118}},"location":{"lat":11.0256218,"lng":78.62285039999999},"location_type":"APPROXIMATE","viewport":{"northeast":{"lat":11.0813989,"lng":78.6842856},"southwest":{"lat":10.9471545,"lng":78.5486118}}},"place_id":"ChIJMefZb3xWqjsRvGW6lJQd79k","types":["postal_code"]},{"address_components":[{"long_name":"Tiruchirappalli","short_name":"Tiruchirappalli","types":["administrative_area_level_2","political"]},{"long_name":"Tamil Nadu","short_name":"TN","types":["administrative_area_level_1","political"]},{"long_name":"India","short_name":"IN","types":["country","political"]}],"formatted_address":"Tiruchirappalli, Tamil Nadu, India","geometry":{"bounds":{"northeast":{"lat":11.4017741,"lng":79.012108},"southwest":{"lat":10.291368,"lng":78.172745}},"location":{"lat":11.034599,"lng":78.5660852},"location_type":"APPROXIMATE","viewport":{"northeast":{"lat":11.4017741,"lng":79.012108},"southwest":{"lat":10.291368,"lng":78.172745}}},"place_id":"ChIJxcabUBz1qjsReNP3EsbCCNI","types":["administrative_area_level_2","political"]},{"address_components":[{"long_name":"Tamil Nadu","short_name":"TN","types":["administrative_area_level_1","political"]},{"long_name":"India","short_name":"IN","types":["country","political"]}],"formatted_address":"Tamil Nadu, India","geometry":{"bounds":{"northeast":{"lat":13.5493493,"lng":80.3464511},"southwest":{"lat":8.0690069,"lng":76.23055409999999}},"location":{"lat":11.1271225,"lng":78.6568942},"location_type":"APPROXIMATE","viewport":{"northeast":{"lat":13.5493493,"lng":80.3464511},"southwest":{"lat":8.0690069,"lng":76.23055409999999}}},"place_id":"ChIJM5YYsYLFADsR8GEzRsx1lFU","types":["administrative_area_level_1","political"]},{"address_components":[{"long_name":"India","short_name":"IN","types":["country","political"]}],"formatted_address":"India","geometry":{"bounds":{"northeast":{"lat":35.513327,"lng":97.39535869999999},"southwest":{"lat":6.4626999,"lng":68.1097}},"location":{"lat":20.593684,"lng":78.96288},"location_type":"APPROXIMATE","viewport":{"northeast":{"lat":35.513327,"lng":97.39535869999999},"southwest":{"lat":6.4626999,"lng":68.1097}}},"place_id":"ChIJkbeSa_BfYzARphNChaFPjNc","types":["country","political"]}]
     * status : OK
     */

    private PlusCodeBean plus_code;
    private String status;
    private List<ResultsBean> results;

    public PlusCodeBean getPlus_code() {
        return plus_code;
    }

    public void setPlus_code(PlusCodeBean plus_code) {
        this.plus_code = plus_code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class PlusCodeBean {
        /**
         * compound_code : 2MQ3+JR Omandhur, Tamil Nadu, India
         * global_code : 7J3W2MQ3+JR
         */

        private String compound_code;
        private String global_code;

        public String getCompound_code() {
            return compound_code;
        }

        public void setCompound_code(String compound_code) {
            this.compound_code = compound_code;
        }

        public String getGlobal_code() {
            return global_code;
        }

        public void setGlobal_code(String global_code) {
            this.global_code = global_code;
        }
    }

    public static class ResultsBean {
        /**
         * address_components : [{"long_name":"Unnamed Road","short_name":"Unnamed Road","types":["route"]},{"long_name":"Tiruchirappalli","short_name":"Tiruchirappalli","types":["administrative_area_level_2","political"]},{"long_name":"Tamil Nadu","short_name":"TN","types":["administrative_area_level_1","political"]},{"long_name":"India","short_name":"IN","types":["country","political"]}]
         * formatted_address : Unnamed Road, Tamil Nadu, India
         * geometry : {"bounds":{"northeast":{"lat":11.0476456,"lng":78.6617557},"southwest":{"lat":11.0326836,"lng":78.6445896}},"location":{"lat":11.0411159,"lng":78.65427509999999},"location_type":"GEOMETRIC_CENTER","viewport":{"northeast":{"lat":11.0476456,"lng":78.6617557},"southwest":{"lat":11.0326836,"lng":78.6445896}}}
         * place_id : ChIJVwfQA_9VqjsRTujg-lB3Tiw
         * types : ["route"]
         */

        private String formatted_address;
        private GeometryBean geometry;
        private String place_id;
        private List<AddressComponentsBean> address_components;
        private List<String> types;

        public String getFormatted_address() {
            return formatted_address;
        }

        public void setFormatted_address(String formatted_address) {
            this.formatted_address = formatted_address;
        }

        public GeometryBean getGeometry() {
            return geometry;
        }

        public void setGeometry(GeometryBean geometry) {
            this.geometry = geometry;
        }

        public String getPlace_id() {
            return place_id;
        }

        public void setPlace_id(String place_id) {
            this.place_id = place_id;
        }

        public List<AddressComponentsBean> getAddress_components() {
            return address_components;
        }

        public void setAddress_components(List<AddressComponentsBean> address_components) {
            this.address_components = address_components;
        }

        public List<String> getTypes() {
            return types;
        }

        public void setTypes(List<String> types) {
            this.types = types;
        }

        public static class GeometryBean {
            /**
             * bounds : {"northeast":{"lat":11.0476456,"lng":78.6617557},"southwest":{"lat":11.0326836,"lng":78.6445896}}
             * location : {"lat":11.0411159,"lng":78.65427509999999}
             * location_type : GEOMETRIC_CENTER
             * viewport : {"northeast":{"lat":11.0476456,"lng":78.6617557},"southwest":{"lat":11.0326836,"lng":78.6445896}}
             */

            private BoundsBean bounds;
            private LocationBean location;
            private String location_type;
            private ViewportBean viewport;

            public BoundsBean getBounds() {
                return bounds;
            }

            public void setBounds(BoundsBean bounds) {
                this.bounds = bounds;
            }

            public LocationBean getLocation() {
                return location;
            }

            public void setLocation(LocationBean location) {
                this.location = location;
            }

            public String getLocation_type() {
                return location_type;
            }

            public void setLocation_type(String location_type) {
                this.location_type = location_type;
            }

            public ViewportBean getViewport() {
                return viewport;
            }

            public void setViewport(ViewportBean viewport) {
                this.viewport = viewport;
            }

            public static class BoundsBean {
                /**
                 * northeast : {"lat":11.0476456,"lng":78.6617557}
                 * southwest : {"lat":11.0326836,"lng":78.6445896}
                 */

                private NortheastBean northeast;
                private SouthwestBean southwest;

                public NortheastBean getNortheast() {
                    return northeast;
                }

                public void setNortheast(NortheastBean northeast) {
                    this.northeast = northeast;
                }

                public SouthwestBean getSouthwest() {
                    return southwest;
                }

                public void setSouthwest(SouthwestBean southwest) {
                    this.southwest = southwest;
                }

                public static class NortheastBean {
                    /**
                     * lat : 11.0476456
                     * lng : 78.6617557
                     */

                    private double lat;
                    private double lng;

                    public double getLat() {
                        return lat;
                    }

                    public void setLat(double lat) {
                        this.lat = lat;
                    }

                    public double getLng() {
                        return lng;
                    }

                    public void setLng(double lng) {
                        this.lng = lng;
                    }
                }

                public static class SouthwestBean {
                    /**
                     * lat : 11.0326836
                     * lng : 78.6445896
                     */

                    private double lat;
                    private double lng;

                    public double getLat() {
                        return lat;
                    }

                    public void setLat(double lat) {
                        this.lat = lat;
                    }

                    public double getLng() {
                        return lng;
                    }

                    public void setLng(double lng) {
                        this.lng = lng;
                    }
                }
            }

            public static class LocationBean {
                /**
                 * lat : 11.0411159
                 * lng : 78.65427509999999
                 */

                private double lat;
                private double lng;

                public double getLat() {
                    return lat;
                }

                public void setLat(double lat) {
                    this.lat = lat;
                }

                public double getLng() {
                    return lng;
                }

                public void setLng(double lng) {
                    this.lng = lng;
                }
            }

            public static class ViewportBean {
                /**
                 * northeast : {"lat":11.0476456,"lng":78.6617557}
                 * southwest : {"lat":11.0326836,"lng":78.6445896}
                 */

                private NortheastBeanX northeast;
                private SouthwestBeanX southwest;

                public NortheastBeanX getNortheast() {
                    return northeast;
                }

                public void setNortheast(NortheastBeanX northeast) {
                    this.northeast = northeast;
                }

                public SouthwestBeanX getSouthwest() {
                    return southwest;
                }

                public void setSouthwest(SouthwestBeanX southwest) {
                    this.southwest = southwest;
                }

                public static class NortheastBeanX {
                    /**
                     * lat : 11.0476456
                     * lng : 78.6617557
                     */

                    private double lat;
                    private double lng;

                    public double getLat() {
                        return lat;
                    }

                    public void setLat(double lat) {
                        this.lat = lat;
                    }

                    public double getLng() {
                        return lng;
                    }

                    public void setLng(double lng) {
                        this.lng = lng;
                    }
                }

                public static class SouthwestBeanX {
                    /**
                     * lat : 11.0326836
                     * lng : 78.6445896
                     */

                    private double lat;
                    private double lng;

                    public double getLat() {
                        return lat;
                    }

                    public void setLat(double lat) {
                        this.lat = lat;
                    }

                    public double getLng() {
                        return lng;
                    }

                    public void setLng(double lng) {
                        this.lng = lng;
                    }
                }
            }
        }

        public static class AddressComponentsBean {
            /**
             * long_name : Unnamed Road
             * short_name : Unnamed Road
             * types : ["route"]
             */

            private String long_name;
            private String short_name;
            private List<String> types;

            public String getLong_name() {
                return long_name;
            }

            public void setLong_name(String long_name) {
                this.long_name = long_name;
            }

            public String getShort_name() {
                return short_name;
            }

            public void setShort_name(String short_name) {
                this.short_name = short_name;
            }

            public List<String> getTypes() {
                return types;
            }

            public void setTypes(List<String> types) {
                this.types = types;
            }
        }
    }
}
