package com.project.lorvent.mmtes;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

import gr.escsoft.michaelprimez.searchablespinner.SearchableSpinner;

public class TimeTableActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar mActionBar=getSupportActionBar();
        mActionBar.setTitle("MMTS Time Table");
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setHomeButtonEnabled(true);

        FragmentTransaction trans1=getSupportFragmentManager().beginTransaction();
        Fragment fragment1=new SearchTimeTableFragment();
        trans1.replace(R.id.frame,fragment1);
        trans1.addToBackStack(null);
        trans1.commit();


    }


}
