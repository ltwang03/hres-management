package edu.huflit.hres_management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class IntroActivity extends AppCompatActivity {
    Button mbtnSignin;
    Button mbtnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        mbtnSignin = (Button) findViewById(R.id.btnSignin);
        mbtnRegister = (Button) findViewById(R.id.btnRegister);
        mbtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent register=new Intent(IntroActivity.this,RegisterActivity.class);
                startActivity(register);
            }
        });
        mbtnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signIn = new Intent(IntroActivity.this,LoginActivity.class);
                startActivity(signIn);
            }
        });

    }
}