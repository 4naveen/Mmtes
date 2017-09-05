package com.project.lorvent.mmtes;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.project.lorvent.mmtes.adapters.SimpleListAdapter;

import java.util.ArrayList;

import gr.escsoft.michaelprimez.searchablespinner.SearchableSpinner;
import gr.escsoft.michaelprimez.searchablespinner.interfaces.IStatusListener;
import gr.escsoft.michaelprimez.searchablespinner.interfaces.OnItemSelectedListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchTimeTableFragment extends Fragment {
    private SearchableSpinner mSearchableSpinner1,mSearchableSpinner2;
    private final ArrayList<String> mStrings = new ArrayList<>();
    private SimpleListAdapter mSimpleListAdapter;
    ArrayList <String>time1List,time2List;
    Spinner time1,time2;
    Button search;
    public SearchTimeTableFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v=inflater.inflate(R.layout.fragment_search_time_table, container, false);
        initListValues();
        mSimpleListAdapter = new SimpleListAdapter(getActivity(), mStrings);
        mSearchableSpinner1 = (SearchableSpinner)v.findViewById(R.id.SearchableSpinner1);
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
        mSearchableSpinner2 = (SearchableSpinner)v.findViewById(R.id.SearchableSpinner2);
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

        time1=(Spinner) v.findViewById(R.id.time1);
        time2=(Spinner) v.findViewById(R.id.time2);
        search=(Button) v.findViewById(R.id.search);

        time1List=new ArrayList<>();
        time2List=new ArrayList<>();
        for (int i=4;i<=23;i++)
        {
            if (i<9){
                time1List.add("0"+i+":00");}
            else {
                time1List.add(i+":00");

            }
        }
        for (int i=5;i<=23;i++)
        {
            if (i<9){
                time2List.add("0"+i+":00");}
            else {
                time2List.add(i+":00");
            }}
        ArrayAdapter<String> time1ArrayAdapter=new ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_item,time1List);
        time1ArrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        time1.setAdapter(time1ArrayAdapter);

        ArrayAdapter<String> time2ArrayAdapter=new ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_item,time2List);
        time2ArrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        time2.setAdapter(time2ArrayAdapter);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                new GetTrainList().execute();
            }
        });

        v.setFocusableInTouchMode(true);
        v.requestFocus();
        v.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        getActivity().finish();
                        return true;
                    }
                }
                return false;
            }
        });
        return v;
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

    private OnItemSelectedListener mOnItemSelectedListener = new OnItemSelectedListener() {
        @Override
        public void onItemSelected(View view, int position, long id) {
            Toast.makeText(getActivity(), "Item on position " + position + " : " + mSimpleListAdapter.getItem(position) + " Selected", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNothingSelected() {
            Toast.makeText(getActivity(), "Nothing Selected", Toast.LENGTH_SHORT).show();
        }
    };


    class GetTrainList extends AsyncTask<String,Integer,String>
    {
        String response;
        boolean running;
        ProgressDialog dialog;
        int progressStatus =0;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            running = true;
            dialog = new ProgressDialog(getActivity());
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
            Fragment fragment1=new TrainListFragment();
            FragmentTransaction transaction=getFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
            transaction.replace(R.id.frame,fragment1);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

}
