package com.example.afliesapp_basic_homescreen;

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
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView home_title;
    TextView app_title;
    TextView today;
    TextView tomorrow;
    TextView overmorrow;

    ListView todayList;
    ListView tomorrowList;
    ListView overmorrowList;

    Button goToCalen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.home_toolbar);
        setSupportActionBar(toolbar);

        home_title = findViewById(R.id.home_title);
        app_title = findViewById(R.id.app_title);
        today = findViewById(R.id.today);
        tomorrow  = findViewById(R.id.tomorrow);
        overmorrow =  findViewById(R.id.overmorrow);

        todayList = findViewById(R.id.today_list);
        tomorrowList = findViewById(R.id.tomorrow_list);
        overmorrowList = findViewById(R.id.overmorrow_list);

        goToCalen  = findViewById(R.id.goToCalen);

        //LIZZIE - CHANGES FROM 4/30/2020 START HERE -----------------------------------------------

        //ACCEPTING ARRAYLIST FROM FUNCTION - TESTING ARRAYLIST
        //ArrayList that will hold events

        //Place function (DayEvent) here that would get the ArrayList for
        //the specific date.
            //todayEvents represents that ArrayList that would be
            //returned by the function (DayEvent).

        //Create ArrayList list
        //NOTICE - ArrayList are set as String. I am not sure if you would need to change that
        ArrayList<String> todayArray;

        ArrayList<String> todayEvents = new ArrayList<>();


        
        //Testing to ensure the method works, scrolling works, and long messages work
            //Changed row_item.xml so that the listitem's height is wrap_content
        todayEvents.add("Test List Item");
        todayEvents.add("Second List Item");
        todayEvents.add("Long List Item that wil test how long this message can be displayed");
        todayEvents.add("Testing Scroll1");
        todayEvents.add("Testing Scroll2");
        todayEvents.add("Testing Scroll3");
        todayEvents.add("Testing Scroll4");
        todayEvents.add("Testing Scroll5");

        //The EmptyView still works with this method
        //of setting the ArrayList

        todayArray = new ArrayList<>(todayEvents);
        //todayArray.addAll(todayEvents);

        //I kept the todayArray as it is the ArrayList used in the adapter.
        //In theory, you should be able to place the DayEvent in the constructor
        //since it returns an ArrayList.
            //If not, I left the commented addAll method
            //in case that does not work.
        
        //Pseudo Code Using Day Event
        //todayArray = newArrayList<>( DayEvent(Today's Date) )

        //Create adapter
        //NOTICE - Again, this is set as String. May need to change.
        ArrayAdapter<String> todayAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.row_item, R.id.listItem, todayArray);
        todayList.setAdapter(todayAdapter);
        todayList.setEmptyView(findViewById(R.id.todayEmpty));

        todayList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, DetailedDisplay.class);

                //Get item that is selected and pass it
                String todayPass = (String) todayList.getItemAtPosition(position);
                intent.putExtra("pass", todayPass);
                startActivity(intent);

            }
        });



        //Create ArrayList list
        //NOTICE - ArrayList are set as String.
        ArrayList<String> tomArray;

        ArrayList<String> tomEvents = new ArrayList<>();

        tomEvents.add("Test List Item");

        tomArray = new ArrayList<>(tomEvents);

        //Pseudo Code Using Day Event
        //tomArray = newArrayList<>( DayEvent(Tomorrow's Date) )

        //Create adapter
        //NOTICE - Again, this is set as String.
        ArrayAdapter<String> tomAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.row_item, R.id.listItem, tomArray);
        tomorrowList.setAdapter(tomAdapter);
        tomorrowList.setEmptyView(findViewById(R.id.tomEmpty));

        tomorrowList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, DetailedDisplay.class);

                //Get item that is selected and pass it
                String tomPass = (String) tomorrowList.getItemAtPosition(position);
                intent.putExtra("pass", tomPass);
                startActivity(intent);
            }
        });

        //Create ArrayList list
        //NOTICE - ArrayList are set as String.
        ArrayList<String> overArray;

        ArrayList<String> overEvents = new ArrayList<>();

        overEvents.add("Test List Item");

        overArray = new ArrayList<>(overEvents);

        //Pseudo Code Using Day Event
        //overArray = newArrayList<>( DayEvent(Overmorrow's Date) )

        //Create adapter
        //NOTICE - Again, this is set as String.
        ArrayAdapter<String> overAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.row_item, R.id.listItem, overArray);
        overmorrowList.setAdapter(overAdapter);
        overmorrowList.setEmptyView(findViewById(R.id.overEmpty));


        overmorrowList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, DetailedDisplay.class);

                //Get item that is selected and pass it
                String overPass = (String) overmorrowList.getItemAtPosition(position);
                intent.putExtra("pass", overPass);
                startActivity(intent);
            }
        });

        //LIZZIE - CHANGES FROM 4/30/2020 END HERE -------------------------------------------------


        goToCalen.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, SwitchTest.class);
                startActivity(intent);
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.home) {

            return true;
        }

        if (id == R.id.ad_event) {
            Intent intent = new Intent(getApplicationContext(), AddEvent.class);
            startActivity(intent);
            return true;
        }
        //do nothing if notification settings is selected
        if (id == R.id.no_settings) {
            Intent intent = new Intent(getApplicationContext(), NotificationSettings.class);
            return true;


        }

        return super.onOptionsItemSelected(item);
    }
}
