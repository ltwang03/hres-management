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

public class MaincourseAdapter extends RecyclerView.Adapter<MaincourseAdapter.MaincourseViewHolder>{
    private Context mContext;
    private List<Maincourse> mListMaincourse;

    public MaincourseAdapter(Context mContext) {
        this.mContext = mContext;
    }
    public void setData(List<Maincourse> list){
        this.mListMaincourse= list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override

    public MaincourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_listfood,parent,false);

        return new MaincourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MaincourseViewHolder holder, int position) {

        Maincourse maincourse = mListMaincourse.get(position);
        if(maincourse == null)
            return;
        holder.imgFood.setImageResource(maincourse.getResourceId());
        holder.tvName.setText(maincourse.getName());
        holder.tvDescribe.setText(maincourse.getDescribe());
        holder.tvPrice.setText(String.valueOf(maincourse.getPrice()));
    }

    @Override
    public int getItemCount() {
        if(mListMaincourse != null){
            return mListMaincourse.size();
        }
        return 0;
    }

    public class MaincourseViewHolder extends RecyclerView.ViewHolder{


        private ImageView imgFood;
        private TextView tvName;
        private TextView tvDescribe;
        private TextView tvPrice;

        public MaincourseViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFood = itemView.findViewById(R.id.img_food);
            tvName = itemView.findViewById(R.id.name_food);
            tvDescribe = itemView.findViewById(R.id.describe_food);
            tvPrice = itemView.findViewById(R.id.price_food);
        }
    }

}
