package edu.huflit.hres_management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import edu.huflit.hres_management.Database.DBHelper;

public class LoginActivity extends AppCompatActivity {
    EditText lusername, lpassword , lrestaurantID;
    CheckBox lcbRemember;
    Button lbtnlogin;
    TextView ltvSignup;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        lrestaurantID = (EditText) findViewById(R.id.lrestaurantID);
        lusername = (EditText) findViewById(R.id.ltvSignup);
        lpassword =(EditText) findViewById(R.id.lpassword);
        lcbRemember = (CheckBox) findViewById(R.id.lcbRemember);
        lbtnlogin = (Button) findViewById(R.id.lbtnSignin);
        ltvSignup =(TextView) findViewById(R.id.ltvSignup);
        DB = new DBHelper(this);
        lbtnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String restaurantID = lrestaurantID.getText().toString();
                String username = lusername.getText().toString();
                String password = lpassword.getText().toString();
                if(username.equals("") || password.equals("") || lrestaurantID.equals("")) {
                    Toast.makeText(LoginActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    Boolean checkAll = DB.checkUsernameandpassword(username,password);
                    if(checkAll == true){
                        Toast.makeText(LoginActivity.this, "Login successfully" , Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(LoginActivity.this, HomePageActivity.class);
                        startActivity(i);
                    }
                    else {
                        Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }

                }



            }
        });
        ltvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(i);
            }
        });



    }
}