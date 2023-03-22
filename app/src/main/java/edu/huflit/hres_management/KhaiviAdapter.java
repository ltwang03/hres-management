package edu.huflit.hres_management;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class KhaiviAdapter extends RecyclerView.Adapter<KhaiviAdapter.KhaiviViewHolder>{
    private Context mContext;
    private List<Khaivi> mListKhaivi;

    public KhaiviAdapter(Context mContext) {
        this.mContext = mContext;
    }
    public void setData(List<Khaivi> list){
        this.mListKhaivi= list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override

    public KhaiviViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_listfood,parent,false);

        return new KhaiviViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KhaiviViewHolder holder, int position) {

        Khaivi food1 = mListKhaivi.get(position);
        if(food1 == null)
            return;
        holder.imgFood.setImageResource(food1.getResourceId());
        holder.tvName.setText(food1.getName());
        holder.tvDescribe.setText(food1.getDescribe());
        holder.tvPrice.setText(String.valueOf(food1.getPrice()));
    }

    @Override
    public int getItemCount() {
        if(mListKhaivi != null){
            return mListKhaivi.size();
        }
        return 0;
    }

    public class KhaiviViewHolder extends RecyclerView.ViewHolder{


        private ImageView imgFood;
        private TextView tvName;
        private TextView tvDescribe;
        private TextView tvPrice;

        public KhaiviViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFood = itemView.findViewById(R.id.img_food);
            tvName = itemView.findViewById(R.id.name_food);
            tvDescribe = itemView.findViewById(R.id.describe_food);
            tvPrice = itemView.findViewById(R.id.price_food);
        }
    }

}
