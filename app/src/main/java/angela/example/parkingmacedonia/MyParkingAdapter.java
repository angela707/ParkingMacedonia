package angela.example.parkingmacedonia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class MyParkingAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    Context context;

    public MyParkingAdapter(Context ct)
    {
        context=ct;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_parking_row, parent, false);
        return new MyAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView parking_name, free_spots, taken_spots;
        Button reserve_button;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            parking_name = itemView.findViewById(R.id.parkingName);
            free_spots = itemView.findViewById(R.id.freeSpots);
            taken_spots = itemView.findViewById(R.id.takenSpots);
            reserve_button = itemView.findViewById(R.id.reserveButton);
        }
    }
}
