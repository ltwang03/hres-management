package edu.huflit.hres_management.Food_Staff;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.huflit.hres_management.R;

public class DessertAdapter extends RecyclerView.Adapter<DessertAdapter.DessertViewHolder>{
    private Context mContext;
    private List<Dessert> mListDessert;

    public DessertAdapter(Context mContext) {
        this.mContext = mContext;
    }
    public void setData(List<Dessert> list){
        this.mListDessert= list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override

    public DessertViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_listfood,parent,false);

        return new DessertViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DessertViewHolder holder, int position) {

        Dessert dessert = mListDessert.get(position);
        if(dessert == null)
            return;
        holder.imgFood.setImageResource(dessert.getResourceId());
        holder.tvName.setText(dessert.getName());
        holder.tvDescribe.setText(dessert.getDescribe());
        holder.tvPrice.setText(String.valueOf(dessert.getPrice()));
    }

    @Override
    public int getItemCount() {
        if(mListDessert != null){
            return mListDessert.size();
        }
        return 0;
    }

    public class DessertViewHolder extends RecyclerView.ViewHolder{


        private ImageView imgFood;
        private TextView tvName;
        private TextView tvDescribe;
        private TextView tvPrice;

        public DessertViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFood = itemView.findViewById(R.id.img_food);
            tvName = itemView.findViewById(R.id.name_food);
            tvDescribe = itemView.findViewById(R.id.describe_food);
            tvPrice = itemView.findViewById(R.id.price_food);
        }
    }

}
