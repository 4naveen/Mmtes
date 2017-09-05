package com.project.lorvent.mmtes;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.bogdwellers.pinchtozoom.ImageMatrixTouchHandler;

public class RootMapActivity extends AppCompatActivity {
ImageView root_map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_root_map);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar mActionBar=getSupportActionBar();
        mActionBar.setTitle("MMTS Route Map");
        root_map=(ImageView)findViewById(R.id.route_map);
        root_map.setOnTouchListener(new ImageMatrixTouchHandler(RootMapActivity.this));

    }
}
