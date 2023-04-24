package edu.huflit.hres_management;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import edu.huflit.hres_management.Fragment.BottomBarFragment;
import edu.huflit.hres_management.Fragment.InfoProfileFragment;

public class ProfileActivity extends AppCompatActivity {
    ImageView icBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Fragment infoProfile = new InfoProfileFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.layout_profile, infoProfile).commit();
        icBack = findViewById(R.id.ic_back_profile);
        icBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ProfileActivity.this, Home.class);
                startActivity(i);
            }
        });

    }
}