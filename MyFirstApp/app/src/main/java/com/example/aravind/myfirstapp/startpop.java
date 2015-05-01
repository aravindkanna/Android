package com.example.aravind.myfirstapp;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;


public class startpop extends ActionBarActivity {


    GsmCellLocation location;
    int cellID;
    int flag=0;
    String[] id = new String[10000];
    static ArrayList<String> ids  = new ArrayList<String>();
    static int count=0;
    //int LENGTH_SHORT  = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startpop);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_startpop, menu);
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

    public void startmsg(View view)
    {
        //should pop up a message.
        flag = 1;
        String f = Integer.toString(flag);
        //Toast.makeText(this, f, Toast.LENGTH_SHORT).show();
        /*while(flag==1)
        {
            //toast the cell id.
            Toast.makeText(this, "tracing", Toast.LENGTH_SHORT).show();
        }*/
        TelephonyManager manager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        location = (GsmCellLocation) manager.getCellLocation();
        cellID = location.getCid();
        //String cid = String.valueOf(cellID);
        String cid = Integer.toString(cellID);
        Calendar cal = Calendar.getInstance();
        int second = cal.get(Calendar.SECOND);
        int minute = cal.get(Calendar.MINUTE);
        int hour = cal.get(Calendar.HOUR);
        String s = Integer.toString(second);
        String m = Integer.toString(minute);
        String h = Integer.toString(hour);
        Toast.makeText(this, "cell id: " + cid + " at time: "+h+":"+m+":"+s , Toast.LENGTH_SHORT).show();

        ids.add(cid + " " +h+ ":"+m+":"+s);
        id[count] = cid + " " +h+ ":" +m+ ":" +s;
        count = count + 1;
        int noOfElement = ids.size();
        String no = Integer.toString(noOfElement);
        Toast.makeText(this, no, Toast.LENGTH_SHORT).show();
        /*while(flag==1) {
            if (cal.get(Calendar.MINUTE) == (minute + 1)) {
                minute = minute + 1;
                Toast.makeText(this, "cell id: " + cid + " at time: " + h + ":" + m + ":" + s, Toast.LENGTH_LONG).show();
            }
        }*/
        /*String x = id[0];
        for(int i=1;i<count;i++)
        {
            x = x + "\n";
            x = x + id[i];
        }
        Toast.makeText(this, x, Toast.LENGTH_SHORT).show();*/
    }

    public void stopmsg(View view)
    {
        //should pop up a stop message.
        flag = 0;
        String f = Integer.toString(flag);
        Toast.makeText(this, f, Toast.LENGTH_SHORT).show();
    }

    public void dispids(View view)
    {
        //should display all ids till now.
        /*String[] idarray = (String[]) ids.toArray();
        String x = idarray[0];
        /*for(int i=1;i<idarray.length;i++)
        {
            x = x + " ";
            x = x + idarray[i];
        }*/
        String x = id[0];
        for(int i=1;i<count;i++)
        {
            x = x + "\n";
            x = x + id[i];
        }
        Toast.makeText(this, x, Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, "hai", Toast.LENGTH_SHORT).show();
    }
}
