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

        //emptyView = findViewById(android.R.id.empty);

        //Create ArrayList list
        ArrayList<String> todayArray = new ArrayList<>();

        todayArray.add("Test List Item");
        todayArray.add("Second List Item");
        
        //Create adapter
        ArrayAdapter<String> todayAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.row_item, R.id.listItem, todayArray);
        todayList.setAdapter(todayAdapter);
        todayList.setEmptyView(findViewById(R.id.todayEmpty));

        
        todayList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, DetailedDisplay.class);
                String pass1 = "Passed from Today";
                intent.putExtra("pass", pass1);
                startActivity(intent);

            }
        });

        
        //Create ArrayList list
        ArrayList<String> tomArray = new ArrayList<>();

        tomArray.add("Test List Item");

        //Create adapter
        ArrayAdapter<String> tomAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.row_item, R.id.listItem, tomArray);
        tomorrowList.setAdapter(tomAdapter);
        tomorrowList.setEmptyView(findViewById(R.id.tomEmpty));

        tomorrowList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, DetailedDisplay.class);
                String pass2 = "Passed from Tomorrow";
                intent.putExtra("pass", pass2);
                startActivity(intent);
            }
        });

        //Create ArrayList list
        ArrayList<String> overArray = new ArrayList<>();

        overArray.add("Test List Item");

        //Create adapter
        ArrayAdapter<String> overAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.row_item, R.id.listItem, overArray);
        overmorrowList.setAdapter(overAdapter);
        overmorrowList.setEmptyView(findViewById(R.id.overEmpty));


        overmorrowList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, DetailedDisplay.class);
                String pass3 = "Passed from Overmorrow";
                intent.putExtra("pass", pass3);
                startActivity(intent);
            }
        });


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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
