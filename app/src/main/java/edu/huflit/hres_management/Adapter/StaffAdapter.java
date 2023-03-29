package edu.huflit.hres_management.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.huflit.hres_management.ListStaffActivity;
import edu.huflit.hres_management.Model.Staff;
import edu.huflit.hres_management.R;

public class StaffAdapter extends RecyclerView.Adapter<StaffAdapter.StaffViewHolder> {
    private Context mContext;
    private List<Staff> mListStaff;

    public StaffAdapter(Context mContext, List<Staff> mListStaff) {
        this.mContext = mContext;
        this.mListStaff = mListStaff;
    }

    @NonNull
    @Override
    public StaffViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_list_staff,parent,false) ;
        return new StaffViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StaffViewHolder holder, int position) {
        Staff staff = mListStaff.get(position);
        if(staff == null)
            return;
        holder.tvName.setText(staff.getFullName());
        holder.ivPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumber = String.valueOf(staff.getPhoneNumber());
                Intent i = new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:" + "0" + phoneNumber));
                if(mContext != null) {
                    mContext.startActivity(i);
                }else {
                    Log.e("staffAdapter", "Context is null");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if(mListStaff != null){
            return mListStaff.size();
        }
        return 0;
    }
    public class StaffViewHolder extends RecyclerView.ViewHolder{
        private TextView tvName;
        private ImageView ivPhone;

        public StaffViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.name_staff);
            ivPhone = itemView.findViewById(R.id.call_staff);
        }
    }
}
