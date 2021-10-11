package com.salveo.mysalveo.api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class APIClient {

    private static Retrofit retrofit = null;
    private static OkHttpClient client;


    /*live*/
  /*public static String BASE_URL = "http://52.25.163.13:3000/api/";
  public static String IMAGE_BASE_URL = "http://52.25.163.13:3000/";*/

     /*dev*/
    public static String BASE_URL = "http://13.57.9.246:3000/api/";
    public static String IMAGE_BASE_URL = "http://13.57.9.246:3000/";

    /*Banner Image*/
    public static String BANNER_IMAGE_URL = BASE_URL+"uploads/bannerempty.jpg";

    /* Profile or other Image*/
    public static String PROFILE_IMAGE_URL = BASE_URL+"uploads/picempty.jpg";

    public static String VENDOR_ID = "";


    public static String DISTANCE = "";

    public static String SP_DISTANCE = "";


    public static Retrofit getClient() {
        client = new OkHttpClient();
        client = new OkHttpClient.Builder()
                .readTimeout(3, TimeUnit.MINUTES)
                .connectTimeout(3, TimeUnit.MINUTES)
                .writeTimeout(3, TimeUnit.MINUTES )
                .cache(null)
                .build();
                retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit;
    }
    public static Retrofit getImageClient() {
        client = new OkHttpClient();
        client = new OkHttpClient.Builder()
                .readTimeout(3, TimeUnit.MINUTES)
                .connectTimeout(3, TimeUnit.MINUTES)
                .writeTimeout(3, TimeUnit.MINUTES )
                .cache(null)
                .build();
                retrofit = new Retrofit.Builder()
                .baseUrl(IMAGE_BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit;
    }
}
