package angela.example.parkingmacedonia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class ParkingPlaces extends AppCompatActivity {

    RecyclerView myRecyclerView;
    TextView nameCity, dateText, time_slot_Text;
    String name, date, time_slot, userName;
    String parking_lots_names [];
    int num_parking_spots [], num_reserved_spots[];
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_places);

        myRecyclerView = findViewById(R.id.recyclerViewCities);
        nameCity = findViewById(R.id.nameText);
        dateText = findViewById(R.id.dateText);
        time_slot_Text = findViewById(R.id.time_slotText);

        getData();
        setData();



        database = new Database(this, null, null, 2);

        parking_lots_names = database.getParkingLotsNames(name);
        num_parking_spots = database.getNumParkingSpots(name);
        num_reserved_spots = database.getReservedSpots(name, date, time_slot, parking_lots_names);



        MyParkingAdapter adapterce = new MyParkingAdapter(this, name, parking_lots_names, num_parking_spots, num_reserved_spots, date, time_slot, userName);
        myRecyclerView.setAdapter(adapterce);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        






    }


    private void getData ()
    {
        if (getIntent().hasExtra("cityName") && getIntent().hasExtra("date") && getIntent().hasExtra("time_slot") && getIntent().hasExtra("userName"))
        {
            name = getIntent().getStringExtra("cityName");
            date = getIntent().getStringExtra("date");
            time_slot = getIntent().getStringExtra("time_slot");
            userName = getIntent().getStringExtra("userName");

        }
        else {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
    }

    private void setData ()
    {
        nameCity.setText(name);
        dateText.setText(date);
        time_slot_Text.setText(time_slot);
    }
}