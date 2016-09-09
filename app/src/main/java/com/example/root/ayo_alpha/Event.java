package com.example.root.ayo_alpha;

/**
 * Created by root on 06/09/16.
 */
public class Event {

    private int _id;
    private String event;
    private String location;
    private String date;
    private String description;
    private String time;

    public Event() {

    }

    public Event(int _id, String event, String location, String date, String description, String time) {
        this._id = _id;
        this.event = event;
        this.location = location;
        this.date = date;
        this.description = description;
        this.time = time;
    }

    public int getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
