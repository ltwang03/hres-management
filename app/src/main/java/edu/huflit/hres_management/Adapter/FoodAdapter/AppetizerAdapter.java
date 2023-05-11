package edu.huflit.hres_management.Adapter.FoodAdapter;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
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

import edu.huflit.hres_management.Database.DBHelper;
import edu.huflit.hres_management.ListTypeFoodActivity;
import edu.huflit.hres_management.Model.Appetizer;
import edu.huflit.hres_management.R;

public class AppetizerAdapter extends RecyclerView.Adapter<AppetizerAdapter.AppetizerViewHolder>{
    private Context mContext;
    private String cate[] = {"Khai vị" , "Món chính" , "Tráng miệng" , "Nước giải khát"};
    private ArrayList<Appetizer> mListAppetizer;
    String procate;
    DBHelper db;
    Dialog dialog;

    public AppetizerAdapter(Context mContext,ArrayList<Appetizer> list) {
        this.mContext = mContext; this.mListAppetizer= list;db=new DBHelper(mContext);
        notifyDataSetChanged();
    }


    @NonNull
    @Override

    public AppetizerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_list_food,parent,false);
        db = new DBHelper(mContext);

        return new AppetizerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppetizerViewHolder holder, int position) {

        Appetizer appetizer = mListAppetizer.get(position);
        if(appetizer == null)
            return;
//        holder.imgFood.setImageResource(food1.getResourceId());
        holder.imgFoodReal.getLayoutParams().width = 150;
        holder.imgFoodReal.getLayoutParams().height = 170;

        Picasso.get().load(mListAppetizer.get(position).getResourceId()).resize(holder.imgFoodReal.getLayoutParams().width, holder.imgFoodReal.getLayoutParams().height).centerCrop().into(holder.imgFoodReal);
        String url = mListAppetizer.get(position).getResourceId();
        holder.tvName.setText(mListAppetizer.get(position).getName());
        String name = mListAppetizer.get(position).getName();
        holder.tvDescribe.setText(mListAppetizer.get(position).getDescribe());
        String descripe = mListAppetizer.get(position).getDescribe();
        holder.tvPrice.setText(mListAppetizer.get(position).getPrice());
        String price = mListAppetizer.get(position).getPrice();
        String category = mListAppetizer.get(position).getCategory();
        Integer product_id = mListAppetizer.get(position).getId();
        holder.icUpdateFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogUpdate(product_id,url,name,price,category,descripe);
                Log.e(TAG, "onClick: " + "tesst" );
            }
        });
        holder.rltFoodItem.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                dialogDelete(product_id);
                return true;
            }
        });



    }


    @Override
    public int getItemCount() {
        if(mListAppetizer != null){
            return mListAppetizer.size();
        }
        return 0;
    }

    public class AppetizerViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgFoodReal , icUpdateFood;
        private TextView imgFood;
        private TextView tvName;
        private TextView tvDescribe;
        private TextView tvPrice;
        private RelativeLayout rltFoodItem;
        private Spinner spnUpdateCate;


        public AppetizerViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFoodReal =itemView.findViewById(R.id.img_food_real);
            tvName = itemView.findViewById(R.id.name_food);
            tvDescribe = itemView.findViewById(R.id.describe_food);
            tvPrice = itemView.findViewById(R.id.price_food);
            rltFoodItem =itemView.findViewById(R.id.rlvFoodItem);
            icUpdateFood= itemView.findViewById(R.id.ic_edit_food);
            spnUpdateCate = itemView.findViewById(R.id.spn_update_product_category);
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