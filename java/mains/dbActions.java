package com.example.alfie_s_app;

import android.content.Context;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;


public class dbActions {

    //WRITES
    static class submit {
        submit(final FirebaseFirestore db, String name, String building, String room, Long time,
               String date, String description) {
            //create map
            Map<String, Object> event = new HashMap<>();
            event.put("title", name);
            event.put("building", building);
            event.put("room", room);
            event.put("time", time);
            event.put("description", description);
            event.put("date", date);

            //put map
            db.collection("Events").add(event);
        }
    }//end of submit

    public static class edit {
        public edit(final FirebaseFirestore db, String name, String building, String room,
                    long time, String description, String date, String docID) {

            //edit
            if (!name.equals("")) {
                db.collection("Events").document(docID)
                        .update("title", name);
            }
            if (!building.equals("")) {
                db.collection("Events").document(docID)
                        .update("building", building);
            }
            if (!room.equals("")) {
                db.collection("Events").document(docID)
                        .update("room", room);
            }
            //FIXME database change to long variable
            if (time != 0) {
                db.collection("Events").document(docID)
                        .update("time", time);
            }
            if (!description.equals("")) {
                db.collection("Events").document(docID)
                        .update("description", description);
            }
            if (!date.equals("")) {
                db.collection("Events").document(docID)
                        .update("date", date);
            }
        }
    } //end of edit

    public static class delete {
        public delete(final FirebaseFirestore db, String docID, final Context context) {

            db.collection("Events").document(docID).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }//end of delete

}
