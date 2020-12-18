package angela.example.parkingmacedonia;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    String data1[], data2[];
    int data3[];
    Context context;


    public MyAdapter(Context ct, String nm[], String sc[], int pl[]){
        context=ct;
        data1=nm;
        data2=sc;
        data3=pl;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder (view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.text1.setText(data1[position]);
        holder.text2.setText(data2[position]);
        holder.text3.setText(Integer.toString(data3[position]));

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (context, ReservationForm.class);
                intent.putExtra("name", data1[position]);
                intent.putExtra("parking_lots", data3[position]);

                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return data1.length;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView text1, text2, text3;
        Button button;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            text1 = itemView.findViewById(R.id.name);
            text2 = itemView.findViewById(R.id.shortcut);
            text3 = itemView.findViewById(R.id.parking_lots);
            button = itemView.findViewById(R.id.button);

        }
    }
}
