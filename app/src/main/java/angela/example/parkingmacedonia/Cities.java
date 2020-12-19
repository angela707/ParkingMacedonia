package angela.example.parkingmacedonia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Cities extends AppCompatActivity {

    RecyclerView recyclerView;
    String nm[],  sc[];
    int pl[];
    int images[]; //{R.drawable.sk, R.drawable.oh, R.drawable.bt, R.drawable.te, R.drawable.su};
    Database database;
    String userName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities);

        if (getIntent().hasExtra("userName"))
        {
            userName = getIntent().getStringExtra("userName");

        }
        else {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }

        recyclerView = findViewById(R.id.recyclerView);

        database = new Database(this, null, null, 2);

        nm = database.getCities();
        sc = database.getShortcuts();
        pl = database.getNumParkingLots();
        images = database.getCityImages();



        MyAdapter myAdapter = new MyAdapter(Cities.this, nm, sc, pl, images, userName);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



    }
}