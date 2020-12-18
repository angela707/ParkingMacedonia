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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities);

        recyclerView = findViewById(R.id.recyclerView);

        nm = getResources().getStringArray(R.array.cities_names);
        sc = getResources().getStringArray(R.array.cities_shortcuts);
        pl = getResources().getIntArray(R.array.parking_lots);


        MyAdapter myAdapter = new MyAdapter(Cities.this, nm, sc, pl);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



    }
}