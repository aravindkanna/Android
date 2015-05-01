package com.example.aravind.myfirstapp;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;


public class displaycellid extends ActionBarActivity {


    GsmCellLocation location;
    int cellID, lac;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displaycellid);
        TelephonyManager manager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        location = (GsmCellLocation) manager.getCellLocation();
        cellID = location.getCid();
        //String cid = String.valueOf(cellID);
        String cid = Integer.toString(cellID);
        TextView txt = (TextView)findViewById(R.id.cellidview);
        //String cid = "12345";
        txt.setText("Your cell tower id is "+cid);

        lac = location.getLac();
        String lacode = Integer.toString(lac);
        TextView txt1 = (TextView)findViewById(R.id.lacview);
        txt1.setText("Your land area code is "+lacode);



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_displaycellid, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
