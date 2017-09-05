package com.project.lorvent.mmtes.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.lorvent.mmtes.models.NearestStation;
import com.project.lorvent.mmtes.R;

import java.util.ArrayList;

/**
 * Created by user on 21/6/17.
 */

public class NearestStationAdapter extends RecyclerView.Adapter<NearestStationAdapter.ViewHolder>{
    private Context context;
    private ArrayList<NearestStation> stationArrayList;

    public NearestStationAdapter(Context context, ArrayList<NearestStation> stationArrayList) {
        this.context = context;
        this.stationArrayList = stationArrayList;
    }

    @Override
    public NearestStationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.indi_view_near_searched_stations,parent,false);

        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(NearestStationAdapter.ViewHolder holder, int position) {
        NearestStation station=stationArrayList.get(position);
        holder.station_name.setText(station.getStation_name());
        holder.distance.setText(station.getDistance());


    }

    @Override
    public int getItemCount() {
        return stationArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView station_name,distance;
        public ViewHolder(View itemView) {


            super(itemView);

            station_name=(TextView)itemView.findViewById(R.id.station_name);
            distance=(TextView)itemView.findViewById(R.id.distance);
        }
    }
}
