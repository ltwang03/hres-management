package edu.huflit.hres_management;
 import androidx.appcompat.app.AppCompatActivity;

 import android.annotation.SuppressLint;
 import android.content.Intent;
 import android.os.Bundle;
 import android.view.View;
 import android.widget.EditText;
 import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        TextView mtvSignin = (TextView) findViewById(R.id.tvSignin);
        mtvSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signin = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(signin);
            }
        });
     }
}