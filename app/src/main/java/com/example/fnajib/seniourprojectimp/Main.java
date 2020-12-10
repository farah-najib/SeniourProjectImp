package com.example.fnajib.seniourprojectimp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by FNAJIB on 6/9/2015.
 */
public class Main extends Activity {

   public static final String MyPREFERENCES = "MyControlPrefs" ;
   public static final String logset = "logginginapp";
    SharedPreferences   sharedpreferences ;
    public String user;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
       user=sharedpreferences.getString(logset, null);
        //Log.d("hi", user);
        if(sharedpreferences.getString(logset, null)==null) {

            Intent myIntent = new Intent(Main.this, Setting_Activity.class);

            startActivity(myIntent);
            finish();


        }
        else
        {
            Intent myIntent1 = new Intent(Main.this, MainActivity.class);

            startActivity(myIntent1);
            finish();

        }

    }
}
