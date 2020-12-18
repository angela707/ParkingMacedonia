package angela.example.parkingmacedonia;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class ReservationForm extends AppCompatActivity {

    TextView date;
    DatePickerDialog.OnDateSetListener onDateSetListener;

    Button reserve;

    TextView cityName;

    String name;
    int parknum;

    int year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_form);

        date = findViewById(R.id.date);
        reserve = findViewById(R.id.reserve);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                year = cal.get(Calendar.YEAR);
                month = cal.get(Calendar.MONTH);
                day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        ReservationForm.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        onDateSetListener,
                        year, month, day);

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month+1;
                String fullDate = dayOfMonth + "/" + month + "/" + year;
                date.setText(fullDate);
            }
        };

        reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReservationForm.this, ParkingPlaces.class);
                intent.putExtra("day", day);
                intent.putExtra("month", month);
                intent.putExtra("year", year);
                intent.putExtra("cityName", name);
                intent.putExtra("parkingLots", parknum);
                startActivity(intent);
            }
        });


        cityName = findViewById(R.id.cityname);

        getData();
        setData();




    }

    private void getData ()
    {
        if (getIntent().hasExtra("name")) // && getIntent().hasExtra("parking_lots"))
        {
            name = getIntent().getStringExtra("name");
            parknum = getIntent().getIntExtra("parking_lots", 1);
        }
        else {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
    }

    private void setData ()
    {
        cityName.setText(name);
    }
}