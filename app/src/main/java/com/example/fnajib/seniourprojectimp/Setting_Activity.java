package com.example.fnajib.seniourprojectimp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by FNAJIB on 6/9/2015.
 */

public class Setting_Activity  extends ActionBarActivity {
    ///////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////
    EditText profile_code;
    EditText folder_code;
    EditText location_code;
    EditText email_con;
    EditText password;
    Button b1;
    public static final String MyPREFERENCES = "MyControlPrefs" ;
    public static final String codepro = "passcode_profile";
    public static final String codefo = "passcode_folder";
    public static final String codeloc = "passcode_location";
    public static final String mail = "emailKey";
    public static final String pass = "passkey";
    public static final String logset = "logginginapp";
    SharedPreferences sharedpreferences;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_layout);
////////    ////    ///////////////////////////////////////////////////////
        View backgroundimage = findViewById(R.id.background);
        Drawable background = backgroundimage.getBackground();
        background.setAlpha(8);
//////////////////////////////////////////////////////////////////////
        profile_code=(EditText)findViewById(R.id.editText1);
        folder_code=(EditText)findViewById(R.id.editTextfolder);
        location_code=(EditText)findViewById(R.id.editText3);
        email_con=(EditText)findViewById(R.id.editText4);
        password=(EditText)findViewById(R.id.editText5);
        b1=(Button)findViewById(R.id.button2);
        b1.setEnabled(false);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        folder_code.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                b1.setEnabled(true);


            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            public void onTextChanged(CharSequence s, int start,int before, int count) {

            }});
        b1.setOnClickListener(new View .OnClickListener() {
            @Override
            public void onClick(View v) {
                String profile_code_1=profile_code.getText().toString();
                String folder_code_1=folder_code.getText().toString();
                String location_code_1=location_code.getText().toString();
                String email_con_1=email_con.getText().toString();
                String password_1=password.getText().toString();
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString(codepro, profile_code_1);
                editor.putString(codefo, folder_code_1);
                editor.putString(codeloc, location_code_1);
                editor.putString(mail, email_con_1);
                editor.putString(pass, password_1);
                editor.putString(logset,"Yes");
                editor.commit();
                Toast.makeText(Setting_Activity.this,"Data is Saved",Toast.LENGTH_LONG).show();
                Intent myIntent = new Intent(Setting_Activity.this, MainActivity.class);
                startActivity(myIntent);
                finish();




            }
        });
    }






}
