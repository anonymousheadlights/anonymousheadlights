package com.example.alfie_s_app;

import android.content.Context;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.sql.Struct;
import java.util.Date;

public class Event {

    private String id, title, building, room, description, date;
    private Long time;
    private Context context;


    final FirebaseFirestore db = FirebaseFirestore.getInstance();
    final CollectionReference colRef = db.collection("Events");

    public Event() {}
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
    public boolean addEvent(){
        new dbActions.submit(db, title, building, room, time, date, description);
        return true;
    }

    public void editEvent(){
        new dbActions.edit(db, title, building, room, time, description, date, id);
    }

    public void deleteEvent(){
        new dbActions.delete(db, id, context);
    }
}
