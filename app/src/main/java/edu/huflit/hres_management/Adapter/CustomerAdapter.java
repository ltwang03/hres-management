package edu.huflit.hres_management.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.huflit.hres_management.Model.Customer;
import edu.huflit.hres_management.R;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.CustomerViewHolder> {
    private Context mContext;
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

        public CustomerViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.name_customer);
        }
    }
}
