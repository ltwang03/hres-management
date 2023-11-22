package edu.huflit.hres_management.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.IllegalFormatCodePointException;
import java.util.List;

import edu.huflit.hres_management.ListCustomerActivity;
import edu.huflit.hres_management.Model.Customer;
import edu.huflit.hres_management.R;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.CustomerViewHolder> {
    private Context mContext;
    private Button mbtn_cancelCustomer, mbtn_updateCustomer;
    private List<Customer> mListCustomer;

    public CustomerAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<Customer> list){
        this.mListCustomer = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_list_customer,parent,false) ;
        return new CustomerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerViewHolder holder, int position) {
        Customer customer = mListCustomer.get(position);
        if(customer == null)
            return;
        holder.tvName.setText(customer.getName());
        holder.mbtn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });
    }
    public void openDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        View form = LayoutInflater.from(mContext).inflate(R.layout.dialog_edit_customer, null);
        builder.setView(form);
        AlertDialog dialog = builder.create();
        mbtn_cancelCustomer = form.findViewById(R.id.btn_cancelCustomer);
        mbtn_updateCustomer = form.findViewById(R.id.btn_updateCustomer);
        mbtn_cancelCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        mbtn_updateCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
