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
import android.widget.EditText;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.Objects;

import edu.huflit.hres_management.API.APIService;
import edu.huflit.hres_management.API.model.EditProfileRequest;
import edu.huflit.hres_management.API.model.EditProfileResponse;
import edu.huflit.hres_management.R;
import retrofit2.Call;
import retrofit2.Response;

public class EditProfileFragment extends Fragment {
    Button mbtnCancel, mbtnSave;
    EditText fullName, phoneNumber;
    SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        sharedPreferences = getContext().getSharedPreferences("data", Context.MODE_PRIVATE);
        View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);
        mbtnSave = view.findViewById(R.id.btn_save_editProfile);
        mbtnCancel = view.findViewById(R.id.btn_cancel_editProfile);
        fullName = (EditText) view.findViewById(R.id.et_fullName);
        phoneNumber = (EditText) view.findViewById(R.id.et_phone);
        setData();
        mbtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //quay về fragment info
                Fragment infoProfile = new InfoProfileFragment();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.layout_profile, infoProfile).addToBackStack(null).commit();
            }
        });
        mbtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateInfo();
                Fragment infoProfile = new InfoProfileFragment();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.layout_profile, infoProfile).addToBackStack(null).commit();
            }
        });
        return view;
    }

    public void UpdateInfo() {
        String getFullName = String.valueOf(fullName.getText());
        String getPhoneNumber = String.valueOf(phoneNumber.getText());
        EditProfileRequest editProfileRequest = new EditProfileRequest(getFullName, Integer.parseInt(getPhoneNumber));
        String token = sharedPreferences.getString("token", "");
        if(Objects.equals(token,"")) return;
        APIService.apiService.editProfile("Bearer " + token, editProfileRequest).enqueue(new retrofit2.Callback<EditProfileResponse>() {
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                if(response.code() != 200) return;
                EditProfileResponse editProfileResponse = (EditProfileResponse) response.body();
                Toast.makeText(getContext(), editProfileResponse.getStatus(), Toast.LENGTH_SHORT).show();
                Log.d("response", editProfileResponse.getStatus());
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull Throwable t) {
                Toast.makeText(getContext(), "Có lỗi xảy ra với server!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setData() {
        String getFullName = sharedPreferences.getString("fullName", "");
        if (Objects.equals(getFullName, "")) return;
        String getPhoneNumber = sharedPreferences.getString("phoneNumber", "");
        if (Objects.equals(getPhoneNumber, "")) return;
        fullName.setText(getFullName);
        phoneNumber.setText(getPhoneNumber);
    }
}