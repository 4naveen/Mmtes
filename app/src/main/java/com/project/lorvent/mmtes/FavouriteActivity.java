package com.project.lorvent.mmtes;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.project.lorvent.mmtes.adapters.SimpleListAdapter;

import java.util.ArrayList;

import gr.escsoft.michaelprimez.searchablespinner.SearchableSpinner;
import gr.escsoft.michaelprimez.searchablespinner.interfaces.IStatusListener;
import gr.escsoft.michaelprimez.searchablespinner.interfaces.OnItemSelectedListener;

public class FavouriteActivity extends AppCompatActivity {
    private SearchableSpinner mSearchableSpinner1,mSearchableSpinner2;
    private final ArrayList<String> mStrings = new ArrayList<>();
    private SimpleListAdapter mSimpleListAdapter1,mSimpleListAdapter2;
    Button favourite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar mActionBar=getSupportActionBar();
        mActionBar.setTitle("Set Favourite");
        favourite=(Button)findViewById(R.id.favourite);
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

favourite.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(FavouriteActivity.this,LiveTrainFavouriteActivity.class);
        startActivity(intent);
        finish();
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
            Toast.makeText(FavouriteActivity.this, "Item on position " + position + " : " + mSimpleListAdapter1.getItem(position) + " Selected", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNothingSelected() {
            Toast.makeText(FavouriteActivity.this, "Nothing Selected", Toast.LENGTH_SHORT).show();
        }
    };
    private OnItemSelectedListener mOnItemSelectedListener2 = new OnItemSelectedListener() {
        @Override
        public void onItemSelected(View view, int position, long id) {
            Toast.makeText(FavouriteActivity.this, "Item on position " + position + " : " + mSimpleListAdapter2.getItem(position) + " Selected", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNothingSelected() {
            Toast.makeText(FavouriteActivity.this, "Nothing Selected", Toast.LENGTH_SHORT).show();
        }
    };
}
