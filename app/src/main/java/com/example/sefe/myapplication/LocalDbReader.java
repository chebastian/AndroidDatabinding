package com.example.sefe.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.compat.BuildConfig;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by sefe on 2017-01-31.
 */

public class LocalDbReader extends SQLiteOpenHelper {

    private final static String TAG = "DB";
    private static String DB_NAME = "test.db";
    private String DB_PATH = "/data/data/" + BuildConfig.APPLICATION_ID + "/databases/";
    private SQLiteDatabase mDb;
    private final Context _ctx;

    public LocalDbReader(Context ctx)
    {
        super(ctx,DB_NAME,null,1);
        _ctx = ctx;
    }

    public void createDataBase() throws IOException
    {
        boolean dbExists = checkExistsDb();
        if(dbExists)
        {
            openDatabase();
            return;
        }
        else
        {
            this.getReadableDatabase();
            try{
                copyDataBase();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    private void openDatabase() {
        mDb = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null,SQLiteDatabase.OPEN_READONLY);
    }


    private SQLiteDatabase openDB() throws SQLiteException
    {
        return  SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.OPEN_READONLY);
    }

    public boolean checkExistsDb() {
        SQLiteDatabase db = null;
        try{
            db = openDB();
        }
        catch (SQLiteException e)
        {
            Log.d(TAG, "ERROR LOADING DB");
            Log.d(TAG, e.getMessage());
        }

        if(db != null)
            db.close();

        return db != null;
    }

     private void copyDataBase() throws IOException{

    	//Open your local db as the input stream
         DB_PATH = _ctx.getApplicationContext().getDataDir() + "/databases/";
    	InputStream myInput = _ctx.getAssets().open(DB_NAME);

    	// Path to the just created empty db
    	String outFileName = DB_PATH + DB_NAME;

    	//Open the empty db as the output stream
    	OutputStream myOutput = new FileOutputStream(outFileName);

    	//transfer bytes from the inputfile to the outputfile
    	byte[] buffer = new byte[1024];
    	int length;
    	while ((length = myInput.read(buffer))>0) {
            myOutput.write(buffer, 0, length);
        }

    	//Close the streams
    	myOutput.flush();
    	myOutput.close();
    	myInput.close();

    }

    public Cursor runQuery(String query)
    {
        if(mDb == null)
        {
            openDatabase();
        }

        Cursor c = mDb.rawQuery(query,null);

        return c;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
