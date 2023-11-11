package com.example.bmi_g07;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import static android.provider.BaseColumns._ID;
import static com.example.bmi_g07.Constants.TABLE_NAME;
import static com.example.bmi_g07.Constants.DATE;
import static com.example.bmi_g07.Constants.WEIGHT;
import static com.example.bmi_g07.Constants.BMI;
import static com.example.bmi_g07.Constants.CRITERIA;

public class EventsData extends SQLiteOpenHelper {
    public EventsData(Context ctx){
        super(ctx, "events.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " ("
                + _ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DATE + "INTEGER, "
                + WEIGHT + " TEXT, "
                + BMI + "TEXT, "
                + CRITERIA + " TEXT );"  );
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS events");
        onCreate(db);
    }
}
