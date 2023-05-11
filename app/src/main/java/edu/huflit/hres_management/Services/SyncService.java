package edu.huflit.hres_management.Services;

import static android.content.ContentValues.TAG;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import java.util.ArrayList;
import java.util.Objects;

import edu.huflit.hres_management.API.APIService;
import edu.huflit.hres_management.API.model.GetFoodResponse;
import edu.huflit.hres_management.Database.DBHelper;
import edu.huflit.hres_management.Model.Food;
import edu.huflit.hres_management.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SyncService extends Service {

    private Looper mServiceLooper;
    private ServiceHandler mServiceHandler;
    private SharedPreferences sharedPreferences;
    private LocalBroadcastManager broadcaster;
    private DBHelper dbHelper;

    private final class ServiceHandler extends Handler {
        public ServiceHandler(Looper looper) {
            super(looper);
            sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);
        }

        private void startSyncTimer() {
            new CountDownTimer(10000, 10000) {
                @Override
                public void onTick(long l) {
                }
                @Override
                public void onFinish() {
                    syncData();
                }
            }.start();
        }
    }



public void syncData() {
        dbHelper = new DBHelper(getApplicationContext());
        String token = sharedPreferences.getString("token","");
        if(Objects.equals(token, "")) return;
        APIService.apiService.getFoods("Bearer " + token).enqueue(new retrofit2.Callback<GetFoodResponse>() {
            @Override
            public void onResponse(@NonNull Call<GetFoodResponse> call,@NonNull Response<GetFoodResponse> response) {
                if(response.code() != 200) return;
                GetFoodResponse getFoodResponse = response.body();
                ArrayList<Food> foodRes = getFoodResponse.getFood();
                dbHelper.deleteAllDatabase();
                for(Food food: foodRes ) {
                    Log.d("name food", food.getName());
                    dbHelper.insertProductData(food.getProduct_id(),food.getResourceID(), food.getName(), food.getPrice(), food.getCategory(), food.getDescribe());
                }
                Log.d(TAG, "onResponse: " + "sync data success");
                dbHelper.close();
            }

            @Override
            public void onFailure(@NonNull Call<GetFoodResponse> call,@NonNull Throwable t) {
                Toast.makeText(SyncService.this, "Có lỗi xảy ra vui lòng thử lại sau", Toast.LENGTH_SHORT).show();

            }
        });

}

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        broadcaster = LocalBroadcastManager.getInstance(this);
        HandlerThread thread = new HandlerThread("MyServiceThread");
        thread.start();
        mServiceLooper = thread.getLooper();
        mServiceHandler = new ServiceHandler(mServiceLooper);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    "channel_id",
                    "Sync Service",
                    NotificationManager.IMPORTANCE_LOW
            );
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Notification notification = new NotificationCompat.Builder(this, "channel_id")
                .setContentTitle("Sync Service")
                .setContentText("Syncing data")
                .setSmallIcon(R.drawable.baseline_person_outline_24)
                .setColor(Color.GREEN)
                .build();
        startForeground(1, notification);
        Message msg = mServiceHandler.obtainMessage();
        msg.arg1 = startId;
        ServiceHandler serviceHandler = new ServiceHandler(mServiceLooper);
        serviceHandler.startSyncTimer();
        mServiceHandler.sendMessage(msg);
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopForeground(true);
    }
}