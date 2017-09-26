package com.borka.targilaugust;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Comp14 on 08/08/17.
 */

public class DBHandler  extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "Workersdb";
    // Player table name
    private static final String TABLE_WORKERS = "workers";
    // Players Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_USERNAME = "uname";
    private static final String KEY_PASSWORDE = "password";
    private static final String KEY_INTIME = "intime";
    private static final String KEY_OUTTIME = "outtime";
    private static final String KEY_APPLOCATION = "applocation";
    private static final String KEY_USERLOCATION = "userlocation";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_WORKERS + "("
                + KEY_ID + " integer primary key autoincrement," + KEY_USERNAME + " TEXT," + KEY_PASSWORDE + " TEXT,"
                + KEY_INTIME + " TEXT," + KEY_OUTTIME + " TEXT, " + KEY_APPLOCATION + " TEXT, "
                + KEY_USERLOCATION + " TEXT" + ")";
        Log.i("table start",CREATE_CONTACTS_TABLE);

        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WORKERS);
        // Creating tables again
        onCreate(db);

    }

    // Adding new Player
    public void addWorker(Worker worker) {
        SQLiteDatabase db = this.getWritableDatabase();

        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, worker.getWname());
        values.put(KEY_PASSWORDE, worker.getPassword());
        values.put(KEY_INTIME,worker.getIntime());
        values.put(KEY_OUTTIME,worker.getOuttime());
        values.put(KEY_APPLOCATION,worker.getApplocation());
        values.put(KEY_USERLOCATION,worker.getUserlocation());

        // Inserting Row
        db.insert(TABLE_WORKERS, null, values);
        db.close(); // Closing database connection
    }

    public Worker getWorker(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_WORKERS, new String[]{KEY_ID,
                        KEY_USERNAME,KEY_PASSWORDE, KEY_INTIME,KEY_OUTTIME,KEY_APPLOCATION,KEY_USERLOCATION}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();


        Worker contact = new Worker();
        // Worker contact = new Worker(Integer.parseInt(cursor.getString(0)),cursor.getString(1), cursor.getString(2));

        return contact;
    }

    public String getWorkerPass(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_WORKERS, new String[]{KEY_PASSWORDE,}, KEY_USERNAME + "=?",
                new String[]{username}, null, null, null, null);
        if (cursor != null&& cursor.moveToFirst()) {
            cursor.moveToFirst();
            String pass = cursor.getString(0);
            return pass;
        } else
            return "";
    }

//    public String getplayername(String username) {
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        Cursor cursor = db.query(TABLE_PLAYERS, new String[]{KEY_NAME,}, KEY_USER_NAME + "=?",
//                new String[]{username}, null, null, null, null);
//        if (cursor != null&& cursor.moveToFirst()) {
//            cursor.moveToFirst();
//            String musername = cursor.getString(0);
//            return musername;
//        } else
//            return "";
//
//    }
    public void setWorkerEnter (Worker worker)
    {

    }

    public void setWorkerExit (Worker worker)
    {

    }
}
