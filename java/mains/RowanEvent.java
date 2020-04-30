package com.example.alfie_s_app;

import com.google.firebase.database.Exclude;

public class Event {

    private String docID;
    private String title;
    private String building;
    private String room;
    private String time;
    private String date;
    private String description;

    public Event() {}

    public Event(String title, String building, String room, String time, String date, String description) {
        this.title = title;
        this.building = building;
        this.room = room;
        this.time = time;
        this.date = date;
        this.description = description;
    }

    @Exclude
    public String getDocID() {
        return docID;
    }

    public void setDocID(String docID) {
        this.docID = docID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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
