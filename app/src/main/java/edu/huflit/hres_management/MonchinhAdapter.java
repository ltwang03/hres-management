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

public class MonchinhAdapter extends RecyclerView.Adapter<MonchinhAdapter.MonchinhViewHolder>{
    private Context mContext;
    private List<Monchinh> mListMonchinh;

    public MonchinhAdapter(Context mContext) {
        this.mContext = mContext;
    }
    public void setData(List<Monchinh> list){
        this.mListMonchinh= list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override

    public MonchinhViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_listfood,parent,false);

        return new MonchinhViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MonchinhViewHolder holder, int position) {

        Monchinh monchinh = mListMonchinh.get(position);
        if(monchinh == null)
            return;
        holder.imgFood.setImageResource(monchinh.getResourceId());
        holder.tvName.setText(monchinh.getName());
        holder.tvDescribe.setText(monchinh.getDescribe());
        holder.tvPrice.setText(String.valueOf(monchinh.getPrice()));
    }

    @Override
    public int getItemCount() {
        if(mListMonchinh != null){
            return mListMonchinh.size();
        }
        return 0;
    }

    public class MonchinhViewHolder extends RecyclerView.ViewHolder{


        private ImageView imgFood;
        private TextView tvName;
        private TextView tvDescribe;
        private TextView tvPrice;

        public MonchinhViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFood = itemView.findViewById(R.id.img_food);
            tvName = itemView.findViewById(R.id.name_food);
            tvDescribe = itemView.findViewById(R.id.describe_food);
            tvPrice = itemView.findViewById(R.id.price_food);
        }
    }

}
