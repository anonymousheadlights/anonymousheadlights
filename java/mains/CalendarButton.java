package com.example.alfie_s_app;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

public class CalendarButton extends AppCompatActivity {

    final FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_calendar);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CalendarView calendar = findViewById(R.id.calendar);
        final ListView calList = findViewById(R.id.calendar_list);

        final Intent intent = new Intent(getApplicationContext(), DetailedDisplay.class);
        final ArrayList<String> dayEvents = new ArrayList<>();

        //get current day for initial database call
        Integer cDay, cMonth, cYear;
        String today;
        final Calendar cal = Calendar.getInstance();
        cDay = cal.get(Calendar.DAY_OF_MONTH);
        cMonth = cal.get(Calendar.MONTH) + 1;
        cYear = cal.get(Calendar.YEAR);

        if (cMonth < 10){
            if (cDay < 10) {
                today = cYear.toString() + "0" + cMonth.toString() + "0" + cDay.toString();
            } else {
                today = cYear.toString() + "0" + cMonth.toString() + cDay.toString();
            }
        } else {
            today = cYear.toString() + cMonth.toString() + cDay.toString();
        }
        //initial call for current date
        db.collection("Events")
                .whereEqualTo("date", today)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        dayEvents.clear();
                        if (task.isSuccessful()) {
                            int i = 0;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String intentName = 0 + String.valueOf(i);
                                intent.putExtra(intentName, document.getId());
                                i++;
                                dayEvents.add((String) document.get("title"));
                                ArrayAdapter<String> todayAdapter = new ArrayAdapter<>(
                                        getApplicationContext(), R.layout.row_item_inverse,
                                        R.id.listItem, dayEvents);
                                calList.setAdapter(todayAdapter);
                                calList.setEmptyView(findViewById(R.id.cal_empty));
                            }
                        } else {
                            calList.setEmptyView(findViewById(R.id.cal_empty));
                        }
                    }

                });

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view,
                                            int year, int month, int dayOfMonth) {

                Integer y = year, m = (month + 1), d = dayOfMonth;
                String date;
                if (m < 10){
                    if (d < 10) {
                        date = y.toString() + "0" + m.toString() + "0" + d.toString();
                    } else {
                        date = y.toString() + "0" + m.toString() + d.toString();
                    }
                } else {
                    date = y.toString() + m.toString() + d.toString();
                }

                db.collection("Events")
                        .whereEqualTo("date", date)
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                dayEvents.clear();
                                ArrayAdapter<String> todayAdapter;
                                calList.setAdapter(null);
                                calList.setEmptyView(findViewById(R.id.cal_empty));
                                if (task.isSuccessful()) {
                                    int i = 0;
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        String intentName = 0 + String.valueOf(i);
                                        intent.putExtra(intentName, document.getId());
                                        i++;
                                        dayEvents.add((String) document.get("title"));
                                        todayAdapter = new ArrayAdapter<>(
                                                getApplicationContext(), R.layout.row_item_inverse,
                                                R.id.listItem, dayEvents);
                                        calList.setAdapter(todayAdapter);
                                        calList.setEmptyView(findViewById(R.id.cal_empty));
                                    }
                                } else {
                                    calList.setEmptyView(findViewById(R.id.cal_empty));
                                }
                            }

                        });

            }
        });

        //show toast on item click
        calList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Get item that is selected and pass it
                String pass = (String) calList.getItemAtPosition(position);
                String temp = 0 + String.valueOf(position);
                intent.putExtra("position", temp);
                intent.putExtra("title", pass);
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
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
