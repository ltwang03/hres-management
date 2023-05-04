//package edu.huflit.hres_management.Adapter;
//
//import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;
//
//import android.app.Dialog;
//import android.content.Context;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.RelativeLayout;
//import android.widget.Spinner;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.squareup.picasso.Picasso;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import edu.huflit.hres_management.Database.DBHelper;
//import edu.huflit.hres_management.Model.Appetizer;
//import edu.huflit.hres_management.Model.Test;
//import edu.huflit.hres_management.R;
//
//public class TestAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
//
//    private static final int VIEW_TYPE_DEFAULT = 1;
//    private static final int VIEW_TYPE_ALT = 2;
//    Context mContext;
//    DBHelper db;
//    Dialog dialog;
//    private static ArrayList<Appetizer> mListAppetizer;
//
//    public TestAdapter(Context mContext, ArrayList<Test> tests) {
//        this.mContext = mContext;
//        this.mListAppetizer = mListAppetizer;
//    }
//
//    @Override
//    public int getItemViewType(int position) {
//        // Chọn layout hiển thị dựa trên vị trí của mục trong danh sách
//        if (position % 2 == 0) {
//            return VIEW_TYPE_DEFAULT;
//        } else {
//            return VIEW_TYPE_ALT;
//        }
//    }
//
//    @NonNull
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
//
//        // Tạo ViewHolder dựa trên viewType
//        if (viewType == VIEW_TYPE_DEFAULT) {
//            View itemView = inflater.inflate(R.layout.layout_item_list_food, parent, false);
//            return new DefaultViewHolder(itemView);
//        } else {
//            View itemView = inflater.inflate(R.layout.layout_food_order, parent, false);
//            return new AltViewHolder(itemView);
//        }
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
//        // Gán dữ liệu vào ViewHolder
//        Test test = mTests.get(position);
//        if (holder instanceof DefaultViewHolder) {
//            ((DefaultViewHolder) holder).bind(position);
//        } else if (holder instanceof AltViewHolder) {
//            ((AltViewHolder) holder).bind(position);
//        }
//    }
//
//    @Override
//    public int getItemCount() {
//        return mTests.size();
//    }
//
//    // ViewHolder cho layout mặc định
//    private static class DefaultViewHolder extends RecyclerView.ViewHolder {
//
//        private ImageView imgFoodReal , icUpdateFood;
//        private TextView imgFood;
//        private TextView tvName;
//        private TextView tvDescribe;
//        private TextView tvPrice;
//        private RelativeLayout rltFoodItem;
//        private Spinner spnUpdateCate;
//
//        public DefaultViewHolder(View itemView) {
//            super(itemView);
//            imgFoodReal =itemView.findViewById(R.id.img_food_real);
//            tvName = itemView.findViewById(R.id.name_food);
//            tvDescribe = itemView.findViewById(R.id.describe_food);
//            tvPrice = itemView.findViewById(R.id.price_food);
//            rltFoodItem =itemView.findViewById(R.id.rlvFoodItem);
//            icUpdateFood= itemView.findViewById(R.id.ic_edit_food);
//            spnUpdateCate = itemView.findViewById(R.id.spn_update_product_category);
//        }
//
//        public void bind( int position) {
//            Appetizer appetizer = mListAppetizer.get(position);
//            if(appetizer == null)
//                return;
////        holder.imgFood.setImageResource(food1.getResourceId());
//            imgFoodReal.getLayoutParams().width = 150;
//            imgFoodReal.getLayoutParams().height = 170;
//
//            Picasso.get().load(mListAppetizer.get(position).getResourceId()).resize(holder.imgFoodReal.getLayoutParams().width, holder.imgFoodReal.getLayoutParams().height).centerCrop().into(holder.imgFoodReal);
//            String url = mListAppetizer.get(position).getResourceId();
//            tvName.setText(mListAppetizer.get(position).getName());
//            String name = mListAppetizer.get(position).getName();
//            tvDescribe.setText(mListAppetizer.get(position).getDescribe());
//            String descripe = mListAppetizer.get(position).getDescribe();
//            tvPrice.setText(mListAppetizer.get(position).getPrice());
//            String price = mListAppetizer.get(position).getPrice();
//            String category = mListAppetizer.get(position).getCategory();
//            Integer product_id = mListAppetizer.get(position).getId();
//            icUpdateFood.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    dialogUpdate(product_id,url,name,price,category,descripe);
//                    Log.e(TAG, "onClick: " + "tesst" );
//                }
//            });
//            holder.rltFoodItem.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View view) {
//                    dialogDelete(product_id);
//                    return true;
//                }
//            });
//        }
//    }
//
//    // ViewHolder cho layout thay thế
//    private static class AltViewHolder extends RecyclerView.ViewHolder {
//
//        private ImageView mTestImageView;
//        private TextView mTitleTextView;
//        private TextView mDescriptionTextView;
//        private TextView mExtraTextView;
//
//        public AltViewHolder(View itemView) {
//            super(itemView);
//            mTestImageView = itemView.findViewById(R.id.image_view_test);
//            mTitleTextView = itemView.findViewById(R.id.text_view_title);
//            mDescriptionTextView = itemView.findViewById(R.id.text_view_description);
//            mExtraTextView = itemView.findViewById(R.id.text_view_extra);
//        }
//
//        public void bind(int position) {
//            mTestImageView.setImageResource(test.getImageResId());
//            mTitleTextView.setText(test.getTitle());
//            mDescriptionTextView.setText(test.getDescription    ());
//            mExtraTextView.setText(test.getExtraText());
//        }
//    }
//}
