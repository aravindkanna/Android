package com.example.aravind.myfirstapp;

/**
 * Created by aravind on 26/12/14.
 */
import java.io.IOException;
        import java.util.List;
        import java.util.Locale;

       // import com.example.aravind.contents.OMitraApplication;
       //import com.example.aravind.utils.CommFunc;

        import android.app.AlertDialog;
        import android.app.Service;
        import android.content.Context;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.location.Address;
        import android.location.Geocoder;
        import android.location.Location;
        import android.location.LocationListener;
        import android.location.LocationManager;
        import android.net.ConnectivityManager;
        import android.net.NetworkInfo;
        import android.net.NetworkInfo.State;
        import android.os.Bundle;
        import android.os.IBinder;
        import android.provider.Settings;
        import android.util.Log;

public class GPSService extends Service implements LocationListener {

    // saving the context for later use
    private final Context mContext;
    public static final String TAG = GPSService.class.getName();


    boolean isGPSEnabled = false;

    public boolean isLocationAvailable = false;
    Location mLocation;
    double mLatitude;
    double mLongitude;


    private static final long TIME = 0;
    private static final long DISTANCE = 1;

    protected LocationManager mLocationManager;

    public GPSService(Context context) {
        this.mContext = context;
        mLocationManager = (LocationManager) mContext
                .getSystemService(LOCATION_SERVICE);

    }

    /**
     * Returns the Location
     *
     * @return Location or null if no location is found
     */
    public Location getLocation() {
//		try {
        Log.e(TAG,"getLocation");
        // Getting GPS status
        isGPSEnabled = mLocationManager
                .isProviderEnabled(LocationManager.GPS_PROVIDER);

        // If GPS enabled, get latitude/longitude using GPS Services
        if (isGPSEnabled) {
            mLocationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER, TIME, DISTANCE, this);
            if (mLocationManager != null) {
                mLocation = mLocationManager
                        .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                if (mLocation != null) {
                    mLatitude = mLocation.getLatitude();
                    mLongitude = mLocation.getLongitude();
                    isLocationAvailable = true; // setting a flag that
                    // location is available
                    return mLocation;
                }
            }
        }


        if (!isGPSEnabled) {
            // so asking user to open GPS
            askUserToOpenGPS();
        }


        isLocationAvailable = false;
        return null;
    }


    public String getLocationAddress() {

        if (isLocationAvailable) {

            Geocoder geocoder = new Geocoder(mContext, Locale.getDefault());
            // Get the current location from the input parameter list
            // Create a list to contain the result address
            List<Address> addresses = null;
            try {
				/*
				 * Return 1 address.
				 */
                addresses = geocoder.getFromLocation(mLatitude, mLongitude, 1);
            } catch (IOException e1) {
                e1.printStackTrace();
                return ("NULL");
            } catch (IllegalArgumentException e2) {
                // Error message to post in the log
                String errorString = "Illegal arguments "
                        + Double.toString(mLatitude) + " , "
                        + Double.toString(mLongitude)
                        + " passed to address service";
                e2.printStackTrace();
                return errorString;
            }
            // If the reverse geocode returned an address
            if (addresses != null && addresses.size() > 0) {
                // Get the first address
                Address address = addresses.get(0);

                String city = null;
                if (address.getLocality() != null)  city=address.getLocality();
                else
                if (address.getSubAdminArea() != null)  city=address.getSubAdminArea();
                return city;
            } else {
                return "NULL";
            }
        } else {
            return "NULL";
        }

    }



    /**
     * get latitude
     *
     * @return latitude in double
     */
    public double getLatitude() {
        if (mLocation != null) {
            mLatitude = mLocation.getLatitude();
        }
        return mLatitude;
    }

    /**
     * get longitude
     *
     * @return longitude in double
     */
    public double getLongitude() {
        if (mLocation != null) {
            mLongitude = mLocation.getLongitude();
        }
        return mLongitude;
    }

    /**
     * close GPS to save battery
     */
    public void closeGPS() {
        if (mLocationManager != null) {
            mLocationManager.removeUpdates(GPSService.this);
        }
    }

    /**
     * show settings to open GPS
     */
    public void askUserToOpenGPS() {

	}

    /**
     * Updating the location when location changes
     */
    @Override
    public void onLocationChanged(Location location) {
        mLatitude = location.getLatitude();
        mLongitude = location.getLongitude();
    }

    @Override
    public void onProviderDisabled(String provider) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }


}