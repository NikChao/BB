package com.example.nik.bbfb;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class Home extends AppCompatActivity {

    TextView fireData;
    Firebase memRef;
    private static String url = "https://dazzling-fire-538.firebaseio.com/";
    private static String User_Name;
    private static String ID;
    private static String balance = "0";
    public static Button groupBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setNameAndID();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Settings", Snackbar.LENGTH_INDEFINITE)
                        .setAction("Action", null).show();
            }
        });

        fireData = (TextView) findViewById(R.id.firedata);
        String finalURL = url.concat(queryUser("Name"));

        memRef = new Firebase(finalURL);
        memRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String SuperData = (String) dataSnapshot.getValue();
                String welcomeMsg;
                if(SuperData == null) {
                    welcomeMsg = "That ID doesn't exist";
                    waitForABit();
                    goToLogin();
                } else {
                    if(!User_Name.equals(SuperData)) {
                        welcomeMsg = "you picked the wrong username!";
                        waitForABit();
                        goToLogin();
                    } else {
                        User.setID(ID);
                        User.setuName(User_Name);
                        welcomeMsg = "Welcome " + SuperData + "...bitch"
                        + "\n" + "Your Bitch Balance = " + balance ;
                    }
                }
                fireData.setText(welcomeMsg);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        groupListener();
    }

    public static void setBalance() {
        Firebase balanceData = new Firebase(url+"User/"+ID);
        User.setBalance(balanceData.child("Balance").toString());
        balance = User.getBalance();
    }

    public static String queryUser(String query) {

        String overallQuery = "User/" + ID + "/" + query;
        return overallQuery;
    }

    public void waitForABit() {
        /*try {
            wait(100);
        } catch(InterruptedException e) {
            System.exit(1);
        }*/
    }
    /**
     * checks if settings are default
     * @return true if default, else false
     */
    public static boolean checkSettings(Context context) {
        SharedPreferences myPrefs = context.getSharedPreferences("myPrefs", MODE_PRIVATE);
        String name = myPrefs.getString("USER_ID", null);
        String ID = myPrefs.getString("NAME", null);

        if(name != null && ID != null) {
            return false;
        } else {
            return true;
        }
    }

    public void setNameAndID() {
        SharedPreferences sp = (SharedPreferences) Home.this.getSharedPreferences(getString(R.string.PREF_FILE),
                MODE_PRIVATE);
        ID = (String) sp.getString(getString(R.string.User_ID), null);
        User_Name = (String) sp.getString(getString(R.string.NAME), null);

    }

    public void goToLogin() {
        Intent intent = new Intent("com.example.nik.bbfb.LoginPage");
        startActivity(intent);
    }

    public void groupListener() {
        groupBtn = (Button) findViewById(R.id.groupButton);
        groupBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.example.nik.bbfb.Groups");
                        startActivity(intent);
                    }
                }
        );
    }

}
