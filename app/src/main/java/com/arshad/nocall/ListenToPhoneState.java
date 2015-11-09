package com.arshad.nocall;

import android.content.Context;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

class ListenToPhoneState extends PhoneStateListener {

    boolean callEnded=false;
    Context context;
    public ListenToPhoneState(Context c) {
        context = c;
    }

    public void onCallStateChanged(int state, String incomingNumber) {

        switch (state) {
            case TelephonyManager.CALL_STATE_IDLE:
                Log.e("State changed: ", state + "Idle");


                if(callEnded)
                {
                    //you will be here at **STEP 4**
                    //you should stop service again over here
                }
                else
                {
                    //you will be here at **STEP 1**
                    //stop your service over here,
                    //i.e. stopService (new Intent(`your_context`,`CallService.class`));
                    //NOTE: `your_context` with appropriate context and `CallService.class` with appropriate class name of your service which you want to stop.

                }


                break;
            case TelephonyManager.CALL_STATE_OFFHOOK:
                Log.e("State changed: " , state+"Offhook");
                //you will be here at **STEP 3**
                // you will be here when you cut call
                callEnded=true;
                break;
            case TelephonyManager.CALL_STATE_RINGING:
                Log.e("State changed: " , state+"Ringing");
                //you will be here at **STEP 2**
                //Intent serviceIntent = new Intent(context, PhoneCallReceiver.class);
                //serviceIntent.setAction("com.arshad.nocall.PhoneCallReceiver");
                //context.startService(serviceIntent);

                break;


            default:
                break;
        }
    }

}
