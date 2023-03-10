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
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        rusername = (EditText) findViewById(R.id.res_edtUsername);
        rpassword = (EditText) findViewById(R.id.res_edtPassword);
        rrestaurantID = (EditText) findViewById(R.id.res_edtRestaurantId);
        rbtnSignup = (Button)findViewById(R.id.btnSignup);
        rtvSignin = (TextView)findViewById(R.id.tvSignin);
        rradManage= (RadioButton) findViewById(R.id.radManage);
        rradStaff =(RadioButton) findViewById(R.id.radStaff);
        DB = new DBHelper(this);
        rbtnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String restaurantID = rrestaurantID.getText().toString();
                String username = rusername.getText().toString();
                String password = rpassword.getText().toString();
                if(username.equals("") || password.equals("") || rrestaurantID.equals("")) {
                    Toast.makeText(RegisterActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                }
                else {

                    Boolean checkuser = DB.checkUsername(username);
                    if(checkuser == false) {
                        Boolean insert = DB.insertData(username,password);
                        if(insert == true){
                            Toast.makeText(RegisterActivity.this, "REGISTER SUCCESSFULLy"  , Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(i);
                        }
                        else {
                            Toast.makeText(RegisterActivity.this, "Register failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(RegisterActivity.this, " User already exists! " ,  Toast.LENGTH_SHORT).show();
                    }

                }





            }
        });


    }
}