
/**
 * Created by aravind on 28/12/14.
 */


package com.example.aravind.myfirstapp;

        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
        import android.widget.Toast;

        import java.sql.SQLException;

/**
 * Created by aravind on 21/12/14.
 */
public class db {

    public static final String KEY_ROWID = "_id";
    public static final String KEY_NAME = "all_data";
    //public static final String KEY_AGE = "persons_age";

    private static final String DATABASE_NAME = "database";
    private static final String DATABASE_TABLE = "peopletable";
    private static final int DATABASE_VERSION = 1;

    private dbhelper ourhelper;
    private final Context ourcontext;
    private SQLiteDatabase ourdatabase;




    private static class dbhelper extends SQLiteOpenHelper
    {
        public dbhelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {
            db.execSQL(

                    "CREATE TABLE "+ DATABASE_TABLE + "(" + KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                            + KEY_NAME + " TEXT NOT NULL);"
            );


        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion)
        {
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME);
            onCreate(db);
        }
    }

    public db(Context c)
    {
        ourcontext = c;

    }

    public db open() throws android.database.SQLException
    {
        ourhelper = new dbhelper(ourcontext);
        ourdatabase = ourhelper.getWritableDatabase();
        return this;
    }



    public void close()
    {
        ourhelper.close();
    }



    public long createentry(String sname) {
        //Toast.makeText(this, Strength, Toast.LENGTH_LONG).show();
        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME, sname);
        //cv.put(KEY_AGE, sage);
        return ourdatabase.insert(DATABASE_TABLE, null, cv);
    }


    public String getdata() {
        String[] columns = new String[]{KEY_ROWID, KEY_NAME};
        Cursor c = ourdatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);
        String result = "";
        int irow = c.getColumnIndex(KEY_ROWID);
        int iname = c.getColumnIndex(KEY_NAME);


        for(c.moveToFirst(); !c.isAfterLast();c.moveToNext())
        {
            result = result + "Id: "+c.getString(irow)+ "\n" + c.getString(iname)  + "\n\n\n\n\n";

        }

        return result;
    }

    public void deleteentry(long lrow)
    {
        ourdatabase.delete(DATABASE_TABLE, KEY_ROWID + "=" + lrow, null);

    }


}
