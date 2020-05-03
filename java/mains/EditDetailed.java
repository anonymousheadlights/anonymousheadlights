package com.example.alfie_s_app;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class EditDetailed extends AppCompatActivity {

    EditText room, building, description, date, time, name;
    Button save;
    String newName, newBuilding, newRoom, newDate, newDescript;
    Long newTime;

    final FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_detailed);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        room = findViewById(R.id.cal_list_room);
        building = findViewById(R.id.cal_list_building);
        description = findViewById(R.id.cal_list_desc);
        time = findViewById(R.id.cal_list_time);
        save = findViewById(R.id.save_button);
        date = findViewById(R.id.cal_list_date);
        name = findViewById(R.id.cal_list_name);

        //Test Getting that Passed Value
        final String id;

        //Sets incomingIntent Equal to Intent Passed by Calling Procedure
        Intent incomingIntent = getIntent();

        //Set String Equal to Passed Value
        id = incomingIntent.getStringExtra("id");

        DocumentReference docRef = db.collection("Events").document(id);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Event event = documentSnapshot.toObject(Event.class);
                name.setText(event.getTitle());
                building.setText(event.getBuilding());
                room.setText(event.getRoom());
                //FIXME formatting
                date.setText(event.getDate());
                //FIXME formatting
                time.setText(event.getTime().toString());
                description.setText(event.getDescription());

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v4) {
                //converts text inputs into strings
                newName = name.getText().toString().trim();
                newBuilding = building.getText().toString().trim();
                newRoom = room.getText().toString().trim();
                //FIXME
                newDate = date.getText().toString().trim();
                //FIXME
                String tempStr = time.getText().toString().trim();
                Long tempLong = new Long(0);
                try {
                    tempLong = Long.parseLong(tempStr);
                } catch (NumberFormatException nfe) {
                    System.out.println("NumberFormatException: " + nfe.getMessage());
                }
                newTime = tempLong;
                newDescript = description.getText().toString().trim();
                Event event = new Event(id, newName, newBuilding, newRoom, newTime, newDate,
                        newDescript);
                event.editEvent();

                Intent intent = new Intent(getApplicationContext(), DetailedDisplay.class);
                intent.putExtra("id", id);
                startActivity(intent);


            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        //make sure menu buttons are properly visible
        MenuItem homeMenu, addMenu;
        homeMenu = menu.findItem(R.id.home);
        addMenu = menu.findItem(R.id.ad_event);
        homeMenu.setVisible(true);
        addMenu.setVisible(true);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.home:
                Intent intentHome = new Intent(getApplicationContext(), HomePage.class);
                startActivity(intentHome);
                return true;
            case R.id.ad_event:
                Intent intentAdd = new Intent(getApplicationContext(), AddEvent.class);
                startActivity(intentAdd);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
