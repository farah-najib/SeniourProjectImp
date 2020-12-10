package com.example.fnajib.seniourprojectimp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.telephony.SmsMessage;
import android.util.Log;

import java.io.File;

/**
 * Created by FNAJIB on 6/12/2015.
 */
public class SMSBReceiver extends BroadcastReceiver {
    SharedPreferences settings;
    public static final String codepro = "passcode_profile";
    public static final String codefo = "passcode_folder";
    public static final String codeloc = "passcode_location";
    public static final String mail = "emailKey";
    public static final String pass = "passkey";
    public static final String MyPREFERENCES = "MyControlPrefs" ;
    private  Mail m;
     boolean ddd;
    @Override
    public void onReceive(Context context, Intent intent)
    {


        // Get Bundle object contained in the SMS intent passed in
        Bundle bundle = intent.getExtras();
        SmsMessage[] smsm = null;
        settings = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE); //1
        if (bundle != null)
        {

            // Get the SMS message
            Object[] pdus = (Object[]) bundle.get("pdus");
            smsm = new SmsMessage[pdus.length];
            Log.i("Message Text", " :is Receive");
            for (int i=0; i<smsm.length; i++){
                smsm[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
                String smsBody = smsm[i].getMessageBody().toString();
                String address=  smsm[i].getOriginatingAddress();
                Log.i("Message Text", ": is Content Read");
                if (smsBody.contains(settings.getString(codepro, null)))    //Step 1: Change Profile from Silent to General
                {
                    Log.i("Message Text", ": is Match to passcode change profile");
                    AudioManager am = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
                    int mod = am.getRingerMode();
                    if(mod == AudioManager.RINGER_MODE_SILENT || mod == AudioManager.RINGER_MODE_VIBRATE)
                    {
                        am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                    }
                }//end:Step_1
                else
                if(smsBody.contains(settings.getString(codefo, null)))  //Step 2 :send Backup folder to email and delete
                {    Log.i("Message Text", ": is Match to passcode care folder ");
                    if (android.os.Build.VERSION.SDK_INT > 9) {
                        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                        StrictMode.setThreadPolicy(policy);
                    }
                    m = new Mail(settings.getString(mail, null),settings.getString(pass, null));
                    String[] toArr = {settings.getString(mail, null)}; // This is an array, you can add more emails, just separate them with a coma
                    m.setTo(toArr); // load array to setTo function
                    m.setFrom(settings.getString(mail, null)); // who is sending the email
                    m.setSubject("subject");
                    m.setBody("your message goes here");
                    try
                    {
                         m.addAttachment("/sdcard/My_picture.jpg");  // path to file you want to attach   My_picture
                        if(m.send())
                        {
                            Log.i("msg", "is sent");
                           // ddd=   deleteDirectory( new File("/sdcard/myPicture.jpg"));
                        }
                        else
                        {
                            Log.i("msg", "proble");
                            // ddd=   deleteDirectory( new File("/sdcard/project-folder"));
                        }
                    }
                    catch(Exception e)
                    {
                        Log.i("msg", "failure");
                       // ddd=   deleteDirectory( new File("/sdcard/project-folder"));
                    }

                }//end:Step_
             else
                if(smsBody.contains( settings.getString(codeloc, null)))// Step 3: Determine Location
                {   Log.i("Message Text", ": is Match to passcode location care");
                    SendLocation ss= new SendLocation(context);
                    ss.send();
                  //  ss.send(address);

                }//end : Step _3


            }


        }
    }

   /* public static boolean deleteNon_EmptyDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i=0; i<children.length; i++) {
                boolean success = deleteNon_EmptyDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }
*/

    public static boolean deleteDirectory(File path) {
        if( path.exists() ) {
            File[] files = path.listFiles();
            if (files == null) {
                return true;
            }
            for(int i=0; i<files.length; i++) {
                if(files[i].isDirectory()) {
                    deleteDirectory(files[i]);
                }
                else {
                    files[i].delete();
                }
            }
        }
        return( path.delete() );
    }

}
