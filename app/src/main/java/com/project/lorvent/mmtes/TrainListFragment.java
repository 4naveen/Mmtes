package com.project.lorvent.mmtes;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.lorvent.mmtes.adapters.TrainListAdapter;
import com.project.lorvent.mmtes.models.Train;
import com.project.lorvent.mmtes.utils.RecyclerTouchListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TrainListFragment extends Fragment {
    ArrayList<Train> trainArrayList;
    RecyclerView rv;
    TrainListAdapter mAdapter;
    public TrainListFragment() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_train_list, container, false);
        trainArrayList=new ArrayList<>();
        setHasOptionsMenu(true);
        rv=(RecyclerView)v.findViewById(R.id.rv);
        for (int i=0;i<20;i++)
        {
            Train train=new Train();
            train.setTrain_no("4716"+i);
            train.setDeparture("08:39");
            train.setArrival("09:16");
            trainArrayList.add(train);
        }
        mAdapter=new TrainListAdapter(getActivity(),trainArrayList);
        rv.setAdapter(mAdapter);
        rv.setItemAnimator(new DefaultItemAnimator());
        // rv.addItemDecoration(new DividerItemDecoration(getActivity(),GridLayoutManager.HORIZONTAL));
        RecyclerView.LayoutManager lmanager=new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false);
        //RecyclerView.LayoutManager lmanager=new GridLayoutManager(getActivity(),3);
        rv.setLayoutManager(lmanager);

        rv.addOnItemTouchListener(new RecyclerTouchListener(getActivity(),rv, new RecyclerTouchListener.ClickListener() {
            @Override

            public void onClick(View view, int position) {
                Fragment fragment1=new TrainDetailsFragment();
                FragmentTransaction transaction=getFragmentManager().beginTransaction();
               // transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
                transaction.replace(R.id.frame,fragment1);
                transaction.addToBackStack(null);
                transaction.commit();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        return v;
    }

}
