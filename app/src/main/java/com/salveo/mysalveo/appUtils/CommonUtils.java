package com.salveo.mysalveo.appUtils;

public class CommonUtils {


    public static CommonUtils instance;

    public static CommonUtils getInstance() {
        return instance;
    }

    public static boolean isCabCheckedin = false;
    public static boolean isMessageToGuardNotification = false;
    public static boolean isEmergencyNotification = false;
    public static int pageDateSelected = 0;
    public static String vendorId = "";
    public static boolean isFirstTab = true;
    public static boolean is_logout = false;


}
