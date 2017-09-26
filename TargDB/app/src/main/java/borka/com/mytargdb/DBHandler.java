package borka.com.mytargdb;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 8;
    private static final String DATABASE_NAME = "playerInfo";
    private static final String TABLE_PLAYER = "player";
    // s Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_DATE = "date";
    private static final String KEY_SCORE = "score";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYER);
        onCreate(db);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_PLAYER + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
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
        db.insert(TABLE_PLAYER, null, values);
        db.close(); // Closing database connection
    }
    public Player getPLayer(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PLAYER, new String[]{KEY_ID,KEY_NAME,KEY_DATE,KEY_SCORE}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Player person = new Player(cursor.getInt(0),cursor.getString(1), cursor.getString(2), cursor.getInt(3));
        // return 
        return person;
    }
    public List<Player> getAllPlayers() {
        List<Player> playerList = new ArrayList<Player>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PLAYER;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Player player = new Player();
                player.setId(cursor.getInt(0));
                player.setName(cursor.getString(1));
                 player.setDate(cursor.getString(2));
                player.setScore(cursor.getInt(3));
                // Adding contact to list
                playerList.add(player);
            } while (cursor.moveToNext());
        }

        // return contact list
        return playerList;
    }

    public void deletePlayer(Player Player) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PLAYER, KEY_ID + " = ?",
                new String[] { String.valueOf(Player.getId()) });

        db.close();
    }
}
