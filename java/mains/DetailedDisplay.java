package com.example.alf;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class DetailedDisplay extends AppCompatActivity {

    Button edit;
    Button delete;

    TextView name;
    TextView building;
    TextView room;
    TextView time;
    TextView desc;

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
        time = findViewById(R.id.cal_list_time);
        desc = findViewById(R.id.cal_list_desc);

        //Test Getting that Passed Value
        String passValue;

        //Sets HomeIntent Equal to Intent Passed by Calling Procedure
        Intent homeIntent = getIntent();

        //Set String Equal to Passed Value
        passValue = homeIntent.getStringExtra("pass");

        //Set TextView to Passed Value
        name.setText(passValue);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {
                Intent intent = new Intent(getApplicationContext(), EditDetailed.class);
                startActivity(intent);
                //how are we transferring/getting data??

            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {

                //imma delete stuff here.... somehow
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);



        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

//hi lizzie

        return super.onOptionsItemSelected(item);
    }
}
