package angela.example.parkingmacedonia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

public class MyReservations extends AppCompatActivity {

    RecyclerView myRecyclerView;
    String userName;
    Database database;
    String parking_lots_names [], reservation_dates[], reservations_time_slots[], citiesForUser[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_reservations);

        myRecyclerView = findViewById(R.id.recyclerViewReservations);

        getData();


        database = new Database(this, null, null, 2);
        parking_lots_names = database.getParkingLotsNamesForUser(userName);



        reservation_dates = database.getReservationDatesForUser(userName);

        reservations_time_slots = database.getReservationTimeSlotsForUser(userName);

        citiesForUser = database.getCitiesForUser(userName);




        MyReservationsAdapter adapterce = new MyReservationsAdapter(this, userName, parking_lots_names, reservation_dates, reservations_time_slots, citiesForUser);
        myRecyclerView.setAdapter(adapterce);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));


    }


    private void getData ()
    {
        if (getIntent().hasExtra("userName"))
        {
            userName = getIntent().getStringExtra("userName");

        }
        else {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
    }
}