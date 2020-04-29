package com.example.alfie_s_app;

import android.content.Context;
import android.widget.EditText;
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
        submit(final FirebaseFirestore db, EditText name, EditText building, EditText room, EditText time, EditText description, EditText date) {

            //make strings
            String sname = name.getText().toString();
            String sbuilding = building.getText().toString();
            String sroom = room.getText().toString();
            String stime = time.getText().toString();
            String sdescription = description.getText().toString();
            String sdate = date.getText().toString();

            //create map
            Map<String, String> event = new HashMap<>();
            event.put("title", sname);
            event.put("building", sbuilding);
            event.put("room", sroom);
            event.put("time", stime);
            event.put("description", sdescription);
            event.put("date", sdate);

            //put map
            db.collection("Events").add(event);
        }
    }//end of submit

    public static class edit {
        public edit(final FirebaseFirestore db, EditText name, EditText building, EditText room, EditText time, EditText description, EditText date, String document) {

            //make strings
            String ename = name.getText().toString();
            String ebuilding = building.getText().toString();
            String eroom = room.getText().toString();
            String etime = time.getText().toString();
            String edescription = description.getText().toString();
            String edate = date.getText().toString();

            //edit
            if(!ename.equals("")) {
                db.collection("Events").document(document).update("title", ename);
            }
            if (!ebuilding.equals("")) {
                db.collection("Events").document(document).update("building", ebuilding);
            }
            if (!eroom.equals("")) {
                db.collection("Events").document(document).update("room", eroom);
            }
            if (!etime.equals("")) {
                db.collection("Events").document(document).update("time", etime);
            }
            if(!edescription.equals("")) {
                db.collection("Events").document(document).update("description", edescription);
            }
            if(!edate.equals("")) {
                db.collection("Events").document(document).update("date", edate);
            }
        }
    } //end of edit

    public static class delete {
        public delete (final FirebaseFirestore db, String document, final Context context) {
            db.collection("Events").document(document).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
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
