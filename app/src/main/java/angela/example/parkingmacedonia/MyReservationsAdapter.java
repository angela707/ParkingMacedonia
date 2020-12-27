package angela.example.parkingmacedonia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyReservationsAdapter extends RecyclerView.Adapter<MyReservationsAdapter.MyReservationsViewHolder> {

    String parking_lots_names [], reservation_dates[], reservations_time_slots[], cities_for_user[];
    int reservations_city_images [];
    String username;
    Context context;


    public MyReservationsAdapter (Context context, String username, String parking_lots_names [], String reservation_dates [],String reservations_time_slots[], String citiesForUser[])
    {
        this.context = context;
        this.username = username;
        this.parking_lots_names = parking_lots_names;
        this.reservation_dates = reservation_dates;
        this.reservations_time_slots = reservations_time_slots;
        this.cities_for_user = citiesForUser;
    }





    @NonNull
    @Override
    public MyReservationsAdapter.MyReservationsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_reservations_row,  parent, false);
        MyReservationsAdapter.MyReservationsViewHolder holder = new MyReservationsAdapter.MyReservationsViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyReservationsViewHolder holder, int position) {
        holder.parkingNameR.setText(parking_lots_names[position]);
        holder.dateSlot.setText(reservation_dates[position]);
        holder.timeSlot.setText(reservations_time_slots[position]);
    }

    @Override
    public int getItemCount() {
        return parking_lots_names.length;
    }


    public class MyReservationsViewHolder extends RecyclerView.ViewHolder{

        TextView parkingNameR, dateSlot, timeSlot;
        ImageView cityPicture;

        public MyReservationsViewHolder(@NonNull View itemView) {
            super(itemView);
            parkingNameR = itemView.findViewById(R.id.parkingNameR);
            dateSlot = itemView.findViewById(R.id.dateSlot);
            timeSlot = itemView.findViewById(R.id.timeSlot);
            cityPicture = itemView.findViewById(R.id.cityReservationsPicture);

        }
    }

}
