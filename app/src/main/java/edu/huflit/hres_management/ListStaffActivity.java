package edu.huflit.hres_management;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;
import java.util.Objects;

import edu.huflit.hres_management.API.APIService;
import edu.huflit.hres_management.API.model.UserResponse;
import edu.huflit.hres_management.Fragment.BottomBarFragment;
import edu.huflit.hres_management.Model.Staff;
import edu.huflit.hres_management.Adapter.StaffAdapter;
import retrofit2.Call;
import retrofit2.Response;

public class ListStaffActivity extends AppCompatActivity {
    private RecyclerView rcvListStaff;
    private StaffAdapter staffAdapter;
    private Context mContext;

    private ImageView callStaff, messageStaff;
    private ProgressBar progressBar;
    private SharedPreferences sharedPreferences;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_staff);
        Fragment bottomBar = new BottomBarFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.bottom_bar, bottomBar).commit();

        progressBar =  findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        getListUser();
        callStaff = (ImageView) findViewById(R.id.call_staff);
        messageStaff = (ImageView) findViewById(R.id.delete_staff);
        rcvListStaff = findViewById(R.id.rcvListStaff);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ListStaffActivity.this,RecyclerView.VERTICAL,false);
        rcvListStaff.setLayoutManager(linearLayoutManager);
        mContext = this;

    }
    private void getListUser() {
        sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token","");
        if(Objects.equals("token", "")) return;
        Log.d("tokenLogin",token);
        APIService.apiService.getUserRestaurant("Bearer " + token).enqueue(new retrofit2.Callback<UserResponse>() {
            @Override
            public void onResponse(@NonNull Call<UserResponse> call, @NonNull Response<UserResponse> response) {
                if(response.code()!= 200) {
                    Toast.makeText(ListStaffActivity.this, "Có vấn đề xảy ra khi đồng bộ dữ liệu", Toast.LENGTH_SHORT).show();
                }else {
                    progressBar.setVisibility(View.GONE);
                    UserResponse userResponse = response.body();
                    List<Staff> listStaff = userResponse.getUser();
                    for(Staff staff : listStaff) {
                        StaffAdapter adapter = new StaffAdapter(mContext, listStaff);
                        rcvListStaff.setAdapter(adapter);
                    }
                }
            }
            @Override
            public void onFailure(@NonNull Call<UserResponse> call, @NonNull Throwable t) {
                Toast.makeText(ListStaffActivity.this, "Có vấn đề xảy ra khi đồng bộ dữ liệu", Toast.LENGTH_SHORT).show();
            }
        });
    }
}