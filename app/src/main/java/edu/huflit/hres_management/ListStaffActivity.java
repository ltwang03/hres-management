package edu.huflit.hres_management;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import edu.huflit.hres_management.Food_Staff.Staff;
import edu.huflit.hres_management.Food_Staff.StaffAdapter;

public class ListStaffActivity extends AppCompatActivity {
    private RecyclerView rcvListStaff;
    private StaffAdapter staffAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_staff);

        rcvListStaff = findViewById(R.id.rcvListStaff);
        staffAdapter = new StaffAdapter(ListStaffActivity.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ListStaffActivity.this,RecyclerView.VERTICAL,false);
        rcvListStaff.setLayoutManager(linearLayoutManager);
        staffAdapter.setData(getListStaff());
        rcvListStaff.setAdapter(staffAdapter);
    }
    public List<Staff> getListStaff(){
        List<Staff> list = new ArrayList<>();
        list.add(new Staff("Dang Phuong"));
        list.add(new Staff("Dang Phuong"));
        list.add(new Staff("Dang Phuong"));

        return list;
    }
}