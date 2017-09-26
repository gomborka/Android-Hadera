package borka.com.dbexample;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import java.util.List;
public class MainActivity extends ActionBarActivity {
    Button btSaveToDB,btGetFromDB;
    DBHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DBHandler(this);
//execSQL("DROP TABLE IF EXISTS shops")
        btSaveToDB=(Button)findViewById(R.id.btinsert);
        btGetFromDB=(Button)findViewById(R.id.btgetall);
        btSaveToDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
// Inserting Shop/Rows
                Log.d("Insert: ", "Inserting ..");
                db.addShop(new Shop("Gil", " Tiberias Israel"));
                db.addShop(new Shop("Gil2", " Rishon Israel"));
                db.addShop(new Shop("Gil3", " Hifa Israel"));
                db.addShop(new Shop("Gil4", " Galil 12 Israel"));
            }
        });
        btGetFromDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
// Reading all shops
                Log.d("Reading: ", "Reading all shops..");
                List<Shop> shops = db.getAllShops();
                for (Shop shop : shops) {
                    String log = "Id: " + shop.getId() + " ,Name: " + shop.getName() + " ,Address: " +
                            shop.getAddress();
// Writing shops to log
                    Log.d("Shop: : ", log);
                }
            }
        });
    }
}