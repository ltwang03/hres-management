package edu.huflit.hres_management.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import edu.huflit.hres_management.Database.DBHelper;
import edu.huflit.hres_management.ListTypeFoodActivity;
import edu.huflit.hres_management.Model.Drinks;
import edu.huflit.hres_management.R;

public class DrinksAdapter extends RecyclerView.Adapter<DrinksAdapter.DrinksViewHolder>{
    private Context mContext;
    private ArrayList<Drinks> mListDrinks;
    private String cate[] = {"Khai vị" , "Món chính" , "Tráng miệng" , "Nước giải khát"};
    Dialog dialog;
    String procate;
    DBHelper db;

    public DrinksAdapter(Context mContext,ArrayList<Drinks> list) {
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

        holder.tvName.setText(mListDrinks.get(position).getName());
        holder.tvDescribe.setText(mListDrinks.get(position).getDescribe());
        holder.tvPrice.setText(mListDrinks.get(position).getPrice());

        String name = mListDrinks.get(position).getName();
        String price = mListDrinks.get(position).getPrice();
        int product_id = mListDrinks.get(position).getId();
        String url = mListDrinks.get(position).getResourceId();
        String category = mListDrinks.get(position).getCategory();
        String descripe = mListDrinks.get(position).getDescribe();
        holder.rlvFoodItem.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                dialogDelete(product_id);
                return true;
            }
        });
        holder.imgvUpdateFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogUpdate(product_id,url,name,price,category,descripe);
            }
        });
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
        private RelativeLayout rlvFoodItem;
        private ImageView imgvUpdateFood;

        public DrinksViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFoodReal = itemView.findViewById(R.id.img_food_real);
            tvName = itemView.findViewById(R.id.name_food);
            tvDescribe = itemView.findViewById(R.id.describe_food);
            tvPrice = itemView.findViewById(R.id.price_food);
            rlvFoodItem = itemView.findViewById(R.id.rlvFoodItem);
            imgvUpdateFood = itemView.findViewById(R.id.ic_edit_food);
        }
    }
    private void dialogDelete(int positonid) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("Thông báo")
                .setMessage("Bạn có muốn xoá sản phẩm không?")
                .setPositiveButton("Xoá", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        db=new DBHelper(mContext);
                        Cursor cursor = db.getProductData();
                        while(cursor.moveToNext()) {
                            String idStr = String.valueOf(positonid);
                            if(cursor.getString(0).equals(idStr)) {
                                boolean checkDelete = db.deleteProductData(positonid);
                                if(checkDelete) {
                                    Toast.makeText(mContext,"Delete success",Toast.LENGTH_SHORT).show();

                                    Intent i = new Intent(mContext , ListTypeFoodActivity.class);
                                    mContext.startActivity(i);
                                }else {
                                    Toast.makeText(mContext,"Delete failed",Toast.LENGTH_SHORT).show();
                                }

                            }
                        }
                        cursor.close();
                    }
                })
                .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                }).show();
    }
    private void dialogUpdate(int id,String url,String name, String price, String category, String descripe) {
        dialog = new Dialog(mContext);
        dialog.setContentView(R.layout.dialog_edit_product);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        db = new DBHelper(mContext);
        EditText edtNameEditProduct = dialog.findViewById(R.id.edt_name_update_product);
        EditText edtPriceEditProduct = dialog.findViewById(R.id.edt_price_update_product);
        Spinner spnCategoryEditProduct = dialog.findViewById(R.id.spn_update_product_category);
        EditText edtDescipeEditProduct = dialog.findViewById(R.id.edt_descripe_update_product);
        Button btnUpdate = dialog.findViewById(R.id.btn_update_product);
        Button btnCancel = dialog.findViewById(R.id.btn_cancelUpdateProduct);
        edtNameEditProduct.setText(name);
        edtPriceEditProduct.setText(price);
        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(mContext , android.R.layout.simple_spinner_dropdown_item,cate);
        arrayAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spnCategoryEditProduct.setAdapter(arrayAdapter);
        edtDescipeEditProduct.setText(descripe);



        spnCategoryEditProduct.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                procate = cate[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameEdited = edtNameEditProduct.getText().toString();
                String descripeEdited = edtDescipeEditProduct.getText().toString();
                String priceEdited = edtPriceEditProduct.getText().toString();

                Cursor cursor  = db.getProductData();
                while (cursor.moveToNext()) {
                    String idStr = String.valueOf(id);
                    if (cursor.getString(0).equals(idStr)) {
                        db.updateProductData(id,url,nameEdited,priceEdited ,procate ,descripeEdited);
                        Intent i = new Intent(mContext,ListTypeFoodActivity.class);
                        mContext.startActivity(i);
                    }
                }
                cursor.close();
            }



        });
        dialog.show();



    }

}
