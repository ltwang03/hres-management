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

import edu.huflit.hres_management.Model.Drinks;
import edu.huflit.hres_management.R;

public class DrinksAdapter extends RecyclerView.Adapter<DrinksAdapter.DrinksViewHolder>{
    private Context mContext;
    private ArrayList<Drinks> mListDrinks;

    public DrinksAdapter(ArrayList<Drinks> list) {
        this.mContext = mContext; this.mListDrinks= list;
        notifyDataSetChanged();
    }


    @NonNull
    @Override

    public DrinksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_list_food,parent,false);

        return new DrinksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DrinksViewHolder holder, int position) {

        Drinks drinks = mListDrinks.get(position);
        if(drinks == null)
            return;
        holder.imgFoodReal.getLayoutParams().width = 150;
        holder.imgFoodReal.getLayoutParams().height = 170;
        Picasso.get().load(mListDrinks.get(position).getResourceId()).resize(holder.imgFoodReal.getLayoutParams().width, holder.imgFoodReal.getLayoutParams().height).centerCrop().into(holder.imgFoodReal);

       // holder.imgFood.setText(mListDrinks.get(position).getResourceId());
        holder.tvName.setText(mListDrinks.get(position).getName());
        holder.tvDescribe.setText(mListDrinks.get(position).getDescribe());
        holder.tvPrice.setText(mListDrinks.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        if(mListDrinks != null){
            return mListDrinks.size();
        }
        return 0;
    }

    public class DrinksViewHolder extends RecyclerView.ViewHolder{



        private TextView tvName;
        private TextView tvDescribe;
        private TextView tvPrice;
        private ImageView imgFoodReal;

        public DrinksViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFoodReal = itemView.findViewById(R.id.img_food_real);
            tvName = itemView.findViewById(R.id.name_food);
            tvDescribe = itemView.findViewById(R.id.describe_food);
            tvPrice = itemView.findViewById(R.id.price_food);
        }
    }

}
