package angela.example.parkingmacedonia;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyParkingAdapter extends RecyclerView.Adapter<MyParkingAdapter.MyParkingViewHolder> {


    String parking_lots_names [];
    int parking_lots_spots [], parking_spots_reserved[];
    String cityName, date, time_slot, userName;
    Context context;



    public MyParkingAdapter (Context context, String cityName, String parking_lots_names [], int parking_lots_spots [], int parking_spots_reserved[], String date, String time_slot, String userName)
    {
        this.context = context;
        this.cityName = cityName;
        this.parking_lots_names = parking_lots_names;
        this.parking_lots_spots = parking_lots_spots;
        this.parking_spots_reserved = parking_spots_reserved;
        this.date = date;
        this.time_slot = time_slot;
        this.userName = userName;

    }

    @NonNull
    @Override
    public MyParkingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_parking_row,  parent, false);
        MyParkingViewHolder holder = new MyParkingViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyParkingViewHolder holder, int position) {

        holder.parkingName.setText(parking_lots_names[position]);

        int free;
        free = parking_lots_spots[position] - parking_spots_reserved[position];

        holder.freeSpots.setText(Integer.toString(free));
        holder.takenSpots.setText(Integer.toString(parking_spots_reserved[position]));

        holder.buttonReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String parkingLotName = parking_lots_names[position];



                Database database = new Database(context, null, null, 2);

                boolean success = database.addReservation(userName, cityName, parkingLotName, date, time_slot);

                /*if (success)
                    Toast.makeText(context, "Successfull reservation", Toast.LENGTH_SHORT).show();

                 */

                Intent intent = new Intent (context, ReservationConfirmation.class);
                intent.putExtra("parkingName", parkingLotName);
                intent.putExtra("username", userName);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return parking_lots_names.length;
    }


    public class MyParkingViewHolder extends RecyclerView.ViewHolder{

        TextView parkingName, freeSpots, takenSpots;
        Button buttonReserve;

        public MyParkingViewHolder(@NonNull View itemView) {
            super(itemView);
            parkingName = itemView.findViewById(R.id.parkingName);
            freeSpots = itemView.findViewById(R.id.freeSpots);
            takenSpots = itemView.findViewById(R.id.takenSpots);
            buttonReserve = itemView.findViewById(R.id.reserveButton);

        }
    }
}
