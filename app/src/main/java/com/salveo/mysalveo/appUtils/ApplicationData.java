package com.salveo.mysalveo.appUtils;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

public class ApplicationData extends Application {
    private static Context mContext;
    public static ApplicationData instance;

    public static boolean isAppVisible = false;



    public long getTimer_milliseconds() {
        return timer_milliseconds;
    }

    public void setTimer_milliseconds(long timer_milliseconds) {
        this.timer_milliseconds = timer_milliseconds;
    }

    public  long timer_milliseconds = 30000;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        mContext = getApplicationContext();

    }

    public static ApplicationData getInstance() {
        return instance ;
    }

    public static Context getAppContext() {
        return ApplicationData.mContext;
    }



    public void showToast(String msg) {
        Toast toast =   new Toast(getApplicationContext());
        toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

}