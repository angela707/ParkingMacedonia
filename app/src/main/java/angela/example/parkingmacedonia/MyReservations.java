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
    int reservations_city_images [];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_reservations);

        myRecyclerView = findViewById(R.id.recyclerViewReservations);

        getData();
        Toast.makeText(this, "PREVZEDE INTENT", Toast.LENGTH_SHORT).show();

        /*database = new Database(this, null, null, 2);
        parking_lots_names = database.getParkingLotsNamesForUser(userName);
        reservation_dates = database.getReservationDatesForUser(userName);
        reservations_time_slots = database.getReservationTimeSlotsForUser(userName);
        citiesForUser = database.getCitiesForUser(userName);


        reservations_city_images = new int[citiesForUser.length];

        int i;
        for (i=0;  i<citiesForUser.length; i++)
        {
            int img = database.getCityImage(citiesForUser[i]);
            reservations_city_images[i] = img;

        }





        MyReservationsAdapter adapterce = new MyReservationsAdapter(this, userName, parking_lots_names, reservation_dates, reservations_time_slots, reservations_city_images);
        myRecyclerView.setAdapter(adapterce);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    */

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