package com.example.root.ayo_alpha;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 06/09/16.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;

    private static final String DATABASE_NAME = "EventManager.db";

    private static final String TABLE_EVENT = "event";

    public static final String KEY_ID = "_id";
    public static final String KEY_EVENT = "event";
    public static final String KEY_LOC = "location";
    public static final String KEY_DATE = "date";
    public static final String KEY_DESC = "description";
    public static final String KEY_TIME = "time";
    public static final String KEY_LAT = "latitude";
    public static final String KEY_LONG = "longitude";
    public static final String KEY_ACC = "onTime";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create table
        db.execSQL("CREATE TABLE " + TABLE_EVENT
                + " (" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_EVENT + " TEXT, "
                + KEY_LOC + " TEXT, "
                + KEY_DATE + " TEXT, "
                + KEY_DESC + " TEXT,"
                + KEY_TIME + " TEXT,"
                + KEY_LAT + " TEXT,"
                + KEY_LONG + " TEXT,"
                + KEY_ACC + " INTEGER)");
        Log.d("Creating: ", "Database created.");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop table if exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENT);

        // Create tables again
        onCreate(db);
    }

    public boolean addEvent(String event, String location, String date, String desc, String time, String lat, String longitude, int onTime) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_EVENT, event); // Event Event Name
        values.put(KEY_LOC, location); // Event Location
        values.put(KEY_DATE, date); // Event Date
        values.put(KEY_DESC, desc); // Event Description
        values.put(KEY_TIME, time); // Event Time
        values.put(KEY_LAT, lat); // Event latitude
        values.put(KEY_LONG, longitude); // Eevnt longitude
        values.put(KEY_ACC, onTime);

        // Inserting Row
        long result = db.insert(TABLE_EVENT, null, values);
        db.close();
        if (result == -1) return false;
        else return true;
    }

    public void addOnTime(int onTime, int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ACC, onTime);
        db.update(TABLE_EVENT, values, "_id="+id, null);
    }

    public Event getEvent(int _id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(true, TABLE_EVENT, new String[] { KEY_ID,
                        KEY_EVENT, KEY_LOC, KEY_DATE, KEY_DESC, KEY_TIME, KEY_LAT, KEY_LONG, KEY_ACC }, KEY_ID + "=?",
                new String[] { String.valueOf(_id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Event event = new Event(cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getString(5),
                cursor.getString(6),
                cursor.getString(7),
                cursor.getInt(8));
        // return event
        return event;
    }

    public List<Event> getAllEvent() {
        List<Event> eventList = new ArrayList<Event>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_EVENT;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Event event = new Event();
                event.setId(Integer.parseInt(cursor.getString(0)));
                event.setEvent(cursor.getString(1));
                event.setLocation(cursor.getString(2));
                event.setDate(cursor.getString(3));
                event.setDescription(cursor.getString(4));
                // Adding contact to list
                eventList.add(event);
            } while (cursor.moveToNext());
        }

        // return contact list
        return eventList;
    }

    public int updateEvent(Event event) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_EVENT, event.getEvent()); // Event Event Name
        values.put(KEY_LOC, event.getLocation()); // Event Location
        values.put(KEY_DATE, event.getDate()); // Event Date
        values.put(KEY_DESC, event.getDescription()); // Event Description

        // updating row
        return db.update(TABLE_EVENT, values, KEY_ID + " = ?",
                new String[] { String.valueOf(event.getId()) });
    }

    public void deleteEvent(Event event) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_EVENT, KEY_ID + " =?", new String[] { String.valueOf(event.getId()) });
        db.close();
    }

    public int getEventCount() {
        String countQuery = "SELECT * FROM " + TABLE_EVENT;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        db.close();
        return count;
    }

    public int getOnTimeCount() {
        String countQuery = "SELECT * FROM " + TABLE_EVENT + " WHERE " + KEY_ACC + "=1";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        db.close();
        return count;
    }

    public Cursor getAllData() {
//        String query = "SELECT * FROM " + TABLE_EVENT;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(true, TABLE_EVENT, null, null, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor getMinId() {
        String query = "SELECT MIN(_id) AS id FROM " + TABLE_EVENT;
//        String[] tableColumn = new String[] { "(SELECT MIN(_id) FROM " + TABLE_EVENT +") AS MIN" };
//        String whereClause = "_id = ?";
//        String[] whereArgs = new String[] { String.valueOf(_id) };
        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.query(true, TABLE_EVENT, tableColumn, whereClause, whereArgs, null, null, null, null);
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }
}
