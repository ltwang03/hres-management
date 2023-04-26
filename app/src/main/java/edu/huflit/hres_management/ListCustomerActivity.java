package edu.huflit.hres_management;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import edu.huflit.hres_management.Fragment.BottomBarFragment;
import edu.huflit.hres_management.Model.Customer;
import edu.huflit.hres_management.Adapter.CustomerAdapter;

public class ListCustomerActivity extends AppCompatActivity {
    private RecyclerView rcvListCustomer;
    private CustomerAdapter customerAdapter;
    FloatingActionButton madd;
    Button mbtncancel,mbtnAdd;
    ImageView clearSearchCustomer;
    EditText searchCustomer;

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

        customerAdapter = new CustomerAdapter(ListCustomerActivity.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ListCustomerActivity.this,RecyclerView.VERTICAL,false);
        rcvListCustomer.setLayoutManager(linearLayoutManager);
        customerAdapter.setData(getListCustomer());
        rcvListCustomer.setAdapter(customerAdapter);
        madd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ListCustomerActivity.this);
                View form = LayoutInflater.from(ListCustomerActivity.this).inflate(R.layout.dialog_form_customer, null);
                builder.setView(form);
                AlertDialog dialog = builder.create();
                mbtncancel = form.findViewById(R.id.btn_cancelCustomer);
                mbtnAdd = form.findViewById(R.id.btn_addCustomer);
                mbtncancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                mbtnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

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
    public List<Customer> getListCustomer(){
        List<Customer> list = new ArrayList<>();
        list.add(0,new Customer("Khách hàng 123"));
        list.add(1,new Customer("Khách hàng 123"));
        list.add(2,new Customer("Khách hàng 123"));
        list.add(3,new Customer("Khách hàng 123"));

        return list;
    }
}