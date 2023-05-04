package edu.huflit.hres_management.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.Objects;

import edu.huflit.hres_management.API.APIService;
import edu.huflit.hres_management.API.model.DeleteFoodRequest;
import edu.huflit.hres_management.API.model.DeleteFoodResponse;
import edu.huflit.hres_management.API.model.UpdateFoodRequest;
import edu.huflit.hres_management.API.model.UpdateFoodResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Helper {
    public void CallAPIDeleteFood(int product_id, String token) {
        if (Objects.equals(token,"")) {
            return;
        }
        Log.e("token", token);
        DeleteFoodRequest deleteFoodRequest = new DeleteFoodRequest(String.valueOf(product_id));
        APIService.apiService.deleteFood("Bearer " + token, deleteFoodRequest).enqueue(new Callback<DeleteFoodResponse>() {
            @Override
            public void onResponse(@NonNull Call<DeleteFoodResponse> call, @NonNull Response<DeleteFoodResponse> response) {
                if (response.code() != 200) return;
                DeleteFoodResponse deleteFoodResponse = response.body();
                Log.e("status",deleteFoodResponse.getStatus());
            }

            @Override
            public void onFailure(@NonNull Call<DeleteFoodResponse> call, @NonNull Throwable t) {
            }
        });
    }
    public void CallAPIUpdateFood(String token,int product_id, String name, String category, String describe, String price ) {
        if(Objects.equals(token,"")) {
            return;
        }
        UpdateFoodRequest updateFoodRequest = new UpdateFoodRequest(product_id, name, category, describe, price);
        APIService.apiService.updateFood("Bearer " + token, updateFoodRequest).enqueue(new Callback<UpdateFoodResponse>() {
            @Override
            public void onResponse(@NonNull Call<UpdateFoodResponse> call, @NonNull Response<UpdateFoodResponse> response) {
                if(response.code() != 200) {
                    return;
                }
                UpdateFoodResponse updateFoodResponse = response.body();
                Log.e("status update", updateFoodResponse.getStatus());
            }

            @Override
            public void onFailure(@NonNull Call<UpdateFoodResponse> call, @NonNull Throwable t) {

            }
        });
    }
}
