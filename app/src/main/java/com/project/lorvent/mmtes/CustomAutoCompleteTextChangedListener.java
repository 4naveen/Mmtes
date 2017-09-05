package com.project.lorvent.mmtes;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.ArrayAdapter;

/**
 * Created by Guest on 9/16/2016.
 */
public class CustomAutoCompleteTextChangedListener implements TextWatcher {
    public static final String TAG = "CustomAutoCompleteTextChangedListener.java";
    Context context;

    public CustomAutoCompleteTextChangedListener(Context context){
        this.context = context;
    }

    @Override
    public void afterTextChanged(Editable s) {
        // TODO Auto-generated method stub
        Log.i("entered", String.valueOf(s));

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count,
                                  int after) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onTextChanged(CharSequence userInput, int start, int before, int count) {

        // if you want to see in the logcat what the user types
        //Log.e(TAG, "User input: " + userInput);

        NearestStationActivity nearestStationActivity = ((NearestStationActivity) context);

        // query the database based on the user input
        nearestStationActivity.item = nearestStationActivity.getItemsFromDb(userInput.toString());

        // update the adapater
        nearestStationActivity.myAdapter.notifyDataSetChanged();
        nearestStationActivity.myAdapter = new ArrayAdapter<String>(nearestStationActivity, android.R.layout.simple_dropdown_item_1line, nearestStationActivity.item);
        nearestStationActivity.myAutoComplete.setAdapter(nearestStationActivity.myAdapter);

    }

}



