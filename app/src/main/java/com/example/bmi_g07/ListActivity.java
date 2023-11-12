package com.example.bmi_g07;

import static android.provider.BaseColumns._ID;

import static com.example.bmi_g07.Constants.TABLE_NAME;
import static com.example.bmi_g07.Constants.DATE;
import static com.example.bmi_g07.Constants.WEIGHT;
import static com.example.bmi_g07.Constants.BMI;
import static com.example.bmi_g07.Constants.CRITERIA;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class ListActivity extends AppCompatActivity {
    private EventsData events;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        events = new EventsData(ListActivity.this);
        try {
            Cursor cursor = getEvents();
            showEvents(cursor);
        }finally {
            events.close();
        }
    }
    private void showEvents(Cursor cursor) {
        final ListView listView = (ListView)findViewById(R.id.listView);
        final ArrayList<HashMap<String, String>> MyArrList = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> map;
        while(cursor.moveToNext()) {
            map = new HashMap<String, String>();
            map.put("date", cursor.getString(1));
            map.put("weight", cursor.getString(2));
            map.put("bmi", cursor.getString(3));
            map.put("criteria", cursor.getString(4));
            MyArrList.add(map);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(ListActivity.this, MyArrList, R.layout.activity_column, new String[]{"date", "weight", "bmi", "criteria"}, new int[]{R.id.col_date, R.id.col_weight, R.id.col_bmi, R.id.col_criteria});
        listView.setAdapter(simpleAdapter);
    }

    private Cursor getEvents() {
        String[] FROM = {_ID, DATE, WEIGHT, BMI, CRITERIA};
        String ORDER_BY = _ID + " DESC";
        SQLiteDatabase db = events.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, FROM, null, null, null, null, ORDER_BY);
        return cursor;
    }

}
