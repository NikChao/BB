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

import org.w3c.dom.Text;

import java.util.Random;

public class LoginPage extends AppCompatActivity {

    Button buttonGo;
    Button buttonConf;
    static EditText nameEdit;
    static EditText idEdit;
    static String name = null;
    static String ID = null;

    static Firebase ref = new Firebase("https://dazzling-fire-538.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        //Firebase.setAndroidContext(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final SharedPreferences sp = (SharedPreferences) LoginPage.this.getSharedPreferences(getString(R.string.PREF_FILE),
                MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.commit();

        if (checkSettings()) {
            goHome();
        }

        nameEdit = (EditText) findViewById(R.id.editText);
        idEdit = (EditText) findViewById(R.id.editText2);
        nameEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameEdit.setText("");
            }
        });
        idEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idEdit.setText("");
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //setValues(view);
                Snackbar.make(view, sp.getString("ID", null), Snackbar.LENGTH_INDEFINITE)
                        .setAction("Action", null).show();
                //Intent intent = new Intent("com.example.nik.bbfb.Groups");
                //startActivity(intent);

            }
        });
        goListener();
    }

    public void goListener() {
        buttonGo = (Button) findViewById(R.id.goButton);
        buttonGo.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String nameFromEdit = nameEdit.getText().toString();
                        String idFromEdit = idEdit.getText().toString();
                        //TextView tv = (TextView) findViewById(R.id.loginTextView);
                        //tv.setText(nameFromEdit);
                        name = nameFromEdit;
                        ID = idFromEdit;
                        if (checkVals(nameFromEdit, idFromEdit)) {
                            setValues();
                            if (checkSettings()) {
                                goHome();
                            }
                        } else {

                        }
                    }
                }
        );
    }

    public void goHome() {
        Intent intent = new Intent("com.example.nik.bbfb.Home");
        startActivity(intent);
    }

    public void setValues() {
        SharedPreferences myPrefs = (SharedPreferences) LoginPage.this.getSharedPreferences(getString(R.string.PREF_FILE),
                MODE_PRIVATE);
        SharedPreferences.Editor editor = myPrefs.edit();
        editor.putString(getString(R.string.User_ID),ID);
        editor.putString(getString(R.string.NAME), name);
        editor.apply();

    }

    public static boolean checkVals(String checkName, String checkID) {
        Firebase userG = ref.child("/User");
        if(checkID.length() == 0) {
            //going to leave the below as true for now because the ID is the unique identifier
            if (true) {
                Random rn = new Random();
                int randnum = rn.nextInt(10000);
                while(userG.child(Integer.toString(randnum)) == null) {
                    randnum = rn.nextInt(10000);
                }
                int i = randnum;
                ID = Integer.toString(i);
                //set new user
                Firebase newUserRef = ref.child("/User").child(Integer.toString(i));
                newUserRef.child("Name").setValue(checkName);
                newUserRef.child("Balance").setValue("0");
                newUserRef.child("Level").setValue("0");
                return true;
            } else {
                return false;
            }
        } else {
            Firebase existingUserRef =  ref.child("User");
            if(existingUserRef.child(ID) != null) {
                return true;
            } else {
                return false;
            }
        }
    }

    public  boolean checkSettings() {
        SharedPreferences myPrefs = LoginPage.this.getSharedPreferences(getString(R.string.PREF_FILE), MODE_PRIVATE);
        String pname = myPrefs.getString(getString(R.string.NAME), null);
        String pID = myPrefs.getString(getString(R.string.User_ID), null);
        String response;
        if(pname != null && pID != null) {
            //you have stored name and ID
            return true;
        } else {
            // You do not have stored name and ID
            // Most likely first time user
            return false;
        }
    }

}
