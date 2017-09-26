package borka.com.gameanddb;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "playersInfo";
    private static final String TABLE_PLAYERS = "players";
    // s Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_FAMILY = "family";
    private static final String KEY_USER = "user";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_DATE = "date";
    private static final String KEY_SCORE = "score";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYERS);
        onCreate(db);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_PLAYERS + "("
                + KEY_ID +  " integer primary key autoincrement," + KEY_NAME + " TEXT,"+
        KEY_FAMILY + " TEXT,"+KEY_USER + " TEXT,"+KEY_PASSWORD + " TEXT,"
                + KEY_DATE + " TEXT," + KEY_SCORE + " INTEGER"+ ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    //@Override


    public void addPlayer(Player player) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
       // values.put(KEY_ID, player.getId()); //  ID
        values.put(KEY_NAME, player.getName()); //  Name
        values.put(KEY_DATE, player.getDate()); //  Phone Number
        values.put(KEY_SCORE, player.getScore()); //  Score

        // Inserting Row
        db.insert(TABLE_PLAYERS, null, values);
        db.close(); // Closing database connection
    }
    public Player getPLayer(String name) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PLAYERS, new String[]{KEY_ID,KEY_NAME,KEY_FAMILY,KEY_USER,KEY_PASSWORD,KEY_DATE,KEY_SCORE}, KEY_NAME + "=?",
                new String[]{String.valueOf(name)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Player person = new Player(cursor.getInt(0),cursor.getString(1), cursor.getString(2), cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getInt(6));
        // return 
        return person;
    }
    public List<Player> getAllPlayers() {
        List<Player> playerList = new ArrayList<Player>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PLAYERS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Player player = new Player();
                player.setId(cursor.getInt(0));
                player.setName(cursor.getString(1));
                player.setFamily(cursor.getString(2));
                player.setUser(cursor.getString(3));
                player.setPassword(cursor.getString(4));
                 player.setDate(cursor.getString(5));
                player.setScore(cursor.getInt(6));
                // Adding contact to list
                playerList.add(player);
            } while (cursor.moveToNext());
        }

        // return contact list
        return playerList;
    }

    public void deletePlayer(Player Player) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PLAYERS, KEY_ID + " = ?",
                new String[] { String.valueOf(Player.getId()) });

        db.close();
    }
}
