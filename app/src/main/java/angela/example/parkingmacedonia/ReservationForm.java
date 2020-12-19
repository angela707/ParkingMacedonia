package angela.example.parkingmacedonia;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class ReservationForm extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    TextView date;
    DatePickerDialog.OnDateSetListener onDateSetListener;
    Button reserve;
    TextView cityName;
    String name;
    String fullDate;
    String time_slot;
    int year, month, day;
    Spinner spinner;
    String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_form);

        date = findViewById(R.id.date);
        reserve = findViewById(R.id.reserve);
        spinner = findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinner_items, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(ReservationForm.this);


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
                fullDate = dayOfMonth + "/" + month + "/" + year;
                date.setText(fullDate);
            }
        };



        cityName = findViewById(R.id.cityname);

        getData();
        setData();


        reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String cityN = cityName.getText().toString();


                Intent intent = new Intent(ReservationForm.this, ParkingPlaces.class);
                intent.putExtra("day", day);
                intent.putExtra("month", month);
                intent.putExtra("year", year);
                intent.putExtra("date", fullDate);
                intent.putExtra("time_slot", time_slot);

                intent.putExtra("cityName", cityN);
                intent.putExtra("userName", userName);

                startActivity(intent);
            }
        });






    }

    private void getData ()
    {
        if (getIntent().hasExtra("name") && getIntent().hasExtra("userName"))
        {
            name = getIntent().getStringExtra("name");
            userName = getIntent().getStringExtra("userName");

        }
        else {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
    }

    private void setData ()
    {
        cityName.setText(name);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        time_slot = parent.getSelectedItem().toString();
        // Toast.makeText(this, parent.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}