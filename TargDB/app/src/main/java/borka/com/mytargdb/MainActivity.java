package borka.com.mytargdb;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button btInsert,btClear,btShowALL, btShowByID;
    EditText edID;
    TextView tvScore,tvDate,tvName;
    DBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DBHandler(this);

        btClear= (Button) findViewById(R.id.buttClear);
        btInsert= (Button) findViewById(R.id.buttInsert);
        btShowALL= (Button) findViewById(R.id.buttShowAll);
        btShowByID= (Button) findViewById(R.id.buttShowOne);

        tvDate = (TextView) findViewById(R.id.textViewName);
        tvName = (TextView) findViewById(R.id.textViewDate);
        tvScore = (TextView) findViewById(R.id.textViewScore);
        edID = (EditText) findViewById(R.id.editTextID);

        btInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Inserting Shop/Rows
                Log.d("Insert: ", "Inserting ..");
                db.addPlayer(new Player(1,"Gil",  "2017-01-08", 26));
                db.addPlayer(new Player(2,"Boris"," 2017-03-11",15));
                db.addPlayer(new Player(3,"Yossi", " 2017-05-28",10));
                db.addPlayer(new Player(4,"Vera", " 2017-06-18", 34));

            }
        });

        btShowALL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Reading all shops
                Log.d("Reading: ", "Reading all shops..");
                List<Player> players = db.getAllPlayers();

                for (Player curr : players) {
                    String log = "Id: " + curr.getId() + " ,Name: " + curr.getName() + " ,Date: " + curr.getDate()+ " , Score: " + curr.getScore();
                    // Writing shops  to log
                    Log.d("Player: : ", log);
                }
            }
        });

        btShowByID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            int id = Integer.parseInt(edID.getText().toString());
              Player playerGet = new Player();

                playerGet= db.getPLayer(id);

                tvName.setText(playerGet.getName());
                tvDate.setText(playerGet.getDate());
                tvScore.setText(""+playerGet.getScore());
            }
        });

//        btClear.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Inserting Shop/Rows
//                db.onUpgrade("TABLE_PLAYERS",1,2);
//                Log.d("Delete: ", "Deleting ..");
//
//            }
//        });
    }
}
