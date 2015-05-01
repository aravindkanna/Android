package com.example.aravind.myfirstapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        items item = new items();
        item.cell_id= 23;
        item.lac = 24;
        item.strength=25;
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        //Toast.makeText(this,gson.toJson(item).toString(),
          //      Toast.LENGTH_LONG).show();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void giveoperator(View view)
    {
        //should return the operator name.
        Intent intent = new Intent(this, displayoperator.class);
        startActivity(intent);
    }

    public void givecellid(View view)
    {
        //should return the cell id now the mobile is using.
        Intent intent = new Intent(this, displaycellid.class);
        startActivity(intent);
    }

    public void givesignalstrength(View view)
    {
        //should return the signal strength of the cell tower
        Intent intent = new Intent(this, displaystrength.class);
        startActivity(intent);
    }

    public void start(View view)
    {
        //should start timer.
        Intent intent = new Intent(this, startpop.class);
        startActivity(intent);
    }

    public void other(View view)
    {
        //just for practice
        //Alarm manager
        Intent intent = new Intent(this, alarm.class);
        startActivity(intent);
    }

    public void strength(View view)
    {
        Intent intent = new Intent(this, sigstrength.class);
        startActivity(intent);
    }

    public void cellid(View view)
    {
        //prints the cellid only when it is changed.
        Intent intent = new Intent(this, cellid.class);
        startActivity(intent);
    }

    public void practice(View view)
    {
        //just for practice
        Intent intent = new Intent(this, practice.class);
        startActivity(intent);
    }

    public void location(View view)
    {
        //should give the present latitude and longitude
        Intent intent = new Intent(this, location.class);
        startActivity(intent);
    }

    class items
    {
        public int cell_id;
        public int lac;
        public int strength;
    }
}
