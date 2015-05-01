package com.example.aravind.myfirstapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class viewdb extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewdb);
        TextView tv = (TextView) findViewById(R.id.viewdb);
        hai info = new hai(this);
        info.open();

        String data = info.getdata();
        info.close();

        tv.setText(data);
    }
}
