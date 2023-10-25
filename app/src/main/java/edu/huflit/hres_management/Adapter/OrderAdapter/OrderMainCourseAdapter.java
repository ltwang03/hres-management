package edu.huflit.hres_management.Adapter.OrderAdapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import edu.huflit.hres_management.Database.DBHelper;
import edu.huflit.hres_management.Model.Appetizer;
import edu.huflit.hres_management.Model.Maincourse;
import edu.huflit.hres_management.R;

public class OrderMainCourseAdapter extends RecyclerView.Adapter<OrderMainCourseAdapter.OrderMainCourseViewHolder> {
    Context mcontext;
    private ArrayList<Maincourse> mListMaincourse;
    SharedPreferences sharedPref;
    DBHelper db;

    public OrderMainCourseAdapter(Context mcontext, ArrayList<Maincourse> mListMaincourse) {
        this.mcontext = mcontext;
        this.mListMaincourse = mListMaincourse;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mListMaincourse.size();
    }

    @NonNull
    @Override
    public OrderMainCourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_food_order, parent,false);

        return new OrderMainCourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderMainCourseViewHolder holder, int position) {
        String getName = mListMaincourse.get(position).getName();
        String getPrice = mListMaincourse.get(position).getPrice();
        int intPrice = Integer.parseInt(getPrice);
        String src = mListMaincourse.get(position).getResourceId();
        holder.imgv_pic_order.getLayoutParams().width = 150;
        holder.imgv_pic_order.getLayoutParams().height = 170;
        Picasso.get().load(mListMaincourse.get(position).getResourceId()).resize(holder.imgv_pic_order.getLayoutParams().width, holder.imgv_pic_order.getLayoutParams().height).centerCrop().into(holder.imgv_pic_order);

        db = new DBHelper(mcontext);
        sharedPref = mcontext.getSharedPreferences( "my_prefs", Context.MODE_PRIVATE);
        String tableNumber = sharedPref.getString("table_number", "");

        if(db.isDataExists(tableNumber,getName) == false) {
            boolean check = db.insertOrderingData(tableNumber,src,getName,0,intPrice);
            if(check) {
                Toast.makeText(mcontext, " sync Succesfull", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(mcontext, "sync Failed", Toast.LENGTH_SHORT).show();
            }
        }
        holder.tvNameFood.setText(getName);
        holder.tvpriceFood.setText(getPrice);
        holder.edtNumberfood.setText("0");
        holder.btnIncrea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor1 = db.getOrdering();
                while(cursor1.moveToNext()) {
                    if(cursor1.getString(1).equals(tableNumber) && cursor1.getString(3).equals(getName)){
                        String amount = holder.edtNumberfood.getText().toString();
                        int intAmount = Integer.parseInt(amount) +1;
                        String strAmount = String.valueOf(intAmount);
                        boolean check = db.updateOrder(tableNumber,src,getName,cursor1.getInt(4)+1,intPrice);
                        if(check) {
                            Toast.makeText(mcontext, "Succesfull", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(mcontext, "Failed", Toast.LENGTH_SHORT).show();
                        }
                        holder.edtNumberfood.setText(strAmount);
                    }
                }
                cursor1.close();

            }
        });
        holder.btnDecrea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor2 = db.getOrdering();
                while(cursor2.moveToNext()) {
                    if(cursor2.getString(1).equals(tableNumber) && cursor2.getString(3).equals(getName)){
                        String amount = holder.edtNumberfood.getText().toString();
                        int intAmount = Integer.parseInt(amount);
                        int updatedAmount = intAmount-1;
                        if(updatedAmount < 0) {
                            holder.edtNumberfood.setText("0");
                            Toast.makeText(mcontext, "giá trị không thể nhỏ hơn 0", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            String strAmount = String.valueOf(updatedAmount);
                            boolean check = db.updateOrder(tableNumber,src,getName,cursor2.getInt(4) -1   ,intPrice);
                            if(check) {
                                Toast.makeText(mcontext, "Succesfull", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(mcontext, "Failed", Toast.LENGTH_SHORT).show();
                            }
                            holder.edtNumberfood.setText(strAmount);
                        }}}
                cursor2.close();
            }

        });
    }

    public class OrderMainCourseViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout lnInfoOrder;
        private TextView tvNameFood, tvpriceFood;
        private EditText edtNumberfood;
        private Button btnIncrea, btnDecrea;
        private ImageView imgv_pic_order;
        public OrderMainCourseViewHolder(@NonNull View itemView) {
            super(itemView);
            lnInfoOrder = itemView.findViewById(R.id.ln_info_order);
            tvpriceFood = itemView.findViewById(R.id.tv_priceFood);
            tvNameFood = itemView.findViewById(R.id.tv_nameFood);
            edtNumberfood  = itemView.findViewById(R.id.edt_amountFood);
            btnDecrea = itemView.findViewById(R.id.btnDecrease);
            btnIncrea = itemView.findViewById(R.id.btnIncrease);
            imgv_pic_order = itemView.findViewById(R.id.imgv_pic_order);
        }
    }
}
