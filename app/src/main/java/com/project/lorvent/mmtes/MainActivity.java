package com.project.lorvent.mmtes;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;
import com.project.lorvent.mmtes.adapters.LiveTrainListAdapter;
import com.project.lorvent.mmtes.adapters.SimpleListAdapter;
import com.project.lorvent.mmtes.models.LiveTrain;
import com.project.lorvent.mmtes.utils.AppSession;

import java.util.ArrayList;

import gr.escsoft.michaelprimez.searchablespinner.SearchableSpinner;
import gr.escsoft.michaelprimez.searchablespinner.interfaces.IStatusListener;
import gr.escsoft.michaelprimez.searchablespinner.interfaces.OnItemSelectedListener;

public class MainActivity extends AppCompatActivity {
    private SearchableSpinner mSearchableSpinner1,mSearchableSpinner2;
    private final ArrayList<String> mStrings = new ArrayList<>();
    private SimpleListAdapter mSimpleListAdapter;
   // private BoomMenuButton bmb;
    LiveTrainListAdapter mAdapter;
    ArrayList<LiveTrain> liveTrainArrayList;
    ArrayList<String>stringArrayList;
    ArrayList<Integer>imageArrayList;
    ImageView favourite;
    RecyclerView rv;
    Button search;
    private AppSession appSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        appSession = new AppSession(this);
         Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
         setSupportActionBar(toolbar);
         ActionBar mActionBar=getSupportActionBar();
        //actionBar.setTitle("MMts Live Status");

        LayoutInflater mInflater = LayoutInflater.from(this);
        favourite=(ImageView)findViewById(R.id.favourite);
        search=(Button)findViewById(R.id.search);
        stringArrayList=new ArrayList<>();
        imageArrayList=new ArrayList<>();
        stringArrayList.add("MMTS Time Table");
        stringArrayList.add("MMTS Route Map");
        stringArrayList.add("MMTS Fares");
        stringArrayList.add("ATVMs");
        stringArrayList.add("Nearest Stations by Locality");
        imageArrayList.add(R.mipmap.mmts_time);
        imageArrayList.add(R.mipmap.mmts_routes);
        imageArrayList.add(R.mipmap.mmts_fare);
        imageArrayList.add(R.mipmap.avtms);
        imageArrayList.add(R.mipmap.nearest_station_by_locality);
        View actionBar = mInflater.inflate(R.layout.custom_actionbar, null);
        TextView mTitleTextView = (TextView) actionBar.findViewById(R.id.title_text);
        ImageView favourite= (ImageView) actionBar.findViewById(R.id.favourite);

        mTitleTextView.setText("MMts Live Status");
        mActionBar.setCustomView(actionBar);
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        mActionBar.setDisplayShowCustomEnabled(true);
        ((Toolbar) actionBar.getParent()).setContentInsetsAbsolute(0,0);
        BoomMenuButton leftBmb = (BoomMenuButton) actionBar.findViewById(R.id.action_bar_left_bmb);
        leftBmb.setButtonEnum(ButtonEnum.Ham);
        leftBmb.setPiecePlaceEnum(PiecePlaceEnum.HAM_5);
        leftBmb.setButtonPlaceEnum(ButtonPlaceEnum.HAM_5);
        leftBmb.setNormalColor(Color.RED);
        for (int i = 0; i < leftBmb.getPiecePlaceEnum().pieceNumber(); i++) {
            HamButton.Builder builder = new HamButton.Builder()
                    .normalText(stringArrayList.get(i))
                    .normalImageRes(imageArrayList.get(i))
                    .listener(new OnBMClickListener() {
                        @Override
                        public void onBoomButtonClick(int index) {
                            // When the boom-button corresponding this builder is clicked.
                            switch (index)
                            {
                                case 0:
                                {

                                    Intent intent=new Intent(MainActivity.this,TimeTableActivity.class);
                                    startActivity(intent);

                                    break;
                                }
                                case 1:
                                {

                                    Intent intent=new Intent(MainActivity.this,RootMapActivity.class);
                                    startActivity(intent);

                                    break;
                                }
                                case 2:
                                {

                                    Intent intent=new Intent(MainActivity.this,MmtsFareActivity.class);
                                    startActivity(intent);
                                    break;
                                }
                                case 3:
                                {

                                    Intent intent=new Intent(MainActivity.this,ATVMsActivity.class);
                                    startActivity(intent);
                                    break;
                                }
                                case 4:
                                {
                                    Intent intent=new Intent(MainActivity.this,NearestStationActivity.class);
                                    startActivity(intent);
                                    break;
                                }
                            }
                        }
                    });
            leftBmb.addBuilder(builder);
        }

