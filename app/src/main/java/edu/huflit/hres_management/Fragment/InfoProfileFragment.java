package edu.huflit.hres_management.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

import edu.huflit.hres_management.API.APIService;
import edu.huflit.hres_management.API.model.ProfileResponse;
import edu.huflit.hres_management.Database.DBHelper;
import edu.huflit.hres_management.Home;
import edu.huflit.hres_management.IntroActivity;
import edu.huflit.hres_management.LoginActivity;
import edu.huflit.hres_management.ProfileActivity;
import edu.huflit.hres_management.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class InfoProfileFragment extends Fragment {
    Button mbtnEdit, mbtnSignOut;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    TextView resID, fullName, userName, phoneNumber;
    DBHelper dbHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_info_profile, container, false);
        mbtnEdit = view.findViewById(R.id.btn_editFragment);
        mbtnSignOut = view.findViewById(R.id.btn_sign_out);
        resID = (TextView) view.findViewById(R.id.tv_idRes);
        fullName = (TextView) view.findViewById(R.id.tv_fullName);
        userName = (TextView) view.findViewById(R.id.tv_userName);
        phoneNumber = (TextView) view.findViewById(R.id.tv_phone);
        GetProfile();
        mbtnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //mở ra trang edit profile
                Fragment editProfileFragment = new EditProfileFragment();
                FragmentTransaction fragmentEditProfile = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentEditProfile.replace(R.id.layout_profile, editProfileFragment).addToBackStack(null).commit();

            }
        });
        mbtnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("token", "");
                editor.apply();
                Intent i = new Intent(getContext(), IntroActivity.class);
                startActivity(i);
            }
        });
        return view;
    }
    // fetch API GET profile
    public void GetProfile() {
        sharedPreferences = getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        String token = sharedPreferences.getString("token", "");
        if (Objects.equals(token, "")) return;
        APIService.apiService.getProfile("Bearer " + token).enqueue(new retrofit2.Callback<ProfileResponse>() {
            @Override
            public void onResponse(@NonNull Call<ProfileResponse> call, @NonNull Response<ProfileResponse> response) {
                if (response.code() != 200)
                    Toast.makeText(getContext(), "có lỗi xảy ra!", Toast.LENGTH_SHORT).show();
                ProfileResponse profileResponse = response.body();
                RenderProfile(profileResponse);
            }


            @Override
            public void onFailure(@NonNull Call<ProfileResponse> call, @NonNull Throwable t) {
                Toast.makeText(getContext(), "Có lỗi xảy ra ở server!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void RenderProfile(ProfileResponse profileResponse) {
//        resID.setText(profileResponse.getRestaurantID());
//        fullName.setText(profileResponse.getFullName());
//        userName.setText(profileResponse.getUserName());
//        phoneNumber.setText("0" + String.valueOf(profileResponse.getPhoneNumber()));
//        editor.putString("fullName", profileResponse.getFullName());
//        editor.putString("phoneNumber", "0" + String.valueOf(profileResponse.getPhoneNumber()));
//        editor.apply();
    }
}