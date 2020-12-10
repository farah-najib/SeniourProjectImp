package com.example.fnajib.seniourprojectimp;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.util.Log;

/**
 * Created by FNAJIB on 6/12/2015.
 */
public class SendLocation extends ContextWrapper {
    public Mail m;
    AppLocationService appLocationService;
    public   String sms;
    public int con=0;
    public SendLocation(Context base) {
        super(base);

    }

    public   void  send ()                                                                                                        //public   void  send (String phoneNo)
    {

        appLocationService = new AppLocationService(SendLocation.this);
        Location location = appLocationService.getLocation(LocationManager.GPS_PROVIDER);

        while (con==0)
        {
            if (location != null)
            {
                Log.i("GPS","try find");

                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                LocationAddress locationAddress = new LocationAddress();
                locationAddress.getAddressFromLocation(latitude, longitude, getApplicationContext(), new GeocoderHandler());
                Log.i("msg", "find location");
                con=1;

            }
            else
            {
                Log.i("GPS", "is off");
                //turnGPSOn();


            }
        }

    }


    public void turnGPSOn()
    {
        //note :That way not work on OS version 4.0 above
        Intent intent = new Intent("android.location.GPS_ENABLED_CHANGE");
        intent.putExtra("enabled", true);
        sendBroadcast(intent);
        Log.i("GPS", "try to set on");



    }



    private class GeocoderHandler extends Handler {
        @Override
        public void handleMessage(Message message) {
            String locationAddress;
            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    locationAddress = bundle.getString("address");
                    break;
                default:
                    locationAddress = null;
            }
            // tvAddress.setText(locationAddress);
            sms=locationAddress.toString();
            Log.d("msg", sms);
            if (android.os.Build.VERSION.SDK_INT > 9) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }
            m = new Mail("farahengnajib@gmail.com","f1992f2014f");
            String[] toArr = {"farahengnajib@gmail.com"}; // This is an array, you can add more emails, just separate them with a coma
            m.setTo(toArr); // load array to setTo function
            m.setFrom("farahengnajib@gmail.com"); // who is sending the email
            m.setSubject("subject");
            m.setBody(sms);
            try
            {
                if(m.send())
                {
                    Log.i("msg", "is location sent");

                }
                else
                {
                    Log.i("msg", "location sent proble");
                    // ddd=   deleteNon_EmptyDir( new File("/sdcard/myPicture.jpg"));
                }
            }
            catch(Exception e)
            {
                Log.d("msg", "failure exception");
                //ddd=   deleteNon_EmptyDir( new File("/sdcard/myPicture.jpg"));
            }
            // ----------------------------------------------------------//
            //*********************************************************//
            //*********************************************************//

              /* try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, sms, null, null);

        } catch (Exception e) {

            e.printStackTrace();
        }*/




        }
    }
}
