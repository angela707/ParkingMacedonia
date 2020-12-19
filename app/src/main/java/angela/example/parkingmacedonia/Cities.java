package angela.example.parkingmacedonia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

public class Cities extends AppCompatActivity {

    RecyclerView recyclerView;
    String nm[],  sc[];
    int pl[];
    int images[]; //{R.drawable.sk, R.drawable.oh, R.drawable.bt, R.drawable.te, R.drawable.su};
    Database database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities);

        recyclerView = findViewById(R.id.recyclerView);

        database = new Database(this, null, null, 2);

        nm = database.getCities();
        sc = database.getShortcuts();
        pl = database.getParkingLots();
        images = database.getCityImages();



        MyAdapter myAdapter = new MyAdapter(Cities.this, nm, sc, pl, images);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



    }
}