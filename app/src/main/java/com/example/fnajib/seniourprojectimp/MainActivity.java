package com.example.fnajib.seniourprojectimp;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
    TextView t1,t2,t3;
    public static final String MyPREFERENCES = "MyControlPrefs" ;
    public static final String codepro = "passcode_profile";
    public static final String codefo = "passcode_folder";
    public static final String codeloc = "passcode_location";
    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //////////////////////////////////////////////



        ///////////////////////////////////////////
        t1=(TextView)findViewById(R.id.passp);
        t2=(TextView)findViewById(R.id.passf);
        t3=(TextView)findViewById(R.id.passloca);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);


        t1.setText("Change profile.........."+sharedpreferences.getString(codepro, null));
        t3.setText("Care folder..............."+sharedpreferences.getString(codefo, null));
        t2.setText("GPS Tracker.............."+sharedpreferences.getString(codeloc, null));





    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
