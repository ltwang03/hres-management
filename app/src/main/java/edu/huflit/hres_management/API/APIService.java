package edu.huflit.hres_management.API;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import edu.huflit.hres_management.API.model.AddFoodRequest;
import edu.huflit.hres_management.API.model.AddFoodResponse;
import edu.huflit.hres_management.API.model.DeleteUserRequest;
import edu.huflit.hres_management.API.model.DeleteUserResponse;
import edu.huflit.hres_management.API.model.EditProfileRequest;
import edu.huflit.hres_management.API.model.EditProfileResponse;
import edu.huflit.hres_management.API.model.GetFoodResponse;
import edu.huflit.hres_management.API.model.LoginRequest;
import edu.huflit.hres_management.API.model.LoginResponse;
import edu.huflit.hres_management.API.model.ProfileResponse;
import edu.huflit.hres_management.API.model.RegisterManagerRequest;
import edu.huflit.hres_management.API.model.RegisterManagerResponse;
import edu.huflit.hres_management.API.model.RegisterStaffRequest;
import edu.huflit.hres_management.API.model.RegisterStaffResponse;
import edu.huflit.hres_management.API.model.UserResponse;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;


public interface APIService {

    static final String baseURL = "https://hres-management-server-production.up.railway.app/";
//    static final String baseURL = "https://hres-management-server.onrender.com/";
//    static final String baseURL = "https://7461-1-52-184-153.ngrok-free.app/";
    Gson gson = new GsonBuilder().setLenient().create();
    APIService apiService = new Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(APIService.class);
    @POST("auth/login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);
    @POST("auth/register/manager")
    Call<RegisterManagerResponse> registerManager(@Body RegisterManagerRequest registerManagerRequest);
    @POST("auth/register/staff")
    Call<RegisterStaffResponse> registerStaff(@Body RegisterStaffRequest registerStaffRequest);
    @GET("auth/get/user")
    Call<UserResponse> getUserRestaurant(@Header("Authorization") String token);
    @GET("auth/get/profile")
    Call<ProfileResponse> getProfile(@Header("Authorization") String token);
    @PUT("auth/edit/profile")
    Call<EditProfileResponse> editProfile(@Header("Authorization") String token, @Body EditProfileRequest editProfileRequest);
    @POST("api/post/food")
    Call<AddFoodResponse> postFood(@Header("Authorization") String token, @Body AddFoodRequest addFoodRequest);
    @GET ("api/get/food")
    Call<GetFoodResponse> getFoods(@Header("Authorization") String token);
    @HTTP(method = "DELETE", path = "auth/delete/user", hasBody = true)
    Call<DeleteUserResponse> deleteUser(@Header("Authorization") String token, @Body DeleteUserRequest deleteUserRequest);


}
