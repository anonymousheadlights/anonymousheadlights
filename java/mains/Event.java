package com.example.alfie_s_app;

import android.content.Context;

import com.google.firebase.firestore.FirebaseFirestore;

public class Event {

    private String id, title, building, room, description, date;
    private Long time;
    private Context context;

    final FirebaseFirestore db = FirebaseFirestore.getInstance();

    public Event() {
        id = "";
        title = "";
        building = "";
        room = "";
        description = "";
        time = 0L;
    }
    public Event(Context context, String id) {
        this.context = context;
        this.id = id;
    }
    public Event(String id, String title, String building, String room, Long time, String date,
                 String description) {
        this.id = id;
        this.title = title;
        this.building = building;
        this.room = room;
        this.time = time;
        this.date = date;
        this.description = description;
    }

    public String getTitle(){return title;}
    public String getBuilding(){return building;}
    public String getRoom(){return room;}
    public Long getTime(){return time;}
    public String getDate(){return date;}
    public String getId(){return id;}
    public String getDescription(){return description;}

    public void setId(String newId){id = newId;}
    public void setTitle(String newTitle){title = newTitle;}
    public void setBuilding(String newBuilding){building = newBuilding;}
    public void setRoom(String newRoom){room = newRoom;}
    public void setDate(String newDate){date = newDate;}
    public void setTime(Long newTime){time = newTime;}
    public void setDescription(String newDescript){description = newDescript;}

    //database stuff here?
    public void addEvent(){
        new dbActions.submit(db, title, building, room, time, date, description);
    }

    public void editEvent(){
        new dbActions.edit(db, title, building, room, time, description, date, id);
    }

    public void deleteEvent(){
        new dbActions.delete(db, id, context);
    }
}
