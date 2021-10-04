package com.salveo.mysalveo.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

public class SmsBroadcastListener extends BroadcastReceiver {

    private static final String TAG = "SmsBroadcastListener" ;
    private static OTPSmsListener mListener;
    Boolean b;
    String otpTextRecived;

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle data  = intent.getExtras();
        assert data != null;
        Object[] pdus = (Object[]) data.get("pdus");
        assert pdus != null;
        for (Object o : pdus) {
            SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) o);
            String sender = smsMessage.getDisplayOriginatingAddress();
            String messageBody = smsMessage.getMessageBody();
            otpTextRecived = messageBody.replaceAll("[^0-9]", "");
            Log.w(TAG,"otpTextRecived: "+otpTextRecived);
            // attach value to interface
            if(mListener != null) {
                mListener.onMessageReceived(otpTextRecived);
            }
        }
    }

    public static void bindListener(OTPSmsListener listener) {
        mListener = listener;
    }
    public static void unbindListener() {
        mListener = null;
    }
}
