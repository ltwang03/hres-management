package edu.huflit.hres_management;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import edu.huflit.hres_management.API.APIService;
import edu.huflit.hres_management.API.model.AddCustomerRequest;
import edu.huflit.hres_management.API.model.AddCustomerResponse;
import edu.huflit.hres_management.API.model.GetCustomerResponse;
import edu.huflit.hres_management.API.model.UserResponse;
import edu.huflit.hres_management.Adapter.StaffAdapter;
import edu.huflit.hres_management.Fragment.BottomBarFragment;
import edu.huflit.hres_management.Model.Customer;
import edu.huflit.hres_management.Adapter.CustomerAdapter;
import edu.huflit.hres_management.Model.Staff;
import retrofit2.Call;
import retrofit2.Response;

public class ListCustomerActivity extends AppCompatActivity {
    private RecyclerView rcvListCustomer;
    private CustomerAdapter customerAdapter;
    FloatingActionButton madd;
    Button mbtncancel,mbtnAdd;
    private Context mContext;
    ImageView clearSearchCustomer;
    EditText searchCustomer;
    EditText etName, etPhone;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_customer);
        Fragment bottomBar = new BottomBarFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.bottom_bar, bottomBar).commit();

        clearSearchCustomer = (ImageView)findViewById(R.id.ic_clear_customer);
        searchCustomer = (EditText) findViewById(R.id.et_search_customer);
        madd = findViewById(R.id.add_customer);
        rcvListCustomer = findViewById(R.id.rcvListCustomer);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ListCustomerActivity.this,RecyclerView.VERTICAL,false);
        rcvListCustomer.setLayoutManager(linearLayoutManager);
        rcvListCustomer.setAdapter(customerAdapter);
        sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token","");
        getListCustomer();
        madd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ListCustomerActivity.this);
                View form = LayoutInflater.from(ListCustomerActivity.this).inflate(R.layout.dialog_form_customer, null);
                builder.setView(form);
                AlertDialog dialog = builder.create();
                mbtncancel = form.findViewById(R.id.btn_cancelCustomer);
                mbtnAdd = form.findViewById(R.id.btn_addCustomer);
                etName = form.findViewById(R.id.name_customer);
                etPhone = form.findViewById(R.id.phone_customer);

                mbtncancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                mbtnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name = etName.getText().toString();
                        String phone = etPhone.getText().toString();
                        AddCustomerRequest addCustomerRequest = new AddCustomerRequest(name,phone);
                        APIService.apiService.addCustomer("Bearer " + token, addCustomerRequest).enqueue(new retrofit2.Callback<AddCustomerResponse>() {
                            @Override
                            public void onResponse(retrofit2.Call<AddCustomerResponse> call, retrofit2.Response<AddCustomerResponse> response) {
                                if (response.code() != 200){
                                    Toast.makeText(ListCustomerActivity.this, "Có lỗi xảy ra!", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(ListCustomerActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                    getListCustomer();
                                }
                            }

                            @Override
                            public void onFailure(retrofit2.Call<AddCustomerResponse> call, Throwable t) {

                            }
                        });
                    }
                });
                dialog.show();
            }
        });
        clearSearchCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchCustomer.setText("");
            }
        });

    }
    public void getListCustomer(){
        sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token","");
        APIService.apiService.getCustomer("Bearer " + token).enqueue(new retrofit2.Callback<GetCustomerResponse>() {
            @Override
            public void onResponse(@NonNull Call<GetCustomerResponse> call, @NonNull Response<GetCustomerResponse> response) {
                if(response.code()!= 200) {
                    Toast.makeText(ListCustomerActivity.this, "Có vấn đề xảy ra khi đồng bộ dữ liệu", Toast.LENGTH_SHORT).show();
                }else {
                    GetCustomerResponse customerResponses = response.body();
                    List<Customer> customers = customerResponses.getCustomer();
                    for(Customer customer : customers) {
                        CustomerAdapter adapter = new CustomerAdapter(mContext, customers, sharedPreferences);
                        rcvListCustomer.setAdapter(adapter);
                    }
                }
            }
            @Override
            public void onFailure(@NonNull Call<GetCustomerResponse> call, @NonNull Throwable t) {
                Toast.makeText(ListCustomerActivity.this, "Có vấn đề xảy ra khi đồng bộ dữ liệu", Toast.LENGTH_SHORT).show();
            }
        });
    }
}