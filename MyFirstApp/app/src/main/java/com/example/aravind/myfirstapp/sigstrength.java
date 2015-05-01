package com.example.aravind.myfirstapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
//import android.telephony.CellLocation;
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

import java.util.Calendar;


public class sigstrength extends ActionBarActivity {

    items item;
    TelephonyManager Tel, tel;
    MyPhoneStateListener    MyListener;
    public int s , count = 0;
    String[] id = new String[10000];
    //String
    GsmCellLocation location;
    int cellid;

    public String Strength = "";
    //int cellid, lac, psc;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sigstrength);


        MyListener   = new MyPhoneStateListener();
        Tel       = ( TelephonyManager )getSystemService(Context.TELEPHONY_SERVICE);
        tel       = ( TelephonyManager )getSystemService(Context.TELEPHONY_SERVICE);
        Tel.listen(MyListener ,PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);
        //tel.listen(MyListener, PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);

       //cellid = location.getCid();
       // lac = location.getLac();
        //psc = location.getPsc();
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

    public void stop(View view)
    {

        Toast.makeText(this, Strength, Toast.LENGTH_LONG).show();


        hello entry = new hello(sigstrength.this);

        entry.sopen();
        entry.screateentry(Strength);
        entry.sclose();


        count = 0;
        Strength = "";
        Tel.listen(MyListener,PhoneStateListener.LISTEN_NONE);
    }

    public void start(View view)
    {
        Tel.listen(MyListener,PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);
    }

    public void db(View view)
    {
        Intent intent = new Intent(sigstrength.this, strengthdb.class);
        startActivity(intent);
    }

    private class MyPhoneStateListener extends PhoneStateListener
    {

        @Override
        public void onSignalStrengthsChanged(SignalStrength signalStrength) {
            super.onSignalStrengthsChanged(signalStrength);

            TextView txt = (TextView) findViewById(R.id.strengthview);

            txt.setText("Signal Strength is : "+String.valueOf(signalStrength.getGsmSignalStrength()));

            s = signalStrength.getGsmSignalStrength();

            Calendar cal = Calendar.getInstance();
            int second = cal.get(Calendar.SECOND);
            int minute = cal.get(Calendar.MINUTE);
            int hour = cal.get(Calendar.HOUR);
            String sec = Integer.toString(second);
            String m = Integer.toString(minute);
            String h = Integer.toString(hour);
            //count = count + 1;
            String addedstring = "strength : " + s + "    at time : " + h + ":" + m+":"+sec;
            //id[count] = addedstring;
            //count = count + 1;
            location = (GsmCellLocation) tel.getCellLocation();
            int c = location.getCid();


            //Toast.makeText(getApplicationContext(), " GSM signal Strength = "
              //      + String.valueOf(signalStrength.getGsmSignalStrength())
                //    + "cell id is : " + cellid, Toast.LENGTH_SHORT).show();

            addedstring = addedstring + "      cell_id: " +String.valueOf(c);
            if(cellid != c)
            {
                id[count] = addedstring;
                count = count + 1;
                cellid = c;
                item = new items();
                item.get_cid= String.valueOf(c);
                item.get_lac = String.valueOf(location.getLac());
                item.get_strength = String.valueOf(signalStrength.getGsmSignalStrength());
                item.get_time =  h + ":" + m+":"+sec;
                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();

               // txt.setText(gson.toJson(item).toString());

            }

            String x = "";
            Strength = "";

            for(int i=0;i<count;i++)
            {
                x = x + "\n";
                x = x + id[i];
                Strength = Strength + "\n";
                Strength = Strength + id[i];
        //        Strength = Strength + "\n";
            }
            //Toast.makeText(getApplicationContext(), x, Toast.LENGTH_SHORT).show();
            TextView txt1 = (TextView) findViewById(R.id.list);
            txt1.setText(x);

            //gives the signal strength from the service provider whenever the it is updated..
        }
    }


    class items
    {
        public String get_cid ;
        public String get_lac;
        public String get_strength;
        public String get_time;
    }

}
