package com.project.lorvent.mmtes;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;

import com.project.lorvent.mmtes.adapters.NearestStationAdapter;
import com.project.lorvent.mmtes.models.MyObject;
import com.project.lorvent.mmtes.models.NearestStation;
import com.project.lorvent.mmtes.utils.DatabaseHandler;

import java.util.ArrayList;
import java.util.List;

public class NearestStationActivity extends AppCompatActivity {
    CustomAutoCompleteView myAutoComplete;
    ArrayAdapter<String> myAdapter;
    DatabaseHandler databaseH;
    String[] item = new String[]{"Please search..."};
    Button search;
    NearestStationAdapter mAdapter;
    RecyclerView rv;
    private ArrayList<NearestStation> stationArrayList;
    LinearLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearest_station);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        search=(Button)findViewById(R.id.search);
        layout=(LinearLayout)findViewById(R.id.bottom_wrapper);
        setSupportActionBar(toolbar);
        ActionBar mActionBar=getSupportActionBar();
        rv=(RecyclerView)findViewById(R.id.rv);
        stationArrayList=new ArrayList<>();
        NearestStation station1=new NearestStation();
        NearestStation station2=new NearestStation();
        station1.setStation_name("Hafizpet");
        station2.setStation_name("HiTech City");
        station1.setDistance("3km");
        station2.setDistance("5km");

        stationArrayList.add(station1);
        stationArrayList.add(station2);

        mActionBar.setTitle("Nearest Station By Locality");
        try {
            // instantiate database handler
            databaseH = new DatabaseHandler(this);
            // put sample data to database
            insertSampleData();
            // autocompletetextview is in activity_main.xml
            myAutoComplete = (CustomAutoCompleteView) findViewById(R.id.actv);

            // add the listener so it will tries to suggest while the user types
            myAutoComplete.addTextChangedListener(new CustomAutoCompleteTextChangedListener(this));
            Editable txt=myAutoComplete.getText();
            Log.i("entered", String.valueOf(txt));
            // set our adapter
            myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, item);
            myAutoComplete.setAdapter(myAdapter);

        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
         search.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                 imm.hideSoftInputFromWindow(search.getWindowToken(), 0);
                 layout.setVisibility(View.VISIBLE);
                 new GetNearestStation().execute();
             }
         });
    }

    public void insertSampleData() {

        // CREATE
        databaseH.create(new MyObject("Gachibowli"));
        databaseH.create(new MyObject("Shamsabad"));
        databaseH.create(new MyObject("kukatpally"));
        databaseH.create(new MyObject("Mallapur"));
        databaseH.create(new MyObject("HiTech City"));
        databaseH.create(new MyObject("Jubliee Hills"));
        databaseH.create(new MyObject("Secunderabad"));
        databaseH.create(new MyObject("Banjara Hills"));
        databaseH.create(new MyObject("Upal kalan"));
        databaseH.create(new MyObject("Ameerpet"));
        databaseH.create(new MyObject("S R Nagar"));
        databaseH.create(new MyObject("Amberpet"));
        databaseH.create(new MyObject("Ashok Nagar"));
        databaseH.create(new MyObject("Azamabad"));
        databaseH.create(new MyObject("Bahadurpurpally"));
        databaseH.create(new MyObject("charminar"));
        databaseH.create(new MyObject("Kondapur"));
        databaseH.create(new MyObject("Kachiguda"));
        databaseH.create(new MyObject("LB Nagar"));
        databaseH.create(new MyObject("Lallaguda"));
        databaseH.create(new MyObject("Miyapur"));
        databaseH.create(new MyObject("Nagarjuna Hills"));
        databaseH.create(new MyObject("Nizampet"));
        databaseH.create(new MyObject("Dilsukhnagar"));
        databaseH.create(new MyObject("Abids"));  databaseH.create(new MyObject("Hanumanpet"));
        databaseH.create(new MyObject("Sitaphalmandi"));
        databaseH.create(new MyObject("Tilak Road"));
        databaseH.create(new MyObject("Rtc Colony"));
        databaseH.create(new MyObject("Adarsh Nagar"));
        databaseH.create(new MyObject("Balkampet"));


    }
    // this function is used in CustomAutoCompleteTextChangedListener.java
    public String[] getItemsFromDb(String searchTerm) {

        // add items on the array dynamically
        List<MyObject> products = databaseH.read(searchTerm);
        int rowCount = products.size();
        String[] item = new String[rowCount];
        int x = 0;

        for (MyObject record : products) {
            item[x] = record.objectName;
            x++;
        }
        return item;
    }

    class GetNearestStation extends AsyncTask<String,Integer,String>
    {
        String response;
        boolean running;
        ProgressDialog dialog;
        int progressStatus =0;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            running = true;
            dialog = new ProgressDialog(NearestStationActivity.this);
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
            mAdapter=new NearestStationAdapter(NearestStationActivity.this,stationArrayList);
            rv.setAdapter(mAdapter);
            rv.setItemAnimator(new DefaultItemAnimator());
            // rv.addItemDecoration(new DividerItemDecoration(getActivity(),GridLayoutManager.HORIZONTAL));
            RecyclerView.LayoutManager lmanager=new LinearLayoutManager(NearestStationActivity.this, LinearLayoutManager.VERTICAL,false);
            //RecyclerView.LayoutManager lmanager=new GridLayoutManager(getActivity(),3);
            rv.setLayoutManager(lmanager);
        }
    }
}
