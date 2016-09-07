package com.example.root.ayo_alpha;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

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
                + KEY_DESC + "TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop table if exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENT);

        // Create tables again
        onCreate(db);
    }

    public void addEvent(Event event) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_EVENT, event.getEvent()); // Event Event Name
        values.put(KEY_LOC, event.getLocation()); // Event Location
        values.put(KEY_DATE, event.getDate()); // Event Date
        values.put(KEY_DESC, event.getDescription()); // Event Description

        // Inserting Row
        db.insert(TABLE_EVENT, null, values);
        db.close(); // Closing database connection
    }

    public Event getEvent(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_EVENT, new String[] { KEY_ID,
                        KEY_EVENT, KEY_LOC, KEY_DATE, KEY_DESC }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Event event = new Event(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
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
        cursor.close();

        return cursor.getCount();
    }

}