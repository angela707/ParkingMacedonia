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
    String name, date, time_slot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_places);

        myRecyclerView = findViewById(R.id.recyclerViewCities);

        MyParkingAdapter adapterce = new MyParkingAdapter(this);
        myRecyclerView.setAdapter(adapterce);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        
        nameCity = findViewById(R.id.nameText);
        dateText = findViewById(R.id.dateText);
        time_slot_Text = findViewById(R.id.time_slotText);



        getData();
        setData();

    }



    private void getData ()
    {
        if (getIntent().hasExtra("cityName") && getIntent().hasExtra("date") && getIntent().hasExtra("time_slot"))
        {
            name = getIntent().getStringExtra("cityName");
            date = getIntent().getStringExtra("date");
            time_slot = getIntent().getStringExtra("time_slot");

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