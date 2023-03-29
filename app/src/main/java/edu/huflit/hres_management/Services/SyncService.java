package edu.huflit.hres_management.Services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Parcelable;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import edu.huflit.hres_management.API.APIService;
import edu.huflit.hres_management.API.model.UserResponse;
import edu.huflit.hres_management.Model.Staff;
import retrofit2.Call;
import retrofit2.Response;

public class SyncService extends Service {

    private Looper mServiceLooper;
    private ServiceHandler mServiceHandler;
    private SharedPreferences sharedPreferences;
    private LocalBroadcastManager broadcaster;

    private final class ServiceHandler extends Handler {
        public ServiceHandler(Looper looper) {
            super(looper);
            sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);

        }
    }


//    public void SyncData() {
//        String token = sharedPreferences.getString("token", "");
//        if(Objects.equals(token,"")) return;
//        APIService.apiService.getUserRestaurant("Bearer" + token).enqueue(new retrofit2.Callback<UserResponse>() {
//            @Override
//            public void onResponse(@NonNull Call<UserResponse> call, @NonNull Response<UserResponse> response) {
//                if(response.code() != 200) {
//                    Toast.makeText(SyncService.this, "Đồng bộ dữ liệu không thành công", Toast.LENGTH_SHORT).show();
//                }
//                UserResponse userResponse = response.body();
//                if(!userResponse.getStatus().equals("success")) return;
//
//
//            }
//
//            @Override
//            public void onFailure(Call<UserResponse> call, Throwable t) {
//
//            }
//        });
//    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        broadcaster = LocalBroadcastManager.getInstance(this);
        HandlerThread thread = new HandlerThread("MyServiceThread");
        thread.start();
        mServiceLooper = thread.getLooper();
        mServiceHandler = new ServiceHandler(mServiceLooper);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Message msg = mServiceHandler.obtainMessage();
        msg.arg1 = startId;
        mServiceHandler.sendMessage(msg);
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}