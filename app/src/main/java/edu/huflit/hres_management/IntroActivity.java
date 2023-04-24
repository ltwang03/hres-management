package edu.huflit.hres_management;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Objects;

public class IntroActivity extends AppCompatActivity {
    Button mbtnSignin;
    Button mbtnRegister;
    SharedPreferences sharedPreferences;
    private static final  int REQUEST_INTERNET_PERMISSION = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        // nếu có access token thì nhảy vào home, không thì trả về trang Intro
        CheckTokenActivityIntro();
        mbtnSignin = (Button) findViewById(R.id.btnSignin);
        mbtnRegister = (Button) findViewById(R.id.btnRegister);
        if (ContextCompat.checkSelfPermission(IntroActivity.this,
                Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(IntroActivity.this, new String[]{Manifest.permission.INTERNET}, REQUEST_INTERNET_PERMISSION);
        } else {
            mbtnRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent register = new Intent(IntroActivity.this, RegisterActivity.class);
                    startActivity(register);
                }
            });
            mbtnSignin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent signIn = new Intent(IntroActivity.this, LoginActivity.class);
                    startActivity(signIn);
                }
            });
        }
    }
    public void CheckTokenActivityIntro() {
        String token = sharedPreferences.getString("token","");
        if(!Objects.equals(token,"")) {
            Intent i = new Intent(IntroActivity.this, Home.class);
            startActivity(i);
        }
    }
}