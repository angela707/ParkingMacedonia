package angela.example.parkingmacedonia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class ParkingPlaces extends AppCompatActivity {

    RecyclerView myRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_places);


        myRecyclerView = findViewById(R.id.recyclerViewCities);

        MyParkingAdapter adapterce = new MyParkingAdapter(this);
        myRecyclerView.setAdapter(adapterce);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}