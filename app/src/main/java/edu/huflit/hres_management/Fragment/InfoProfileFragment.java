package edu.huflit.hres_management.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import edu.huflit.hres_management.R;


public class InfoProfileFragment extends Fragment {
    Button mbtnEdit;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_info_profile, container, false);
        mbtnEdit = view.findViewById(R.id.btn_editFragment);
        mbtnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //mở ra trang edit profile
                Fragment editProfileFragment = new EditProfileFragment();
                FragmentTransaction fragmentEditProfile = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentEditProfile.replace(R.id.layout_profile,editProfileFragment).addToBackStack(null).commit();

            }
        });
        return view;
    }
}