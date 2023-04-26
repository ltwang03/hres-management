package edu.huflit.hres_management.Adapter;

import android.content.Context;
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

import edu.huflit.hres_management.Model.Maincourse;
import edu.huflit.hres_management.R;

public class MaincourseAdapter extends RecyclerView.Adapter<MaincourseAdapter.MaincourseViewHolder>{
    private Context mContext;
    private ArrayList<Maincourse> mListMaincourse;

    public MaincourseAdapter(ArrayList<Maincourse> list) {
        this.mContext = mContext; this.mListMaincourse= list;
        notifyDataSetChanged();
    }


    @NonNull
    @Override

    public MaincourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_list_food,parent,false);

        return new MaincourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MaincourseViewHolder holder, int position) {

        Maincourse maincourse = mListMaincourse.get(position);
        if(maincourse == null)
            return;
        holder.imgFoodReal.getLayoutParams().width = 150;
        holder.imgFoodReal.getLayoutParams().height = 170;
        Picasso.get().load(mListMaincourse.get(position).getResourceId()).resize(holder.imgFoodReal.getLayoutParams().width, holder.imgFoodReal.getLayoutParams().height).centerCrop().into(holder.imgFoodReal);

        //holder.imgFood.setText(mListMaincourse.get(position).getResourceId());
        holder.tvName.setText(mListMaincourse.get(position).getName());
        holder.tvDescribe.setText(mListMaincourse.get(position).getDescribe());
        holder.tvPrice.setText(mListMaincourse.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        if(mListMaincourse != null){
            return mListMaincourse.size();
        }
        return 0;
    }

    public class MaincourseViewHolder extends RecyclerView.ViewHolder{


        private TextView imgFood;
        private TextView tvName;
        private TextView tvDescribe;
        private TextView tvPrice;
        private ImageView imgFoodReal;

        public MaincourseViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFoodReal = itemView.findViewById(R.id.img_food_real);
            tvName = itemView.findViewById(R.id.name_food);
            tvDescribe = itemView.findViewById(R.id.describe_food);
            tvPrice = itemView.findViewById(R.id.price_food);
        }
    }

}
