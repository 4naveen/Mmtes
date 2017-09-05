package com.project.lorvent.mmtes;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.project.lorvent.mmtes.adapters.LiveTrainListAdapter;
import com.project.lorvent.mmtes.models.LiveTrain;

import java.util.ArrayList;

public class LiveTrainFavouriteActivity extends AppCompatActivity {
    LiveTrainListAdapter mAdapter;
    ArrayList<LiveTrain> liveTrainArrayList;
    RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_train_favourite);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar mActionBar=getSupportActionBar();
        mActionBar.setTitle("MMts Live Status");

        liveTrainArrayList=new ArrayList<>();

        rv=(RecyclerView)findViewById(R.id.rv);


        LiveTrain liveTrain1=new LiveTrain();
        LiveTrain liveTrain2=new LiveTrain();
        LiveTrain liveTrain3=new LiveTrain();

        liveTrain1.setTrainNumber("47162");
        liveTrain1.setCurr_location("Hafizpet");
        liveTrain1.setExpectedArrival("Arrived");
        liveTrain2.setTrainNumber("47163");
        liveTrain2.setCurr_location("Begumpet");
        liveTrain2.setExpectedArrival("10 mins");
        liveTrain3.setTrainNumber("47166");
        liveTrain3.setCurr_location("Chandanagar");
        liveTrain3.setExpectedArrival("1 mins");

        liveTrainArrayList.add(liveTrain1);
        liveTrainArrayList.add(liveTrain2);
        liveTrainArrayList.add(liveTrain3);

        new GetLiveTrain().execute();
    }

    class GetLiveTrain extends AsyncTask<String,Integer,String>
    {
        String response;
        boolean running;
        ProgressDialog dialog;
        int progressStatus =0;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            running = true;
            dialog = new ProgressDialog(LiveTrainFavouriteActivity.this);
            dialog.setMessage("Loading, please wait");
            dialog.setTitle("Connecting server");
            dialog.show();
            dialog.setCancelable(false);
            dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    running = false;
                }
            });
        }
        @Override
        protected String doInBackground(String... params) {
            int i = 5;
            while(running & progressStatus<5){
                try {
                    progressStatus++;
                    publishProgress(progressStatus);
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if(i-- == 0){
                    running = false;
                }
                publishProgress(i);

            }
            return response;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            // progressDialog.setMessage(String.valueOf(values[0]));
            dialog.setProgress(progressStatus);
        }
        @Override
        protected void onPostExecute(String response) {
            if (dialog!=null&&dialog.isShowing()){dialog.dismiss();}
            mAdapter=new LiveTrainListAdapter(LiveTrainFavouriteActivity.this,liveTrainArrayList);
            rv.setAdapter(mAdapter);
            rv.setItemAnimator(new DefaultItemAnimator());
            // rv.addItemDecoration(new DividerItemDecoration(getActivity(),GridLayoutManager.HORIZONTAL));
            RecyclerView.LayoutManager lmanager=new LinearLayoutManager(LiveTrainFavouriteActivity.this, LinearLayoutManager.VERTICAL,false);
            //RecyclerView.LayoutManager lmanager=new GridLayoutManager(getActivity(),3);
            rv.setLayoutManager(lmanager);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_refresh,menu);
        // this.menu=menu;
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()==R.id.refresh)
        {
            Intent intent=new Intent(LiveTrainFavouriteActivity.this,FavouriteActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
