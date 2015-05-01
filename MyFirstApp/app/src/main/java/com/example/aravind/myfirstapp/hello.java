package com.example.aravind.myfirstapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by aravind on 22/12/14.
 */
public class hello {

    public static final String KEY_ID = "_id";
    public static final String KEY_STRENGTH = "Strength";


    private static final String DB_NAME = "Strength_DataBase";
    private static final String DB_TABLE = "Strength_Table";
    private static final int DB_VERSION = 1;

    private sdbhelper sourhelper;
    private final Context sourcontext;
    private SQLiteDatabase sourdatabase;



    private static class sdbhelper extends SQLiteOpenHelper
    {
        public sdbhelper(Context scontext)
        {
            super(scontext, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {
            db.execSQL(

                    "CREATE TABLE "+ DB_TABLE + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                            + KEY_STRENGTH + " TEXT NOT NULL );"
            );


        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion)
        {
            db.execSQL("DROP TABLE IF EXISTS " + DB_NAME);
            onCreate(db);
        }
    }

    public hello(Context c)
    {
        sourcontext = c;
        // sourcontext = c;
    }

    public hello sopen(){

        sourhelper = new sdbhelper(sourcontext);
        sourdatabase = sourhelper.getWritableDatabase();
        return this;
    }

    public void sclose()
    {
        sourhelper.close();
    }

    public long screateentry(String strength) {

        ContentValues cv = new ContentValues();
        cv.put(KEY_STRENGTH, strength);
        return sourdatabase.insert(DB_TABLE, null, cv);
    }


    public String sgetdata() {
        String[] columns = new String[]{KEY_ID, KEY_STRENGTH};
        Cursor c = sourdatabase.query(DB_TABLE, columns, null, null, null, null, null);
        String result = "";
        int irow = c.getColumnIndex(KEY_ID);
        int istrength = c.getColumnIndex(KEY_STRENGTH);
        //int iage = c.getColumnIndex(KEY_AGE);

        for(c.moveToFirst(); !c.isAfterLast();c.moveToNext())
        {
            result = result + c.getString(irow)+ " " + c.getString(istrength)  + "\n";

        }

        return result;
    }


}
