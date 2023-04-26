package edu.huflit.hres_management.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Objects;

import edu.huflit.hres_management.API.APIService;
import edu.huflit.hres_management.API.model.DeleteUserRequest;
import edu.huflit.hres_management.API.model.DeleteUserResponse;
import edu.huflit.hres_management.ListStaffActivity;
import edu.huflit.hres_management.Model.Staff;
import edu.huflit.hres_management.R;
import retrofit2.Call;
import retrofit2.Response;

public class StaffAdapter extends RecyclerView.Adapter<StaffAdapter.StaffViewHolder> {
    private Context mContext;
    private List<Staff> mListStaff;
    SharedPreferences sharedPreferences;

    public StaffAdapter(Context mContext, List<Staff> mListStaff) {
        this.mContext = mContext;
        this.mListStaff = mListStaff;
    }

    @NonNull
    @Override
    public StaffViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        sharedPreferences = mContext.getSharedPreferences("data", Context.MODE_PRIVATE);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_list_staff,parent,false);
        return new StaffViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StaffViewHolder holder, int position) {
        Staff staff = mListStaff.get(position);
        if(staff == null)
            return;
        holder.tvName.setText(staff.getFullName());
        holder.ivPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumber = String.valueOf(staff.getPhoneNumber());
                Intent i = new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:" + "0" + phoneNumber));
                if(mContext != null) {
                    mContext.startActivity(i);
                }else {
                    Log.e("staffAdapter", "Context is null");
                }
            }
        });
        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String token = sharedPreferences.getString("token", "");
                String role = sharedPreferences.getString("role", "");
                String userName = String.valueOf(staff.getUserName());
                DeleteUserRequest deleteUserRequest = new DeleteUserRequest(userName);
                if(Objects.equals(token,"")) return;
                if(Objects.equals(role,"staff")) {
                    Toast.makeText(mContext, "Bạn không có quyền xóa user!", Toast.LENGTH_SHORT).show();
                    return;
                }
                APIService.apiService.deleteUser("Bearer "+token, deleteUserRequest).enqueue(new retrofit2.Callback<DeleteUserResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<DeleteUserResponse> call, @NonNull Response<DeleteUserResponse> response) {
                        if(response.code() != 200) {
                            Toast.makeText(mContext, "Có lỗi xảy ra!!!!", Toast.LENGTH_SHORT).show();
                        }
                        DeleteUserResponse deleteUserResponse = response.body();
                        Toast.makeText(mContext, deleteUserResponse.getStatus(), Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onFailure(@NonNull Call<DeleteUserResponse> call, @NonNull Throwable t) {

                    }
                });
                Intent i = new Intent(mContext, ListStaffActivity.class);
                mContext.startActivity(i);
            }

        });

    }

    @Override
    public int getItemCount() {
        if(mListStaff != null){
            return mListStaff.size();
        }
        return 0;
    }
    public class StaffViewHolder extends RecyclerView.ViewHolder{
        private TextView tvName;
        private ImageView ivPhone;

        private ImageView ivDelete;

        public StaffViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.name_staff);
            ivPhone = itemView.findViewById(R.id.call_staff);
            ivDelete = itemView.findViewById(R.id.delete_staff);
        }
    }
}
