package angela.example.parkingmacedonia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ReservationConfirmation extends AppCompatActivity {

    TextView parking_name, pomos;
    Button click;
    String parkingName, userName;
    Database database;
    String latitude[], longitude[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_confirmation);


        click = findViewById(R.id.clickable);
        parking_name = findViewById(R.id.parkingName);


        getData();
        setData();

        database = new Database(this, null, null, 2);



        latitude = database.getLatitude(parkingName);
        longitude = database.getLongitude(parkingName);


        String location = "google.navigation:q="+latitude[0]+","+longitude[0];

        //pomos.setText(location);



        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent (Intent.ACTION_VIEW, Uri.parse(location));

                intent.setPackage("com.google.android.apps.maps");

                if (intent.resolveActivity(getPackageManager()) != null){
                    startActivity(intent);
                }

            }
        });




    }

    private void setData() {

        parking_name.setText(parkingName);
    }

    private void getData() {

        if (getIntent().hasExtra("username") && getIntent().hasExtra("parkingName"))
        {
            userName = getIntent().getStringExtra("username");
            parkingName = getIntent().getStringExtra("parkingName");

        }
        else {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
    }
}