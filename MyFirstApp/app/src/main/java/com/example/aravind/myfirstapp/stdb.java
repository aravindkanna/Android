package com.example.aravind.myfirstapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class stdb extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stdb);


        TextView tv = (TextView) findViewById(R.id.stview);

        String data;
        db info = new db(this);
        info.open();
        data = info.getdata();
        info.close();

        tv.setText(data);
    }

}