        liveTrainArrayList=new ArrayList<>();

        rv=(RecyclerView)findViewById(R.id.rv);
        initListValues();
        mSimpleListAdapter = new SimpleListAdapter(this, mStrings);
        mSearchableSpinner1 = (SearchableSpinner) findViewById(R.id.SearchableSpinner1);
        mSearchableSpinner1.setAdapter(mSimpleListAdapter);
        mSearchableSpinner1.setOnItemSelectedListener(mOnItemSelectedListener);
        mSearchableSpinner1.setStatusListener(new IStatusListener() {
            @Override
            public void spinnerIsOpening() {
               /* mSearchableSpinner1.hideEdit();
                mSearchableSpinner2.hideEdit();*/
            }

            @Override
            public void spinnerIsClosing() {

            }
        });
        mSearchableSpinner2 = (SearchableSpinner) findViewById(R.id.SearchableSpinner2);
        mSearchableSpinner2.setAdapter(mSimpleListAdapter);
        mSearchableSpinner2.setOnItemSelectedListener(mOnItemSelectedListener);
        mSearchableSpinner2.setStatusListener(new IStatusListener() {
            @Override
            public void spinnerIsOpening() {
            /*    mSearchableSpinner1.hideEdit();
                mSearchableSpinner2.hideEdit();*/
            }

            @Override
            public void spinnerIsClosing() {

            }
        });

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

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              new GetLiveTrain().execute();
            }
        });

        favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (appSession.isFirstTimeLaunch()) {
                    Intent intent=new Intent(MainActivity.this,FavouriteActivity.class);
                    startActivity(intent);
                    appSession.setFirstTimeLaunch(false);

                }
                else {
                    Intent intent=new Intent(MainActivity.this,LiveTrainFavouriteActivity.class);
                    startActivity(intent);
                }

            }
        });
    }
    private void initListValues() {
        mStrings.add("Arts College");
        mStrings.add("Begumpet");
        mStrings.add("Bharat Nagar");
        mStrings.add("Borabanda");
        mStrings.add("Chanda Nagar");
        mStrings.add("Dabirpura");
        mStrings.add("Falaknuma");
        mStrings.add("Fateh Nagar");
        mStrings.add("Hafeezpet");
        mStrings.add("Hi-Tec City");
        mStrings.add("Huppuguda");
        mStrings.add("Hyderabad");
        mStrings.add("Jamia Osmania");
        mStrings.add("James Street");
        mStrings.add("Kachiguda");
        mStrings.add("Khairtabad");
        mStrings.add("Lakdi-ka-pul");
        mStrings.add("Lingampalli");
        mStrings.add("Malakpet");
        mStrings.add("Nature Cure Hospital");
        mStrings.add("Sanjeevaiah Park");
        mStrings.add("Secunderabad");
        mStrings.add("Sitaphalmandi");
        mStrings.add("Vidyanagar");
        mStrings.add("Yakutpura");

    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!mSearchableSpinner1.isInsideSearchEditText(event)) {
           // mSearchableSpinner1.hideEdit();
        }
        if (!mSearchableSpinner2.isInsideSearchEditText(event)) {
           // mSearchableSpinner2.hideEdit();
        }
        return super.onTouchEvent(event);
    }

    private OnItemSelectedListener mOnItemSelectedListener = new OnItemSelectedListener() {
        @Override
        public void onItemSelected(View view, int position, long id) {
            Toast.makeText(MainActivity.this, "Item on position " + position + " : " + mSimpleListAdapter.getItem(position) + " Selected", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNothingSelected() {
            Toast.makeText(MainActivity.this, "Nothing Selected", Toast.LENGTH_SHORT).show();
        }
    };

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
            dialog = new ProgressDialog(MainActivity.this);
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
            mAdapter=new LiveTrainListAdapter(MainActivity.this,liveTrainArrayList);
            rv.setAdapter(mAdapter);
            rv.setItemAnimator(new DefaultItemAnimator());
            // rv.addItemDecoration(new DividerItemDecoration(getActivity(),GridLayoutManager.HORIZONTAL));
            RecyclerView.LayoutManager lmanager=new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL,false);
            //RecyclerView.LayoutManager lmanager=new GridLayoutManager(getActivity(),3);
            rv.setLayoutManager(lmanager);
            }
        }


}
