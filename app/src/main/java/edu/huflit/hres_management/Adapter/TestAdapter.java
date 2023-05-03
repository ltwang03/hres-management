package edu.huflit.hres_management.Adapter;

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
import android.widget.LinearLayout;
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
import edu.huflit.hres_management.Model.Appetizer;
import edu.huflit.hres_management.Model.Test;
import edu.huflit.hres_management.R;

public class TestAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_DEFAULT = 1;
    private static final int VIEW_TYPE_ALT = 2;
    String cate[] = {"Khai vị" , "Món chính" , "Tráng miệng" , "Nước giải khát"};
    Context mContext;
    DBHelper db;
    Dialog dialog;
    String procate;
    private static ArrayList<Appetizer> mListAppetizer;

    public TestAdapter(Context mContext, ArrayList<Test> tests) {
        this.mContext = mContext;
        this.mListAppetizer = mListAppetizer;
    }

    @Override
    public int getItemViewType(int position) {
        // Chọn layout hiển thị dựa trên vị trí của mục trong danh sách
        if (position % 2 == 0) {
            return VIEW_TYPE_DEFAULT;
        } else {
            return VIEW_TYPE_ALT;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        // Tạo ViewHolder dựa trên viewType
        if (viewType == VIEW_TYPE_DEFAULT) {
            View itemView = inflater.inflate(R.layout.layout_item_list_food, parent, false);
            return new DefaultViewHolder(itemView);
        } else {
            View itemView = inflater.inflate(R.layout.layout_food_order, parent, false);
            return new AltViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        // Gán dữ liệu vào ViewHolder
        if (holder instanceof DefaultViewHolder) {
            ((DefaultViewHolder) holder).bind(position);
        } else if (holder instanceof AltViewHolder) {
            ((AltViewHolder) holder).bind(position);
        }
    }

    @Override
    public int getItemCount() {
        return mListAppetizer.size();
    }

    // ViewHolder cho layout mặc định
    private class DefaultViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgFoodReal , icUpdateFood;
        private TextView imgFood;
        private TextView tvName;
        private TextView tvDescribe;
        private TextView tvPrice;
        private RelativeLayout rltFoodItem;
        private Spinner spnUpdateCate;

        public DefaultViewHolder(View itemView) {
            super(itemView);
            imgFoodReal =itemView.findViewById(R.id.img_food_real);
            tvName = itemView.findViewById(R.id.name_food);
            tvDescribe = itemView.findViewById(R.id.describe_food);
            tvPrice = itemView.findViewById(R.id.price_food);
            rltFoodItem =itemView.findViewById(R.id.rlvFoodItem);
            icUpdateFood= itemView.findViewById(R.id.ic_edit_food);
            spnUpdateCate = itemView.findViewById(R.id.spn_update_product_category);
        }

        public void bind( int position) {
            Appetizer appetizer = mListAppetizer.get(position);
            if(appetizer == null)
                return;
//        holder.imgFood.setImageResource(food1.getResourceId());
            imgFoodReal.getLayoutParams().width = 150;
            imgFoodReal.getLayoutParams().height = 170;

            Picasso.get().load(mListAppetizer.get(position).getResourceId()).resize(imgFoodReal.getLayoutParams().width, imgFoodReal.getLayoutParams().height).centerCrop().into(imgFoodReal);
            String url = mListAppetizer.get(position).getResourceId();
            tvName.setText(mListAppetizer.get(position).getName());
            String name = mListAppetizer.get(position).getName();
            tvDescribe.setText(mListAppetizer.get(position).getDescribe());
            String descripe = mListAppetizer.get(position).getDescribe();
            tvPrice.setText(mListAppetizer.get(position).getPrice());
            String price = mListAppetizer.get(position).getPrice();
            String category = mListAppetizer.get(position).getCategory();
            Integer product_id = mListAppetizer.get(position).getId();
            icUpdateFood.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialogUpdate(product_id,url,name,price,category,descripe);
                    Log.e(TAG, "onClick: " + "tesst" );
                }
            });
            rltFoodItem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    dialogDelete(product_id);
                    return true;
                }
            });
        }


    }

    // ViewHolder cho layout thay thế
    private static class AltViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout lnInfoFood;
        private TextView tvNameFood;
        private TextView tvPriceFood;
        private EditText edtAmountOrder;
        private Button btnIncrea,btnDeCrea;

        public AltViewHolder(View itemView) {
            super(itemView);
            lnInfoFood = itemView.findViewById(R.id.ln_info_order);
            tvNameFood = itemView.findViewById(R.id.tv_nameFood);
            tvPriceFood = itemView.findViewById(R.id.tv_priceFood);
            edtAmountOrder = itemView.findViewById(R.id.edt_amountFood);
            btnDeCrea = itemView.findViewById(R.id.btnDecrease);
            btnIncrea= itemView.findViewById(R.id.btnIncrease);
        }

        public void bind(int position) {
            tvNameFood.setText(mListAppetizer.get(position).getName());
            tvPriceFood.setText(mListAppetizer.get(position).getPrice());
            edtAmountOrder.setText("0");
            btnIncrea.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   String amount = edtAmountOrder.getText().toString();
                   int intAmount = Integer.parseInt(amount);
                   String strAmount = String.valueOf(intAmount+1);
                   edtAmountOrder.setText(strAmount);
                }
            });
            btnDeCrea.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String amount = edtAmountOrder.getText().toString();
                    int intAmount = Integer.parseInt(amount);
                    int updatedAmount = intAmount-1;
                    if(updatedAmount <= 0) {
                        edtAmountOrder.setText(0);
                    }
                    else {
                        String strAmount = String.valueOf(updatedAmount);
                        edtAmountOrder.setText(strAmount);
                    }
                }
            });

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
