package edu.huflit.hres_management.API;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import edu.huflit.hres_management.API.model.LoginRequest;
import edu.huflit.hres_management.API.model.LoginResponse;
import edu.huflit.hres_management.API.model.RegisterManagerRequest;
import edu.huflit.hres_management.API.model.RegisterManagerResponse;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface APIService {

    static final String baseURL = "https://hres-management-server-production.up.railway.app/";
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

}
