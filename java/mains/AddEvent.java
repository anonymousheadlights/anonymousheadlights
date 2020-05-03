package com.example.alfie_s_app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;


public class AddEvent extends AppCompatActivity {

    EditText name, room, building, description, time, date;
    String newName, newBuilding, newRoom, newDate, newDescript;
    Long newTime;
    Button save;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_detailed);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent extrasIntent = getIntent();
        final String id = extrasIntent.getStringExtra("id");
        context = this;

        //converts text inputs into strings
        name = findViewById(R.id.cal_list_name);
        building =findViewById(R.id.cal_list_building);
        room = findViewById(R.id.cal_list_room);
        date = findViewById(R.id.cal_list_date);
        time = findViewById(R.id.cal_list_time);
        description = findViewById(R.id.cal_list_desc);
        save = findViewById(R.id.save_button);

        final Event newEvent = new Event();
        newEvent.setId(id);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newName = name.getText().toString().trim();
                newBuilding = building.getText().toString().trim();
                newRoom = room.getText().toString().trim();
                //FIXME
                newDate = date.getText().toString().trim();
                //FIXME
                String tempStr = time.getText().toString().trim();
                Long tempLong = 0L;
                try {
                    tempLong = Long.parseLong(tempStr);
                } catch (NumberFormatException nfe) {
                    System.out.println("NumberFormatException: " + nfe.getMessage());
                }
                newTime = tempLong;
                newDescript = description.getText().toString().trim();
                Event event = new Event(id, newName, newBuilding, newRoom, newTime, newDate,
                        newDescript);

                event.addEvent();
                Intent intent = new Intent(getApplicationContext(), HomePage.class);
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
        addMenu.setVisible(false);

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

