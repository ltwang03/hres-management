package edu.huflit.hres_management;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import edu.huflit.hres_management.Model.Staff;

public class StaffInformationActivity extends AppCompatActivity {
    EditText edtStaffName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        edtStaffName= (EditText) findViewById(R.id.edt_staff_name);
        setContentView(R.layout.activity_staff_information);
        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            return;
//        }
//        Staff st =(Staff) bundle.get("object_staff");
//        edtStaffName.setText(st.getName() );

    }
}}