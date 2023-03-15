package edu.huflit.hres_management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import edu.huflit.hres_management.Database.DBHelper;

public class RegisterActivity extends AppCompatActivity {

    EditText rusername, rpassword , rrestaurantID;
    Button rbtnSignup;
    RadioGroup rradgroup;
    RadioButton rradManage,rradStaff;
    TextView rtvSignin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        rusername = (EditText) findViewById(R.id.res_edtUsername);
        rpassword = (EditText) findViewById(R.id.res_edtPassword);
        rrestaurantID = (EditText) findViewById(R.id.res_edtRestaurantId);
        rbtnSignup = (Button)findViewById(R.id.btnSignup);
        rtvSignin = (TextView)findViewById(R.id.tvSignin);
        rradgroup = (RadioGroup) findViewById(R.id.radCheckRole);
        rradManage= (RadioButton) findViewById(R.id.radManage);
        rradStaff =(RadioButton) findViewById(R.id.radStaff);
        if(rradManage.isChecked() ) {
            Toast.makeText(this, "this is manager", Toast.LENGTH_SHORT).show();
        } else if(rradStaff.isChecked()){
            Toast.makeText(this, "this is staff", Toast.LENGTH_SHORT).show();
        }

    }
}