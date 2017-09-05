package com.project.lorvent.mmtes;

import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.project.lorvent.mmtes.adapters.SimpleListAdapter;

import java.util.ArrayList;

import gr.escsoft.michaelprimez.searchablespinner.SearchableSpinner;
import gr.escsoft.michaelprimez.searchablespinner.interfaces.IStatusListener;
import gr.escsoft.michaelprimez.searchablespinner.interfaces.OnItemSelectedListener;

public class MmtsFareActivity extends AppCompatActivity {
    private SearchableSpinner mSearchableSpinner1,mSearchableSpinner2;
    private final ArrayList<String> mStrings = new ArrayList<>();
    private SimpleListAdapter mSimpleListAdapter1,mSimpleListAdapter2;
    Spinner category;
    ArrayList <String>categoryList;
    LinearLayout layout,layout1;
    Button find_fare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mmts_fare);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        layout=(LinearLayout)findViewById(R.id.bottom_wrapper);
        layout1=(LinearLayout)findViewById(R.id.bottom_wrapper1);

        find_fare=(Button)findViewById(R.id.fare);

        setSupportActionBar(toolbar);
        ActionBar mActionBar=getSupportActionBar();
        mActionBar.setTitle("MMts Ticket Fare");
        category=(Spinner)findViewById(R.id.category);
        categoryList=new ArrayList<>();
        categoryList.add("General");
        categoryList.add("Special");
        initListValues();
        mSimpleListAdapter1 = new SimpleListAdapter(this, mStrings);
        mSearchableSpinner1 = (SearchableSpinner) findViewById(R.id.SearchableSpinner1);
        mSearchableSpinner1.setAdapter(mSimpleListAdapter1);
        mSearchableSpinner1.setOnItemSelectedListener(mOnItemSelectedListener1);
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
        mSimpleListAdapter2 = new SimpleListAdapter(this, mStrings);
        mSearchableSpinner2 = (SearchableSpinner) findViewById(R.id.SearchableSpinner2);
        mSearchableSpinner2.setAdapter(mSimpleListAdapter2);
        mSearchableSpinner2.setOnItemSelectedListener(mOnItemSelectedListener2);
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

        ArrayAdapter<String> time1ArrayAdapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,categoryList);
        time1ArrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        category.setAdapter(time1ArrayAdapter);

        find_fare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(find_fare.getWindowToken(), 0);
                layout.setVisibility(View.VISIBLE);
                layout1.setVisibility(View.VISIBLE);


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

    private OnItemSelectedListener mOnItemSelectedListener1 = new OnItemSelectedListener() {
        @Override
        public void onItemSelected(View view, int position, long id) {
            Toast.makeText(MmtsFareActivity.this, "Item on position " + position + " : " + mSimpleListAdapter1.getItem(position) + " Selected", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNothingSelected() {
            Toast.makeText(MmtsFareActivity.this, "Nothing Selected", Toast.LENGTH_SHORT).show();
        }
    };
    private OnItemSelectedListener mOnItemSelectedListener2 = new OnItemSelectedListener() {
        @Override
        public void onItemSelected(View view, int position, long id) {
            Toast.makeText(MmtsFareActivity.this, "Item on position " + position + " : " + mSimpleListAdapter2.getItem(position) + " Selected", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNothingSelected() {
            Toast.makeText(MmtsFareActivity.this, "Nothing Selected", Toast.LENGTH_SHORT).show();
        }
    };

}
