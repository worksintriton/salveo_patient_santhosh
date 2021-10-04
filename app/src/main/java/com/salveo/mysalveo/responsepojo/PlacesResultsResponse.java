package com.salveo.mysalveo.responsepojo;

import java.util.List;

public class PlacesResultsResponse {


    /**
     * predictions : [{"description":"Iluppaiyur, Tamil Nadu, India","id":"810c09fb040d3a0eb4bbdf6c172da9b090eb9d02","matched_substrings":[{"length":10,"offset":0}],"place_id":"ChIJEXV-471VqjsR2zieWirwsoE","reference":"ChIJEXV-471VqjsR2zieWirwsoE","structured_formatting":{"main_text":"Iluppaiyur","main_text_matched_substrings":[{"length":10,"offset":0}],"secondary_text":"Tamil Nadu, India"},"terms":[{"offset":0,"value":"Iluppaiyur"},{"offset":12,"value":"Tamil Nadu"},{"offset":24,"value":"India"}],"types":["locality","political","geocode"]},{"description":"Iluppaiyurani, Kovilpatti, Tamil Nadu, India","matched_substrings":[{"length":10,"offset":0}],"place_id":"ChIJL76EZ_KyBjsRql07EQNqkB4","reference":"ChIJL76EZ_KyBjsRql07EQNqkB4","structured_formatting":{"main_text":"Iluppaiyurani","main_text_matched_substrings":[{"length":10,"offset":0}],"secondary_text":"Kovilpatti, Tamil Nadu, India"},"terms":[{"offset":0,"value":"Iluppaiyurani"},{"offset":15,"value":"Kovilpatti"},{"offset":27,"value":"Tamil Nadu"},{"offset":39,"value":"India"}],"types":["sublocality_level_1","sublocality","political","geocode"]},{"description":"Iluppaiyur Road, Illuppaiyur, Tamil Nadu, India","matched_substrings":[{"length":10,"offset":0}],"place_id":"Ei9JbHVwcGFpeXVyIFJvYWQsIElsbHVwcGFpeXVyLCBUYW1pbCBOYWR1LCBJbmRpYSIuKiwKFAoSCa8K6PEPFgE7ET97cIjcoXi8EhQKEgn59PdOpj0BOxFv3i98BuunNA","reference":"Ei9JbHVwcGFpeXVyIFJvYWQsIElsbHVwcGFpeXVyLCBUYW1pbCBOYWR1LCBJbmRpYSIuKiwKFAoSCa8K6PEPFgE7ET97cIjcoXi8EhQKEgn59PdOpj0BOxFv3i98BuunNA","structured_formatting":{"main_text":"Iluppaiyur Road","main_text_matched_substrings":[{"length":10,"offset":0}],"secondary_text":"Illuppaiyur, Tamil Nadu, India"},"terms":[{"offset":0,"value":"Iluppaiyur Road"},{"offset":17,"value":"Illuppaiyur"},{"offset":30,"value":"Tamil Nadu"},{"offset":42,"value":"India"}],"types":["route","geocode"]},{"description":"Iluppaiyur Bridge, Illuppaiyur, Tamil Nadu, India","id":"cf3108eb6afcfa8d5bc5e56c6aa98e96f183d89b","matched_substrings":[{"length":10,"offset":0}],"place_id":"ChIJB75sxKY9ATsRVpwec-2GL2I","reference":"ChIJB75sxKY9ATsRVpwec-2GL2I","structured_formatting":{"main_text":"Iluppaiyur Bridge","main_text_matched_substrings":[{"length":10,"offset":0}],"secondary_text":"Illuppaiyur, Tamil Nadu, India"},"terms":[{"offset":0,"value":"Iluppaiyur Bridge"},{"offset":19,"value":"Illuppaiyur"},{"offset":32,"value":"Tamil Nadu"},{"offset":44,"value":"India"}],"types":["point_of_interest","establishment"]},{"description":"ILUPPAIYUR, Iluppaiyur, Tamil Nadu","matched_substrings":[{"length":10,"offset":0}],"place_id":"ChIJZVVVVVVVqjsR7fzY3SjAA98","reference":"ChIJZVVVVVVVqjsR7fzY3SjAA98","structured_formatting":{"main_text":"ILUPPAIYUR","main_text_matched_substrings":[{"length":10,"offset":0}],"secondary_text":"Iluppaiyur, Tamil Nadu"},"terms":[{"offset":0,"value":"ILUPPAIYUR"},{"offset":12,"value":"Iluppaiyur"},{"offset":24,"value":"Tamil Nadu"}],"types":["city_hall","local_government_office","point_of_interest","establishment"]}]
     * status : OK
     */

