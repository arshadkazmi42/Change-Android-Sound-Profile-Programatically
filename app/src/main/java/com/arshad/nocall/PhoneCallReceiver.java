package com.arshad.nocall;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import com.android.internal.telephony.ITelephony;

import java.lang.reflect.Method;

public class PhoneCallReceiver extends BroadcastReceiver {
    Context context = null;
    private static final String TAG = "Phone call";
    private ITelephony telephonyService;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e(TAG, "Receving....");
        Log.e("Testing", "code");
        Toast.makeText(context, "Testing", Toast.LENGTH_SHORT).show();
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        try {
            Class c = Class.forName(tm.getClass().getName());
            Method m = c.getDeclaredMethod("getITelephony");
            m.setAccessible(true);
            ITelephony telephonyService = (ITelephony) m.invoke(tm);
            Bundle bundle = intent.getExtras();
            String phoneNumber = bundle.getString("incoming_number");
            Log.e("INCOMING", phoneNumber);
            //if ((phoneNumber != null) && phoneNumber.equals(blacklistednumber)) {
                telephonyService.silenceRinger();
                telephonyService.endCall();
                Log.e("HANG UP", phoneNumber);
            //}

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}