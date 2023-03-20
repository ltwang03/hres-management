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

public class TypeFoodAdapter extends RecyclerView.Adapter<TypeFoodAdapter.TypeNameViewHolder> {
    private Context mContext;
    private List<TypeFood> mListTypeFood;

    public TypeFoodAdapter(Context mContext) {
        this.mContext = mContext;
    }
    public void setData(List<TypeFood> list){
        this.mListTypeFood = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TypeNameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_typefood,parent,false);
        return new TypeNameViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TypeNameViewHolder holder, int position) {

        TypeFood typeFood = mListTypeFood.get(position);
        if(typeFood == null)
            return;
        holder.imgTypeFood.setImageResource(typeFood.getResourceId());
        holder.tvTypeName.setText(typeFood.getName_type());
    }

    @Override
    public int getItemCount() {
        if(mListTypeFood != null){
            return mListTypeFood.size();
        }
        return 0;
    }

    public class TypeNameViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgTypeFood;
        private TextView tvTypeName;
        public TypeNameViewHolder(@NonNull View itemView) {
            super(itemView);

            imgTypeFood = itemView.findViewById(R.id.img_typefood);
            tvTypeName = itemView.findViewById(R.id.name_type);
        }
    }
}
