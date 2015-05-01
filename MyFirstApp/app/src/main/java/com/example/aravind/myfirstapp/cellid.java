package com.example.aravind.myfirstapp;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.CellLocation;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class cellid extends ActionBarActivity {


    GPSService mGPSService ;
    JSONArray j;
    TelephonyManager Tel;
    MyPhoneStateListener    MyListener;
    GsmCellLocation location;
    int global_cellid;
    String[] list = new String[10000];
    String[] list1 = new String[10000];
    items item;
    int count;
    LocationManager mlocManager;
    String Strength;
    Geocoder geocoder ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cellid);

        MyListener   = new MyPhoneStateListener();
        Tel       = ( TelephonyManager )getSystemService(Context.TELEPHONY_SERVICE);
        location = (GsmCellLocation) Tel.getCellLocation();
        global_cellid = location.getCid();
        Tel.listen(MyListener , PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);
        j = new JSONArray();
        mlocManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        mGPSService = new GPSService(this);


        geocoder = new Geocoder(this, Locale.getDefault());

    }


    private class MyPhoneStateListener extends PhoneStateListener
    {
        @Override
        public void onSignalStrengthsChanged(SignalStrength s)
        {
            super.onSignalStrengthsChanged(s);
            location = (GsmCellLocation) Tel.getCellLocation();
            item = new items();
            int cellid = location.getCid();
            int strength = s.getGsmSignalStrength();

            if(global_cellid!=cellid || count==0)
            {
                global_cellid = cellid;
                item.get_cid = String.valueOf(location.getCid());
                item.get_lac = String.valueOf(location.getLac());
                item.get_strength = String.valueOf(s.getGsmSignalStrength());
                Calendar cal = Calendar.getInstance();
                int second = cal.get(Calendar.SECOND);
                int minute = cal.get(Calendar.MINUTE);
                int hour = cal.get(Calendar.HOUR);
                String sec = Integer.toString(second);
                String m = Integer.toString(minute);
                String h = Integer.toString(hour);
                item.get_time = h +":" + m + ":" + sec;
                //GPSService mGPSService = new GPSService(this);



                mGPSService.getLocation();
                double latitude = mGPSService.getLatitude();
                double longitude = mGPSService.getLongitude();
                String add = mGPSService.getLocationAddress();
                item.get_lat = String.valueOf(latitude);
                item.get_lon = String.valueOf(longitude);

                TextView tv = (TextView) findViewById(R.id.values);
                TextView tv1 = (TextView) findViewById(R.id.tv);
                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();

                try {
                    List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);

                    if(addresses != null) {
                        Address returnedAddress = addresses.get(0);
                        StringBuilder strReturnedAddress = new StringBuilder("Address:\n");
                        for(int i=0; i<returnedAddress.getMaxAddressLineIndex(); i++) {
                            strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
                        }
                        tv1.setText(strReturnedAddress.toString());
                    }
                    else{
                        tv1.setText("No Address returned!");
                    }
                } catch (IOException e) {

                    e.printStackTrace();
                    tv1.setText("Canont get Address!");
                }

                String st = "Strength: " + item.get_strength + "    Cid: " + item.get_cid+ "    Lac: " + item.get_lac
                        + "    Time: " + item.get_time + "    Lat: " + item.get_lat + "    Lon: " + item.get_lon
                        + "\n\n";

                list1[count] = st;
                list[count] = gson.toJson(item).toString();
                count = count + 1;
                String x="";
                for(int i=0;i<count;i++)
                {
                    x = x + list[i];
                    x = x + '\n';
                }
                tv.setText(x);

            }

        }
    }

    class items
    {
        public String get_cid;
        public String get_lac;
        public String get_strength;
        public String get_lat;
        public String get_lon;
        public String get_time;
    }

    class items_array
    {
        items[] item_array = new items[]{};
    }

    public void stop(View view)
    {
        Strength = "";
        for(int j=0;j<count;j++)
        {
            Strength = Strength + list1[j];
            Strength = Strength + "\n";
        }

        db entry = new db(cellid.this);

        entry.open();
        entry.createentry(Strength);
        entry.close();

        count = 0;
        Tel.listen(MyListener , PhoneStateListener.LISTEN_NONE);

    }

    public void start(View view)
    {
        Tel.listen(MyListener , PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);
    }

    @Override
    protected void onPause()
    {
        //this is called when the application is paused i.e. when it is minimised
        super.onPause();
        Tel.listen(MyListener, PhoneStateListener.LISTEN_NONE);
        //tel.listen(MyListener, PhoneStateListener.LISTEN_NONE);
    }

    @Override
    protected void onResume()
    {
        //this is called when the application is resumed.
        super.onResume();
        Tel.listen(MyListener,PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);
        //tel.listen(MyListener, PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);
    }

    protected void onStop()
    {
        super.onStop();
        Tel.listen(MyListener,PhoneStateListener.LISTEN_NONE);
    }

    public void dbview(View view)
    {
        Intent intent = new Intent(cellid.this, stdb.class);
        startActivity(intent);
    }


}
