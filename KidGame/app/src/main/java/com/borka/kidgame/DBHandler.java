package com.borka.kidgame;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "Playersdb";
    // Player table name
    private static final String TABLE_PLAYERS = "players";
    // Players Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "fname";
    private static final String KEY_LAST_NAME = "lname";
    private static final String KEY_USER_NAME = "username";
    private static final String KEY_PASSWORDE = "password";
    private static final String KEY_Score = "score";
    private static final String KEY_Date = "date";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_PLAYERS + "("
                + KEY_ID + " integer primary key autoincrement," + KEY_NAME + " TEXT,"
                + KEY_LAST_NAME + " TEXT," + KEY_USER_NAME + " TEXT, " + KEY_PASSWORDE + " TEXT, "
                + KEY_Score + " integer," + KEY_Date + " TEXT" + ")";
        Log.i("table start",CREATE_CONTACTS_TABLE);

        db.execSQL(CREATE_CONTACTS_TABLE);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYERS);
        // Creating tables again
        onCreate(db);
    }


    // Adding new Player
    public void addplayer(Player player) {
        SQLiteDatabase db = this.getWritableDatabase();

        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        ContentValues values = new ContentValues();
        values.put(KEY_Date, date);
        values.put(KEY_PASSWORDE, player.getPassword());
        values.put(KEY_Score, 0);
        values.put(KEY_USER_NAME, player.getUsername());
        values.put(KEY_NAME, player.getFname());
        values.put(KEY_LAST_NAME, player.getLname());

        // Inserting Row
        db.insert(TABLE_PLAYERS, null, values);
        db.close(); // Closing database connection
    }



    public Player getplayer(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PLAYERS, new String[]{KEY_ID,
                        KEY_NAME, KEY_LAST_NAME}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();


        Player contact = new Player();
        // Player contact = new Player(Integer.parseInt(cursor.getString(0)),cursor.getString(1), cursor.getString(2));
        // return shop
        return contact;
    }// not using this time

    //get the password to check login
    public String getplayerPass(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_PLAYERS, new String[]{KEY_PASSWORDE,}, KEY_USER_NAME + "=?",
                new String[]{username}, null, null, null, null);
        if (cursor != null&& cursor.moveToFirst()) {
            cursor.moveToFirst();
            String pass = cursor.getString(0);
            return pass;
        } else
            return "";
    }


    public String getplayername(String username) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PLAYERS, new String[]{KEY_NAME,}, KEY_USER_NAME + "=?",
                new String[]{username}, null, null, null, null);
        if (cursor != null&& cursor.moveToFirst()) {
            cursor.moveToFirst();
            String musername = cursor.getString(0);
            return musername;
        } else
            return "";

    }
    public String getplayerScore(String username) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PLAYERS, new String[]{KEY_Score,}, KEY_USER_NAME + "=?",
                new String[]{username}, null, null, null, null);
        if (cursor != null&& cursor.moveToFirst()) {
            cursor.moveToFirst();
            String mscore = String.valueOf(  cursor.getInt(0));
            return mscore;
        } else
            return "0";

    }

    public List<Player> getAllTable() {
        List<Player> playerList = new ArrayList<Player>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PLAYERS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Player player = new Player();
                player.setId(Integer.parseInt(cursor.getString(0)));
                player.setFname(cursor.getString(1));
                player.setLname(cursor.getString(2));
                player.setUsername(cursor.getString(3));
                player.setPassword(cursor.getString(4));
                player.setScore(Integer.parseInt(cursor.getString(5)));
                player.setDate(cursor.getString(6));


                //    public Player(int id,String fname,String lname,String username,String password, int score,String Date)
                playerList.add(player);
            } while (cursor.moveToNext());
        }

        return playerList;
    }

    public int getShopsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_PLAYERS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }//not using this time













    public int updatePlayerScorenDate(Player player) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_Date, player.getDate());
        values.put(KEY_Score, player.getScore());

        // updating row
        return db.update(TABLE_PLAYERS, values, KEY_USER_NAME + " = ?",
                new String[]{ player.getUsername()});
    }



    public void deleteShop(Player player) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PLAYERS, KEY_ID + " = ?",
                new String[]{String.valueOf(player.getId())});
        db.close();
    }//not using this time
}




