package angela.example.parkingmacedonia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ReservationConfirmation extends AppCompatActivity {

    TextView click, parking_name;
    String parkingName, userName;
    Database database;
    float coordinates [];

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_confirmation);


        click = findViewById(R.id.textView3);
        parking_name = findViewById(R.id.parkingName);

        getData();
        setData();

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                database = new Database(ReservationConfirmation.this, null, null, 2);
                coordinates = database.getCoordinates(parkingName);

                String location = "google.navigation:q="+coordinates[0]+","+coordinates[1] + "&mode=1";

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(location));
                intent.setPackage("com.google.android.app.maps");

                if (intent.resolveActivity(getPackageManager())!=null) {
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