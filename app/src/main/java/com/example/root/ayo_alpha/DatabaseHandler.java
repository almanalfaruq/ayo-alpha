package com.example.root.ayo_alpha;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by root on 06/09/16.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "EventManager";

    private static final String TABLE_EVENT = "event";

    private static final String KEY_ID = "id";
    private static final String KEY_EVENT = "event";
    private static final String KEY_LOC = "location";
    private static final String KEY_DATE = "date";
    private static final String KEY_DESC = "description";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create table
        db.execSQL("CREATE TABLE" + TABLE_EVENT
                + " (" + KEY_ID + "INTEGER PRIMARY KEY, "
                + KEY_EVENT + "TEXT, "
                + KEY_LOC + "TEXT, "
                + KEY_DATE + "TEXT, "
                + KEY_DESC + "TEXT) ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop table if exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENT);

        // Create tables again
        onCreate(db);
    }
}
