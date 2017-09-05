package com.project.lorvent.mmtes.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.lorvent.mmtes.R;
import com.project.lorvent.mmtes.models.Train;

import java.util.ArrayList;

/**
 * Created by user on 16/6/17.
 */

public class TrainListAdapter  extends RecyclerView.Adapter<TrainListAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Train>trains;

    public TrainListAdapter(Context context, ArrayList<Train> trains) {
        this.context = context;
        this.trains = trains;
    }

    @Override
    public TrainListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.indi_view_train,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TrainListAdapter.ViewHolder holder, int position) {

        Train train=trains.get(position);
        holder.train_number.setText(train.getTrain_no());
        holder.departure.setText(train.getDeparture());
        holder.arrival.setText(train.getArrival());
    }

    @Override
    public int getItemCount() {
        return trains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView train_number,departure,arrival;
        public ViewHolder(View itemView) {
            super(itemView);
            train_number=(TextView)itemView.findViewById(R.id.train_number);
            departure=(TextView)itemView.findViewById(R.id.departure);
            arrival=(TextView)itemView.findViewById(R.id.arrival);


        }
    }
}
