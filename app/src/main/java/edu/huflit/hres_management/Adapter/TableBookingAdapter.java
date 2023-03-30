package edu.huflit.hres_management.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.huflit.hres_management.Model.TableBooking;
import edu.huflit.hres_management.R;

public class TableBookingAdapter extends RecyclerView.Adapter<TableBookingAdapter.TableBookingViewHolder> {
    private Context mContext;
    private List<TableBooking> mListTable;

    public TableBookingAdapter(Context mContext) {
        this.mContext = mContext;
    }
    public void setData(List<TableBooking> list){
        this.mListTable = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TableBookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull TableBookingViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class TableBookingViewHolder extends RecyclerView.ViewHolder{

        private TextView tvNumberTable;
        private TextView tvAmountCustomer;
        private TextView tvTimeCheckin;
        public TableBookingViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNumberTable = itemView.findViewById(R.id.number_table);
            tvAmountCustomer = itemView.findViewById(R.id.tv_amount_people1);
            tvTimeCheckin = itemView.findViewById(R.id.tv_time_booking);

        }
    }
}
