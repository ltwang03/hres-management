package edu.huflit.hres_management;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.cloudinary.Url;
import com.cloudinary.android.MediaManager;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.huflit.hres_management.Database.DBHelper;



public class AddProductsAcitivity extends AppCompatActivity {
    private static boolean checkInit = false;
    private static int IMAGE_REQ=1;
    private static final String TAG = "Upload image  ###";
    private Uri imagePath;
    String cate[] = {"Khai vị" , "Món chính" , "Tráng miệng" , "Nước giải khát"};
    EditText edtProductName, edtProductPrice, edtProductDescripe;
    Button btnAddProduct;
    String imageUrl , proCate;
    Spinner spnCategory;
    ImageView imgvAddImage, imgbackToFood;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_products);
        edtProductName = (EditText) findViewById(R.id.edt_product_name);
        edtProductPrice = (EditText) findViewById(R.id.edt_product_price);
        edtProductDescripe = (EditText) findViewById(R.id.edt_product_descripe);
        imgvAddImage = (ImageView) findViewById(R.id.imgv_add_product);
        spnCategory = (Spinner) findViewById(R.id.spn_product_category);
        btnAddProduct = (Button) findViewById(R.id.btn_add_product);
        db = new DBHelper(this);

        if (!checkInit) {
           initConfig();
            checkInit = true;
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,cate);
        arrayAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spnCategory.setAdapter(arrayAdapter);
        spnCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                //đối số postion là vị trí phần tử trong list Data
                Toast.makeText(getApplicationContext(), "Selected category: " + cate[position] ,Toast.LENGTH_SHORT).show();
                proCate = cate[position];
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                Toast.makeText(AddProductsAcitivity.this, "onNothingSelected", Toast.LENGTH_SHORT).show();
            }


        });

        imgbackToFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AddProductsAcitivity.this, ListTypeFoodActivity.class);
                startActivity(i);
            }
        });


        imgvAddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestPermission();

            }
        });
        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaManager.get().upload(imagePath).callback(new UploadCallback() {
                    @Override
                    public void onStart(String requestId) {
                        Log.d(TAG, "onStart: " + "started");
                        Toast.makeText(AddProductsAcitivity.this,"Adding" , Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onProgress(String requestId, long bytes, long totalBytes) {
                        Log.d(TAG, "onStart: " + "uploading");
                    }

                    @Override
                    public void onSuccess(String requestId, Map resultData) {
                        Log.d(TAG, "onStart: " + "usuccess" );
                         imageUrl = (String) resultData.get("secure_url");
                        Log.e(TAG, "onSuccess: " +imageUrl );
                        String productName = edtProductName.getText().toString();
                        String productPrice = edtProductPrice.getText().toString();
                        String productDesc = edtProductDescripe.getText().toString();

                        // procate  + imageUrl;

                        Boolean checkInsertProductData = db.insertProductData(imageUrl , productName,productPrice,proCate,productDesc);
                        if(checkInsertProductData== true) {
                            Toast.makeText(AddProductsAcitivity.this, "New entry inserted", Toast.LENGTH_SHORT ).show();
                        }else {
                            Toast.makeText(AddProductsAcitivity.this, "New entry inserted Failed", Toast.LENGTH_SHORT ).show();
                        }
                        imageUrl = "";
                        edtProductName.setText("");
                        edtProductDescripe.setText("");
                        edtProductPrice.setText("");
                        proCate= "";



                    }

                    @Override
                    public void onError(String requestId, ErrorInfo error) {
                        Log.d(TAG, "onStart: " + "error");
                    }

                    @Override
                    public void onReschedule(String requestId, ErrorInfo error) {
                        Log.d(TAG, "onStart: " + "error");
                    }
                }).dispatch();



                Log.e(TAG, "onClick: " + proCate + imageUrl );





            }
        });


    }
//cloudinary init

    private void initConfig() {
        Map config = new HashMap();
        config.put("cloud_name", "da3nhk2m6");
        config.put("api_key" , "922262966345755");
        config.put("api_secret" , "Vn8ehbvPvLc0eSb9WTaxJ8uutYo");
//      config.put("secure", true);
        MediaManager.init(this, config);

    }

    private void RequestPermission(){
        if(ContextCompat.checkSelfPermission(AddProductsAcitivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
            selectImage();

        }else {
            ActivityCompat.requestPermissions(AddProductsAcitivity.this,new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE
            },IMAGE_REQ);
        }

        }

    private void selectImage() {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(i,IMAGE_REQ);
    }
    @Override
    protected  void onActivityResult(int requestcode , int resultcode, Intent data) {
        super.onActivityResult(requestcode,resultcode,data);
        if(requestcode ==IMAGE_REQ && resultcode == Activity.RESULT_OK && data != null && data.getData()!= null) {
            imagePath = data.getData();

            Picasso.get().load(imagePath).into(imgvAddImage);




        }
    }

}