package com.example.aravind.myfirstapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

/**
 * Created by aravind on 29/12/14.
 */
public class myservices extends Service {

    @Override
    public IBinder onBind(Intent arg0)
    {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent , int flags, int startId)
    {
        Toast.makeText(this, "The service started", Toast.LENGTH_LONG).show();
        return START_STICKY;
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        Toast.makeText(this, "The service stopped", Toast.LENGTH_LONG).show();
    }
}
