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
        super(ctx, "BMI_History.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " ("
                + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DATE + " TEXT NOT NULL, "
                + WEIGHT + " TEXT NOT NULL, "
                + BMI + " TEXT NOT NULL, "
                + CRITERIA + " TEXT NOT NULL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS BMI_History");
        onCreate(db);
    }
}
