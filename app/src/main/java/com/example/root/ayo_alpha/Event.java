package com.example.root.ayo_alpha;

/**
 * Created by root on 06/09/16.
 */
public class Event {

    private int id;
    private String event;
    private String location;
    private String date;
    private String description;

    public Event() {

    }

    public Event(int id, String event, String location, String date, String description) {
        this.id = id;
        this.event = event;
        this.location = location;
        this.date = date;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

}
