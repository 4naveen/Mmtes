package com.project.lorvent.mmtes.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.lorvent.mmtes.R;
import com.project.lorvent.mmtes.models.TrainDetail;

import java.util.ArrayList;

/**
 * Created by user on 17/6/17.
 */

public class TrainDetailAdapter extends RecyclerView.Adapter<TrainDetailAdapter.ViewHolder> {

    private Context context;
    private ArrayList<TrainDetail> trainDetails;


    public TrainDetailAdapter(Context context, ArrayList<TrainDetail> trainDetails) {
        this.context = context;
        this.trainDetails = trainDetails;
    }

    @Override
    public TrainDetailAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.indi_view_train_details,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TrainDetailAdapter.ViewHolder holder, int position) {

        TrainDetail detail=trainDetails.get(position);
        holder.train_number.setText(detail.getTrain_no());
        holder.departure.setText(detail.getDeparture());
        holder.station_name.setText(detail.getStn_name());

    }

    @Override
    public int getItemCount() {
        return trainDetails.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView train_number,departure,station_name;

        public ViewHolder(View itemView) {
            super(itemView);

            train_number=(TextView)itemView.findViewById(R.id.train_number);
            departure=(TextView)itemView.findViewById(R.id.departure);
            station_name=(TextView)itemView.findViewById(R.id.station_name);
        }
    }
}
