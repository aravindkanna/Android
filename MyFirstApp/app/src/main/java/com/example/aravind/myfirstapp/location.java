package com.example.aravind.myfirstapp;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;


public class location extends ActionBarActivity {


    LocationManager mlocManager;
    LocationListener mlocListener;
    Location mlocation;
    double newlatitude, newlongitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        /*mlocManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        mlocListener = new MyLocationListener();
        mlocManager.requestLocationUpdates( LocationManager.GPS_PROVIDER, 10000, 1, mlocListener);
        mlocation = mlocManager
                .getLastKnownLocation(LocationManager.GPS_PROVIDER);
        //Toast.makeText(getApplicationContext(), "latitude: " + latitude + "longitude: " + longitude, Toast.LENGTH_SHORT).show();

        MyLocationListener m = new MyLocationListener();
        double dlat = m.getlat();
        Toast.makeText( getApplicationContext(),String.valueOf(dlat),Toast.LENGTH_SHORT).show();*/

        GPSService mGPSService = new GPSService(this);
        mGPSService.getLocation();
        double latitude = mGPSService.getLatitude();
        double longitude = mGPSService.getLongitude();
        String address = mGPSService.getLocationAddress();


        Toast.makeText( getApplicationContext(),String.valueOf("lat is "+longitude),Toast.LENGTH_SHORT).show();





    }

    public class MyLocationListener implements LocationListener
    {

        @Override

        public void onLocationChanged(Location loc)

        {

            loc.getLatitude();

            loc.getLongitude();

            String Text = "My current location is: " +

            "Latitud = " + String.valueOf(loc.getLatitude()) +

            "Longitud = " + String.valueOf(loc.getLongitude());

            Toast.makeText( getApplicationContext(),

                    Text,

                    Toast.LENGTH_SHORT).show();
            TextView tv = (TextView) findViewById(R.id.viewlocation);
            tv.setText(Text);
        }


        @Override

        public void onProviderDisabled(String provider)

        {

            Toast.makeText( getApplicationContext(),

                    " Hey Your Gps is Disabled",

                    Toast.LENGTH_SHORT ).show();

        }

        @Override

        public void onProviderEnabled(String provider)

        {

            Toast.makeText( getApplicationContext(),

                    "Gps Enabled",

                    Toast.LENGTH_SHORT).show();



        }

        @Override

        public void onStatusChanged(String provider, int status, Bundle extras)

        {

        }


        public double getlat()
        {
            if(mlocation != null)
            {
                newlatitude = mlocation.getLatitude();
            }
            return newlatitude;
        }

        public double getlon()
        {
            if(mlocation != null)
            {
                newlongitude = mlocation.getLongitude();
            }
            return newlongitude;
        }

    }
}
