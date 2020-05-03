package com.example.alfiesapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.EditText;
import android.os.Bundle;
import android.widget.Toast;

public class NotificationSettings extends AppCompatActivity {

    TextView allNotifStr, kennedyStr, ellisStr, vlbStr, bacStr, foundersStr, quadStr, goodStr, theatreStr, libraryStr, museumStr, gibbelStr, knoxStr;
    
    Switch allNotif, kennedyNotif, ellisNotif, vlbNotif, bacNotif, foundersNotif, quadNotif, goodNotif, theatreNotif, libraryNotif, museumNotif, gibbelNotif, knoxNotif;

    //create preferences file and file editor
    SharedPreferences preferences = getSharedPreferences("NotificationPreferences",MODE_PRIVATE);
    SharedPreferences.Editor editor = preferences.edit();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_settings);

        //set up toolbar
        Toolbar toolbar = findViewById(R.id.admin_page_toolbar);
        setSupportActionBar(toolbar);

        //enter location notification settings in preferences file (default On)
        editor.putString("AllNotif", "On");
        editor.putString("kennedy", "On");
        editor.putString("ellis", "On");
        editor.putString("vlb", "On");
        editor.putString("bac", "On");
        editor.putString("founders", "On");
        editor.putString("quad", "On");
        editor.putString("good", "On");
        editor.putString("theatre", "On");
        editor.putString("library", "On");
        editor.putString("museum", "On");
        editor.putString("gibbel", "On");
        editor.putString("knox", "On");

        editor.apply();

        allNotifStr = (TextView) findViewById(R.id.allNotifStr);
        allNotif = (Switch) findViewById(R.id.allNotif);

        kennedyStr = (TextView) findViewById(R.id.kennedyStr);
        kennedyNotif = (Switch) findViewById(R.id.kennedyNotif);

        ellisStr = (TextView) findViewById(R.id.ellisStr);
        ellisNotif = (Switch) findViewById(R.id.ellisNotif);

        vlbStr = (TextView) findViewById(R.id.vlbStr);
        vlbNotif = (Switch) findViewById(R.id.vlbNotif);

        bacStr = (TextView) findViewById(R.id.bacStr);
        bacNotif = (Switch) findViewById(R.id.bacNotif);

        foundersStr = (TextView) findViewById(R.id.foundersStr);
        foundersNotif = (Switch) findViewById(R.id.foundersNotif);

        quadStr = (TextView) findViewById(R.id.quadStr);
        quadNotif = (Switch) findViewById(R.id.quadNotif);

        goodStr = (TextView) findViewById(R.id.goodStr);
        goodNotif = (Switch) findViewById(R.id.goodNotif);

        theatreStr = (TextView) findViewById(R.id.theatreStr);
        theatreNotif = (Switch) findViewById(R.id.theatreNotif);

        libraryStr = (TextView) findViewById(R.id.libraryStr);
        libraryNotif = (Switch) findViewById(R.id.libraryNotif);

        museumStr = (TextView) findViewById(R.id.museumStr);
        museumNotif = (Switch) findViewById(R.id.museumNotif);

        gibbelStr = (TextView) findViewById(R.id.gibbelStr);
        gibbelNotif = (Switch) findViewById(R.id.gibbelNotif);

        knoxStr = (TextView) findViewById(R.id.knoxStr);
        knoxNotif = (Switch) findViewById(R.id.knoxNotif);

        allNotif.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean onOff) {
                if(onOff)
                    setAllNotif(true);
                else
                    setAllNotif(false);
            }
        });

        kennedyNotif.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean onOff) {
                if(onOff)
                    setLocationNotif("kennedy",true);
                else
                    setLocationNotif("kennedy",false);
            }
        });

        ellisNotif.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean onOff) {
                if(onOff)
                    setLocationNotif("ellis",true);
                else
                    setLocationNotif("ellis",false);
            }
        });

        vlbNotif.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean onOff) {
                if(onOff)
                    setLocationNotif("vlb",true);
                else
                    setLocationNotif("vlb",false);
            }
        });

        bacNotif.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean onOff) {
                if(onOff)
                    setLocationNotif("bac",true);
                else
                    setLocationNotif("bac",false);
            }
        });

        foundersNotif.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean onOff) {
                if(onOff)
                    setLocationNotif("founders",true);
                else
                    setLocationNotif("founders",false);
            }
        });

        quadNotif.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean onOff) {
                if(onOff)
                    setLocationNotif("quad",true);
                else
                    setLocationNotif("quad",false);
            }
        });

        goodNotif.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean onOff) {
                if(onOff)
                    setLocationNotif("good",true);
                else
                    setLocationNotif("good",false);
            }
        });

        theatreNotif.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean onOff) {
                if(onOff)
                    setLocationNotif("theatre",true);
                else
                    setLocationNotif("theatre",false);
            }
        });

        libraryNotif.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean onOff) {
                if(onOff)
                    setLocationNotif("library",true);
                else
                    setLocationNotif("library",false);
            }
        });

        museumNotif.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean onOff) {
                if(onOff)
                    setLocationNotif("museum",true);
                else
                    setLocationNotif("museum",false);
            }
        });

        gibbelNotif.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean onOff) {
                if(onOff)
                    setLocationNotif("gibbel",true);
                else
                    setLocationNotif("gibbel",false);
            }
        });

        knoxNotif.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean onOff) {
                if(onOff)
                    setLocationNotif("knox",true);
                else
                    setLocationNotif("knox",false);
            }
        });
    }

    void setLocationNotif(String location, boolean notifOn) {
        //remove location in preference file and add new specified preference
        editor.remove(location);
        if(notifOn)
            editor.putString(location, "On");
        else
            editor.putString(location, "Off");

        editor.apply();
    }

    void setAllNotif(boolean notifOn){
        //remove notification setting for all locations from the preference file and add a new setting
        editor.remove("AllNotif");
        if(notifOn)
            editor.putString("AllNotif", "On");
        else
            editor.putString("AllNotif", "Off");

        editor.apply();
    }

//    void setWaitTime(int waitTime) {
//        locationWaitTime = waitTime
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.ad_login) {
            Intent intent = new Intent(getApplicationContext(), AdminLogin.class);
            startActivity(intent);
            return true;
        }

        if (id == R.id.ad_event) {
            Intent intent = new Intent(getApplicationContext(), AddEvent.class);
            startActivity(intent);
            return true;
        }
        //do nothing if notification settings is selected
        if (id == R.id.no_settings)
            return true;

        return super.onOptionsItemSelected(item);
    }
}
