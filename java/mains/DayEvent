package com.example.alfie_s_app;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class DayEvent {


    final FirebaseFirestore db = FirebaseFirestore.getInstance();
    final CollectionReference colRef = db.collection("Events");

    public DayEvent(CollectionReference colRef) {
    }

    public ArrayList<Event> getData() {
        ArrayList<Event> allData = new ArrayList<>();
        new dbActions.readAll(colRef, allData);
        return allData;
    }

    public DayEvent(CollectionReference colRef, String date) {
    }

    public ArrayList<Event> dateSearch(String date) {
        ArrayList<Event> dateSearch = new ArrayList<>();
        new dbActions.dateSearch(colRef, dateSearch, date);
        return dateSearch;
    }
}
