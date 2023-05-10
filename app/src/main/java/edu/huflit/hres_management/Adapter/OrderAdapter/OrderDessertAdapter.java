package edu.huflit.hres_management.Adapter.OrderAdapter;

import android.content.Context;
import android.content.SharedPreferences;
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
import edu.huflit.hres_management.Model.Dessert;
import edu.huflit.hres_management.Model.Maincourse;
import edu.huflit.hres_management.R;

public class OrderDessertAdapter extends RecyclerView.Adapter<OrderDessertAdapter.OrderDessertViewHolder> {
    Context mcontext;
    private ArrayList<Dessert> mListDessert;
    SharedPreferences sharedPref;
    DBHelper db;

    public OrderDessertAdapter(Context mcontext, ArrayList<Dessert> mListDessert) {
        this.mcontext = mcontext;
        this.mListDessert = mListDessert;
        notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        return mListDessert.size();
    }

    @NonNull
    @Override
    public OrderDessertViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_food_order, parent,false);

        return new OrderDessertViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderDessertViewHolder holder, int position) {
        String getName = mListDessert.get(position).getName();
        String getPrice = mListDessert.get(position).getPrice();
        int intPrice = Integer.parseInt(getPrice);
        String src = mListDessert.get(position).getResourceId();
        holder.imgv_pic_order.getLayoutParams().width = 150;
        holder.imgv_pic_order.getLayoutParams().height = 170;
        Picasso.get().load(mListDessert.get(position).getResourceId()).resize(holder.imgv_pic_order.getLayoutParams().width, holder.imgv_pic_order.getLayoutParams().height).centerCrop().into(holder.imgv_pic_order);

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
                String amount = holder.edtNumberfood.getText().toString();
                int intAmount = Integer.parseInt(amount) +1;
                String strAmount = String.valueOf(intAmount);
                boolean check = db.updateOrder(tableNumber,src,getName,intAmount,intPrice);
                if(check) {
                    Toast.makeText(mcontext, "succes in", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(mcontext, "failed in", Toast.LENGTH_SHORT).show();
                }
                holder.edtNumberfood.setText(strAmount);
            }
        });
        holder.btnDecrea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String amount = holder.edtNumberfood.getText().toString();
                int intAmount = Integer.parseInt(amount);
                int updatedAmount = intAmount-1;
                if(updatedAmount < 0) {
                    holder.edtNumberfood.setText("0");
                    Toast.makeText(mcontext, "giá trị không thể nhỏ hơn 0", Toast.LENGTH_SHORT).show();
                }
                else {

                    String strAmount = String.valueOf(updatedAmount);
                    boolean check = db.updateOrder(tableNumber,src,getName,updatedAmount,intPrice);
                    if(check) {
                        Toast.makeText(mcontext, "Succesfull de", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(mcontext, "Failed de", Toast.LENGTH_SHORT).show();
                    }
                    holder.edtNumberfood.setText(strAmount);
                }
            }
        });
    }

    public class OrderDessertViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout lnInfoOrder;
        private TextView tvNameFood, tvpriceFood;
        private EditText edtNumberfood;
        private Button btnIncrea, btnDecrea;
        private ImageView imgv_pic_order;
        public OrderDessertViewHolder(@NonNull View itemView) {
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
