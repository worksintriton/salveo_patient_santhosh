package com.salveo.mysalveo.appUtils;

public final class Constants {

    public static final String FirebaseToken = "firebase_token";
    public static final String AccessToken = "access_token";
    public static final String USER_ID = "user_id";
    public static final String IS_LOGOUT = "is_logout";
    public static final String LAST_SEQUENCE = "last_sequence";
    public static final String INBOX_TOTAL = "total_inbox";
    public static final String APARTMENT_NUMBER = "apartment_no";
    public static final String APARTMENT_ID = "apartment_id";

    public static final String DateFormat1 = "dd-MM-yyyy HH:mm:ss";
    public static final String DateFormat2 = "dd-MM-yyyy h:mm a";

    public static final String DateFormat3 = "h:mm a";
    public static final String DateFormat4 = "dd-MM-yyyy / hh:mm a";
    public static final String DateFormat5 = "dd MMM";
    public static boolean isTokenUpdated = false;
    public static String isTokenUpdateBoolean = "isTokenUpdate";
    public static String isHomeProfileOpen = "isHomeProfileOpen";

    private static Constants mInstance;

    private Constants() {
    }  //private constructor.

    public static Constants getInstance() {
        if (mInstance == null) { //if there is no instance available... create new one
            mInstance = new Constants();
        }

        return mInstance;
    }


}
