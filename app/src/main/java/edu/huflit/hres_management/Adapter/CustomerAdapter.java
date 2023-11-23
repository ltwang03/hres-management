package edu.huflit.hres_management.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.IllegalFormatCodePointException;
import java.util.List;

import edu.huflit.hres_management.API.APIService;
import edu.huflit.hres_management.API.model.DeleteCustomerRequest;
import edu.huflit.hres_management.API.model.DeleteCustomerResponse;
import edu.huflit.hres_management.API.model.EditCustomerRequest;
import edu.huflit.hres_management.API.model.EditCustomerResponse;
import edu.huflit.hres_management.ListCustomerActivity;
import edu.huflit.hres_management.Model.Customer;
import edu.huflit.hres_management.R;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.CustomerViewHolder> {
    private Context mContext;
    private Button mbtn_cancelCustomer, mbtn_updateCustomer;
    private EditText etName, etPhone;
    private List<Customer> mListCustomer;
    private SharedPreferences sharedPreferences;



    public CustomerAdapter(Context mContext, List<Customer> mListCustomer, SharedPreferences sharedPreferences) {
        this.mContext = mContext;
        this.mListCustomer = mListCustomer;
        this.sharedPreferences = sharedPreferences;
    }

    @NonNull
    @Override
    public CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_list_customer,parent,false) ;
        return new CustomerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerViewHolder holder, int position) {
        String token = sharedPreferences.getString("token", "");
        Customer customer = mListCustomer.get(position);
        if(customer == null)
            return;
        holder.tvName.setText(customer.getName());
        holder.mbtn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog(view,customer.get_id(),customer.getName(), customer.getPhone(), position);
            }
        });
        holder.mbtn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                APIService.apiService.deleteCustomer("Bearer " +token, String.valueOf(customer.get_id())).enqueue(new retrofit2.Callback<DeleteCustomerResponse>() {
                    @Override
                    public void onResponse(retrofit2.Call<DeleteCustomerResponse> call, retrofit2.Response<DeleteCustomerResponse> response) {
                        if(response.code() != 200){
                            Toast.makeText(view.getContext(), "Delete failed", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(view.getContext(), "Delete success", Toast.LENGTH_SHORT).show();
                            mListCustomer.remove(position);
                            notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(retrofit2.Call<DeleteCustomerResponse> call, Throwable t) {

                    }
                });
            }
        });
    }
    public void openDialog(View view,String id, String name, String phone, int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        View form = LayoutInflater.from(view.getContext()).inflate(R.layout.dialog_edit_customer, null);
        builder.setView(form);
        AlertDialog dialog = builder.create();

        mbtn_cancelCustomer = form.findViewById(R.id.btn_cancelCustomer);
        mbtn_updateCustomer = form.findViewById(R.id.btn_updateCustomer);
        EditText etName = form.findViewById(R.id.edit_name_customer);
        EditText etPhone = form.findViewById(R.id.edit_phone_customer);
        etName.setText(name);
        etPhone.setText(phone);
        mbtn_cancelCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        mbtn_updateCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String token = sharedPreferences.getString("token", "");
                String name = etName.getText().toString();
                String phone = etPhone.getText().toString();
                EditCustomerRequest editCustomerRequest = new EditCustomerRequest(name, phone);
                editCustomerRequest.setName(name);
                editCustomerRequest.setPhone(phone);
                APIService.apiService.editCustomer("Bearer " +token, id, editCustomerRequest).enqueue(new retrofit2.Callback<EditCustomerResponse>() {
                    @Override
                    public void onResponse(retrofit2.Call<EditCustomerResponse> call, retrofit2.Response<EditCustomerResponse> response) {
                        if(response.code() != 200){
                            Toast.makeText(view.getContext(), "Update failed", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(view.getContext(), "Update success", Toast.LENGTH_SHORT).show();
                            mListCustomer.get(position).setName(name);
                            mListCustomer.get(position).setPhone(phone);
                            notifyDataSetChanged();
                            dialog.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(retrofit2.Call<EditCustomerResponse> call, Throwable t) {

                    }
                });

            }
        });
        dialog.show();

    }

    @Override
    public int getItemCount() {
        if(mListCustomer != null){
            return mListCustomer.size();
        }
        return 0;
    }
    public class CustomerViewHolder extends RecyclerView.ViewHolder{
        private TextView tvName;
        private ImageView mbtn_edit, mbtn_delete;



        public CustomerViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.name_customer);
            mbtn_delete = itemView.findViewById(R.id.delete_customer);
            mbtn_edit = itemView.findViewById(R.id.ic_edit_customer);
            mbtn_cancelCustomer = itemView.findViewById(R.id.btn_cancelCustomer);
            mbtn_updateCustomer = itemView.findViewById(R.id.btn_updateCustomer);
        }
    }
}
