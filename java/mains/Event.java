package com.example.alfie_s_app;

import java.util.Date;

public class Event {

    public String id, name, building, room, description, day;
    public Date time;

    public Event() {}
    public Event(String id, String name, String building, String room, Date time, String day) {
        this.id = id;
        this.name = name;
        this.building = building;
        this.room = room;
        this.time = time;
        this.day = day;
    }
    public Event(String id, String name, String building, String room, Date time, String day,
                 String description) {
        this.id = id;
        this.name = name;
        this.building = building;
        this.room = room;
        this.time = time;
        this.day = day;
        this.description = description;
    }

    public String getName(){return name;}
    public String getBuilding(){return building;}
    public String getRoom(){return room;}
    public Date getTime(){return time;}
    public String getDay(){return day;}
    public String getId(){return id;}
    public String getDescript(){return description;}
    public void setName(String newName){name = newName;}
    public void setBuilding(String newBuilding){building = newBuilding;}
    public void setRoom(String newRoom){room = newRoom;}
    public void setDay(String newDay){day = newDay;}
    public void setTime(Date newTime){time = newTime;}
    public void setDescript(String newDescript){description = newDescript;}

    //database stuff here?
    public  boolean addEvent(){return true;}
    
    public boolean editEvent(){return true;}

    public boolean deleteEvent(){return true;}
}
