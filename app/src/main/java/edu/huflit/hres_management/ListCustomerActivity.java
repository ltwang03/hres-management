package edu.huflit.hres_management;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import edu.huflit.hres_management.Model.Customer;
import edu.huflit.hres_management.Adapter.CustomerAdapter;

public class ListCustomerActivity extends AppCompatActivity {
    private RecyclerView rcvListCustomer;
    private CustomerAdapter customerAdapter;
    ImageView clearSearchCustomer;
    EditText searchCustomer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_customer);
        clearSearchCustomer = (ImageView)findViewById(R.id.clear_search_customer);
        searchCustomer = (EditText) findViewById(R.id.search_customer);


        rcvListCustomer = findViewById(R.id.rcvListCustomer);
        customerAdapter = new CustomerAdapter(ListCustomerActivity.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ListCustomerActivity.this,RecyclerView.VERTICAL,false);
        rcvListCustomer.setLayoutManager(linearLayoutManager);
        customerAdapter.setData(getListCustomer());
        rcvListCustomer.setAdapter(customerAdapter);
        clearSearchCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchCustomer.setText("");
            }
        });
    }
    public List<Customer> getListCustomer(){
        List<Customer> list = new ArrayList<>();
        list.add(new Customer("Khách hàng 123"));
        list.add(new Customer("Khách hàng 123"));
        list.add(new Customer("Khách hàng 123"));
        list.add(new Customer("Khách hàng 123"));

        return list;
    }
}