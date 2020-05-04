package com.example.alfie_s_app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


public class AddEvent extends AppCompatActivity {

    EditText name, room, description;
    String newName, newBuilding, newRoom, newDate, newTime, newDescript;
    String newMonth, newDay, newYear, newHour, newMinute, newAmPm;
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
        room = findViewById(R.id.cal_list_room);
        description = findViewById(R.id.cal_list_desc);
        save = findViewById(R.id.save_button);

        final Spinner buildingSpinner, monthSpinner, daySpinner, yearSpinner, hourSpinner,
                minuteSpinner, ampmSpinner;

        buildingSpinner = findViewById(R.id.building_spinner);
        monthSpinner = findViewById(R.id.month_spinner);
        daySpinner = findViewById(R.id.day_spinner);
        yearSpinner = findViewById(R.id.year_spinner);
        hourSpinner = findViewById(R.id.hour_spinner);
        minuteSpinner = findViewById(R.id.minute_spinner);
        ampmSpinner = findViewById(R.id.am_pm_spinner);

        ArrayAdapter<CharSequence> adapter;

        //set up all adapters for drop downs
        adapter = ArrayAdapter.createFromResource(this,
                R.array.building_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        buildingSpinner.setAdapter(adapter);

        adapter = ArrayAdapter.createFromResource(this,
                R.array.month_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthSpinner.setAdapter(adapter);

        adapter = ArrayAdapter.createFromResource(this,
                R.array.day_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        daySpinner.setAdapter(adapter);

        adapter = ArrayAdapter.createFromResource(this,
                R.array.year_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearSpinner.setAdapter(adapter);

        adapter = ArrayAdapter.createFromResource(this,
                R.array.hour_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hourSpinner.setAdapter(adapter);

        adapter = ArrayAdapter.createFromResource(this,
                R.array.minute_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        minuteSpinner.setAdapter(adapter);

        adapter = ArrayAdapter.createFromResource(this,
                R.array.am_pm_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ampmSpinner.setAdapter(adapter);

        buildingSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                newBuilding = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                newBuilding = "Other";
            }
        });

        monthSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                newMonth = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                newMonth = "01";
            }
        });

        daySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                newDay = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                newDay = "01";
            }
        });

        yearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                newYear = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                newYear = "2020";
            }
        });

        hourSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                newHour = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                newHour = "12";
            }
        });

        minuteSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                newMinute = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                newMinute = "00";
            }
        });

        ampmSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                newAmPm = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                newAmPm = "AM";
            }
        });

        final Event newEvent = new Event();
        newEvent.setId(id);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newName = name.getText().toString().trim();
                newRoom = room.getText().toString().trim();
                newDate = newYear + newMonth + newDay;
                newTime = newHour + newMinute + newAmPm;
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

