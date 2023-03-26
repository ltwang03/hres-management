package edu.huflit.hres_management.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.huflit.hres_management.R;

public class StaffAdapter extends RecyclerView.Adapter<StaffAdapter.StaffViewHolder> {
    private Context mContext;
    private List<Staff> mListStaff;

    public StaffAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<Staff> list){
        this.mListStaff = list;
        notifyDataSetChanged();
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
        holder.tvName.setText(staff.getName());
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

        public StaffViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.name_staff);
        }
    }
}
