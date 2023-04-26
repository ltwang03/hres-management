package edu.huflit.hres_management.Adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import edu.huflit.hres_management.Model.Dessert;
import edu.huflit.hres_management.R;

public class  DessertAdapter extends RecyclerView.Adapter<DessertAdapter.DessertViewHolder>{
    private Context mContext;
    private ArrayList<Dessert> mListDessert;

    public DessertAdapter(ArrayList<Dessert> list) {
        this.mContext = mContext; this.mListDessert= list;
        notifyDataSetChanged();
    }


    @NonNull
    @Override

    public DessertViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_list_food,parent,false);

        return new DessertViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DessertViewHolder holder, int position) {

        Dessert dessert = mListDessert.get(position);
        if(dessert == null)
            return;
        holder.imgFoodReal.getLayoutParams().width = 150;
        holder.imgFoodReal.getLayoutParams().height = 170;
        Picasso.get().load(mListDessert.get(position).getResourceId()).resize(holder.imgFoodReal.getLayoutParams().width, holder.imgFoodReal.getLayoutParams().height).centerCrop().into(holder.imgFoodReal);

        //holder.imgFood.setText(mListDessert.get(position).getResourceId());
        holder.tvName.setText(mListDessert.get(position).getName());
        holder.tvDescribe.setText(mListDessert.get(position).getDescribe());
        holder.tvPrice.setText(mListDessert.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        if(mListDessert != null){
            return mListDessert.size();
        }
        return 0;
    }

    public class DessertViewHolder extends RecyclerView.ViewHolder{


        private TextView imgFood;
        private TextView tvName;
        private TextView tvDescribe;
        private TextView tvPrice;
        private ImageView imgFoodReal;

        public DessertViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFoodReal =itemView.findViewById(R.id.img_food_real);
            tvName = itemView.findViewById(R.id.name_food);
            tvDescribe = itemView.findViewById(R.id.describe_food);
            tvPrice = itemView.findViewById(R.id.price_food);
        }
    }

}
