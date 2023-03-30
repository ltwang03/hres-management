package edu.huflit.hres_management;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.metrics.Event;
import android.os.Build;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import edu.huflit.hres_management.API.APIService;
import edu.huflit.hres_management.API.model.LoginRequest;
import edu.huflit.hres_management.API.model.LoginResponse;
import edu.huflit.hres_management.Database.DBHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText lusername, lpassword , lrestaurantID;
    private CheckBox lcbRemember;
    private Button lbtnlogin;
    private TextView ltvSignup;
    private DBHelper DB;
    private  boolean passwordvisible;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");
        if(!token.equals("")) {
            Intent i = new Intent(LoginActivity.this, Home.class);
            startActivity(i);
            finish();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        lrestaurantID = (EditText) findViewById(R.id.lrestaurantID);
        lusername = (EditText) findViewById(R.id.ltvSignup);
        lpassword =(EditText) findViewById(R.id.lpassword);
        lcbRemember = (CheckBox) findViewById(R.id.lcbRemember);
        lbtnlogin = (Button) findViewById(R.id.lbtnSignin);
        ltvSignup =(TextView) findViewById(R.id.ltvSignup);
        lpassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                final int right= 2;
                if(motionEvent.getAction()==motionEvent.ACTION_UP){
                    if(motionEvent.getRawX()>= lpassword.getRight()-lpassword.getCompoundDrawables()[right].getBounds().width()){
                        int selection = lpassword.getSelectionEnd();
                        if(passwordvisible){
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                                lpassword.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.baseline_visibility_off_24,0);
                                lpassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                                passwordvisible=false;
                            }
                        }else {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                                lpassword.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.baseline_visibility_24,0);
                            }
                            lpassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordvisible=true;
                        }
                        lpassword.setSelection(selection);
                        return true;
                    }
                }
                return false;
            }
        });
        lbtnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String restaurantID = lrestaurantID.getText().toString();
                String userName = lusername.getText().toString();
                String password = lpassword.getText().toString();
                login(restaurantID,userName,password);
            }
        });

    }
    private void login (String restaurantID, String userName, String password) {
        LoginRequest loginRequest = new LoginRequest(restaurantID, userName, password);
        APIService.apiService.login(loginRequest).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(@NonNull Call<LoginResponse> call,@NonNull Response<LoginResponse> response) {
                if(response.code() != 200) {
                    Toast.makeText(LoginActivity.this, "Vui lòng kiểm tra lại tài khoản", Toast.LENGTH_SHORT).show();
                    return;
                }
                LoginResponse loginResponse = response.body();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("token", loginResponse.getToken());
                editor.apply();
                Toast.makeText(LoginActivity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(LoginActivity.this, Home.class);
                startActivity(i);
                finish();
            }
            @Override
            public void onFailure(@NonNull Call<LoginResponse> call,@NonNull Throwable t) {
                Toast.makeText(LoginActivity.this, "Server đang có vấn đề vui lòng thử lại sau!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}