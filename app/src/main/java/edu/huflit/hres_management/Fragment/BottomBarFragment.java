package edu.huflit.hres_management.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import edu.huflit.hres_management.Home;
import edu.huflit.hres_management.ListBillActivity;
import edu.huflit.hres_management.ListTypeFoodActivity;
import edu.huflit.hres_management.OrderTableActivity;
import edu.huflit.hres_management.ProfileActivity;
import edu.huflit.hres_management.R;


public class BottomBarFragment extends Fragment {
    ImageView imgHome,imgOrder,imgListFood,imgProfile;


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom_bar, container, false);

        imgHome=view.findViewById(R.id.ic_home);
        imgHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), Home.class);
                startActivity(i);
            }
        });
        imgOrder=view.findViewById(R.id.ic_order);
        imgOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), ListBillActivity.class);
                startActivity(i);
            }
        });
        imgListFood=view.findViewById(R.id.ic_menu_food);
        imgListFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), ListTypeFoodActivity.class);
                startActivity(i);
            }
        });
        imgProfile=view.findViewById(R.id.ic_profile);
        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), ProfileActivity.class);
                startActivity(i);
            }
        });
        return view;
    }
}