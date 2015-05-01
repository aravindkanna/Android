package com.example.aravind.myfirstapp;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.NeighboringCellInfo;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.os.*;
import android.net.*;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class displaystrength extends ActionBarActivity {

    GsmCellLocation location;
    int sig, cellID,c,s,l,count = 0;
    //String array = new Array
    String[] list = new String[10000];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displaystrength);
        TelephonyManager manager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        location = (GsmCellLocation) manager.getCellLocation();
        //NeighboringCellInfo n = manager.getNeighboringCellInfo();
        //sig = n.getRssi();
        cellID = location.getCid();
        List<NeighboringCellInfo> neighbors = manager.getNeighboringCellInfo();
        for (NeighboringCellInfo n : neighbors) {
            s = n.getRssi();
            c = n.getCid();
            l = n.getLac();


            //primary scrambling code.
            String signal = Integer.toString(s);
            String cellid = Integer.toString(c);
            String lac = Integer.toString(l);


            list[count] = "sig: "+signal+"CellID: " +cellid+"Lac: " +lac;
            count++;
            Toast.makeText(this, list[count], Toast.LENGTH_SHORT).show();

            if(c==cellID)
            {
                sig = s;
            }

            /*String strength = Integer.toString(sig);
            TextView txt = (TextView)findViewById(R.id.strengthview);
            txt.append(strength);
            txt.append("\n");*/
        }
        String cnt = Integer.toString(count);
        String x = "hai" + cnt;
        for(int i = 0;i<count;i++)
        {
            //appends list elements to the string x
            x = x + "\n";
            x = x + list[i];
        }
        //Toast.makeText(this, x, Toast.LENGTH_SHORT).show();
        TextView txt2 = (TextView)findViewById(R.id.list);
        txt2.setText(x);

        String strength = Integer.toString(sig);
        TextView txt = (TextView)findViewById(R.id.strengthview);

        if (sig == 0)
        {
            txt.setText("less than or equal to -113dBm");
        }
        else
        {
            txt.setText(strength + " asu");
        }

        String c = Integer.toString(cellID);
        TextView txt1 = (TextView)findViewById(R.id.cid);
        txt1.setText(c);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_displaystrength, menu);
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