    private String status;
    private List<PredictionsBean> predictions;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<PredictionsBean> getPredictions() {
        return predictions;
    }

    public void setPredictions(List<PredictionsBean> predictions) {
        this.predictions = predictions;
    }

    public static class PredictionsBean {
        /**
         * description : Iluppaiyur, Tamil Nadu, India
         * id : 810c09fb040d3a0eb4bbdf6c172da9b090eb9d02
         * matched_substrings : [{"length":10,"offset":0}]
         * place_id : ChIJEXV-471VqjsR2zieWirwsoE
         * reference : ChIJEXV-471VqjsR2zieWirwsoE
         * structured_formatting : {"main_text":"Iluppaiyur","main_text_matched_substrings":[{"length":10,"offset":0}],"secondary_text":"Tamil Nadu, India"}
         * terms : [{"offset":0,"value":"Iluppaiyur"},{"offset":12,"value":"Tamil Nadu"},{"offset":24,"value":"India"}]
         * types : ["locality","political","geocode"]
         */

        private String description;
        private String id;
        private String place_id;
        private String reference;
        private StructuredFormattingBean structured_formatting;
        private List<MatchedSubstringsBean> matched_substrings;
        private List<TermsBean> terms;
        private List<String> types;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPlace_id() {
            return place_id;
        }

        public void setPlace_id(String place_id) {
            this.place_id = place_id;
        }

        public String getReference() {
            return reference;
        }

        public void setReference(String reference) {
            this.reference = reference;
        }

        public StructuredFormattingBean getStructured_formatting() {
            return structured_formatting;
        }

        public void setStructured_formatting(StructuredFormattingBean structured_formatting) {
            this.structured_formatting = structured_formatting;
        }

        public List<MatchedSubstringsBean> getMatched_substrings() {
            return matched_substrings;
        }

        public void setMatched_substrings(List<MatchedSubstringsBean> matched_substrings) {
            this.matched_substrings = matched_substrings;
        }

        public List<TermsBean> getTerms() {
            return terms;
        }

        public void setTerms(List<TermsBean> terms) {
            this.terms = terms;
        }

        public List<String> getTypes() {
            return types;
        }

        public void setTypes(List<String> types) {
            this.types = types;
        }

        public static class StructuredFormattingBean {
            /**
             * main_text : Iluppaiyur
             * main_text_matched_substrings : [{"length":10,"offset":0}]
             * secondary_text : Tamil Nadu, India
             */

            private String main_text;
            private String secondary_text;
            private List<MainTextMatchedSubstringsBean> main_text_matched_substrings;

            public String getMain_text() {
                return main_text;
            }

            public void setMain_text(String main_text) {
                this.main_text = main_text;
            }

            public String getSecondary_text() {
                return secondary_text;
            }

            public void setSecondary_text(String secondary_text) {
                this.secondary_text = secondary_text;
            }

            public List<MainTextMatchedSubstringsBean> getMain_text_matched_substrings() {
                return main_text_matched_substrings;
            }

            public void setMain_text_matched_substrings(List<MainTextMatchedSubstringsBean> main_text_matched_substrings) {
                this.main_text_matched_substrings = main_text_matched_substrings;
            }

            public static class MainTextMatchedSubstringsBean {
                /**
                 * length : 10
                 * offset : 0
                 */

                private int length;
                private int offset;

                public int getLength() {
                    return length;
                }

                public void setLength(int length) {
                    this.length = length;
                }

                public int getOffset() {
                    return offset;
                }

                public void setOffset(int offset) {
                    this.offset = offset;
                }
            }
        }

        public static class MatchedSubstringsBean {
            /**
             * length : 10
             * offset : 0
             */

            private int length;
            private int offset;

            public int getLength() {
                return length;
            }

            public void setLength(int length) {
                this.length = length;
            }

            public int getOffset() {
                return offset;
            }

            public void setOffset(int offset) {
                this.offset = offset;
            }
        }

        public static class TermsBean {
            /**
             * offset : 0
             * value : Iluppaiyur
             */

            private int offset;
            private String value;

            public int getOffset() {
                return offset;
            }

            public void setOffset(int offset) {
                this.offset = offset;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }
    }
}
