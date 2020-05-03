package com.example.alfiesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.os.Bundle;
import android.widget.Toast;

public class AdminLogin extends AppCompatActivity {

    static final String USERNAME = "administrator";
    static final String PASSWORD = "d33r1nh3@dl1gh+5";

    Button loginBut;
    TextView userTitle;
    EditText userInput;
    TextView pwrdTitle;
    EditText pwrdInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_login);

        //set up toolbar
        Toolbar toolbar = findViewById(R.id.admin_page_toolbar);
        setSupportActionBar(toolbar);

        //create a reference to TextViews, EditTexts, and Buttons
        loginBut = (Button) findViewById(R.id.login_but);
        userTitle = (TextView) findViewById(R.id.user_title);
        userInput = (EditText) findViewById(R.id.user_input);
        pwrdTitle = (TextView) findViewById(R.id.pwrd_title);
        pwrdInput = (EditText) findViewById(R.id.pwrd_input);

        loginBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkCred(userInput.getText().toString(), pwrdInput.getText().toString())){
                    //make edit/delete buttons and add event menu item visible
                    makeVisible();

                    //go to home page
                    //INSERT NAME OF HOME PAGE CLASS INSTEAD OF HomePage!!!!!
                    Intent intent = new Intent(getApplicationContext(), HomePage.class);
                    startActivity(intent);

                } else {
                    //return toast if login fails
                    Context context = getApplicationContext();
                    CharSequence text = "Login Failed";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
        });
    }
    //make the edit/delete buttons and the add event menu item visible
    protected void makeVisible(){
        edit_button.setVisibility(true);
        delete_button.setVisibility(true);
        ad_event.seVisibility(true);
    }
    //make the edit/delete buttons and the add event menu item invisible
    void makeInvisible() {
        edit_button.setVisibility(false);
        delete_button.setVisibility(false);
        ad_event.setVisibility(false);
    }
    //verify login info
    boolean checkCred(String user, String pass) {
        if ((user == USERNAME) && (pass == PASSWORD)) {
            return true;
        } else {
            return false;
        }
    }
}


