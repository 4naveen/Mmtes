package com.project.lorvent.mmtes.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.project.lorvent.mmtes.models.LiveTrain;
import com.project.lorvent.mmtes.R;

import java.util.ArrayList;

/**
 * Created by user on 10/6/17.
 */

public class LiveTrainListAdapter extends RecyclerView.Adapter<LiveTrainListAdapter.ViewHolder>{
    private Context context;
    private ArrayList<LiveTrain> liveTrains;
    Animation blink_text;
    public LiveTrainListAdapter(Context context, ArrayList<LiveTrain> liveTrains) {
        this.context = context;
        this.liveTrains = liveTrains;
    }

    @Override
    public LiveTrainListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.indi_view_live_train,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(LiveTrainListAdapter.ViewHolder holder, int position) {
        blink_text = AnimationUtils.loadAnimation(context,
                R.anim.blink_text);
        LiveTrain liveTrain=liveTrains.get(position);
        holder.train_number.setText(liveTrain.getTrainNumber());
        holder.location.setText(liveTrain.getCurr_location());
        holder.expected_arrival.setText(liveTrain.getExpectedArrival());

        holder.expected_arrival.startAnimation(blink_text);

    }

    @Override
    public int getItemCount() {
        return liveTrains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView train_number,location,expected_arrival;

        public ViewHolder(View itemView) {
            super(itemView);
            train_number=(TextView)itemView.findViewById(R.id.train_number);
            location=(TextView)itemView.findViewById(R.id.location);
            expected_arrival=(TextView)itemView.findViewById(R.id.expected_arrival);

        }
    }
}
