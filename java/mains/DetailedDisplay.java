package com.example.alfie_s_app;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class DetailedDisplay extends AppCompatActivity {

    Button edit;
    Button delete;

    TextView name, building, room, date, time, desc;

    final FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        edit = findViewById(R.id.edit_button);
        delete = findViewById(R.id.delete_button);

        name = findViewById(R.id.cal_list_name);
        building = findViewById(R.id.cal_list_building);
        room = findViewById(R.id.cal_list_room);
        date = findViewById(R.id.cal_list_date);
        time = findViewById(R.id.cal_list_time);
        desc = findViewById(R.id.cal_list_desc);

        //Test Getting that Passed Value
        final String id;
        String idPosition;

        //Sets incomingIntent Equal to Intent Passed by Calling Procedure
        Intent incomingIntent = getIntent();

        //Set String Equal to Passed Value
        idPosition = incomingIntent.getStringExtra("position");
        id = incomingIntent.getStringExtra(idPosition);

        //Set TextView to Passed Value

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
                desc.setText(event.getDescription());

            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {
                Intent intent = new Intent(getApplicationContext(), EditDetailed.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                Event event = new Event(getApplicationContext(), id);
                event.deleteEvent();
                Intent intent = new Intent(getApplicationContext(), HomePage.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu );

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
