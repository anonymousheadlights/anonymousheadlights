package com.example.alf;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class EditDetailed extends AppCompatActivity {

    EditText room, building, description, time;
    Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_detailed);
        Toolbar toolbar = findViewById(R.id.toolbar);
   //     setSupportActionBar(toolbar);

        name = (EditText) findViewById(R.id.cal_list_name);
        room = (EditText) findViewById(R.id.cal_list_room);
        building = (EditText) findViewById(R.id.cal_list_building);
        description = (EditText) findViewById(R.id.cal_list_desc);
        time = (EditText) findViewById(R.id.cal_list_time);
        save = findViewById(R.id.save_button);

        save.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v4) {
            Intent intent = new Intent(getApplicationContext(),DetailedDisplay.class);
            startActivity(intent);
                                //how are we transferring/getting data??

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
