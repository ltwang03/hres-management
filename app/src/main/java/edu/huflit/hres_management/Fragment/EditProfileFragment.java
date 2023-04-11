package edu.huflit.hres_management.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import edu.huflit.hres_management.R;


public class EditProfileFragment extends Fragment {
    Button mbtnCancel,mbtnSave;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);
        mbtnCancel = view.findViewById(R.id.btn_cancel_editProfile);
        mbtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //quay về fragment info
                Fragment infoProfile = new InfoProfileFragment();

                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.layout_profile, infoProfile).addToBackStack(null).commit();

            }
        });
        mbtnSave = view.findViewById(R.id.btn_save_editProfile);
        mbtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return view;
    }
}