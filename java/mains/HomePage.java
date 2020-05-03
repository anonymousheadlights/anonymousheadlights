package com.example.alfie_s_app;

import android.Manifest;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Calendar;

public class HomePage extends AppCompatActivity {
    private static final String TAG = HomePage.class.getSimpleName();

    String today, tomorrow, overmorrow;

    ListView todayList;
    ListView tomorrowList;
    ListView overmorrowList;

    Button goToCalen;

    final FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        Toolbar toolbar = findViewById(R.id.home_toolbar);
        setSupportActionBar(toolbar);

        if(!(isMyServiceRunning(GeofenceService.class))) {
            startService(new Intent(this, GeofenceService.class));
            askPermission();
        }

        todayList = findViewById(R.id.today_list);
        tomorrowList = findViewById(R.id.tomorrow_list);
        overmorrowList = findViewById(R.id.overmorrow_list);

        goToCalen  = findViewById(R.id.goToCalen);

        final Intent intent = new Intent(getApplicationContext(), DetailedDisplay.class);

        //Create ArrayList lists
        final ArrayList<String> todayEvents = new ArrayList<>();
        final ArrayList<String> tomEvents = new ArrayList<>();
        final ArrayList<String> overEvents = new ArrayList<>();

        Integer cDay, cMonth, cYear;
        Calendar cal = Calendar.getInstance();
        cDay = cal.get(Calendar.DAY_OF_MONTH);
        cMonth = cal.get(Calendar.MONTH) + 1;
        cYear = cal.get(Calendar.YEAR);

        today = getDateFormat(cDay, cMonth, cYear);
        tomorrow = getNextDay(cDay, cMonth, cYear, 1);
        overmorrow = getNextDay(cDay, cMonth, cYear, 2);

        //The EmptyView still works with this method
        //of setting the ArrayList
        db.collection("Events")
            .whereEqualTo("date", today)
            .get()
            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            int i = 0;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String intentName = 1 + String.valueOf(i);
                                intent.putExtra(intentName, document.getId());
                                i++;
                                todayEvents.add((String) document.get("title"));
                                ArrayAdapter<String> todayAdapter =
                                        new ArrayAdapter<>(getApplicationContext(),
                                                R.layout.row_item, R.id.listItem, todayEvents);
                                todayList.setAdapter(todayAdapter);
                                todayList.setEmptyView(findViewById(R.id.todayEmpty));
                            }
                        } else {
                            todayEvents.add("file failure");
                        }
                    }

                  });
        todayList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Get item that is selected and pass it
                String pass = (String) todayList.getItemAtPosition(position);
                String temp = 1 + String.valueOf(position);
                intent.putExtra("position", temp);
                intent.putExtra("title", pass);
                startActivity(intent);
            }
        });

        db.collection("Events")
                .whereEqualTo("date", tomorrow)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            int i = 0;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String intentName = 2 + String.valueOf(i);
                                intent.putExtra(intentName, document.getId());
                                i++;
                                tomEvents.add((String) document.get("title"));
                                ArrayAdapter<String> tomAdapter =
                                        new ArrayAdapter<>(getApplicationContext(),
                                                R.layout.row_item, R.id.listItem, tomEvents);
                                tomorrowList.setAdapter(tomAdapter);
                                tomorrowList.setEmptyView(findViewById(R.id.tomEmpty));
                            }
                        } else {
                            tomEvents.add("file failure");
                        }
                    }

                });

        tomorrowList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Get item that is selected and pass it
                String tomPass = (String) tomorrowList.getItemAtPosition(position);
                String temp = 2 + String.valueOf(position);
                intent.putExtra("position", temp);
                intent.putExtra("title", tomPass);
                startActivity(intent);
            }
        });

        db.collection("Events")
                .whereEqualTo("date", overmorrow)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            int i = 0;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String intentName = 3 + String.valueOf(i);
                                intent.putExtra(intentName, document.getId());
                                i++;
                                overEvents.add((String) document.get("title"));
                                ArrayAdapter<String> overAdapter =
                                        new ArrayAdapter<>(getApplicationContext(),
                                                R.layout.row_item, R.id.listItem, overEvents);
                                overmorrowList.setAdapter(overAdapter);
                                overmorrowList.setEmptyView(findViewById(R.id.overEmpty));
                            }
                        } else {
                            overEvents.add("file failure");
                        }
                    }

                });
        overmorrowList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Get item that is selected and pass it
                String overPass = (String) overmorrowList.getItemAtPosition(position);
                String temp = 3 + String.valueOf(position);
                intent.putExtra("position", temp);
                intent.putExtra("title", overPass);
                startActivity(intent);
            }
        });

        goToCalen.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(), CalendarButton.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        //make sure menu buttons are properly (in)visible
        MenuItem homeMenu, addMenu;
        homeMenu = menu.findItem(R.id.home);
        addMenu = menu.findItem(R.id.ad_event);
        homeMenu.setVisible(false);
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

    private String getDateFormat(Integer day, Integer month, Integer year){
        String dateString;
        if (month < 10){
            if (day < 10) {
                dateString = year.toString() + "0" + month.toString() + "0" + day.toString();
            } else {
                dateString = year.toString() + "0" + month.toString() + day.toString();
            }
        } else {
            dateString = year.toString() + month.toString() + day.toString();
        }
        return dateString;
    }

    public String getNextDay(Integer day, Integer month, Integer year, int daysForward) {
        Integer nDay, nMonth, nYear;
        nDay = day;
        nMonth = month;
        nYear = year;
        for (int i = daysForward; i > 0; i--) {
            switch (month) {
                case 12:
                    if (++nDay > 31){
                        nDay = 1;
                        nMonth = 1;
                        nYear ++;
                    }
                case 2:
                    if ((year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0)){
                        if(++nDay > 29){
                            nMonth++;
                            nDay = 1;
                        }

                    } else {
                        if (++nDay > 28){
                            nMonth++;
                            nDay = 1;
                        }
                    }
                case 4:
                case 6:
                case 9:
                case 11:
                    if (++nDay > 30){
                        nDay = 1;
                        nMonth++;
                    }
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                    if (++nDay > 31){
                        nDay = 1;
                        nMonth++;
                    }

            }
        }
        return getDateFormat(nDay, nMonth, nYear);
    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    // Asks for permission
    void askPermission() {
        final int REQ_PERMISSION = 999;
        Log.d(TAG, "askPermission()");
        ActivityCompat.requestPermissions(
                this,
                new String[] { Manifest.permission.ACCESS_FINE_LOCATION },
                REQ_PERMISSION
        );
    }
}
