package com.example.aravind.myfirstapp;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class practice extends ActionBarActivity {

    EditText name, age, id;
    String sname, sage, sid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);
        name = (EditText) findViewById(R.id.name);
        age = (EditText) findViewById(R.id.age);
        id = (EditText) findViewById(R.id.rowid);
    }

    public void viewdb(View view)
    {
        Intent intent = new Intent(this, viewdb.class);
        startActivity(intent);
    }

    public void update(View view)
    {
        boolean diditwork = true;
        try {
            sname = name.getText().toString();
            sage = age.getText().toString();
            hai entry = new hai(practice.this);
            entry.open();

            entry.createentry(sname, sage);


            entry.close();
        }

        catch (Exception e)
        {
            diditwork = false;
            String error = e.toString();
            Dialog d = new Dialog(this);
            d.setTitle("Sorry there is some fault");
            TextView tv = new TextView(this);
            tv.setText(error);
            d.setContentView(tv);
            d.show();

        }

        finally
        {
            Dialog d = new Dialog(this);
            d.setTitle("Yeah it is inserted !!!!!");
            TextView tv = new TextView(this);
            tv.setText("Successfull.. !!!!");
            d.setContentView(tv);
            d.show();
        }
    }

    public void get(View view)
    {
        //to get the information of a particular row when its id is given.
        sid = id.getText().toString();
    }

    public void edit(View view)
    {
        //to edit the value

    }

    public void delete(View view)
    {
        //to delete a particular row.
        sid = id.getText().toString();
        long lrow = Long.parseLong(sid);

        hai h = new hai(this);
        h.open();

        h.deleteentry(lrow);

        h.close();
    }
}
