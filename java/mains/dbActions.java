package com.example.alfie_s_app;

import android.content.Context;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.HashMap;
import java.util.Map;
import androidx.annotation.NonNull;
import androidx.core.provider.FontsContractCompat;


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
        public edit(final FirebaseFirestore db, EditText name, EditText building, EditText room, EditText time, EditText description, EditText date, String docID) {

            //make strings
            String ename = name.getText().toString();
            String ebuilding = building.getText().toString();
            String eroom = room.getText().toString();
            String etime = time.getText().toString();
            String edescription = description.getText().toString();
            String edate = date.getText().toString();

            //edit
            if(!ename.equals("")) {
                db.collection("Events").document(docID).update("title", ename);
            }
            if (!ebuilding.equals("")) {
                db.collection("Events").document(docID).update("building", ebuilding);
            }
            if (!eroom.equals("")) {
                db.collection("Events").document(docID).update("room", eroom);
            }
            if (!etime.equals("")) {
                db.collection("Events").document(docID).update("time", etime);
            }
            if(!edescription.equals("")) {
                db.collection("Events").document(docID).update("description", edescription);
            }
            if(!edate.equals("")) {
                db.collection("Events").document(docID).update("date", edate);
            }
        }
    } //end of edit

    public static class delete {
        public delete (final FirebaseFirestore db, TextView docID, final Context context) {

            String ddocID = docID.getText().toString();

            db.collection("Events").document(ddocID).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
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

//READS
    public static class readAll {
        public readAll (final CollectionReference colRef, final TextView namereturn, final TextView buildingreturn, final TextView roomreturn, final TextView timereturn, final TextView datereturn, final TextView descriptionreturn, final TextView docID) {
            colRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                    for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                        Event event = documentSnapshot.toObject(Event.class);
                        event.setDocID(documentSnapshot.getId());

                        //sets all values on AddEvent to what is in the db
                        namereturn.setText(event.getTitle());
                        buildingreturn.setText(event.getBuilding());
                        roomreturn.setText(event.getRoom());
                        timereturn.setText(event.getTime());
                        datereturn.setText(event.getDate());
                        descriptionreturn.setText(event.getDescription());
                        docID.setText(event.getDocID());
                    }
                }
            });
        }
    }//end of readall

    public static class dateSearch {
        public dateSearch (final CollectionReference colRef, EditText date, final Context context, final TextView namereturn, final TextView buildingreturn, final TextView roomreturn, final TextView timereturn, final TextView datereturn, final TextView descriptionreturn, final TextView docID) {

            String sdate = date.getText().toString();

            colRef.whereEqualTo("date", sdate).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                    for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                        Event event = documentSnapshot.toObject(Event.class);
                        event.setDocID(documentSnapshot.getId());

                        namereturn.setText(event.getTitle());
                        buildingreturn.setText(event.getBuilding());
                        roomreturn.setText(event.getRoom());
                        timereturn.setText(event.getTime());
                        datereturn.setText(event.getDate());
                        descriptionreturn.setText(event.getDescription());
                        docID.setText(event.getDocID());
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }//end of dateSearch
}
