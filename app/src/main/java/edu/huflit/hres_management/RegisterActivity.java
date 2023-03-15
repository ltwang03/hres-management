package edu.huflit.hres_management;

import androidx.annotation.NonNull;
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

import edu.huflit.hres_management.API.APIService;
import edu.huflit.hres_management.API.model.RegisterManagerRequest;
import edu.huflit.hres_management.API.model.RegisterManagerResponse;
import edu.huflit.hres_management.API.model.RegisterStaffRequest;
import edu.huflit.hres_management.API.model.RegisterStaffResponse;
import edu.huflit.hres_management.Database.DBHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        rbtnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rradManage.isChecked() ) {
                    String restaurantID = rrestaurantID.getText().toString();
                    String userName = rusername.getText().toString();
                    String password = rpassword.getText().toString();
                    String role = rradManage.getText().toString();
                    registerManager(restaurantID, userName,password,role);

                } else if(rradStaff.isChecked()){
                    String restaurantID = rrestaurantID.getText().toString();
                    String userName = rusername.getText().toString();
                    String password = rpassword.getText().toString();
                    String role = rradManage.getText().toString();
                    registerStaff(restaurantID, userName,password, role);

                }else {
                    Toast.makeText(RegisterActivity.this, "Vui lòng chọn role của bạn!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private void registerManager(String restaurantID, String userName, String password, String role ) {
        RegisterManagerRequest registerManagerRequest = new RegisterManagerRequest(restaurantID,userName, password, role);
        APIService.apiService.registerManager(registerManagerRequest).enqueue(new Callback<RegisterManagerResponse>() {
            @Override
            public void onResponse(@NonNull Call<RegisterManagerResponse> call,@NonNull Response<RegisterManagerResponse> response) {
                if(response.code() != 200) {
                    Toast.makeText(RegisterActivity.this, "Vui lòng kiểm tra lại tài khoản của bạn!", Toast.LENGTH_SHORT).show();
                    return;
                }
                RegisterManagerResponse registerManagerResponse = response.body();
                Toast.makeText(RegisterActivity.this, registerManagerResponse.getMessage(), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(i);
            }

            @Override
            public void onFailure(Call<RegisterManagerResponse> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Server đang có vấn đề vui lòng thử lại sau!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void registerStaff(String restaurantID, String userName, String password, String role) {
        RegisterStaffRequest registerStaffRequest = new RegisterStaffRequest(restaurantID, userName, password, role);
        APIService.apiService.registerStaff(registerStaffRequest).enqueue(new Callback<RegisterStaffResponse>() {
            @Override
            public void onResponse(@NonNull Call<RegisterStaffResponse> call,@NonNull Response<RegisterStaffResponse> response) {
                if(response.code() != 200) {
                    Toast.makeText(RegisterActivity.this, "Vui lòng kiểm tra lại tài khoản của bạn", Toast.LENGTH_SHORT).show();
                    return;
                }
                RegisterStaffResponse registerStaffResponse = response.body();
                Toast.makeText(RegisterActivity.this, registerStaffResponse.getMessage(), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(i);
            }

            @Override
            public void onFailure(Call<RegisterStaffResponse> call, Throwable t) {

            }
        });
    }
}