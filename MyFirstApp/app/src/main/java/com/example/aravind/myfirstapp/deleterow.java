package com.example.aravind.myfirstapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class deleterow extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deleterow);
    }


    public void delete(View view)
    {
        //deletes the row with the given id
        EditText et = (EditText) findViewById(R.id.rowtext);
        String row_text = et.getText().toString();

        long lrow1 = Long.parseLong(row_text);

        db del = new db(this);

        del.open();
        del.deleteentry(lrow1);
        del.close();

        Toast.makeText(getApplicationContext(), "The requested row is deleted successfully...!!", Toast.LENGTH_SHORT).show();
    }
}
