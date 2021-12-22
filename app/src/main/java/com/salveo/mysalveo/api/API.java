package com.salveo.mysalveo.api;


import com.salveo.mysalveo.responsepojo.AddressResultsResponse;
import com.salveo.mysalveo.responsepojo.GetAddressResultResponse;
import com.salveo.mysalveo.responsepojo.PlacesResultsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {

    // https://maps.googleapis.com/maps/api/place/autocomplete/json?input=iluppaiyur&key=AIzaSyCVeEuZcqrs9phnrk1aNSpiJ57hb-V8hhE

    String BASE_URL = "https://maps.googleapis.com";
   // String key = "AIzaSyCVeEuZcqrs9phnrk1aNSpiJ57hb-V8hhE";
   public static String MAP_KEY = "AIzaSyDap8qav1flUsql0VWUYkjgB0noN0o_U1Y";

    @GET("/maps/api/place/autocomplete/json")
    Call<PlacesResultsResponse> getCityResults(@Query("input") String input, @Query("key") String key);

    // https://maps.googleapis.com/maps/api/geocode/json?&address=chennai&key=AIzaSyCVeEuZcqrs9phnrk1aNSpiJ57hb-V8hhE


    @GET("/maps/api/geocode/json")
    Call<AddressResultsResponse> getaddressResults(@Query("address") String input, @Query("key") String key);

    //https://maps.googleapis.com/maps/api/geocode/json?latlng=11.039049521240667,78.65450095385313&key=AIzaSyCVeEuZcqrs9phnrk1aNSpiJ57hb-V8hhE

    @GET("/maps/api/geocode/json")
    Call<GetAddressResultResponse> getAddressResultResponseCall(@Query("latlng") String input, @Query("key") String key);
}