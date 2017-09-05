package com.project.lorvent.mmtes;


import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.lorvent.mmtes.adapters.TrainDetailAdapter;
import com.project.lorvent.mmtes.models.TrainDetail;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TrainDetailsFragment extends Fragment {
    ArrayList<TrainDetail> trainDetailArrayList;
    RecyclerView rv;
    TrainDetailAdapter mAdapter;
    CollapsingToolbarLayout collapsingToolbar;

    public TrainDetailsFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_train_details, container, false);

        final Toolbar toolbar = (Toolbar) v.findViewById(R.id.toolbar_train_detail);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /*Creating a collapsing collapsing_toolbar_movie_detail, defined in the fragment_movie_details.xml  */
        collapsingToolbar =
                (CollapsingToolbarLayout) v.findViewById(R.id.toolbar_layout);
        collapsingToolbar.setTitle("MMTS Time Table");
        trainDetailArrayList=new ArrayList<>();
        rv=(RecyclerView)v.findViewById(R.id.rv);
        for (int i=0;i<20;i++)
        {
            TrainDetail detail=new TrainDetail();
            detail.setTrain_no("47162");
            detail.setDeparture("08:39");
            detail.setStn_name("Hafizpet");
            trainDetailArrayList.add(detail);
        }
        mAdapter=new TrainDetailAdapter(getActivity(),trainDetailArrayList);
        rv.setAdapter(mAdapter);
        rv.setItemAnimator(new DefaultItemAnimator());
        // rv.addItemDecoration(new DividerItemDecoration(getActivity(),GridLayoutManager.HORIZONTAL));
        RecyclerView.LayoutManager lmanager=new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false);
        //RecyclerView.LayoutManager lmanager=new GridLayoutManager(getActivity(),3);
        rv.setLayoutManager(lmanager);
        return v;
    }

}
