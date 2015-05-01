package com.example.aravind.myfirstapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class strengthdb extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strengthdb);

        TextView tv = (TextView) findViewById(R.id.strengthdbview);

        String data;
        hello info = new hello(this);
        info.sopen();
        data = info.sgetdata();
        info.sclose();

        tv.setText(data);
    }


}
