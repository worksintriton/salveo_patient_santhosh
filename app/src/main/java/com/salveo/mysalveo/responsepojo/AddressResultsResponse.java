package com.salveo.mysalveo.responsepojo;

import java.util.List;

public class AddressResultsResponse {

    /**
     * results : [{"address_components":[{"long_name":"Chennai","short_name":"Chennai","types":["locality","political"]},{"long_name":"Chennai","short_name":"Chennai","types":["administrative_area_level_2","political"]},{"long_name":"Tamil Nadu","short_name":"TN","types":["administrative_area_level_1","political"]},{"long_name":"India","short_name":"IN","types":["country","political"]}],"formatted_address":"Chennai, Tamil Nadu, India","geometry":{"bounds":{"northeast":{"lat":13.2611661,"lng":80.33632279999999},"southwest":{"lat":12.8338848,"lng":80.0817007}},"location":{"lat":13.0826802,"lng":80.2707184},"location_type":"APPROXIMATE","viewport":{"northeast":{"lat":13.2611661,"lng":80.33632279999999},"southwest":{"lat":12.8338848,"lng":80.0817007}}},"place_id":"ChIJYTN9T-plUjoRM9RjaAunYW4","types":["locality","political"]}]
     * status : OK
     */

    private String status;
    private List<ResultsBean> results;

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

    public static class ResultsBean {
        /**
         * address_components : [{"long_name":"Chennai","short_name":"Chennai","types":["locality","political"]},{"long_name":"Chennai","short_name":"Chennai","types":["administrative_area_level_2","political"]},{"long_name":"Tamil Nadu","short_name":"TN","types":["administrative_area_level_1","political"]},{"long_name":"India","short_name":"IN","types":["country","political"]}]
         * formatted_address : Chennai, Tamil Nadu, India
         * geometry : {"bounds":{"northeast":{"lat":13.2611661,"lng":80.33632279999999},"southwest":{"lat":12.8338848,"lng":80.0817007}},"location":{"lat":13.0826802,"lng":80.2707184},"location_type":"APPROXIMATE","viewport":{"northeast":{"lat":13.2611661,"lng":80.33632279999999},"southwest":{"lat":12.8338848,"lng":80.0817007}}}
         * place_id : ChIJYTN9T-plUjoRM9RjaAunYW4
         * types : ["locality","political"]
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
             * bounds : {"northeast":{"lat":13.2611661,"lng":80.33632279999999},"southwest":{"lat":12.8338848,"lng":80.0817007}}
             * location : {"lat":13.0826802,"lng":80.2707184}
             * location_type : APPROXIMATE
             * viewport : {"northeast":{"lat":13.2611661,"lng":80.33632279999999},"southwest":{"lat":12.8338848,"lng":80.0817007}}
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
                 * northeast : {"lat":13.2611661,"lng":80.33632279999999}
                 * southwest : {"lat":12.8338848,"lng":80.0817007}
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
                     * lat : 13.2611661
                     * lng : 80.33632279999999
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
                     * lat : 12.8338848
                     * lng : 80.0817007
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
                 * lat : 13.0826802
                 * lng : 80.2707184
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
                 * northeast : {"lat":13.2611661,"lng":80.33632279999999}
                 * southwest : {"lat":12.8338848,"lng":80.0817007}
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
                     * lat : 13.2611661
                     * lng : 80.33632279999999
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
                     * lat : 12.8338848
                     * lng : 80.0817007
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
             * long_name : Chennai
             * short_name : Chennai
             * types : ["locality","political"]
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
